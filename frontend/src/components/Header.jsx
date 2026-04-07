import React, { useState } from 'react';
import { Link } from 'react-router-dom';
// LƯU Ý: Đảm bảo đường dẫn này đúng với cấu trúc thư mục của bạn
import '../assets/css/client/header.css'; 

const Header = () => {
    const [loggedName, setLoggedName] = useState(null); 
    const [wishlistCount] = useState(2);
    const [cartCount] = useState(3);
    const [showAdminMenu, setShowAdminMenu] = useState(false);
    const [showLoginModal, setShowLoginModal] = useState(false);
    const [showPassword, setShowPassword] = useState(false);

    const handleLogout = (e) => {
        e.preventDefault();
        setLoggedName(null);
    };

    return (
        <>
            {/* --- TOPBAR --- */}
            <div className="topbar">
                <div className="container topbar-inner">
                    <div className="left">
                        <span className="icon">⏱</span> 08:30 - 22:00
                        <span className="sep">|</span>
                        <span className="icon">📞</span> 0838 369 639 - 09 6688 9393
                    </div>
                    <div className="right">
                        <Link to="/wishlist" className="wishlist-btn">
                            <i className="fa-regular fa-heart"></i>
                            <span>{wishlistCount}</span>
                        </Link>
                        <span className="sep">|</span>
                        
                        {!loggedName ? (
                            <a href="#" onClick={(e) => { e.preventDefault(); setShowLoginModal(true); }}>Login</a>
                        ) : (
                            <div style={{ display: 'flex', alignItems: 'center' }}>
                                {loggedName.includes('Admin') ? (
                                    <div className="my-custom-dropdown" style={{ position: 'relative' }}>
                                        <span onClick={() => setShowAdminMenu(!showAdminMenu)} style={{ cursor: 'pointer' }}>
                                            👋 <b>{loggedName}</b> ▾
                                        </span>
                                        {showAdminMenu && (
                                            <div className="dropdown-content show" style={{ position: 'absolute', right: 0, background: '#fff', boxShadow: '0px 8px 16px rgba(0,0,0,0.2)', padding: '10px', borderRadius: '5px', zIndex: 999 }}>
                                                <Link to="/admin/dashboard" style={{ color: '#000', textDecoration: 'none' }}>⚙️ Vào trang Admin</Link>
                                            </div>
                                        )}
                                    </div>
                                ) : (
                                    <span>👋 <b>{loggedName}</b></span>
                                )}
                                <span className="sep">|</span>
                                <button onClick={handleLogout} className="btn-logout" style={{ background:'transparent', color:'inherit', border:'none', cursor:'pointer' }}>Logout</button>
                            </div>
                        )}
                    </div>
                </div>
            </div>

            {/* --- MAIN HEADER --- */}
            <header className="main-header">
                <div className="container header-inner">
                    <div className="logo">
                        <Link to="/"><img src="/images/logo.jpg" alt="MOW Logo" /></Link>
                    </div>
                    <nav className="menu">
                        <ul className="menu-list">
                            <li className="menu-item"><Link to="/product/cay">Cây cảnh</Link></li>
                            <li className="menu-item"><Link to="/product/chau">Chậu cây</Link></li>
                            <li className="menu-item"><Link to="/product/phukien">Phụ kiện</Link></li>
                            <li className="menu-item"><Link to="/instruction">Hướng dẫn</Link></li>
                            <li className="menu-item"><Link to="/order/history">Đơn hàng</Link></li>
                        </ul>
                    </nav>
                    <div className="actions">
                        <div className="cart">
                            <Link to="/cart" className="cart-link">
                                <span className="icon">🛒</span>
                                <span className="cart-badge">{cartCount}</span>
                            </Link>
                        </div>
                        <div className="search-box">
                            <form action="/product/search" method="get">
                                <input type="text" name="keyword" placeholder="Tìm cây cảnh..." />
                                <button type="submit">🔍</button>
                            </form>
                        </div>
                    </div>
                </div>
            </header>

            {/* --- MODAL ĐĂNG NHẬP (Chỉ hiện khi showLoginModal === true) --- */}
            {showLoginModal && (
                <div className="modal-overlay" style={{ display: 'flex', position: 'fixed', zIndex: 9999, left: 0, top: 0, width: '100%', height: '100%', backgroundColor: 'rgba(0,0,0,0.6)', justifyContent: 'center', alignItems: 'center' }}>
                    <div className="modal-box" style={{ display: 'flex', width: '800px', maxWidth: '95%', background: 'white', position: 'relative', borderRadius: '10px', overflow: 'hidden' }}>
                        <span className="close-btn" onClick={() => setShowLoginModal(false)} style={{ position: 'absolute', top: '10px', right: '15px', fontSize: '24px', cursor: 'pointer', color: '#000', zIndex: 10 }}>×</span>
                        
                        <div className="col-left" style={{ flex: 1, padding: '40px', background: '#2d6a4f', color: '#fff', textAlign: 'center' }}>
                            <h2>ĐĂNG KÝ</h2>
                            <p>CHƯA CÓ TÀI KHOẢN? ĐĂNG KÝ NGAY!</p>
                            <Link to="/register" onClick={() => setShowLoginModal(false)} style={{ display:'inline-block', padding:'10px 20px', background:'#fff', color:'#2d6a4f', textDecoration:'none', fontWeight:'bold', borderRadius:'5px', marginTop:'15px' }}>ĐĂNG KÝ TÀI KHOẢN</Link>
                        </div>
                        
                        <div className="col-right" style={{ flex: 1, padding: '40px', background: 'white' }}>
                            <h2 style={{ marginBottom: '20px' }}>ĐĂNG NHẬP</h2>
                            <form onSubmit={(e) => { e.preventDefault(); alert("Đăng nhập thành công!"); setShowLoginModal(false); setLoggedName("Khách Hàng"); }}>
                                <label style={{ display:'block', marginBottom:'5px', fontSize:'13px', fontWeight:'bold' }}>Username or email *</label>
                                <input type="text" name="username" required placeholder="Email" style={{ width:'100%', padding:'10px', marginBottom:'15px', border:'1px solid #ddd', boxSizing:'border-box' }} />
                                
                                <label style={{ display:'block', marginBottom:'5px', fontSize:'13px', fontWeight:'bold' }}>Mật khẩu *</label>
                                <input type={showPassword ? "text" : "password"} name="password" required placeholder="Mật khẩu" style={{ width:'100%', padding:'10px', border:'1px solid #ddd', boxSizing:'border-box' }} />
                                
                                <div style={{ marginTop: '10px', marginBottom: '15px', display: 'flex', alignItems: 'center', gap: '8px' }}>
                                    <input type="checkbox" id="chkShowPass" onChange={() => setShowPassword(!showPassword)} style={{ cursor: 'pointer' }} />
                                    <label htmlFor="chkShowPass" style={{ fontSize: '13px', cursor: 'pointer', userSelect: 'none' }}>Hiện mật khẩu</label>
                                </div>

                                <button type="submit" style={{ width:'100%', padding:'12px', background:'#1d472e', color:'#fff', border:'none', cursor:'pointer', fontWeight:'bold' }}>Đăng nhập</button>
                            </form>
                        </div>
                    </div>
                </div>
            )}
        </>
    );
};

export default Header;