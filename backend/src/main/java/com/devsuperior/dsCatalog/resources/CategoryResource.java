package com.devsuperior.dsCatalog.resources;

import java.util.ArrayList;
import java.util.List;

import com.devsuperior.dsCatalog.entities.Category;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/categories")
public class CategoryResource {
    // ResponseEntity - encapsula resposta HTTP
    @GetMapping
    public ResponseEntity<List<Category>> findAll() {
        List<Category> list = new ArrayList<>();
        list.add(new Category(1L, "Books"));
        list.add(new Category(2L, "Electronics"));
        //Protocolo 200 - retorna no corpo uma lista
        return ResponseEntity.ok().body(list);
    }
}
