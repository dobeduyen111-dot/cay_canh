import React, { useState, useEffect } from 'react';
import { Link, useNavigate } from 'react-router-dom';

const Checkout = () => {
    const navigate = useNavigate();
    
    // Giả lập dữ liệu giỏ hàng được load từ API hoặc Redux/Context
    const [cartItems, setCartItems] = useState([
        { cartItemId: 1, quantity: 2, product: { productId: 101, productName: 'Cây Kim Tiền', price: 150000, image: 'kim-tien.jpg' } }
    ]);
    const [total, setTotal] = useState(300000);

    const [shippingData, setShippingData] = useState({
        shippingAddress: '',
        note: ''
    });

    const handleChange = (e) => {
        setShippingData({ ...shippingData, [e.target.name]: e.target.value });
    };

    const handleCheckoutSubmit = (e) => {
        e.preventDefault();
        console.log("Xác nhận thanh toán:", { ...shippingData, items: cartItems, total });
        // TODO: Gọi API tạo Đơn hàng
        alert("Đặt hàng thành công!");
        navigate('/'); // Điều hướng về trang chủ
    };

    return (
        <section className="home-content checkout-page" style={{ padding: '40px 20px', maxWidth: '1100px', margin: '0 auto' }}>
            <h1 style={{ textAlign: 'center', color: '#1b4332', textTransform: 'uppercase', marginBottom: '30px' }}>Xác nhận đơn hàng</h1>
            
            {cartItems.length === 0 ? (
                <div style={{ textAlign: 'center', marginTop: '50px' }}>
                    <img src="https://cdn-icons-png.flaticon.com/512/2038/2038854.png" alt="Empty" style={{ width: '100px', opacity: 0.5 }} />
                    <h3>Giỏ hàng của bạn đang trống</h3>
                    <Link to="/product" style={{ color: '#2d6a4f', textDecoration: 'underline', fontWeight: 600 }}>Quay lại cửa hàng</Link>
                </div>
            ) : (
                <div className="checkout-layout" style={{ display: 'grid', gridTemplateColumns: '1.5fr 1fr', gap: '30px' }}>
                    {/* Cột Trái: Sản phẩm */}
                    <div className="card-box" style={{ background: '#fff', padding: '25px', borderRadius: '15px', boxShadow: '0 5px 20px rgba(0,0,0,0.05)' }}>
                        <div className="section-header" style={{ fontSize: '18px', fontWeight: 700, color: '#2d6a4f', borderBottom: '2px solid #e8f5e9', paddingBottom: '10px', marginBottom: '20px' }}>Sản phẩm đã chọn</div>
                        
                        {cartItems.map(ci => (
                            <div key={ci.cartItemId} className="cart-item-row" style={{ display: 'flex', gap: '15px', paddingBottom: '15px', marginBottom: '15px', borderBottom: '1px dashed #eee', alignItems: 'center' }}>
                                <img src={`/images/${ci.product.image}`} alt={ci.product.productName} style={{ width: '70px', height: '70px', borderRadius: '8px', objectFit: 'cover' }} />
                                <div className="item-info">
                                    <div className="item-name" style={{ fontWeight: 700, fontSize: '15px' }}>{ci.product.productName}</div>
                                    <div style={{ fontSize: '14px' }}>Đơn giá: {ci.product.price.toLocaleString('vi-VN')} đ</div>
                                    <div style={{ fontSize: '14px' }}>Số lượng: <b>{ci.quantity}</b></div>
                                    <div className="item-price" style={{ color: '#2d6a4f', fontWeight: 600 }}>
                                        Thành tiền: {(ci.product.price * ci.quantity).toLocaleString('vi-VN')} VNĐ
                                    </div>
                                </div>
                            </div>
                        ))}
                        
                        <div className="total-row" style={{ textAlign: 'right', fontSize: '20px', fontWeight: 700, color: '#1b4332', marginTop: '20px' }}>
                            Tổng cộng: {total.toLocaleString('vi-VN')} VNĐ
                        </div>
                    </div>

                    {/* Cột Phải: Form Giao hàng */}
                    <div className="card-box" style={{ background: '#fff', padding: '25px', borderRadius: '15px', boxShadow: '0 5px 20px rgba(0,0,0,0.05)' }}>
                        <div className="section-header" style={{ fontSize: '18px', fontWeight: 700, color: '#2d6a4f', borderBottom: '2px solid #e8f5e9', paddingBottom: '10px', marginBottom: '20px' }}>Thông tin giao hàng</div>
                        <form onSubmit={handleCheckoutSubmit}>
                            <div className="form-group" style={{ marginBottom: '15px' }}>
                                <label style={{ display: 'block', marginBottom: '8px', fontWeight: 600 }}>Địa chỉ nhận hàng (*)</label>
                                <input type="text" name="shippingAddress" value={shippingData.shippingAddress} onChange={handleChange} className="form-control" required placeholder="Số nhà, đường, phường, quận..." style={{ width: '100%', padding: '12px', border: '1px solid #ddd', borderRadius: '8px' }} />
                            </div>
                            <div className="form-group" style={{ marginBottom: '15px' }}>
                                <label style={{ display: 'block', marginBottom: '8px', fontWeight: 600 }}>Ghi chú thêm (Nếu có)</label>
                                <textarea name="note" value={shippingData.note} onChange={handleChange} className="form-control" rows="4" placeholder="Ví dụ: Giao giờ hành chính..." style={{ width: '100%', padding: '12px', border: '1px solid #ddd', borderRadius: '8px' }}></textarea>
                            </div>
                            <button type="submit" className="btn-confirm" style={{ width: '100%', padding: '15px', background: '#164a3e', color: 'white', borderRadius: '50px', border: 'none', fontWeight: 700, cursor: 'pointer', marginTop: '10px' }}>
                                TIẾN HÀNH THANH TOÁN
                            </button>
                        </form>
                    </div>
                </div>
            )}
        </section>
    );
};

export default Checkout;