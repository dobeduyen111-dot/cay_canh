package ceb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ceb.model.Categories;
import ceb.service.CategoriesService;

@RestController
@RequestMapping("/api/categories")
public class CategoriesController {

    @Autowired
    private CategoriesService service;

    // ✅ Lấy tất cả
    @GetMapping
    public ResponseEntity<List<Categories>> getAll() {
        return ResponseEntity.ok(service.all());
    }

    // ✅ Lấy theo id
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable int id) {
        Categories c = service.get(id);

        if (c == null) {
            return ResponseEntity.status(404).body("Không tìm thấy category");
        }

        return ResponseEntity.ok(c);
    }

    // ✅ Tạo mới
    @PostMapping
    public ResponseEntity<?> create(@RequestBody Categories c) {
        service.save(c);
        return ResponseEntity.status(201).body("Tạo thành công");
    }

    // ✅ Update
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody Categories c) {
        c.setCategoryId(id);
        service.save(c);
        return ResponseEntity.ok("Cập nhật thành công");
    }

    // ✅ Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        service.delete(id);
        return ResponseEntity.ok("Xóa thành công");
    }
}