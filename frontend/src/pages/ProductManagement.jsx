import React, { useState, useEffect } from 'react';
import AdminLayout from '../components/AdminLayout';

const ProductManagement = () => {
    const [products, setProducts] = useState([
        // Dữ liệu mẫu
        { productId: 1, productName: 'Cây Kim Tiền', price: 150000, stock: 20, image: 'kim-tien.jpg', description: 'Cây phong thủy...' }
    ]);

    useEffect(() => {
        // TODO: Gọi API fetch danh sách sản phẩm
    }, []);

    const handleDelete = (id) => {
        if (window.confirm('Bạn có chắc chắn muốn xóa cây này không?')) {
            // TODO: Gọi API xóa
            setProducts(products.filter(p => p.productId !== id));
        }
    };

    return (
        <AdminLayout>
            <section className="home-content">
                <h1>Tất cả sản phẩm</h1>
                <div className="action-bar">
                    <a href="/admin/product/add" className="btn-add">
                        <i className="fas fa-plus"></i> Thêm sản phẩm mới
                    </a>
                </div>
                
                <div className="product-container">
                    {products.map(p => (
                        <div key={p.productId} className="product-box">
                            {/* Chú ý sửa lại đường dẫn ảnh cho đúng với public/images của React */}
                            <img src={`/images/${p.image}`} alt={p.productName} />
                            <h3>{p.productName}</h3>
                            <div className="meta-info">
                                <span className="price-tag">{p.price.toLocaleString('vi-VN')} VNĐ</span>
                                <span>|</span>
                                <span>Kho: <b>{p.stock}</b></span>
                            </div>
                            {/* React cần dangerouslySetInnerHTML nếu description là mã HTML từ CkEditor/TinyMCE */}
                            <div className="desc-text" dangerouslySetInnerHTML={{ __html: p.description }}></div>
                            
                            <div className="admin-tools">
                                <a href={`/admin/product/edit/${p.productId}`} className="btn-edit">
                                    <i className="fas fa-pen"></i> Sửa
                                </a>
                                <button onClick={() => handleDelete(p.productId)} className="btn-delete" style={{ border: 'none', cursor: 'pointer' }}>
                                    <i className="fas fa-trash"></i> Xóa
                                </button>
                            </div>
                        </div>
                    ))}
                </div>
            </section>
        </AdminLayout>
    );
};

export default ProductManagement;