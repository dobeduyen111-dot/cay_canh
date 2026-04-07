import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';

const OrderHistory = () => {
    // State lưu danh sách đơn hàng của User
    const [orders, setOrders] = useState([
        { orderId: '889922', orderDate: '2023-10-25', shippingAddress: 'Hà Nội', totalAmount: 450000, status: 'Chờ xử lý' },
        { orderId: '775533', orderDate: '2023-10-20', shippingAddress: 'Đà Nẵng', totalAmount: 120000, status: 'Hoàn thành' },
        { orderId: '664411', orderDate: '2023-10-15', shippingAddress: 'Hồ Chí Minh', totalAmount: 300000, status: 'Đã hủy' }
    ]);

    useEffect(() => {
        // TODO: Gọi API fetch danh sách lịch sử đơn hàng của User đang đăng nhập
    }, []);

    // Hàm render màu sắc badge dựa theo trạng thái
    const renderStatusBadge = (status) => {
        switch(status) {
            case 'Chờ xử lý': return <span className="order-badge badge-pending">Chờ xử lý</span>;
            case 'Đang giao': return <span className="order-badge badge-shipping">Đang giao</span>;
            case 'Hoàn thành': return <span className="order-badge badge-completed">Hoàn thành</span>;
            case 'Đã hủy': return <span className="order-badge badge-cancelled">Đã hủy</span>;
            default: return <span className="order-badge bg-secondary text-white">{status}</span>;
        }
    };

    return (
        <section className="home-content order-history-container mt-5">
            <h2 className="mb-4 text-center" style={{ fontSize: '32px', color: '#1b4332', fontWeight: 700 }}>
                <i className="fas fa-history"></i> Lịch sử đơn hàng
            </h2>
            
            {orders.length === 0 ? (
                <div className="alert alert-info">
                    Bạn chưa có đơn hàng nào. <Link to="/product" className="alert-link">Mua sắm ngay!</Link>
                </div>
            ) : (
                <table className="table table-order-history">
                    <thead>
                        <tr>
                            <th>Mã đơn</th>
                            <th>Ngày đặt</th>
                            <th>Địa chỉ nhận</th>
                            <th>Tổng tiền</th>
                            <th>Trạng thái</th>
                            <th>Hành động</th>
                        </tr>
                    </thead>
                    <tbody>
                        {orders.map(o => (
                            <tr key={o.orderId}>
                                <td>#{o.orderId}</td>
                                <td>{o.orderDate}</td> 
                                <td>{o.shippingAddress}</td>
                                <td>{o.totalAmount.toLocaleString('vi-VN')} đ</td>
                                <td>
                                    {renderStatusBadge(o.status)}
                                </td>
                                <td>
                                    {/* Link dẫn đến trang chi tiết đơn hàng (Nếu bạn có làm trang chi tiết) */}
                                    <Link to={`/order/confirm/${o.orderId}`} className="btn btn-sm btn-info btn-detail-order">
                                        <i className="fas fa-info-circle"></i> Chi tiết
                                    </Link>
                                </td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            )}
        </section>
    );
};

export default OrderHistory;