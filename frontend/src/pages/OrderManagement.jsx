import React, { useState, useEffect } from 'react';
import '../assets/css/admin_style.css'; // Đường dẫn tới file CSS của bạn

const OrderManagement = () => {
    // State lưu danh sách đơn hàng (Tạm thời dùng dữ liệu giả để test giao diện)
    const [orders, setOrders] = useState([
        { orderId: 101, fullName: 'Nguyễn Văn A', orderDate: '2023-10-25', totalAmount: 1500000, status: 'Chờ xử lý' },
        { orderId: 102, fullName: 'Trần Thị B', orderDate: '2023-10-26', totalAmount: 750000, status: 'Đang giao' }
    ]);

    useEffect(() => {
        // TODO: Gọi API fetch danh sách đơn hàng ở đây
        // fetchOrders().then(data => setOrders(data));
    }, []);

    // Xử lý khi bấm nút Cập nhật trạng thái
    const handleUpdateStatus = (e, orderId) => {
        e.preventDefault();
        const formData = new FormData(e.target);
        const newStatus = formData.get('status');
        
        // TODO: Gọi API cập nhật trạng thái đơn hàng
        console.log(`Đã cập nhật đơn #${orderId} thành: ${newStatus}`);
        alert(`Cập nhật đơn #${orderId} thành công!`);
    };

    // Xử lý khi bấm nút Xóa
    const handleDelete = (e, orderId) => {
        e.preventDefault();
        if (window.confirm('Bạn có chắc chắn muốn XÓA đơn hàng này không?')) {
            // TODO: Gọi API xóa đơn hàng
            console.log(`Đã xóa đơn #${orderId}`);
            // Cập nhật lại state để giao diện mất dòng vừa xóa
            setOrders(orders.filter(o => o.orderId !== orderId));
        }
    };

    return (
        <section className="admin-content">
            <div className="manage-order-page">
                <div className="inner-container">
                    <h2 className="manage-order-title">Quản lý Đơn hàng</h2>

                    <div className="order-table-wrapper">
                        <table className="order-table">
                            <thead>
                                <tr>
                                    <th style={{ width: '8%' }}>Mã Đơn</th>
                                    <th style={{ width: '15%' }}>Người mua</th>
                                    <th style={{ width: '12%' }}>Ngày đặt</th>
                                    <th style={{ width: '15%' }}>Tổng tiền</th>
                                    <th style={{ width: '15%' }}>Trạng thái</th>
                                    <th style={{ width: '35%' }}>Hành động</th>
                                </tr>
                            </thead>
                            <tbody>
                                {orders.map(o => (
                                    <tr key={o.orderId}>
                                        <td><b>#{o.orderId}</b></td>
                                        <td>
                                            <div className="user-info">
                                                <span 
                                                    className="user-name-text" 
                                                    style={{ display: 'block', fontWeight: 'bold', color: '#1b4332', marginTop: '4px' }}
                                                >
                                                    {o.fullName}
                                                </span>
                                            </div>
                                        </td>
                                        <td>{o.orderDate}</td>
                                        <td style={{ color: '#d35400', fontWeight: '700' }}>
                                            {o.totalAmount.toLocaleString('vi-VN')} ₫
                                        </td>
                                        <td>
                                            <span className="status-text">{o.status}</span>
                                        </td>
                                        <td>
                                            <div style={{ display: 'flex', alignItems: 'center', justifyContent: 'center' }}>
                                                <form onSubmit={(e) => handleUpdateStatus(e, o.orderId)} className="action-form">
                                                    <select name="status" defaultValue={o.status}>
                                                        <option value="Chờ xử lý">Chờ xử lý</option>
                                                        <option value="Đang giao">Đang giao</option>
                                                        <option value="Hoàn thành">Hoàn thành</option>
                                                        <option value="Đã hủy">Đã hủy</option>
                                                    </select>
                                                    <button type="submit" className="btn-update">Cập nhật</button>
                                                </form>

                                                <button 
                                                    onClick={(e) => handleDelete(e, o.orderId)}
                                                    className="btn-delete"
                                                    title="Xóa đơn hàng"
                                                >
                                                    <i className="fas fa-trash"></i>
                                                </button>
                                            </div>
                                        </td>
                                    </tr>
                                ))}
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </section>
    );
};

export default OrderManagement;