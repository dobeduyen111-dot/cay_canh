import React, { useState, useEffect } from 'react';
import '../assets/css/admin_style.css'; // Đường dẫn tới file CSS của bạn

const UserManagement = () => {
    // State lưu danh sách user (Dữ liệu giả lập)
    const [users, setUsers] = useState([
        { userId: 1, fullName: 'Nguyễn Quản Trị', email: 'admin@system.com', password: 'hashed_password_1', role: 'ROLE_ADMIN' },
        { userId: 2, fullName: 'Trần Khách Hàng', email: 'khachhang@gmail.com', password: 'hashed_password_2', role: 'ROLE_USER' }
    ]);

    useEffect(() => {
        // TODO: Gọi API lấy danh sách user
        // fetchUsers().then(data => setUsers(data));
    }, []);

    // Xử lý khi bấm nút Lưu mật khẩu
    const handleUpdatePassword = (e, userId) => {
        e.preventDefault();
        const formData = new FormData(e.target);
        const newPassword = formData.get('password');
        
        // TODO: Gọi API đổi mật khẩu
        console.log(`Cập nhật mật khẩu user ${userId} thành: ${newPassword}`);
        alert('Cập nhật mật khẩu thành công!');
    };

    // Xử lý khi bấm nút Xóa user
    const handleDelete = (e, userId) => {
        e.preventDefault();
        if (window.confirm('Bạn có chắc chắn muốn xóa vĩnh viễn user này?')) {
            // TODO: Gọi API xóa user
            console.log(`Đã xóa user ${userId}`);
            // Xóa khỏi UI
            setUsers(users.filter(u => u.userId !== userId));
        }
    };

    return (
        <section className="admin-content">
            <div className="page-header">
                <h2>Danh sách & Mật khẩu User</h2>
            </div>

            <div className="user-table-wrapper">
                <table>
                    <thead>
                        <tr>
                            <th style={{ width: '5%' }}>ID</th>
                            <th style={{ width: '25%' }}>Thông tin</th>
                            <th style={{ width: '25%' }}>Mật khẩu</th>
                            <th style={{ width: '15%' }}>Vai trò</th>
                            <th style={{ width: '15%' }}>Hành động</th>
                        </tr>
                    </thead>
                    <tbody>
                        {users.map(u => (
                            <tr key={u.userId}>
                                <td>{u.userId}</td>
                                
                                <td>
                                    <strong style={{ display: 'block', color: '#333' }}>{u.fullName}</strong>
                                    <span style={{ fontSize: '12px', color: '#777' }}>{u.email}</span>
                                </td>

                                <td>
                                    <form onSubmit={(e) => handleUpdatePassword(e, u.userId)} className="pass-form">
                                        <input 
                                            type="text" 
                                            name="password" 
                                            defaultValue={u.password} 
                                            className="pass-input" 
                                        />
                                        <button type="submit" className="btn-save-pass" title="Lưu mật khẩu mới">
                                            <i className="fas fa-save"></i> Lưu
                                        </button>
                                    </form>
                                </td>

                                <td>
                                    {(u.role === 'ROLE_ADMIN' || u.role === 'ADMIN') ? (
                                        <span className="badge role-admin">ADMIN</span>
                                    ) : (
                                        <span className="badge role-user">USER</span>
                                    )}
                                </td>

                                <td>
                                    <button 
                                        onClick={(e) => handleDelete(e, u.userId)}
                                        className="btn-delete"
                                        style={{ border: 'none', cursor: 'pointer' }}
                                    >
                                        <i className="fas fa-trash"></i> Xóa
                                    </button>
                                </td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            </div>
        </section>
    );
};

export default UserManagement;