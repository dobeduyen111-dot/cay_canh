package ceb.model;

import java.util.Date;

public class Users {
    private Integer UserId;
    private String FullName;
    private String Email;
    private byte[] Password;
    private String Phone;
    private String Address;
    private String Role;
    private Date CreatedAt;

    public Users() {}

    public Users(Integer userId, String fullName, String email, byte[] password,
                 String phone, String address, String role, Date createdAt) {
        UserId = userId;
        FullName = fullName;
        Email = email;
        Password = password;
        Phone = phone;
        Address = address;
        Role = role;
        CreatedAt = createdAt;
    }
    public static byte[] hashPassword(String raw) {
    try {
        java.security.MessageDigest md = java.security.MessageDigest.getInstance("SHA-512");
        return md.digest(raw.getBytes(java.nio.charset.StandardCharsets.UTF_8));
    } catch (Exception e) {
        throw new RuntimeException(e);
    }
    }
    public Integer getUserId() { return UserId; }
    public void setUserId(Integer userId) { UserId = userId; }

    public String getFullName() { return FullName; }
    public void setFullName(String fullName) { FullName = fullName; }

    public String getEmail() { return Email; }
    public void setEmail(String email) { Email = email; }

    public byte[] getPassword() { return Password; }
    public void setPassword(byte[] password) { Password = password; }

    public String getPhone() { return Phone; }
    public void setPhone(String phone) { Phone = phone; }

    public String getAddress() { return Address; }
    public void setAddress(String address) { Address = address; }

    public String getRole() { return Role; }
    public void setRole(String role) { Role = role; }

    public Date getCreatedAt() { return CreatedAt; }
    public void setCreatedAt(Date createdAt) { CreatedAt = createdAt; }
}
