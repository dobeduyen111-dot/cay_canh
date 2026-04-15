package ceb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ceb.domain.entity.Users;
import ceb.service.service.UsersService;

@RestController
@RequestMapping("/api/user")
public class UsersController {

    @Autowired
    private UsersService usersService;

    


    // ✅ Lấy tất cả sản phẩm
    @GetMapping
    public ResponseEntity<List<Users>> getAlls() {
        return ResponseEntity.ok(usersService.findAlls());
    }
    @GetMapping("/email")
    public ResponseEntity<Users> getByEmail(@RequestParam String email) {
        return ResponseEntity.ok(usersService.getUsersByEmail(email));
    }

    
}