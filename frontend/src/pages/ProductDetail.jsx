import React, { useState, useEffect } from 'react';
import { useParams, Link } from 'react-router-dom';
import '../assets/css/ProductDetail.css'; // Tự tạo file CSS và dán style vào nhé

const ProductDetail = () => {
    // useParams() dùng để lấy ID sản phẩm trên URL (ví dụ: /product/1)
    const { id } = useParams();

    // Mock data sản phẩm
    const [product, setProduct] = useState({
        productId: id || 1,
        productName: 'Cây Kim Tiền Phong Thủy',
        price: 150000,
        stock: 12,
        image: 'kim-tien.jpg',
        description: '<p>Cây Kim Tiền mang lại tài lộc, may mắn cho gia chủ. Rất dễ chăm sóc, phù hợp để trong nhà hoặc văn phòng.</p>'
    });

    const handleAddToCart = (e) => {
        e.preventDefault();
        // TODO: Gọi API thêm vào giỏ hàng
        alert('Đã thêm sản phẩm vào giỏ hàng!');
    };

    const handleAddToWishlist = (e) => {
        e.preventDefault();
        // TODO: Gọi API thêm vào yêu thích
        alert('Đã lưu vào danh sách yêu thích!');
    };

    return (
        <section className="product-detail-page">
            <div className="container">
                <div className="detail-wrapper">
                    {/* Hình ảnh */}
                    <div className="detail-gallery">
                        <img src={`/images/${product.image}`} alt={product.productName} className="main-image" />
                    </div>

                    {/* Thông tin */}
                    <div className="detail-info">
                        <div className="breadcrumb">Trang chủ / Cây cảnh / <span>{product.productName}</span></div>
                        <h1 className="product-title">{product.productName}</h1>
                        
                        <div className="product-meta">
                            <span className="price-tag">
                                {product.price.toLocaleString('vi-VN')}₫
                            </span>
                            {product.stock > 0 ? (
                                <span className="stock-badge in-stock">
                                    <i className="fas fa-check-circle"></i> Còn hàng ({product.stock})
                                </span>
                            ) : (
                                <span className="stock-badge out-stock">
                                    <i className="fas fa-times-circle"></i> Hết hàng
                                </span>
                            )}
                        </div>

                        <div className="product-meta" style={{ marginTop: '-15px' }}>
                             <span className="rating-stars">
                                <i className="fas fa-star"></i><i className="fas fa-star"></i><i className="fas fa-star"></i><i className="fas fa-star"></i><i className="fas fa-star-half-alt"></i>
                            </span>
                            <span className="review-count">(Xem 18 đánh giá)</span>
                        </div>

                        {/* Description render HTML */}
                        <div className="description" dangerouslySetInnerHTML={{ __html: product.description }}></div>
                        
                        <div className="action-group">
                            {product.stock > 0 ? (
                                <>
                                    <button onClick={handleAddToCart} className="btn-add-cart" style={{ border: 'none', cursor: 'pointer' }}>
                                        <i className="fas fa-shopping-bag"></i> Thêm vào giỏ hàng
                                    </button>
                                    <button onClick={handleAddToWishlist} className="btn-wishlist">
                                        <i className="fa-regular fa-heart"></i>
                                    </button>
                                </>
                            ) : (
                                <button className="btn-add-cart" style={{ background: '#999', cursor: 'not-allowed', border: 'none' }}>
                                    <i className="fas fa-envelope"></i> Nhận tin khi có hàng
                                </button>
                            )}
                        </div>
                    </div>
                </div>

                {/* Phần Review (Đánh giá) */}
                <div className="section-box">
                    <h3 className="box-title">Khách hàng nói gì?</h3>
                    <div className="review-list">
                        <div className="review-card">
                            <div className="review-user">
                                <span className="user-name">Nguyễn Thùy Linh</span>
                                <span className="rating-stars" style={{ fontSize: '10px' }}>
                                    <i className="fas fa-star"></i><i className="fas fa-star"></i><i className="fas fa-star"></i><i className="fas fa-star"></i><i className="fas fa-star"></i>
                                </span>
                            </div>
                            <p className="review-text">"Cây xinh xỉu shop ơi! Đóng gói siêu kỹ, lá không bị dập tí nào. Sẽ ủng hộ dài dài."</p>
                            <span className="review-time">2 ngày trước</span>
                        </div>
                        <div className="review-card">
                            <div className="review-user">
                                <span className="user-name">Hoàng Minh</span>
                                 <span className="rating-stars" style={{ fontSize: '10px' }}>
                                    <i className="fas fa-star"></i><i className="fas fa-star"></i><i className="fas fa-star"></i><i className="fas fa-star"></i><i className="far fa-star"></i>
                                </span>
                            </div>
                            <p className="review-text">"Giao hàng hơi lâu chút nhưng bù lại cây rất tươi. Giá hợp lý."</p>
                            <span className="review-time">1 tuần trước</span>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    );
};

export default ProductDetail;