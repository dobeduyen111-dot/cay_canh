import React, { useState, useEffect } from 'react';
import AdminLayout from '../components/AdminLayout';

const AdminDashboard = () => {
    // State lưu dữ liệu thống kê
    const [stats, setStats] = useState({
        totalOrders: 0,
        totalRevenue: 0,
        totalCustomers: 0
    });

    useEffect(() => {
        // TODO: Gọi API lấy số liệu thống kê thực tế
        setStats({
            totalOrders: 150,
            totalRevenue: 45000000,
            totalCustomers: 320
        });
    }, []);

    return (
        <AdminLayout>
            <section>
                <h2>Tổng quan hệ thống</h2>
                <div className="dashboard-cards">
                    <div className="card bg-1">
                        <div>
                            <h3>{stats.totalOrders}</h3>
                            <p>Tổng đơn hàng</p>
                        </div>
                        <i className="fas fa-shopping-bag icon-box" style={{ color: '#17a2b8' }}></i>
                    </div>

                    <div className="card bg-2">
                        <div>
                            <h3>{stats.totalRevenue.toLocaleString('vi-VN')}₫</h3>
                            <p>Tổng doanh thu</p>
                        </div>
                        <i className="fas fa-chart-line icon-box" style={{ color: '#28a745' }}></i>
                    </div>

                    <div className="card bg-3">
                        <div>
                            <h3>{stats.totalCustomers}</h3>
                            <p>Khách hàng thành viên</p>
                        </div>
                        <i className="fas fa-user-plus icon-box" style={{ color: '#ffc107' }}></i>
                    </div>
                </div>
            </section>
        </AdminLayout>
    );
};

export default AdminDashboard;