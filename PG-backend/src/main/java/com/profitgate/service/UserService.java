package com.profitgate.service;

import com.profitgate.entity.User;
import com.profitgate.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Component
public class UserService {

    @Autowired
    private UserRepository repository;

    public boolean deleteUserById(Integer id) {
         Optional<User> u=repository.findById(id);
                    repository.deleteById(id);
       return true;
    }

}
