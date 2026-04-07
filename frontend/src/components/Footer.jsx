import React from 'react';
import '../assets/css/client/footer.css';

const Footer = () => {
  return (
    <footer className="main-footer">
      <div className="footer-container">
        <div className="footer-column">
          <h3>TREE SHOP</h3>
          <p>Chuyên cung cấp cây cảnh và giải pháp xanh cho văn phòng, nhà ở.</p>
        </div>
        <div className="footer-column">
          <h4>Về chúng tôi</h4>
          <ul>
            <li>Giới thiệu</li>
            <li>Chính sách bảo mật</li>
          </ul>
        </div>
        <div className="footer-column">
          <h4>Liên hệ</h4>
          <p><i className="fas fa-map-marker-alt"></i> Hà Nội, Việt Nam</p>
        </div>
      </div>
      <div className="copyright-bar">
        © 2026 Tree Shop - All Rights Reserved.
      </div>
      {/* Nút liên hệ trôi nổi */}
      <a href="#" className="floating-contact-btn">
        <i className="fab fa-messenger"></i>
      </a>
    </footer>
    
  );
};
export default Footer;