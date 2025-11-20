package ceb.repository;

import ceb.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional; 
import java.util.stream.Collectors;

@Repository
public class UsersRepository {

    @Autowired
    private JdbcTemplate jdbc;
    
    // ĐỊNH NGHĨA EMAIL ADMIN CỦA BẠN
    private static final String ADMIN_EMAIL = "1250080014@sv.hcmunre.edu.vn";

    private RowMapper<Users> mapper = new RowMapper<Users>() {
        @Override
        public Users mapRow(ResultSet rs, int rowNum) throws SQLException {
            Users u = new Users();
            u.setUserId(rs.getInt("UserId"));
            u.setFullName(rs.getString("FullName"));
            u.setEmail(rs.getString("Email"));
            // Lấy dữ liệu Password dưới dạng byte[] (cho VARBINARY)
            u.setPassword(rs.getBytes("Password")); 
            u.setPhone(rs.getString("Phone"));
            u.setAddress(rs.getString("Address"));
            u.setRole(rs.getString("Role"));
            u.setCreatedAt(rs.getDate("CreatedAt"));
            return u;
        }
    };

    // Phương thức create: Nhận hash mật khẩu dưới dạng byte[]
    // Lưu ý: Bạn đang truyền String passwordHashHex (cần kiểm tra lại SP hoặc cấu trúc SQL)
    // Để khớp với code hiện tại: ta sẽ giả định passwordHashHex là String HEX hash, và DB tự chuyển.
    // Tốt nhất là dùng HASHBYTES trong SQL như sau:
    public int create(Users u, String rawPassword) {
        String sql = "INSERT INTO Users (FullName, Email, Password, Phone, Address, Role) VALUES (?, ?, HASHBYTES('SHA2_512', ?), ?, ?, ?)";
        // Truyền mật khẩu thô và để SQL HASH nó
        return jdbc.update(sql, u.getFullName(), u.getEmail(), rawPassword, u.getPhone(), u.getAddress(), u.getRole() == null ? "Customer" : u.getRole());
    }

    // Sửa đổi findByEmail để trả về Optional<Users>
    public Optional<Users> findByEmail(String email) {
        String sql = "SELECT * FROM Users WHERE Email = ?";
        try {
            Users user = jdbc.queryForObject(sql, mapper, email);
            return Optional.ofNullable(user);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }
    
    // Phương thức findByEmail trả về Users (để tương thích với UsersService cũ)
    public Users findByEmailLegacy(String email) {
        return findByEmail(email).orElse(null);
    }
    
    // PHƯƠNG THỨC BỔ SUNG CHO OAUTH2 VÀ PHÂN QUYỀN
    public String saveOrUpdateGoogleUser(String subId, String email, String givenName, String familyName, String name) {
        Optional<Users> existingUser = findByEmail(email);
        
        if (existingUser.isEmpty()) {
            Users newUser = new Users();
            newUser.setFullName(name);
            newUser.setEmail(email);
            String tempPassword = subId; // Dùng subId làm mật khẩu thô tạm thời
            
            // LOGIC PHÂN QUYỀN
            String role = email.equals(ADMIN_EMAIL) ? "Admin" : "Customer"; 
            newUser.setRole(role); 
            
            // INSERT user mới vào DB với Role và mật khẩu được HASH bằng HASHBYTES trong SQL
            String insertSql = "INSERT INTO Users (FullName, Email, Password, Role) VALUES (?, ?, HASHBYTES('SHA2_512', ?), ?)";
            jdbc.update(insertSql, newUser.getFullName(), newUser.getEmail(), tempPassword, newUser.getRole());
            
            return role;
        } else {
            // Trả về Role đã lưu trong DB
            return existingUser.get().getRole();
        }
    }
    
    // Giữ nguyên các phương thức khác
    public Users findById(int id) {
        String sql = "SELECT * FROM Users WHERE UserId = ?";
        List<Users> list = jdbc.query(sql, new Object[]{id}, mapper);
        return list.isEmpty() ? null : list.get(0);
    }

    public List<Users> findAll() {
        return jdbc.query("SELECT * FROM Users", mapper);
    }
}