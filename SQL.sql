
CREATE DATABASE CayCanh;
GO
USE CayCanh;
GO

-- =============================================
-- 2. TẠO CÁC BẢNG (TABLES)
-- =============================================

-- Bảng Người dùng (Users)
CREATE TABLE Users (
    UserId INT IDENTITY(1,1) PRIMARY KEY,
    FullName NVARCHAR(100) NOT NULL,
    Email VARCHAR(150) NOT NULL UNIQUE,
    Password VARBINARY(64) NOT NULL, -- Mật khẩu mã hóa SHA2_512
    Phone VARCHAR(20),
    Address NVARCHAR(255),
    Role VARCHAR(20) DEFAULT 'Customer' CHECK (Role IN ('Admin', 'Customer')),
    CreatedAt DATETIME DEFAULT GETDATE()
);
GO

-- Bảng Danh mục (Categories)
CREATE TABLE Categories (
    CategoryId INT IDENTITY(1,1) PRIMARY KEY,
    CategoryName NVARCHAR(100) NOT NULL,
    Description NVARCHAR(500),
    Icon VARCHAR(100) DEFAULT 'plant-icon.png'
);
GO

-- Bảng Sản phẩm (Products - Cây cảnh)
CREATE TABLE Products (
    ProductId INT IDENTITY(1,1) PRIMARY KEY,
    CategoryId INT NOT NULL REFERENCES Categories(CategoryId),
    ProductName NVARCHAR(150) NOT NULL,
    Description NVARCHAR(MAX),      -- Mô tả chi tiết
    CareGuide NVARCHAR(MAX),        -- HƯỚNG DẪN CHĂM SÓC (Đặc thù cây cảnh: Tưới nước, ánh sáng)
    Price DECIMAL(18, 2) NOT NULL,
    Stock INT DEFAULT 0,            -- Số lượng tồn kho
    Image VARCHAR(255),             -- Ảnh đại diện
    IsActive BIT DEFAULT 1,         -- 1: Đang bán, 0: Ngừng bán
    CreatedAt DATETIME DEFAULT GETDATE()
);
GO

-- Bảng Giỏ hàng (Cart) - Mỗi user có 1 giỏ hàng
CREATE TABLE Cart (
    CartId INT IDENTITY(1,1) PRIMARY KEY,
    UserId INT NOT NULL REFERENCES Users(UserId) ON DELETE CASCADE,
    UpdatedAt DATETIME DEFAULT GETDATE()
);
GO

-- Bảng Chi tiết giỏ hàng (CartItems)
CREATE TABLE CartItems (
    CartItemId INT IDENTITY(1,1) PRIMARY KEY,
    CartId INT NOT NULL REFERENCES Cart(CartId) ON DELETE CASCADE,
    ProductId INT NOT NULL REFERENCES Products(ProductId),
    Quantity INT NOT NULL DEFAULT 1,
    AddedAt DATETIME DEFAULT GETDATE()
);
GO

-- Bảng Đơn hàng (Orders)
CREATE TABLE Orders (
    OrderId INT IDENTITY(1,1) PRIMARY KEY,
    UserId INT NOT NULL REFERENCES Users(UserId),
    OrderDate DATETIME DEFAULT GETDATE(),
    Status NVARCHAR(50) DEFAULT N'Chờ xử lý', -- Chờ xử lý, Đang giao, Hoàn thành, Đã hủy
    TotalAmount DECIMAL(18, 2) DEFAULT 0,
    ShippingAddress NVARCHAR(255) NOT NULL,
    Note NVARCHAR(500) -- Ghi chú (VD: Giao giờ hành chính)
);
GO

-- Bảng Chi tiết đơn hàng (OrderItems)
CREATE TABLE OrderItems (
    OrderItemId INT IDENTITY(1,1) PRIMARY KEY,
    OrderId INT NOT NULL REFERENCES Orders(OrderId) ON DELETE CASCADE,
    ProductId INT NOT NULL REFERENCES Products(ProductId),
    Quantity INT NOT NULL,
    Price DECIMAL(18, 2) NOT NULL -- Giá tại thời điểm mua
);
GO

-- Bảng Thanh toán (Payments)
CREATE TABLE Payments (
    PaymentId INT IDENTITY(1,1) PRIMARY KEY,
    OrderId INT NOT NULL REFERENCES Orders(OrderId) ON DELETE CASCADE,
    PaymentMethod NVARCHAR(50) DEFAULT N'Tiền mặt' CHECK (PaymentMethod IN (N'Tiền mặt', N'Chuyển khoản', N'Momo', N'Thẻ tín dụng')),
    Amount DECIMAL(18, 2) NOT NULL,
    PaymentDate DATETIME DEFAULT GETDATE(),
    IsSuccessful BIT DEFAULT 0 -- 0: Chưa thanh toán/Lỗi, 1: Thành công
);
GO

-- Bảng Yêu thích (Wishlist)
CREATE TABLE Wishlist (
    WishlistId INT IDENTITY(1,1) PRIMARY KEY,
    UserId INT NOT NULL REFERENCES Users(UserId) ON DELETE CASCADE,
    ProductId INT NOT NULL REFERENCES Products(ProductId) ON DELETE CASCADE,
    AddedAt DATETIME DEFAULT GETDATE(),
    UNIQUE (UserId, ProductId) -- Một user không thể thích 1 cây 2 lần
);
GO

-- =============================================
-- 3. CÁC STORED PROCEDURE (XỬ LÝ NGHIỆP VỤ)
-- =============================================

-- 3.1. Đăng ký tài khoản
GO
CREATE PROC sp_RegisterUser
    @FullName NVARCHAR(100),
    @Email VARCHAR(150),
    @Password VARCHAR(100),
    @Phone VARCHAR(20) = NULL,
    @Address NVARCHAR(255) = NULL
AS
BEGIN
    IF EXISTS (SELECT * FROM Users WHERE Email = @Email)
    BEGIN
        SELECT -1 AS Status, N'Email đã tồn tại' AS Message;
        RETURN;
    END

    INSERT INTO Users (FullName, Email, Password, Phone, Address, Role)
    VALUES (@FullName, @Email, HASHBYTES('SHA2_512', @Password), @Phone, @Address, 'Customer');
    
    -- Tự động tạo giỏ hàng cho user mới
    DECLARE @NewUserId INT = SCOPE_IDENTITY();
    INSERT INTO Cart (UserId) VALUES (@NewUserId);

    SELECT 1 AS Status, N'Đăng ký thành công' AS Message;
END
GO

-- 3.2. Thêm vào giỏ hàng
GO
CREATE PROC sp_AddToCart
    @UserId INT,
    @ProductId INT,
    @Quantity INT
AS
BEGIN
    DECLARE @CartId INT;
    SELECT @CartId = CartId FROM Cart WHERE UserId = @UserId;

    -- Nếu chưa có giỏ thì tạo (phòng hờ)
    IF @CartId IS NULL
    BEGIN
        INSERT INTO Cart (UserId) VALUES (@UserId);
        SET @CartId = SCOPE_IDENTITY();
    END

    -- Kiểm tra xem sản phẩm đã có trong giỏ chưa
    IF EXISTS (SELECT * FROM CartItems WHERE CartId = @CartId AND ProductId = @ProductId)
    BEGIN
        UPDATE CartItems 
        SET Quantity = Quantity + @Quantity 
        WHERE CartId = @CartId AND ProductId = @ProductId;
    END
    ELSE
    BEGIN
        INSERT INTO CartItems (CartId, ProductId, Quantity)
        VALUES (@CartId, @ProductId, @Quantity);
    END
END
GO

-- 3.3. Đặt hàng (Checkout) - Chuyển từ Cart sang Order
GO
CREATE PROC sp_Checkout
    @UserId INT,
    @ShippingAddress NVARCHAR(255),
    @Note NVARCHAR(500),
    @PaymentMethod NVARCHAR(50)
AS
BEGIN
    SET NOCOUNT ON;
    DECLARE @CartId INT;
    DECLARE @OrderId INT;
    DECLARE @TotalAmount DECIMAL(18, 2);

    SELECT @CartId = CartId FROM Cart WHERE UserId = @UserId;

    -- Tính tổng tiền
    SELECT @TotalAmount = SUM(c.Quantity * p.Price)
    FROM CartItems c
    JOIN Products p ON c.ProductId = p.ProductId
    WHERE c.CartId = @CartId;

    IF @TotalAmount IS NULL
    BEGIN
        SELECT 0 AS Status, N'Giỏ hàng trống' AS Message;
        RETURN;
    END

    -- 1. Tạo Order Header
    INSERT INTO Orders (UserId, TotalAmount, ShippingAddress, Note, Status)
    VALUES (@UserId, @TotalAmount, @ShippingAddress, @Note, N'Chờ xử lý');
    SET @OrderId = SCOPE_IDENTITY();

    -- 2. Tạo Order Items (Copy từ CartItems sang)
    INSERT INTO OrderItems (OrderId, ProductId, Quantity, Price)
    SELECT @OrderId, c.ProductId, c.Quantity, p.Price
    FROM CartItems c
    JOIN Products p ON c.ProductId = p.ProductId
    WHERE c.CartId = @CartId;

    -- 3. Tạo thông tin thanh toán
    INSERT INTO Payments (OrderId, PaymentMethod, Amount, IsSuccessful)
    VALUES (@OrderId, @PaymentMethod, @TotalAmount, 0); -- Mặc định chưa thanh toán

    -- 4. Xóa CartItems
    DELETE FROM CartItems WHERE CartId = @CartId;

    SELECT 1 AS Status, N'Đặt hàng thành công' AS Message, @OrderId AS OrderId;
END
GO

-- =============================================
-- 4. DỮ LIỆU MẪU (SEED DATA) - CÂY CẢNH
-- =============================================

-- Insert Danh mục
INSERT INTO Categories (CategoryName, Description) VALUES 
(N'Cây Văn Phòng', N'Cây chịu bóng râm tốt, lọc không khí, thích hợp để bàn làm việc'),
(N'Cây Thủy Sinh', N'Cây trồng trong nước, dễ chăm sóc, sạch sẽ'),
(N'Sen Đá & Xương Rồng', N'Cây nhỏ, chịu hạn tốt, vẻ đẹp độc đáo'),
(N'Cây Phong Thủy', N'Mang lại tài lộc, may mắn cho gia chủ');
GO

-- Insert Sản phẩm (Có hướng dẫn chăm sóc)
INSERT INTO Products (CategoryId, ProductName, Price, Stock, Image, Description, CareGuide) VALUES
-- Cây văn phòng
(1, N'Cây Kim Tiền', 150000, 50, 'kimtien.jpg', 
 N'<p>Cây Kim Tiền lá xanh mướt, thân mập mạp, biểu tượng của sự giàu sang phú quý.</p>',
 N'Tưới nước 1 tuần/lần. Tránh ánh nắng gắt trực tiếp.'),

(1, N'Cây Lưỡi Hổ', 120000, 40, 'luoiho.jpg', 
 N'<p>Lọc không khí cực tốt vào ban đêm, thích hợp để phòng ngủ.</p>',
 N'Cây chịu hạn tốt, chỉ tưới khi đất khô hẳn. Đặt nơi có ánh sáng nhẹ.'),

-- Cây thủy sinh
(2, N'Cây Trầu Bà Thủy Sinh', 85000, 30, 'trauba.jpg', 
 N'<p>Cây rủ đẹp mắt, bộ rễ trắng muốt trong bình thủy tinh.</p>',
 N'Thay nước 1 tuần/lần. Nhỏ vài giọt dung dịch thủy canh để cây xanh tốt.'),

-- Sen đá
(3, N'Sen Đá Nâu', 45000, 100, 'sendanau.jpg', 
 N'<p>Màu nâu socola đặc trưng, cánh xếp như đài sen.</p>',
 N'Tưới dưới gốc, không tưới lên lá. Cần nhiều nắng để lên màu đẹp.'),

(3, N'Xương Rồng Tai Thỏ', 60000, 80, 'xuongrongtaitho.jpg', 
 N'<p>Hình dáng ngộ nghĩnh như tai thỏ, có gai nhỏ.</p>',
 N'Rất ưa nắng, tưới ít nước (2 tuần/lần).');
GO

-- Insert Users (Admin & Khách)
-- Pass: 123456 (đã hash mẫu)
EXEC sp_RegisterUser N'Admin Quản Lý', '1250080014@sv.hcmunre.edu.vn', '123456';
EXEC sp_RegisterUser N'Nguyễn Văn A', 'khachhang@gmail.com', '123456', '0909123456', N'TP.HCM';
GO

UPDATE Users
SET Role = 'Admin'
WHERE Email = '1250080014@sv.hcmunre.edu.vn';
GO

-- Insert Giỏ hàng mẫu cho User 2
EXEC sp_AddToCart 2, 1, 2; -- Mua 2 cây Kim Tiền
EXEC sp_AddToCart 2, 3, 1; -- Mua 1 cây Trầu Bà
GO

-- Insert Đơn hàng mẫu (User 2 đã mua trước đó)
INSERT INTO Orders (UserId, TotalAmount, ShippingAddress, Note, Status, OrderDate)
VALUES (2, 195000, N'123 Đường 3/2, Q.10, TP.HCM', N'Giao buổi sáng', N'Hoàn thành', '2023-11-01');

INSERT INTO OrderItems (OrderId, ProductId, Quantity, Price) VALUES
(1, 1, 1, 150000), -- 1 Kim Tiền
(1, 3, 1, 45000);  -- 1 Sen đá
GO

-- =============================================
-- 5. TRUY VẤN KIỂM TRA (TEST QUERIES)
-- =============================================

-- Xem danh sách sản phẩm kèm danh mục
SELECT p.ProductName, c.CategoryName, p.Price, p.CareGuide 
FROM Products p
JOIN Categories c ON p.CategoryId = c.CategoryId;

-- Xem giỏ hàng của khách hàng 'khachhang@gmail.com'
SELECT u.FullName, p.ProductName, ci.Quantity, (ci.Quantity * p.Price) AS SubTotal
FROM CartItems ci
JOIN Cart c ON ci.CartId = c.CartId
JOIN Users u ON c.UserId = u.UserId
JOIN Products p ON ci.ProductId = p.ProductId
WHERE u.Email = 'khachhang@gmail.com';

-- Test Checkout (Chạy dòng này để tạo đơn hàng từ giỏ hàng đang có)
-- EXEC sp_Checkout 2, N'456 Lê Lợi, Q.1', N'Gói quà giúp mình', N'Momo';

-- Xem bảng Payments
SELECT * FROM Payments;

SELECT Email, Role FROM Users WHERE Email IN ('1250080014@sv.hcmunre.edu.vn', 'khachhang@gmail.com');