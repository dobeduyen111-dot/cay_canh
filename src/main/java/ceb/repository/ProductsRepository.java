package ceb.repository;

import ceb.model.Products;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ProductsRepository {

    @Autowired
    private JdbcTemplate jdbc;

    private RowMapper<Products> mapper = new RowMapper<Products>() {
        @Override
        public Products mapRow(ResultSet rs, int rowNum) throws SQLException {
            Products p = new Products();
            p.setProductId(rs.getInt("ProductId"));
            p.setCategoryId(rs.getInt("CategoryId"));
            p.setProductName(rs.getString("ProductName"));
            p.setDescription(rs.getString("Description"));
            p.setCareGuide(rs.getString("CareGuide"));
            p.setPrice(rs.getDouble("Price"));
            p.setStock(rs.getInt("Stock"));
            p.setImage(rs.getString("Image"));
            p.setisActive(rs.getBoolean("IsActive"));
            p.setCreatedAt(rs.getDate("CreatedAt"));
            return p;
        }
    };

    public List<Products> findAll() {
        return jdbc.query("SELECT * FROM Products", mapper);
    }

    public Products findById(int id) {
        List<Products> list = jdbc.query("SELECT * FROM Products WHERE ProductId = ?", new Object[]{id}, mapper);
        return list.isEmpty() ? null : list.get(0);
    }

    public List<Products> findByCategoryId(int categoryId) {
        return jdbc.query("SELECT * FROM Products WHERE CategoryId = ?", new Object[]{categoryId}, mapper);
    }

    public int save(Products p) {
        if (p.getProductId() == null) {
            String sql = "INSERT INTO Products (CategoryId, ProductName, Description, CareGuide, Price, Stock, Image, IsActive) VALUES (?,?,?,?,?,?,?,?)";
            return jdbc.update(sql, p.getCategoryId(), p.getProductName(), p.getDescription(), p.getCareGuide(),
                    p.getPrice(), p.getStock(), p.getImage(), p.isActive());
        } else {
            String sql = "UPDATE Products SET CategoryId=?, ProductName=?, Description=?, CareGuide=?, Price=?, Stock=?, Image=?, IsActive=? WHERE ProductId=?";
            return jdbc.update(sql, p.getCategoryId(), p.getProductName(), p.getDescription(), p.getCareGuide(),
                    p.getPrice(), p.getStock(), p.getImage(), p.isActive(), p.getProductId());
        }
    }

    public int delete(int id) {
        return jdbc.update("DELETE FROM Products WHERE ProductId = ?", id);
    }
}
