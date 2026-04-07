import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import '../assets/css/Products.css';

const Pots = () => {
    // Dữ liệu mẫu thay thế cho ${products}
    const [products] = useState([
        { productId: 101, productName: 'Chậu Đất Nung Basic', price: 45000, stock: 50, image: 'chau-dat-nung.jpg' },
        { productId: 102, productName: 'Chậu Gốm Sứ Trắng', price: 85000, stock: 0, image: 'chau-gom-trang.jpg' },
    ]);

    return (
        <section className="products-section">
            <div className="section-header">
                <h2 className="section-title">Chậu cây</h2>
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
                            <div className="product-category">Plant Pots</div>
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

export default Pots;