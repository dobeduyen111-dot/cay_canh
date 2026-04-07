------------------------------------------------------------
-- TẠO DATABASE
------------------------------------------------------------
CREATE DATABASE CayCanh;
GO
USE CayCanh;
GO

------------------------------------------------------------
-- 1. BẢNG USERS — CHUẨN SPRING SECURITY BASIC + BCRYPT
------------------------------------------------------------
IF OBJECT_ID('Users') IS NOT NULL DROP TABLE Users;
GO

CREATE TABLE Users (
    UserId INT IDENTITY(1,1) PRIMARY KEY,
    FullName NVARCHAR(100) NOT NULL,
    Email VARCHAR(150) NOT NULL UNIQUE,
    Password VARCHAR(255) NOT NULL,   -- BCrypt
    Phone VARCHAR(20),
    Address NVARCHAR(255),
    Role VARCHAR(20) NOT NULL CHECK (Role IN ('ADMIN', 'USER')) DEFAULT 'USER',
    Enabled BIT DEFAULT 1,
    CreatedAt DATETIME DEFAULT GETDATE()
);
GO


------------------------------------------------------------
-- 2. BẢNG DANH MỤC
------------------------------------------------------------
CREATE TABLE Categories (
    CategoryId INT IDENTITY(1,1) PRIMARY KEY,
    CategoryName NVARCHAR(100) NOT NULL,
    Description NVARCHAR(500),
    Icon VARCHAR(100) DEFAULT 'plant-icon.png'
);
GO

------------------------------------------------------------
-- 3. BẢNG SẢN PHẨM
------------------------------------------------------------
CREATE TABLE Products (
    ProductId INT IDENTITY(1,1) PRIMARY KEY,
    CategoryId INT NOT NULL REFERENCES Categories(CategoryId),
    ProductName NVARCHAR(150) NOT NULL,
    Description NVARCHAR(MAX),
    CareGuide NVARCHAR(MAX),
    Price DECIMAL(18, 2) NOT NULL,
    Stock INT DEFAULT 0,
    Image VARCHAR(255),
    IsActive BIT DEFAULT 1,
    CreatedAt DATETIME DEFAULT GETDATE()
);
GO

------------------------------------------------------------
-- 4. GIỎ HÀNG USER
------------------------------------------------------------
CREATE TABLE Cart (
    CartId INT IDENTITY(1,1) PRIMARY KEY,
    UserId INT NOT NULL REFERENCES Users(UserId) ON DELETE CASCADE,
    UpdatedAt DATETIME DEFAULT GETDATE()
);
GO

------------------------------------------------------------
-- 5. CHI TIẾT GIỎ HÀNG
------------------------------------------------------------
CREATE TABLE CartItems (
    CartItemId INT IDENTITY(1,1) PRIMARY KEY,
    CartId INT NOT NULL REFERENCES Cart(CartId) ON DELETE CASCADE,
    ProductId INT NOT NULL REFERENCES Products(ProductId),
    Quantity INT NOT NULL DEFAULT 1,
    AddedAt DATETIME DEFAULT GETDATE()
);
GO

------------------------------------------------------------
-- 6. ĐƠN HÀNG
------------------------------------------------------------
CREATE TABLE Orders (
    OrderId INT IDENTITY(1,1) PRIMARY KEY,
    UserId INT NOT NULL REFERENCES Users(UserId),
    OrderDate DATETIME DEFAULT GETDATE(),
    Status NVARCHAR(50) DEFAULT N'Chờ xử lý',
    TotalAmount DECIMAL(18, 2) DEFAULT 0,
    ShippingAddress NVARCHAR(255) NOT NULL,
    Note NVARCHAR(500)
);
GO

Select *From Orders
------------------------------------------------------------
-- 7. CHI TIẾT ĐƠN HÀNG
------------------------------------------------------------
CREATE TABLE OrderItems (
    OrderItemId INT IDENTITY(1,1) PRIMARY KEY,
    OrderId INT NOT NULL REFERENCES Orders(OrderId) ON DELETE CASCADE,
    ProductId INT NOT NULL REFERENCES Products(ProductId),
    Quantity INT NOT NULL,
    Price DECIMAL(18, 2) NOT NULL
);
GO

------------------------------------------------------------
-- 8. THANH TOÁN
------------------------------------------------------------
CREATE TABLE Payments (
    PaymentId INT IDENTITY(1,1) PRIMARY KEY,
    OrderId INT NOT NULL REFERENCES Orders(OrderId) ON DELETE CASCADE,
    PaymentMethod NVARCHAR(50) DEFAULT N'Tiền mặt' CHECK (PaymentMethod IN (N'Tiền mặt', N'Chuyển khoản', N'Momo', N'Thẻ tín dụng')),
    Amount DECIMAL(18, 2) NOT NULL,
    PaymentDate DATETIME DEFAULT GETDATE(),
    IsSuccessful BIT DEFAULT 0
);
GO

------------------------------------------------------------
-- 9. WISHLIST
------------------------------------------------------------
CREATE TABLE Wishlist (
    WishlistId INT IDENTITY(1,1) PRIMARY KEY,
    UserId INT NOT NULL REFERENCES Users(UserId) ON DELETE CASCADE,
    ProductId INT NOT NULL REFERENCES Products(ProductId) ON DELETE CASCADE,
    AddedAt DATETIME DEFAULT GETDATE(),
    UNIQUE (UserId, ProductId)
);
GO
select *from Wishlist
------------------------------------------------------------
-- 10. STORED PROCEDURE ĐĂNG KÝ USER (KHÔNG HASH SQL)
------------------------------------------------------------
IF OBJECT_ID('sp_RegisterUser') IS NOT NULL DROP PROC sp_RegisterUser;
GO

CREATE PROC sp_RegisterUser
    @FullName NVARCHAR(100),
    @Email VARCHAR(150),
    @Password VARCHAR(255),    -- Nhận BCrypt từ Java
    @Phone VARCHAR(20) = NULL,
    @Address NVARCHAR(255) = NULL,
    @Role VARCHAR(20) = 'USER'
AS
BEGIN
    IF EXISTS (SELECT * FROM Users WHERE Email = @Email)
    BEGIN
        SELECT -1 AS Status, N'Email đã tồn tại' AS Message;
        RETURN;
    END

    INSERT INTO Users (FullName, Email, Password, Phone, Address, Role)
    VALUES (@FullName, @Email, @Password, @Phone, @Address, @Role);

    DECLARE @NewUserId INT = SCOPE_IDENTITY();
    INSERT INTO Cart (UserId) VALUES (@NewUserId);

    SELECT 1 AS Status, N'Đăng ký thành công' AS Message;
END
GO

------------------------------------------------------------
-- 11. DATA MẪU: DANH MỤC
------------------------------------------------------------
INSERT INTO Categories (CategoryName, Description) VALUES
(N'Cây Cảnh', N'Các loại cây xanh trang trí, để bàn, nội thất'),
(N'Chậu Trồng Cây', N'Chậu cảnh với nhiều kích thước và chất liệu'),
(N'Phụ Kiện Chăm Sóc', N'Dụng cụ và vật liệu chăm sóc cây cảnh');
GO

------------------------------------------------------------
-- 12. DATA MẪU: SẢN PHẨM
------------------------------------------------------------
-- CÂY CẢNH
INSERT INTO Products (CategoryId, ProductName, Price, Stock, Image, Description, CareGuide) VALUES
(1, N'Cây Kim Tiền', 150000, 50, 'kimtien.jpg', N'Cây tài lộc phong thủy thu hút may mắn.', N'Đặt nơi sáng vừa, tưới 1 tuần/lần'),
(1, N'Cây Lưỡi Hổ', 120000, 40, 'luoiho.jpg', N'Lọc không khí hiệu quả, dễ trồng.', N'Ít tưới nước, tránh úng'),
(1, N'Cây Trầu Bà', 90000, 35, 'trauba.jpg', N'Dễ chăm, sống tốt trong nhà.', N'Ánh sáng nhẹ, tưới khi đất khô'),
(1, N'Cây Bàng Singapore Mini', 250000, 20, 'bangsing.jpg', N'Dáng đẹp thích hợp trang trí nội thất.', N'Tránh ánh nắng trực tiếp'),
(1, N'Sen Đá Nâu', 45000, 100, 'sendanau.jpg', N'Nhỏ gọn, dễ thương, để bàn.', N'Cần nắng sáng, tưới 10 ngày/lần'),
(1, N'Xương Rồng Tai Thỏ', 60000, 80, 'taitho.jpg', N'Độc đáo, dễ chăm sóc.', N'Nắng mạnh, tưới rất ít'),
(1, N'Cây Lan Ý', 130000, 45, 'lany.jpg', N'Lọc độc tố trong không khí.', N'Tưới vừa phải, tránh nắng gắt'),
(1, N'Cây Trầu Bà Vàng', 95000, 50, 'traubavang.jpg', N'Màu lá đẹp, phát triển nhanh.', N'Ánh sáng gián tiếp'),
(1, N'Cây Phát Lộc', 70000, 60, 'phatloc.jpg', N'Phong thủy may mắn cho gia chủ.', N'Thay nước mỗi tuần'),
(1, N'Cây Xương Rồng Trứng Chim', 50000, 75, 'trungchim.jpg', N'Xinh xắn phù hợp để bàn.', N'Nắng nhiều, rất ít tưới'),
(1, N'Cây Cau Tiểu Trâm', 160000, 40, 'cautieutram.jpg', N'Cây để bàn hút khí độc.', N'Tưới đều đặn, không để ngập úng'),
(1, N'Cây Trúc Nhật', 110000, 35, 'trucnhat.jpg', N'Thanh lọc không khí tốt.', N'Ánh sáng nhẹ, tưới 2-3 lần/tuần'),
(1, N'Cây Hạnh Phúc Mini', 180000, 25, 'hanhphuc.jpg', N'Tượng trưng cho sự hạnh phúc viên mãn.', N'Ánh sáng nhẹ, tưới khi đất khô'),
(1, N'Cây Cọ Nhật', 200000, 20, 'conhat.jpg', N'Tạo điểm nhấn sang trọng trong phòng.', N'Tránh phơi nắng gắt'),
(1, N'Cây Vạn Lộc', 150000, 30, 'vanloc.jpg', N'Màu sắc rực rỡ mang lại may mắn.', N'Tưới 2 lần/tuần, đặt nơi thoáng mát'),

(2, N'Chậu Xi Măng Tròn', 80000, 40, 'ximang1.jpg', N'Bền chắc, phong cách tối giản.', N'Phù hợp cây để ngoài trời'),
(2, N'Chậu Xi Măng Vuông', 90000, 35, 'ximang2.jpg', N'Đơn giản và hiện đại.', N'Sử dụng được trong nhà hoặc ngoài trời'),
(2, N'Chậu Gốm Sứ Trắng', 70000, 50, 'gom1.jpg', N'Phù hợp cây trang trí nội thất.', N'Chú ý tránh va đập'),
(2, N'Chậu Gốm Họa Tiết', 95000, 30, 'gom2.jpg', N'Họa tiết thủ công đẹp mắt.', N'Lau sạch bụi thường xuyên'),
(2, N'Chậu Nhựa Đen', 20000, 100, 'nhua1.jpg', N'Nhẹ và bền.', N'Phù hợp ươm cây'),
(2, N'Chậu Treo Mini', 35000, 60, 'treo1.jpg', N'Trang trí ban công.', N'Không treo nơi gió mạnh'),
(2, N'Chậu Sứ Để Bàn', 55000, 70, 'su1.jpg', N'Màu sắc tinh tế.', N'Khuyến khích trồng cây nhỏ'),
(2, N'Chậu Gỗ Rustic', 150000, 20, 'go1.jpg', N'Phong cách mộc mạc vintage.', N'Tránh tiếp xúc nước kéo dài'),
(2, N'Chậu Xi Măng Vân Đá', 110000, 25, 'ximang3.jpg', N'Thẩm mỹ cao, bền bỉ.', N'Phù hợp cây cảnh cỡ trung'),
(2, N'Chậu Đá Nhẹ', 120000, 18, 'danhe.jpg', N'Nhẹ nhưng cứng cáp.', N'Dễ di chuyển'),
(2, N'Chậu Sứ Vân Mây', 85000, 40, 'vanmay.jpg', N'Tạo điểm nhấn sang trọng.', N'Dễ vệ sinh'),
(2, N'Chậu Nhựa Trắng Trơn', 25000, 90, 'nhuatrang.jpg', N'Sạch đẹp, bền.', N'Thích hợp trồng cây để bàn'),
(2, N'Chậu Gốm Men Xanh', 98000, 28, 'gomxanh.jpg', N'Màu xanh bắt mắt.', N'Tránh rơi vỡ'),
(2, N'Chậu Treo Sứ', 60000, 35, 'treosuxinh.jpg', N'Dành cho cây rủ.', N'Treo nơi thoáng mát'),
(2, N'Chậu Composite Tròn Cao', 180000, 15, 'composite.jpg', N'Chống thấm tốt, sang trọng.', N'Dùng cho cây cao'),

(3, N'Sỏi Trang Trí Trắng', 22000, 100, 'soi1.jpg', N'Tạo độ thẩm mỹ cao cho chậu cây.', N'Rải lớp trên mặt đất'),
(3, N'Sỏi Màu Mix', 28000, 80, 'soi2.jpg', N'Đa dạng màu sắc.', N'Rải trang trí hoặc thủy sinh'),
(3, N'Đất Tribat', 35000, 90, 'dat1.jpg', N'Dinh dưỡng phù hợp nhiều loại cây.', N'Thay đất định kỳ 6 tháng'),
(3, N'Phân Bón Tan Chậm', 30000, 70, 'phan1.jpg', N'Cung cấp dưỡng chất lâu dài.', N'Rải đều quanh gốc cây'),
(3, N'Bình Tưới Nhỏ', 45000, 45, 'binhtuoi.jpg', N'Tưới dễ dàng, tiện lợi.', N'Vệ sinh thường xuyên'),
(3, N'Kéo Cắt Cành', 65000, 30, 'keo.jpg', N'Giúp tỉa cành gọn gàng.', N'Lau khô sau khi dùng'),
(3, N'Dụng Cụ Xới Đất Mini', 60000, 40, 'xoidat.jpg', N'Hỗ trợ làm tơi đất.', N'Bảo quản nơi khô ráo'),
(3, N'Than Hoạt Tính Trồng Cây', 25000, 55, 'thanhoat.jpg', N'Khử mùi, chống nấm.', N'Trộn với đất'),
(3, N'Bột Dưỡng Rễ', 30000, 30, 'duongre.jpg', N'Giúp cây phát triển rễ nhanh.', N'Sử dụng khi giâm cành'),
(3, N'Giá Đỡ Chậu Cây', 120000, 25, 'giado.jpg', N'Nâng chậu tạo bố cục đẹp.', N'Dùng cho chậu trung – lớn'),
(3, N'Bình Xịt Phun Sương', 40000, 60, 'phunsuong.jpg', N'Giữ ẩm cho lá.', N'Dùng mỗi ngày cho cây trong nhà'),
(3, N'Dụng Cụ Trồng Cây Mini 3 Món', 85000, 45, 'toolset.jpg', N'Hỗ trợ trồng và chăm cây.', N'Lau sạch khi không dùng'),
(3, N'Lưới Lót Chậu', 15000, 90, 'luichau.jpg', N'Ngăn đất trôi ra ngoài.', N'Đặt dưới đáy chậu trước khi trồng'),
(3, N'Găng Tay Làm Vườn', 25000, 70, 'gangtay.jpg', N'Chống trầy xước khi chăm cây.', N'Giặt sau mỗi lần dùng'),
(3, N'Đèn Chiếu Cây Cảnh', 180000, 15, 'dencay.jpg', N'Hỗ trợ ánh sáng cho cây trong nhà.', N'Bật 6–8 giờ/ngày');
GO
------------------------------------------------------------
-- 13. DATA MẪU: USER (BCrypt password: 123456)
------------------------------------------------------------


-- Tạo giỏ hàng tự động
INSERT INTO Cart (UserId) VALUES (1), (2);
GO

------------------------------------------------------------
-- 14. TEST QUERY
------------------------------------------------------------

GO

UPDATE Products
SET Image = 'cay_luoi_ho.jqp'
WHERE ProductName = N'Cây Lưỡi Hổ';

-- Xóa toàn bộ dữ liệu trong bảng Products và reset ID về 1
TRUNCATE TABLE Products; 

-- Sau đó chạy lại script INSERT dữ liệu của bạn 1 lần duy nhất.
-- Xóa các sản phẩm bị trùng lặp (Ví dụ xóa ID 9, 10, 11...)
DELETE FROM Products 
WHERE ProductId IN (1, 2, 3 ,4, 5, 6, 7, 8, 9, 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35 36 37 38 39 40 41 42 43 44 45 46 47 48 49 50 51 52 53 54 55 56 57 58 59 60 61 61 63 64 65 66); 
-- Bạn tự điền thêm các ID bị thừa vào trong ngoặc

DELETE FROM Users
WHERE UserId IN (3,6)

SELECT * FROM Cart WHERE UserId = 1;

DELETE FROM Cart;
DELETE FROM CartItems;

INSERT INTO Cart(UserId)
SELECT UserId FROM Users;

SELECT * FROM Orders;
SELECT * FROM OrderItems;
SELECT * FROM Payments;
SELECT * FROM Users;
SELECT * FROM Cart;
SELECT * FROM CartItems
SELECT * FROM Categories;
SELECT * FROM Products;
SELECT * FROM Wishlist;

-- Reset mật khẩu của tất cả user về '123' (hoặc gì tùy bạn) để đăng nhập được
UPDATE Users 
SET Password = '123';

DELETE FROM Products;
DBCC CHECKIDENT ('Products', RESEED, 0);

DELETE FROM Categories;
DBCC CHECKIDENT ('Categories', RESEED, 0);

DELETE FROM CartItems;
DELETE FROM Orders;
DELETE FROM OrderItems;
DELETE FROM Payments;
DELETE FROM Wishlist;
DELETE FROM Cart;
DELETE FROM Products;
DELETE FROM Categories;
DBCC CHECKIDENT ('Products', RESEED, 0);
DBCC CHECKIDENT ('Categories', RESEED, 0);
SELECT ProductId, ProductName, CategoryId
FROM Products
ORDER BY ProductId DESC;
