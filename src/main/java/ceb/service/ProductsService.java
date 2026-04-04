package ceb.service;

import ceb.model.Products;
import ceb.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductsService {

    private static final String DEFAULT_IMAGE = "no-image.png";

    @Autowired
    private ProductsRepository repo;

    public List<Products> all() {
        return repo.findAll();
    }

    public Products get(int id) {
        return repo.findById(id);
    }

    public void add(Products p) {
        if (p.getImage() == null || p.getImage().isBlank()) {
            p.setImage(DEFAULT_IMAGE);
        }

        p.setActive(true); // 👉 Thêm dòng này để sản phẩm luôn hiển thị ra User

        repo.save(p);
    }

    public void edit(Products p) {
        if (p.getImage() == null || p.getImage().isBlank()) {
            p.setImage(DEFAULT_IMAGE);
        }
        repo.update(p);
    }

    public void delete(int id) {
        repo.delete(id);
    }
    public Products findById(Integer id) {
        return repo.findById(id);
    }
    public List<Products> search(String keyword) {
    return repo.search(keyword);
    }
    public List<Products> getProductsByCategory(int categoryId) {
    return repo.findByCategory(categoryId);
    }
    public List<Products> getByCategoryLimit(int categoryId, int limit) {
        return repo.getByCategoryLimit(categoryId, limit);
    }
}
