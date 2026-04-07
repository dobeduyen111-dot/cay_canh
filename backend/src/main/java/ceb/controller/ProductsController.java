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

import ceb.model.Products;
import ceb.service.CategoriesService;
import ceb.service.ProductsService;

@RestController
@RequestMapping("/api/products")
public class ProductsController {

    @Autowired
    private ProductsService service;

    @Autowired
    private CategoriesService categoryService;

    // ✅ Lấy tất cả sản phẩm
    @GetMapping
    public ResponseEntity<List<Products>> getAll() {
        return ResponseEntity.ok(service.all());
    }

    // ✅ Lấy chi tiết sản phẩm
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable int id) {
        Products p = service.get(id);

        if (p == null) {
            return ResponseEntity.status(404).body("Không tìm thấy sản phẩm");
        }

        return ResponseEntity.ok(p);
    }

    // ✅ Thêm sản phẩm
    @PostMapping
    public ResponseEntity<?> create(@RequestBody Products product) {

        if (product.getCategoryId() == 0) {
            return ResponseEntity.badRequest().body("Chưa chọn category");
        }

        service.add(product);
        return ResponseEntity.status(201).body("Thêm sản phẩm thành công");
    }

    // ✅ Cập nhật sản phẩm
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable int id,
                                    @RequestBody Products product) {

        product.setProductId(id);
        service.edit(product);

        return ResponseEntity.ok("Cập nhật thành công");
    }

    // ✅ Xóa sản phẩm
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        service.delete(id);
        return ResponseEntity.ok("Xóa thành công");
    }

    // ✅ Lấy danh sách category (để dùng dropdown)
    @GetMapping("/categories")
    public ResponseEntity<?> getCategories() {
        return ResponseEntity.ok(categoryService.all());
    }
}