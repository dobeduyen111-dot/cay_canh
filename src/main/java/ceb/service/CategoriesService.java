package ceb.service;

import ceb.model.Categories;
import ceb.repository.CategoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriesService {

    @Autowired
    private CategoriesRepository repo;

    public List<Categories> all() { return repo.findAll(); }
    public Categories get(int id) { return repo.findById(id); }
    public void save(Categories c) { repo.save(c); }
    public void delete(int id) { repo.delete(id); }
}
