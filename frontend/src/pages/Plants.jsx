import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import '../assets/css/Products.css'; // File CSS chung cho danh sách sản phẩm

const Plants = () => {
    // Dữ liệu mẫu thay thế cho ${products}
    const [products] = useState([
        { productId: 1, productName: 'Cây Kim Tiền', price: 150000, stock: 15, image: 'kim-tien.jpg' },
        { productId: 2, productName: 'Cây Bàng Singapore', price: 250000, stock: 0, image: 'bang-sing.jpg' },
        { productId: 3, productName: 'Cây Lưỡi Hổ', price: 120000, stock: 8, image: 'luoi-ho.jpg' },
    ]);

    return (
        <section className="products-section">
            <div className="section-header">
                <h2 className="section-title">Cây Cảnh</h2>
            </div>
            
            <div className="product-grid">
                {products.map(p => (
                    <Link key={p.productId} to={`/product/${p.productId}`} className="product-card">
                        <div className="image-wrapper">
                            <img src={`/images/${p.image}`} alt={p.productName} className="product-image" />
                            <div className="card-action">
                                Xem chi tiết <i className="fas fa-arrow-right"></i>
                            </div>
                        </div>

                        <div className="product-info">
                            <div className="product-category">Plants</div>
                            <h3 className="product-name">{p.productName}</h3>
                            
                            <div className="product-meta">
                                <span className="product-price">{p.price.toLocaleString('vi-VN')}₫</span>
                                
                                {p.stock > 0 ? (
                                    <span className="badge badge-stock">Sẵn hàng</span>
                                ) : (
                                    <span className="badge badge-out">Hết hàng</span>
                                )}
                            </div>
                        </div>
                    </Link>
                ))}
            </div>
        </section>
    );
};

export default Plants;