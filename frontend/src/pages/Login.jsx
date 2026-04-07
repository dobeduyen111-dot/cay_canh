import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
// import '../assets/css/style.css'; 

const Login = () => {
    const navigate = useNavigate();
    const [loginData, setLoginData] = useState({ username: '', password: '', rememberMe: false });
    const [registerData, setRegisterData] = useState({ fullName: '', email: '', password: '', phone: '', address: '' });
    
    // Quản lý thông báo lỗi/thành công
    const [message, setMessage] = useState({ type: '', text: '' });

    const handleLoginChange = (e) => {
        const { name, value, type, checked } = e.target;
        setLoginData({ ...loginData, [name]: type === 'checkbox' ? checked : value });
    };

    const handleRegisterChange = (e) => {
        setRegisterData({ ...registerData, [e.target.name]: e.target.value });
    };

    const handleLoginSubmit = (e) => {
        e.preventDefault();
        console.log("Submit Login: ", loginData);
        // TODO: Gọi API Đăng nhập
        // Thành công: navigate('/')
    };

    const handleRegisterSubmit = (e) => {
        e.preventDefault();
        console.log("Submit Register: ", registerData);
        // TODO: Gọi API Đăng ký
        setMessage({ type: 'success', text: 'Đăng ký thành công! Bạn có thể đăng nhập ngay bên cạnh.' });
    };

    return (
        <div className="home-content" style={{ backgroundColor: '#fdfdf9', fontFamily: "'Lora', serif", color: '#333' }}>
            <div className="page-title" style={{ backgroundColor: '#2ecc71', padding: '10px 0', textAlign: 'center', marginBottom: '50px' }}>
                <h1 style={{ color: 'white', fontSize: '22px', fontWeight: 500, letterSpacing: '1px' }}>ĐĂNG KÝ / ĐĂNG NHẬP</h1>
            </div>

            <div className="container" style={{ maxWidth: '1200px', margin: '0 auto', padding: '0 20px' }}>
                <div className="auth-wrapper" style={{ display: 'flex', justifyContent: 'space-between', paddingBottom: '50px' }}>
                    
                    {/* Cột Đăng Nhập */}
                    <div className="column login-column" style={{ width: '46%' }}>
                        <h2 style={{ fontSize: '24px', marginBottom: '25px', fontWeight: 500 }}>ĐĂNG NHẬP</h2>
                        <form onSubmit={handleLoginSubmit}>
                            <div className="form-group" style={{ marginBottom: '20px' }}>
                                <label style={{ display: 'block', marginBottom: '8px', fontWeight: 600 }}>Email <span style={{ color: 'red' }}>*</span></label>
                                <input type="email" name="username" value={loginData.username} onChange={handleLoginChange} required style={{ width: '100%', padding: '12px', border: '1px solid #ddd' }} />
                            </div>

                            <div className="form-group" style={{ marginBottom: '20px' }}>
                                <label style={{ display: 'block', marginBottom: '8px', fontWeight: 600 }}>Mật khẩu <span style={{ color: 'red' }}>*</span></label>
                                <input type="password" name="password" value={loginData.password} onChange={handleLoginChange} required style={{ width: '100%', padding: '12px', border: '1px solid #ddd' }} />
                            </div>

                            <div className="form-actions" style={{ marginBottom: '20px' }}>
                                <label style={{ display: 'flex', alignItems: 'center', cursor: 'pointer' }}>
                                    <input type="checkbox" name="rememberMe" checked={loginData.rememberMe} onChange={handleLoginChange} style={{ marginRight: '10px' }} /> 
                                    Ghi nhớ đăng nhập
                                </label>
                            </div>

                            <button type="submit" style={{ backgroundColor: '#1e4d40', color: 'white', border: 'none', padding: '12px 25px', fontWeight: 700, cursor: 'pointer' }}>ĐĂNG NHẬP</button>
                            <a href="#" style={{ display: 'block', marginTop: '15px', color: '#333', textDecoration: 'none' }}>Quên mật khẩu?</a>
                        </form>
                    </div>

                    {/* Đường kẻ giữa 2 cột (Dùng CSS Auth-wrapper::after trong file CSS) */}

                    {/* Cột Đăng Ký */}
                    <div className="column register-column" style={{ width: '46%' }}>
                        <h2 style={{ fontSize: '24px', marginBottom: '25px', fontWeight: 500 }}>ĐĂNG KÝ</h2>
                        <form onSubmit={handleRegisterSubmit}>
                            <div className="form-group" style={{ marginBottom: '20px' }}>
                                <label style={{ display: 'block', marginBottom: '8px', fontWeight: 600 }}>Họ tên <span style={{ color: 'red' }}>*</span></label>
                                <input type="text" name="fullName" value={registerData.fullName} onChange={handleRegisterChange} required style={{ width: '100%', padding: '12px', border: '1px solid #ddd' }} />
                            </div>
                            <div className="form-group" style={{ marginBottom: '20px' }}>
                                <label style={{ display: 'block', marginBottom: '8px', fontWeight: 600 }}>Email <span style={{ color: 'red' }}>*</span></label>
                                <input type="email" name="email" value={registerData.email} onChange={handleRegisterChange} required style={{ width: '100%', padding: '12px', border: '1px solid #ddd' }} />
                            </div>
                            <div className="form-group" style={{ marginBottom: '20px' }}>
                                <label style={{ display: 'block', marginBottom: '8px', fontWeight: 600 }}>Mật khẩu <span style={{ color: 'red' }}>*</span></label>
                                <input type="password" name="password" value={registerData.password} onChange={handleRegisterChange} required style={{ width: '100%', padding: '12px', border: '1px solid #ddd' }} />
                            </div>
                            <div className="form-group" style={{ marginBottom: '20px' }}>
                                <label style={{ display: 'block', marginBottom: '8px', fontWeight: 600 }}>Số điện thoại</label>
                                <input type="text" name="phone" value={registerData.phone} onChange={handleRegisterChange} style={{ width: '100%', padding: '12px', border: '1px solid #ddd' }} />
                            </div>
                            <div className="form-group" style={{ marginBottom: '20px' }}>
                                <label style={{ display: 'block', marginBottom: '8px', fontWeight: 600 }}>Địa chỉ</label>
                                <input type="text" name="address" value={registerData.address} onChange={handleRegisterChange} style={{ width: '100%', padding: '12px', border: '1px solid #ddd' }} />
                            </div>
                            
                            <p style={{ fontSize: '14px', color: '#666', fontStyle: 'italic', marginBottom: '20px' }}>Thông tin cá nhân của bạn sẽ được sử dụng để hỗ trợ trải nghiệm trên website này.</p>
                            
                            <button type="submit" style={{ backgroundColor: '#1e4d40', color: 'white', border: 'none', padding: '12px 25px', fontWeight: 700, cursor: 'pointer' }}>ĐĂNG KÝ</button>
                        </form>

                        {message.text && (
                            <div style={{ marginTop: '20px', padding: '10px', backgroundColor: message.type === 'success' ? '#d4edda' : '#f8d7da', color: message.type === 'success' ? '#155724' : '#721c24' }}>
                                {message.text}
                            </div>
                        )}
                    </div>

                </div>
            </div>
        </div>
    );
};

export default Login;