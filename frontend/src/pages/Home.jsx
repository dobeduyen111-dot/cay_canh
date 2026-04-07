import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import '../assets/css/client/homeindex.css'; 

const Home = () => {
    const [cayCanh] = useState([
        { productId: 1, productName: 'Cây Kim Tiền', price: 150000, image: 'kim-tien.jpg' },
        { productId: 2, productName: 'Cây Bàng Singapore', price: 250000, image: 'bang-sing.jpg' }
    ]);
    
    const [chauCay] = useState([
        { productId: 3, productName: 'Chậu Đất Nung', price: 50000, image: 'chau-dat-nung.jpg' }
    ]);

    const [phuKien] = useState([
        { productId: 4, productName: 'Bình Tưới Nước', price: 80000, image: 'binh-tuoi.jpg' }
    ]);

    return (
        <section className="home-content">
            {/* HERO BANNER */}
            <div className="hero-banner">
                <div className="container hero-content-wrapper"> 
                    <div className="content-box">
                        <h1>Tận hưởng không gian sống xanh</h1>
                        <p>
                            Bổ sung thêm cây xanh là một cách đơn giản nhất để tạo ra sự thoải mái cho không gian sống của bạn, giúp mang lại hiệu quả công việc và thư giãn mỗi khi trở về.
                        </p>
                        <div className="button-group">
                            <a href="https://www.messenger.com/" className="btn btn-outline" target="_blank" rel="noreferrer">LIÊN HỆ</a>
                            <Link to="/product" className="btn btn-filled">MUA NGAY</Link>
                        </div>
                    </div>
                </div>
            </div>

            {/* VÌ SAO CHỌN CHÚNG TÔI */}
            <div className="why-choose-section">
                <div className="container">
                    <h2 className="section-title">Tại Sao Chọn Chúng Tôi?</h2>
                    <div className="choose-grid">
                        <div className="choose-item">
                            <img src="/images/icon-thu-cong.jpg" alt="Thủ Công" className="choose-icon" />
                            <h3 className="choose-title">Chăm Sóc Tận Tâm</h3>
                            <p className="choose-desc">Mỗi sản phẩm cây xanh đều được chăm sóc cẩn thận bởi đội ngũ chuyên môn</p>
                        </div>
                        <div className="choose-item">
                            <img src="/images/icon-da-that.png" alt="Chất Lượng" className="choose-icon" />
                            <h3 className="choose-title">Chất Lượng Hàng Đầu</h3>
                            <p className="choose-desc">Cây khỏe mạnh, đúng mô tả, đảm bảo nguồn gốc uy tín</p>
                        </div>
                        <div className="choose-item">
                            <img src="/images/icon-bao-hanh.jpg" alt="Bảo hành" className="choose-icon" />
                            <h3 className="choose-title">Đổi Trả Linh Hoạt</h3>
                            <p className="choose-desc">Đổi trả 3 ngày nếu sản phẩm gặp vấn đề từ nhà cung cấp</p>
                        </div>
                        <div className="choose-item">
                            <img src="/images/icon-giao-hang.jpg" alt="Giao hàng" className="choose-icon" />
                            <h3 className="choose-title">Giao Hàng Nhanh</h3>
                            <p className="choose-desc">Vận chuyển toàn quốc, giao nhanh chỉ trong 2–3 ngày</p>
                        </div>
                    </div>
                </div>
            </div>

            {/* DANH MỤC: CÂY CẢNH VĂN PHÒNG */}
            <div className="new-arrival">
                <div className="container">
                    <h2 className="section-title">CÂY CẢNH VĂN PHÒNG</h2>
                    <div className="product-grid">
                        {cayCanh.map(p => (
                            <Link key={p.productId} to={`/product/${p.productId}`} className="product-card">
                                <div className="product-image-area">
                                    <img src={`/images/${p.image}`} alt={p.productName} className="product-image" />
                                </div>
                                <div className="product-info">
                                    <p className="product-name">{p.productName}</p>
                                    <p className="product-price">{p.price.toLocaleString('vi-VN')}₫</p>
                                </div>
                            </Link>
                        ))}
                    </div>
                    <div style={{ textAlign: 'center' }}>
                        <Link to="/product/cay" className="view-more-button">Xem thêm</Link>
                    </div>
                </div>
            </div>

            {/* DANH MỤC: CHẬU CÂY */}
            <div className="new-arrival">
                <div className="container">
                    <h2 className="section-title">CHẬU CÂY</h2>
                    <div className="product-grid">
                        {chauCay.map(p => (
                            <Link key={p.productId} to={`/product/${p.productId}`} className="product-card">
                                <div className="product-image-area">
                                    <img src={`/images/${p.image}`} alt={p.productName} className="product-image" />
                                </div>
                                <div className="product-info">
                                    <p className="product-name">{p.productName}</p>
                                    <p className="product-price">{p.price.toLocaleString('vi-VN')}₫</p>
                                </div>
                            </Link>
                        ))}
                    </div>
                    <div style={{ textAlign: 'center' }}>
                        <Link to="/product/chau" className="view-more-button">Xem thêm</Link>
                    </div>
                </div>
            </div>

            {/* DANH MỤC: PHỤ KIỆN */}
            <div className="new-arrival">
                <div className="container">
                    <h2 className="section-title">PHỤ KIỆN TRANG TRÍ</h2>
                    <div className="product-grid">
                        {phuKien.map(p => (
                            <Link key={p.productId} to={`/product/${p.productId}`} className="product-card">
                                <div className="product-image-area">
                                    <img src={`/images/${p.image}`} alt={p.productName} className="product-image" />
                                </div>
                                <div className="product-info">
                                    <p className="product-name">{p.productName}</p>
                                    <p className="product-price">{p.price.toLocaleString('vi-VN')}₫</p>
                                </div>
                            </Link>
                        ))}
                    </div>
                    <div style={{ textAlign: 'center' }}>
                        <Link to="/product/phukien" className="view-more-button">Xem thêm</Link>
                    </div>
                </div>
            </div>

            {/* ĐÁNH GIÁ TỪ KHÁCH HÀNG */}
            <div className="testimonial-section">
                <div className="container">
                    <h2 className="section-title-serif">Khách Hàng Nói Về MOW Garden</h2>
                    <div className="testimonial-grid">
                        <div className="testimonial-card">
                            <div className="quote-box">
                                Mình mua của shop 2 lần rồi, phải nói là cây rất đẹp luôn ấy. Nhỏ nhỏ, dễ thương, xanh tốt. Đóng gói rất kĩ càng...
                            </div>
                            <div className="author-info">
                                <img src="https://i.pravatar.cc/150?img=11" alt="Avatar" className="author-avatar" />
                                <div className="author-details">
                                    <h4>Thành Lê</h4>
                                    <span>Mẫu ảnh nam, Freelancer</span>
                                </div>
                            </div>
                        </div>
                        <div className="testimonial-card">
                            <div className="quote-box">
                                Mua của shop cây nào về cũng đẹp á? Giá lại rẻ nữa, đóng gói quá chắc chắn, chuyên nghiệp hihi...
                            </div>
                            <div className="author-info">
                                <img src="https://i.pravatar.cc/150?img=5" alt="Avatar" className="author-avatar" />
                                <div className="author-details">
                                    <h4>Phương Nga</h4>
                                    <span>Nhân viên văn phòng</span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            
            {/* ... Phần Blog bạn giữ nguyên cấu trúc thẻ HTML tĩnh nhé ... */}
        </section>
    );
};

export default Home;