import React, { useState, useEffect } from 'react';
// import { useParams, useNavigate } from 'react-router-dom'; // Dùng để lấy ID từ URL và chuyển trang
import AdminLayout from '../components/AdminLayout';
import '../assets/css/admin/admin_style.css';

const EditProduct = () => {
    // const { id } = useParams(); // Lấy ID sản phẩm từ URL (bỏ comment khi bạn đã cài react-router-dom)
    // const navigate = useNavigate();
    
    // Giả lập ID để test
    const id = 1; 

    const [formData, setFormData] = useState({
        productId: id,
        categoryId: '',
        productName: '',
        description: '',
        careGuide: '',
        price: '',
        stock: '',
        image: '',
        active: false
    });

    useEffect(() => {
        // TODO: Gọi API lấy chi tiết sản phẩm theo ID
        // fetchProductById(id).then(data => setFormData(data));
        
        // Dữ liệu giả lập trả về từ API
        setFormData({
            productId: id,
            categoryId: 2, // Thuộc danh mục số 2
            productName: 'Cây Kim Tiền',
            description: 'Cây mang lại tài lộc cho gia chủ.',
            careGuide: 'Tưới nước 1 lần/tuần, để nơi mát mẻ.',
            price: 150000,
            stock: 20,
            image: 'kim-tien.jpg',
            active: true
        });
    }, [id]);

    const handleChange = (e) => {
        const { name, value, type, checked } = e.target;
        setFormData({
            ...formData,
            [name]: type === 'checkbox' ? checked : value
        });
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        // TODO: Gọi API PUT/POST cập nhật dữ liệu
        console.log("Dữ liệu cập nhật:", formData);
        alert('Cập nhật sản phẩm thành công!');
        // navigate('/admin/product'); // Quay lại trang danh sách sản phẩm sau khi lưu xong
    };

    return (
        <AdminLayout>
            <div className="form-wrapper">
                <h2>Chỉnh sửa sản phẩm</h2>
                <form onSubmit={handleSubmit} className="form-card">
                    {/* ID ẩn đi không cho người dùng sửa */}
                    <input type="hidden" name="productId" value={formData.productId} />

                    <label>Category ID (ID Danh mục):</label>
                    <input type="number" name="categoryId" value={formData.categoryId} onChange={handleChange} required />

                    <label>Tên sản phẩm:</label>
                    <input type="text" name="productName" value={formData.productName} onChange={handleChange} required />

                    <label>Mô tả:</label>
                    <textarea name="description" value={formData.description} onChange={handleChange}></textarea>

                    <label>Care Guide:</label>
                    <textarea name="careGuide" value={formData.careGuide} onChange={handleChange}></textarea>

                    <label>Giá (VNĐ):</label>
                    <input type="number" name="price" value={formData.price} onChange={handleChange} required />

                    <label>Số lượng trong kho (Stock):</label>
                    <input type="number" name="stock" value={formData.stock} onChange={handleChange} required />

                    <label>Ảnh (tên file):</label>
                    <input type="text" name="image" value={formData.image} onChange={handleChange} />

                    <label>Hoạt động:</label>
                    <input type="checkbox" name="active" checked={formData.active} onChange={handleChange} />

                    <button type="submit">Cập nhật</button>
                </form>
            </div>
        </AdminLayout>
    );
};

export default EditProduct;