package com.devsuperior.dsCatalog.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import com.devsuperior.dsCatalog.dto.CategoryDTO;
import com.devsuperior.dsCatalog.dto.ProductDTO;
import com.devsuperior.dsCatalog.entities.Category;
import com.devsuperior.dsCatalog.entities.Product;
import com.devsuperior.dsCatalog.repositories.CategoryRepository;
import com.devsuperior.dsCatalog.repositories.ProductRepository;
import com.devsuperior.dsCatalog.services.exceptions.DataBaseException;
import com.devsuperior.dsCatalog.services.exceptions.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//serviço
@Service
public class ProductService {

    // injeção de dependencia(repository)
    @Autowired
    private ProductRepository repository;

    @Autowired
    private CategoryRepository CategoryRepository;

    @Transactional(readOnly = true)
    public Page<ProductDTO> findAll(PageRequest pageRequest) {
        Page<Product> list = repository.findAll(pageRequest);

        return list.map(entity -> new ProductDTO(entity));
    }

    @Transactional(readOnly = true)
    public ProductDTO findById(long id) {
        Optional<Product> obj = repository.findById(id);

        Product entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));

        return new ProductDTO(entity, entity.getCategories());
    }

    @Transactional
    public ProductDTO insert(ProductDTO dto) {

        Product entity = new Product();
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return new ProductDTO(entity);
    }

    @Transactional
    public ProductDTO update(long id, ProductDTO dto) {
        try {
            Product entity = repository.getOne(id);
            copyDtoToEntity(dto, entity);
            entity = repository.save(entity);
            return new ProductDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id not found: " + id);
        }
    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Id not found: " + id);
        } catch (DataIntegrityViolationException e) {
            throw new DataBaseException("Integrity violation");
        }
    }

    private void copyDtoToEntity(ProductDTO dto, Product entity) {
        entity.setName(dto.getName());
        entity.setPrice(dto.getPrice());
        entity.setDescription(dto.getDescription());
        entity.setDate(dto.getDate());
        entity.setImgUrl(dto.getImgUrl());

        entity.getCategories().clear();
        for (CategoryDTO catDTO : dto.getCategories()) {
            Category category = CategoryRepository.getOne(catDTO.getId());
            entity.getCategories().add(category);
        }
    }

}
