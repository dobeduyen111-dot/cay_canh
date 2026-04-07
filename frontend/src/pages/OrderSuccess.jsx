import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';

const OrderSuccess = () => {
    // Dữ liệu giả lập về đơn hàng vừa đặt
    const [order, setOrder] = useState({
        orderId: '889922',
        status: 'Chờ xử lý',
        totalAmount: 450000,
        shippingAddress: '123 Đường Cầu Giấy, Phường Quan Hoa, Quận Cầu Giấy, Hà Nội'
    });

    useEffect(() => {
        // Gọi API lấy thông tin đơn hàng vừa đặt (nếu cần)
    }, []);

    return (
        <section className="home-content">
            <div className="success-container">
                <div className="check-icon">
                    <i className="fas fa-check"></i>
                </div>
                <h1 className="success-title">Đặt Hàng Thành Công!</h1>
                <p className="success-msg">Cảm ơn bạn đã tin tưởng. Chúng tôi sẽ sớm liên hệ.</p>
                
                <div className="order-details-box">
                    <div className="detail-row">
                        <span className="label">Mã đơn hàng:</span>
                        <span className="value">#{order.orderId}</span>
                    </div>
                    <div className="detail-row">
                        <span className="label">Trạng thái:</span>
                        <span className="value" style={{ color: '#d4a373' }}>{order.status}</span>
                    </div>
                    <div className="detail-row">
                        <span className="label">Tổng thanh toán:</span>
                        <span className="value" style={{ color: '#2d6a4f' }}>
                            {order.totalAmount.toLocaleString('vi-VN')} VNĐ
                        </span>
                    </div>
                    <div className="detail-row" style={{ flexDirection: 'column', alignItems: 'flex-start', gap: '5px' }}>
                        <span className="label">Địa chỉ giao hàng:</span>
                        <span className="value" style={{ textAlign: 'left', lineHeight: '1.4' }}>
                            {order.shippingAddress}
                        </span>
                    </div>
                </div>
                
                <Link to="/product" className="btn-home">
                    <i className="fas fa-arrow-left"></i> Tiếp tục mua sắm
                </Link>
            </div>
        </section>
    );
};

export default OrderSuccess;