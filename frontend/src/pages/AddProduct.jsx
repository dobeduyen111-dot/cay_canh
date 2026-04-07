import React, { useState, useEffect } from 'react';
import AdminLayout from '../components/AdminLayout';
import '../assets/css/admin/admin_style.css'; 

const AddProduct = () => {
    // State lưu danh sách danh mục (để in ra thẻ <select>)
    const [categories, setCategories] = useState([]);
    
    // State lưu trữ dữ liệu người dùng nhập vào form
    const [formData, setFormData] = useState({
        categoryId: '',
        productName: '',
        description: '',
        careGuide: '',
        price: '',
        stock: '',
        image: '',
        active: true // Mặc định là có hoạt động
    });

    useEffect(() => {
        // TODO: Gọi API lấy danh sách Danh mục từ Backend
        // Dữ liệu giả lập tạm thời:
        setCategories([
            { categoryId: 1, categoryName: 'Cây để bàn' },
            { categoryId: 2, categoryName: 'Cây phong thủy' },
            { categoryId: 3, categoryName: 'Sen đá - Xương rồng' }
        ]);
    }, []);

    // Hàm xử lý khi người dùng gõ vào các ô input
    const handleChange = (e) => {
        const { name, value, type, checked } = e.target;
        setFormData({
            ...formData,
            [name]: type === 'checkbox' ? checked : value
        });
    };

    // Hàm xử lý khi bấm nút "Thêm"
    const handleSubmit = (e) => {
        e.preventDefault(); // Chặn hành vi load lại trang mặc định của form
        
        // TODO: Gọi API POST dữ liệu formData xuống Backend
        console.log("Dữ liệu gửi lên server:", formData);
        alert('Thêm sản phẩm thành công!');
    };

    return (
        <AdminLayout>
            <div className="form-wrapper">
                <h2>Thêm sản phẩm mới</h2>
                <form onSubmit={handleSubmit} className="form-card">
                    
                    <label>Danh mục:</label>
                    <select name="categoryId" value={formData.categoryId} onChange={handleChange} required> 
                        <option value="">-- Chọn danh mục --</option>
                        {categories.map(c => (
                            <option key={c.categoryId} value={c.categoryId}>
                                {c.categoryName}
                            </option>
                        ))}
                    </select>

                    <label>Tên sản phẩm:</label>
                    <input type="text" name="productName" value={formData.productName} onChange={handleChange} required />

                    <label>Mô tả:</label>
                    <textarea name="description" value={formData.description} onChange={handleChange}></textarea>

                    <label>Care Guide (Hướng dẫn chăm sóc):</label>
                    <textarea name="careGuide" value={formData.careGuide} onChange={handleChange}></textarea>

                    <label>Giá (VNĐ):</label>
                    <input type="number" name="price" value={formData.price} onChange={handleChange} required />

                    <label>Số lượng trong kho (Stock):</label>
                    <input type="number" name="stock" value={formData.stock} onChange={handleChange} required />

                    <label>Ảnh (tên file hoặc URL):</label>
                    <input type="text" name="image" value={formData.image} onChange={handleChange} />

                    <label>Hoạt động (Hiển thị lên web):</label>
                    <input type="checkbox" name="active" checked={formData.active} onChange={handleChange} />

                    <button type="submit">Thêm Sản Phẩm</button>
                </form>
            </div>
        </AdminLayout>
    );
};

export default AddProduct;