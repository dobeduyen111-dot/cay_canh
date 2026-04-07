import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';

const AllProducts = () => {
    const navigate = useNavigate();

    // Dữ liệu mẫu thay thế cho ${list}
    const [products] = useState([
        { productId: 1, productName: 'Cây Bàng Singapore', price: 250000, stock: 15, image: 'bang-sing.jpg', description: 'Cây lọc không khí cực tốt, phù hợp để trong nhà.' },
        { productId: 101, productName: 'Chậu Đất Nung Basic', price: 45000, stock: 50, image: 'chau-dat-nung.jpg', description: 'Thoát nước tốt, phù hợp cho sen đá.' }
    ]);

    // Giả lập logic kiểm tra thêm vào giỏ (thay cho đoạn script fetch cũ)
    const handleAddToCart = (e, productId) => {
        e.preventDefault(); // Ngăn chặn thẻ Link chuyển trang
        
        // Giả lập: Nếu chưa đăng nhập thì chuyển hướng sang trang Login
        const isLoggedIn = true; // Đổi thành false để test chức năng chuyển hướng
        if (!isLoggedIn) {
            navigate('/login');
            return;
        }

        // TODO: Gọi API thêm vào giỏ hàng
        alert(`Đã thêm sản phẩm #${productId} vào giỏ hàng!`);
    };

    return (
        <section className="home-content">
            {/* Bạn có thể đưa phần CSS này ra file AllProducts.css */}
            <style>{`
                .home-content { font-family: 'Quicksand', sans-serif; background-color: #fdfdfd; color: #444; padding: 40px 20px; }
                .home-content h1 { text-align: center; color: #2d6a4f; font-weight: 700; margin-bottom: 40px; text-transform: uppercase; letter-spacing: 1px; }
                .product-container { display: grid; grid-template-columns: repeat(auto-fill, minmax(260px, 1fr)); gap: 30px; max-width: 1200px; margin: 0 auto; }
                .product-box { background: #fff; border-radius: 20px; padding: 15px; text-align: center; transition: all 0.3s ease; border: 1px solid #f0f0f0; display: flex; flex-direction: column; justify-content: space-between; }
                .product-box:hover { transform: translateY(-8px); box-shadow: 0 10px 20px rgba(45, 106, 79, 0.1); border-color: #d8f3dc; }
                .product-box img { width: 100%; height: 220px; object-fit: cover; border-radius: 15px; margin-bottom: 15px; }
                .product-box h3 { font-size: 18px; font-weight: 700; color: #1b4332; margin: 0 0 10px 0; white-space: nowrap; overflow: hidden; text-overflow: ellipsis; }
                .meta-info { display: flex; justify-content: center; align-items: center; gap: 10px; font-size: 14px; margin-bottom: 10px; color: #666; background: #f8fcf9; padding: 5px; border-radius: 8px; }
                .price-tag { color: #2d6a4f; font-weight: 700; font-size: 16px; }
                .desc-text { font-size: 13px; color: #777; margin-bottom: 20px; display: -webkit-box; -webkit-box-orient: vertical; overflow: hidden; height: 38px; line-height: 1.5; }
                .action-buttons { display: flex; gap: 10px; justify-content: center; margin-top: auto; }
                .btn-detail { padding: 10px 15px; border: 1px solid #2d6a4f; color: #2d6a4f; border-radius: 50px; text-decoration: none; font-size: 13px; font-weight: 600; transition: 0.3s; flex: 1; }
                .btn-detail:hover { background: #e8f5e9; }
                .btn-cart { padding: 10px 15px; background: #d4a373; color: white; border-radius: 50px; text-decoration: none; font-weight: 600; font-size: 13px; border: none; transition: 0.3s; display: flex; align-items: center; justify-content: center; gap: 5px; flex: 1.5; cursor: pointer; }
                .btn-cart:hover { background: #b08968; color: #fff; transform: translateY(-2px); box-shadow: 0 4px 8px rgba(212, 163, 115, 0.4); }
            `}</style>

            <h1>Tất cả sản phẩm</h1>
            <div className="product-container">
                {products.map(p => (
                    <div key={p.productId} className="product-box">
                        <img src={`/images/${p.image}`} alt={p.productName} />
                        <h3>{p.productName}</h3>
                        
                        <div className="meta-info">
                            <span className="price-tag">{p.price.toLocaleString('vi-VN')} VNĐ</span>
                            <span style={{ color: '#ddd' }}>|</span>
                            <span>Kho: <b>{p.stock}</b></span>
                        </div>
                        
                        <div className="desc-text" dangerouslySetInnerHTML={{ __html: p.description }}></div>
                        
                        <div className="action-buttons">
                            <Link to={`/product/${p.productId}`} className="btn-detail">
                                Xem chi tiết
                            </Link>
                            <button onClick={(e) => handleAddToCart(e, p.productId)} className="btn-cart">
                                <i className="fas fa-shopping-cart"></i> Mua ngay
                            </button>
                        </div>
                    </div>
                ))}
            </div>
        </section>
    );
};

export default AllProducts;