package ceb.repository;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ceb.model.Users;

@Repository
public class UserRepository {

    @Autowired
    private JdbcTemplate jdbc;

    public Users findByEmail(String email) {
        String sql = "SELECT * FROM Users WHERE Email = ?";

        List<Users> list = jdbc.query(sql, (rs, rowNum) -> {
            Users u = new Users();
            u.setUserId(rs.getInt("UserId"));
            u.setFullName(rs.getString("FullName"));
            u.setEmail(rs.getString("Email"));
            u.setPassword(rs.getString("Password"));
            u.setPhone(rs.getString("Phone"));
            u.setAddress(rs.getString("Address"));
            u.setRole(rs.getString("Role"));
            u.setEnabled(rs.getBoolean("Enabled"));
            if (rs.getTimestamp("CreatedAt") != null) {
                u.setCreatedAt(rs.getTimestamp("CreatedAt").toLocalDateTime());
            }
            return u;
        }, email); 

        return list.isEmpty() ? null : list.get(0);
    }

    public int save(Users user) {
        String sql = """
            INSERT INTO Users (FullName, Email, Password, Phone, Address, Role, Enabled)
            VALUES (?, ?, ?, ?, ?, ?, ?)
        """;

        return jdbc.update(sql,
            user.getFullName(),
            user.getEmail(),
            user.getPassword(),
            user.getPhone(),
            user.getAddress(),
            user.getRole(),
            user.isEnabled()
        );
    }

    public Integer getUserIdByEmail(String email) {
        try {
            String sql = "SELECT UserId FROM Users WHERE Email = ?";
            return jdbc.queryForObject(sql, Integer.class, email);
        } catch (Exception e) {
            return null;
        }
    }
    public List<Users> findAll() {
        String sql = "SELECT * FROM Users";
        return jdbc.query(sql, (rs, rowNum) -> {
            Users u = new Users();
            u.setUserId(rs.getInt("UserId"));
            u.setFullName(rs.getString("FullName"));
            u.setEmail(rs.getString("Email"));
            u.setPhone(rs.getString("Phone"));
            u.setAddress(rs.getString("Address"));
            u.setRole(rs.getString("Role"));
            u.setEnabled(rs.getBoolean("Enabled"));
            return u;
        });
    }
    public void updatePassword(int userId, String newPassword) {
        String sql = "UPDATE Users SET Password = ? WHERE UserId = ?";
        jdbc.update(sql, newPassword, userId);
    }
    // Hàm xóa theo ID
    public int deleteById(int id) {
        String sql = "DELETE FROM Users WHERE UserId = ?";
        return jdbc.update(sql, id);
    }
}