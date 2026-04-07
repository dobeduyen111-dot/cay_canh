import React, { useState } from 'react';
import './GuidePage.css';

const GuidePage = () => {
  const [activeTab, setActiveTab] = useState('purchase');

  const tabs = [
    { id: 'purchase', label: 'Hướng dẫn mua hàng', icon: 'fas fa-shopping-bag' },
    { id: 'payment', label: 'Thanh toán & Vận chuyển', icon: 'fas fa-credit-card' },
    { id: 'warranty', label: 'Chính sách bảo hành', icon: 'fas fa-sync-alt' },
    { id: 'care', label: 'Mẹo chăm sóc cây', icon: 'fas fa-seedling' },
    { id: 'contact', label: 'Liên hệ hỗ trợ', icon: 'fas fa-envelope' },
  ];

  return (
    <div className="guide-page-wrapper">
      <div className="guide-main-header">
        <h1>Trung Tâm Trợ Giúp</h1>
        <p>Mọi thông tin bạn cần để mua sắm và chăm sóc cây dễ dàng hơn</p>
      </div>

      <div className="guide-main-container">
        {/* Sidebar */}
        <div className="guide-sidebar-box">
          {tabs.map((tab) => (
            <div
              key={tab.id}
              className={`guide-nav-item ${activeTab === tab.id ? 'active' : ''}`}
              onClick={() => setActiveTab(tab.id)}
            >
              <i className={tab.icon}></i> {tab.label}
            </div>
          ))}
        </div>

        {/* Content Area */}
        <div className="guide-content-area">
          {activeTab === 'purchase' && (
            <div className="guide-tab-content active">
              <h2>Hướng Dẫn Mua Hàng</h2>
              <p>Chào mừng bạn đến với chúng tôi! Việc đặt mua một chậu cây xanh mát chưa bao giờ dễ dàng đến thế.</p>
              <h3>Bước 1: Tìm kiếm sản phẩm</h3>
              <p>Bạn có thể duyệt qua danh mục <b>Cây trong nhà</b>, <b>Cây để bàn</b> hoặc sử dụng thanh tìm kiếm.</p>
              <h3>Bước 2: Thêm vào giỏ hàng</h3>
              <p>Tại trang chi tiết, chọn số lượng và bấm <b>"Thêm vào giỏ hàng"</b>.</p>
              <div className="guide-note-box">
                <i className="fas fa-info-circle"></i> <b>Lưu ý:</b> Shop hỗ trợ viết thiệp miễn phí nếu bạn làm quà tặng!
              </div>
            </div>
          )}

          {activeTab === 'payment' && (
            <div className="guide-tab-content active">
              <h2>Thanh Toán & Vận Chuyển</h2>
              <h3>1. Phương thức thanh toán</h3>
              <ul>
                <li><b>COD:</b> Thanh toán tiền mặt khi nhận hàng.</li>
                <li><b>Chuyển khoản:</b> VietQR, Momo (Nội dung: Tên + SĐT).</li>
              </ul>
              <h3>2. Phí vận chuyển</h3>
              <p>Nội thành TP.HCM: 30.000đ (Freeship đơn {'>'} 500k).</p>
            </div>
          )}

          {activeTab === 'warranty' && (
            <div className="guide-tab-content active">
              <h2>Chính Sách Đổi Trả</h2>
              <p>Cam kết đổi mới 1-1 nếu cây bị gãy héo do vận chuyển trong vòng 24h.</p>
            </div>
          )}

          {activeTab === 'care' && (
            <div className="guide-tab-content active">
              <h2>Mẹo Chăm Sóc Cây</h2>
              <p>Nhớ quy tắc <b>"3 Đủ"</b>: Đủ Nước - Đủ Sáng - Đủ Dinh Dưỡng.</p>
            </div>
          )}

          {activeTab === 'contact' && (
            <div className="guide-tab-content active">
              <h2>Liên Hệ Hỗ Trợ</h2>
              <p><i className="fas fa-phone-alt"></i> <b>Hotline:</b> 0909.123.456</p>
              <p><i className="fas fa-envelope"></i> <b>Email:</b> hotro@shopcayxanh.com</p>
            </div>
          )}
        </div>
      </div>
    </div>
  );
};

export default GuidePage;