package com.devsuperior.dsCatalog.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.devsuperior.dsCatalog.dto.CategoryDTO;
import com.devsuperior.dsCatalog.entities.Category;
import com.devsuperior.dsCatalog.repositories.CategoryRepository;
import com.devsuperior.dsCatalog.services.exceptions.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//serviço
@Service
public class CategoryService {

    //injeção de dependencia(repository)
    @Autowired
    private CategoryRepository repository;

    @Transactional(readOnly = true)
    public List<CategoryDTO> findAll(){
        List<Category> list = repository.findAll();

        return list.stream().map(entity -> new CategoryDTO(entity)).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CategoryDTO findById(long id){
        Optional<Category> obj = repository.findById(id);

        Category entity = obj.orElseThrow(()-> new EntityNotFoundException("Entity not found"));

        return new CategoryDTO(entity);
    }

    @Transactional
	public CategoryDTO insert(CategoryDTO dto) {

        Category entity = new Category();
        entity.setName(dto.getName());
        entity=repository.save(entity);
		return new CategoryDTO(entity);
	}



}
