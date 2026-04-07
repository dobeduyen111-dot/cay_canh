import React from 'react';
import '../assets/css/admin/admin_style.css'; // Thay bằng đường dẫn file CSS của bạn

const AdminLayout = ({ children }) => {
    const handleLogout = (e) => {
        e.preventDefault();
        // TODO: Xóa token, gọi API đăng xuất, chuyển hướng về trang chủ
        console.log("Đã đăng xuất");
    };

    return (
        <div style={{ display: 'flex', height: '100vh', overflow: 'hidden', backgroundColor: '#ebedef', fontFamily: "'Roboto', sans-serif" }}>
            {/* --- SIDEBAR --- */}
            <nav className="sidebar">
                <div className="logo"><i className="fas fa-shield-alt"></i>ADMIN</div>
                <ul className="menu">
                    <li><a href="/admin/dashboard"><i className="fas fa-tachometer-alt"></i> Dashboard</a></li>
                    <li><a href="/admin/product"><i className="fas fa-box"></i> Quản lý Sản phẩm</a></li>
                    <li><a href="/admin/orders"><i className="fas fa-shopping-cart"></i> Quản lý Đơn hàng</a></li>
                    <li><a href="/admin/users"><i className="fas fa-users"></i> Khách hàng</a></li>
                    <form onSubmit={handleLogout} style={{ display: 'inline' }}>
                        <button type="submit" className="btn-logout">Logout</button>
                    </form>
                </ul>
            </nav>

            {/* --- NỘI DUNG BÊN PHẢI --- */}
            <div className="content-wrapper">
                <div className="top-navbar">
                    <span>Xin chào, <b>Admin</b></span>
                </div>

                <div className="main-content">
                    {/* Các trang con sẽ được render ở đây */}
                    {children}
                </div>
            </div>
        </div>
    );
};

export default AdminLayout;