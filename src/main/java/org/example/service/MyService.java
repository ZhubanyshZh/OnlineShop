package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.dto.DTO;
import org.example.dto.UserDto;
import org.example.entity.CustomEntity;
import org.example.entity.Product;
import org.example.repository.ProductRepository;
import org.example.repository.UserRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
@Service
@RequiredArgsConstructor
public abstract class MyService {
    protected final ProductRepository productRepository;
    protected final UserRepository userRepository;
    protected final LoggedUserManagementService loggedUserManagementService;

    public <T> boolean deleteById(Long id, JpaRepository<T, Long> repository) {
        try {
            repository.deleteById(id);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }
        return true;
    }

    public <T> boolean save(DTO dto, JpaRepository<T, Long> repository, Model model){
        if(!haveSuchTuple(dto, model)
                && validation(dto, model)){
            T entity = (T) dtoToEntity(dto);
            try {
                repository.save(entity);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                return false;
            }
            return true;

        }

        return false;
    }


    abstract CustomEntity dtoToEntity(DTO dto);

    boolean validation(DTO dto, Model model){
        return true;
    }

    boolean haveSuchTuple(DTO dto, Model model){
        return false;
    }
}
