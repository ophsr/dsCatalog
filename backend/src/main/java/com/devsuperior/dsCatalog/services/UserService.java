package com.devsuperior.dsCatalog.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import com.devsuperior.dsCatalog.dto.RoleDTO;
import com.devsuperior.dsCatalog.dto.UserDTO;
import com.devsuperior.dsCatalog.dto.UserInsertDTO;
import com.devsuperior.dsCatalog.dto.UserUpdateDTO;
import com.devsuperior.dsCatalog.entities.Role;
import com.devsuperior.dsCatalog.entities.User;
import com.devsuperior.dsCatalog.repositories.RoleRepository;
import com.devsuperior.dsCatalog.repositories.UserRepository;
import com.devsuperior.dsCatalog.services.exceptions.DataBaseException;
import com.devsuperior.dsCatalog.services.exceptions.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//serviço
@Service
public class UserService {

    // injeção de dependencia(userRepository)
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public Page<UserDTO> findAll(PageRequest pageRequest) {
        Page<User> list = userRepository.findAll(pageRequest);
        return list.map(entity -> new UserDTO(entity));
    }

    @Transactional(readOnly = true)
    public UserDTO findById(long id) {
        Optional<User> obj = userRepository.findById(id);

        User entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));

        return new UserDTO(entity);
    }

    @Transactional
    public UserDTO insert(UserInsertDTO dto) {

        User entity = new User();
        copyDtoToEntity(dto, entity);
        entity.setPassword(passwordEncoder.encode(dto.getPassword()));
        entity = userRepository.save(entity);
        return new UserDTO(entity);
    }

    @Transactional
    public UserDTO update(long id, UserUpdateDTO dto) {
        try {
            User entity = userRepository.getOne(id);
            copyDtoToEntity(dto, entity);
            entity = userRepository.save(entity);
            return new UserDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id not found: " + id);
        }
    }

    public void delete(Long id) {
        try {
            userRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Id not found: " + id);
        } catch (DataIntegrityViolationException e) {
            throw new DataBaseException("Integrity violation");
        }
    }

    private void copyDtoToEntity(UserDTO dto, User entity) {
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setEmail(dto.getEmail());

        entity.getRoles().clear();
        for (RoleDTO roleDTO : dto.getRoles()) {
            Role role = roleRepository.getOne(roleDTO.getId());
            entity.getRoles().add(role);
        }
    }

}
