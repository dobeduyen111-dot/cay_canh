package ceb.controller;

import ceb.model.Categories;
import ceb.service.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoriesController {

    @Autowired private CategoriesService service;

    @GetMapping
    public List<Categories> all() { return service.all(); }

    @GetMapping("/{id}")
    public Categories get(@PathVariable int id) { return service.get(id); }

    @PostMapping
    public Object create(@RequestBody Categories c) { service.save(c); return java.util.Map.of("status","ok"); }

    @PutMapping("/{id}")
    public Object update(@PathVariable int id, @RequestBody Categories c) { c.setCategoryId(id); service.save(c); return java.util.Map.of("status","ok"); }

    @DeleteMapping("/{id}")
    public Object delete(@PathVariable int id) { service.delete(id); return java.util.Map.of("status","ok"); }
}
