package ceb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ceb.model.Products;
import ceb.service.ProductsService;

@RestController
@RequestMapping("/api/user/products")
public class UserController {

    @Autowired
    private ProductsService service;

    // ✅ Lấy tất cả sản phẩm
    @GetMapping
    public ResponseEntity<List<Products>> getAll() {
        return ResponseEntity.ok(service.all());
    }

    // ✅ Tìm kiếm
    @GetMapping("/search")
    public ResponseEntity<List<Products>> search(@RequestParam String keyword) {
        return ResponseEntity.ok(service.search(keyword));
    }

    // ✅ Chi tiết sản phẩm
    @GetMapping("/{id}")
    public ResponseEntity<?> getDetail(@PathVariable int id) {
        Products p = service.get(id);

        if (p == null) {
            return ResponseEntity.status(404).body("Không tìm thấy sản phẩm");
        }

        return ResponseEntity.ok(p);
    }

    // ✅ Lấy theo category
    @GetMapping("/category/{id}")
    public ResponseEntity<List<Products>> getByCategory(@PathVariable int id) {
        return ResponseEntity.ok(service.getProductsByCategory(id));
    }
}