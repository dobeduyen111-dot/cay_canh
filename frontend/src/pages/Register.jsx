import React from 'react';
import { Link } from 'react-router-dom';
import '../assets/css/style.css'; // Đảm bảo bạn đã có file style.css trong thư mục này

const Register = () => {
    return (
        <div className="modal-overlay" style={{ display: 'flex' }}>
            <div className="modal-box">
                {/* Bên trái: Thông tin chào mừng */}
                <div className="col-left" style={{ background: '#7b8ea5', color: '#fff', padding: '40px', flex: '1' }}>
                    <h2>Chào mừng bạn!</h2>
                    <p>Đã có tài khoản?</p>
                    <Link to="/login" className="btn-register" style={{ border: '1px solid white', padding: '10px 20px', display: 'inline-block', marginTop: '15px' }}>
                        ĐĂNG NHẬP NGAY
                    </Link>
                </div>

                {/* Bên phải: Form đăng ký */}
                <div className="col-right" style={{ flex: '1.5', padding: '40px', background: 'white' }}>
                    <h2>Đăng Ký Tài Khoản</h2>
                    <form>
                        <div className="form-group">
                            <label>Họ và tên</label>
                            <input type="text" placeholder="Nhập họ tên..." style={{ width: '100%', padding: '10px', marginBottom: '15px', border: '1px solid #ddd' }} />
                        </div>
                        <div className="form-group">
                            <label>Email</label>
                            <input type="email" placeholder="Nhập email..." style={{ width: '100%', padding: '10px', marginBottom: '15px', border: '1px solid #ddd' }} />
                        </div>
                        <div className="form-group">
                            <label>Mật khẩu</label>
                            <input type="password" placeholder="Nhập mật khẩu..." style={{ width: '100%', padding: '10px', marginBottom: '15px', border: '1px solid #ddd' }} />
                        </div>
                        <button type="submit" className="btn-login" style={{ width: '100%', padding: '12px', background: '#1d472e', color: '#fff', border: 'none', cursor: 'pointer', fontWeight: 'bold' }}>
                            ĐĂNG KÝ
                        </button>
                    </form>
                </div>
            </div>
        </div>
    );
};

export default Register;
