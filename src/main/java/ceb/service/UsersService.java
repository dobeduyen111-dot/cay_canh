package ceb.service;

import ceb.model.Users;
import ceb.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.util.Arrays;

@Service
public class UsersService {

    @Autowired
    private UsersRepository userRepo;

    public String register(Users u, String rawPassword) {
        // Thay đổi findByEmail() để dùng phương thức trả về Users cũ
        if (userRepo.findByEmailLegacy(u.getEmail()) != null) {
            return "EMAIL_EXISTS";
        }
        // Thay vì truyền hex, ta truyền mật khẩu thô để Repository dùng HASHBYTES trong SQL
        userRepo.create(u, rawPassword);
        return "OK";
    }

    public Users login(String email, String rawPassword) {
        Users u = userRepo.findByEmailLegacy(email);
        if (u == null) return null;
        
        // 1. Hash mật khẩu thô nhập vào bằng SHA-512 (Java)
        byte[] rawHashBytes = sha512Bytes(rawPassword);
        
        // 2. Lấy hash đã lưu trong DB (dưới dạng byte[])
        byte[] storedHashBytes = u.getPassword();
        
        // 3. So sánh hai mảng byte[]
        if (Arrays.equals(rawHashBytes, storedHashBytes)) return u;
        
        return null;
    }

    // Phương thức HASH trả về byte[] (để so sánh với DB)
    private byte[] sha512Bytes(String s) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            return md.digest(s.getBytes());
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
    
    // (Bỏ qua sha512Hex vì không dùng để so sánh nữa)
}