import React, { useState, useEffect } from 'react';
import { Link, useSearchParams } from 'react-router-dom';

const SearchResults = () => {
    // Lấy keyword từ URL parameter
    const [searchParams] = useSearchParams();
    const keyword = searchParams.get('keyword') || '';

    // Dữ liệu mẫu
    const [results, setResults] = useState([]);

    useEffect(() => {
        // TODO: Gọi API tìm kiếm sản phẩm theo keyword
        // Ví dụ giả lập có kết quả nếu tìm chữ "kim":
        if (keyword.toLowerCase().includes('kim')) {
            setResults([{ productId: 1, productName: 'Cây Kim Tiền', price: 150000, image: 'kim-tien.jpg' }]);
        } else {
            setResults([]); // Không có kết quả
        }
    }, [keyword]);

    return (
        <section className="home-content">
            {/* Bạn có thể đưa phần CSS này ra file SearchResults.css */}
            <style>{`
                .home-content { font-family: 'Quicksand', sans-serif; background-color: #f7fcf7; padding: 40px 20px; color: #444; min-height: 80vh; }
                .container-search { max-width: 1200px; margin: 0 auto; }
                .search-title { text-align: center; color: #1b4332; margin-bottom: 40px; font-size: 28px; font-weight: 700; }
                .keyword-highlight { color: #d4a373; font-style: italic; text-decoration: underline; }
                .no-result-box { text-align: center; padding: 50px 20px; background: #fff; border-radius: 15px; box-shadow: 0 5px 20px rgba(0,0,0,0.05); max-width: 600px; margin: 0 auto; }
                .no-result-icon { font-size: 50px; color: #ccc; margin-bottom: 20px; }
                .btn-back { display: inline-block; margin-top: 20px; padding: 10px 25px; background: #2d6a4f; color: white; border-radius: 50px; text-decoration: none; font-weight: 600; transition: 0.3s; }
                .btn-back:hover { background: #1b4332; }
                .product-list { display: grid; grid-template-columns: repeat(auto-fill, minmax(260px, 1fr)); gap: 30px; }
                .product-item { background: #fff; border-radius: 15px; overflow: hidden; box-shadow: 0 5px 15px rgba(0,0,0,0.05); transition: transform 0.3s ease; text-align: center; padding-bottom: 20px; border: 1px solid #f0f0f0; display: flex; flex-direction: column; justify-content: space-between; }
                .product-item:hover { transform: translateY(-8px); box-shadow: 0 10px 25px rgba(45, 106, 79, 0.15); border-color: #d8f3dc; }
                .product-item img { width: 100%; height: 220px; object-fit: cover; border-bottom: 1px solid #f9f9f9; }
                .product-info { padding: 15px; }
                .product-item h3 { font-size: 18px; color: #1b4332; font-weight: 700; margin: 10px 0; white-space: nowrap; overflow: hidden; text-overflow: ellipsis; }
                .product-price { color: #2d6a4f; font-weight: 700; font-size: 16px; margin-bottom: 15px; display: block; }
                .btn-view { display: inline-block; padding: 8px 25px; background: #d4a373; color: #fff; border-radius: 50px; text-decoration: none; font-weight: 600; font-size: 14px; transition: 0.3s; }
                .btn-view:hover { background: #b08968; transform: translateY(-2px); box-shadow: 0 4px 10px rgba(212, 163, 115, 0.4); }
            `}</style>

            <div className="container-search">
                <h2 className="search-title">
                    Kết quả tìm kiếm cho: <span className="keyword-highlight">"{keyword}"</span>
                </h2>
                
                {results.length === 0 ? (
                    <div className="no-result-box">
                        <i className="fas fa-search no-result-icon"></i>
                        <h3>Rất tiếc, không tìm thấy sản phẩm nào!</h3>
                        <p style={{ color: '#777' }}>Hãy thử tìm kiếm bằng từ khóa khác hoặc xem danh mục sản phẩm.</p>
                        <Link to="/" className="btn-back">Về trang chủ</Link>
                    </div>
                ) : (
                    <div className="product-list">       
                        {results.map(p => (
                            <div key={p.productId} className="product-item">               
                                <Link to={`/product/${p.productId}`}>
                                    <img src={`/images/${p.image}`} alt={p.productName} />
                                </Link>
                                <div className="product-info">
                                    <h3>{p.productName}</h3>
                                    <span className="product-price">
                                        {p.price.toLocaleString('vi-VN')} VNĐ
                                    </span>
                                    <Link to={`/product/${p.productId}`} className="btn-view">
                                        Xem chi tiết
                                    </Link>
                                </div>
                            </div>
                        ))}
                    </div>
                )}
            </div>
        </section>
    );
};

export default SearchResults;