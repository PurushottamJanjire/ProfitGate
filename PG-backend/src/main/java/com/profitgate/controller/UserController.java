package com.profitgate.controller;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import com.profitgate.entity.User;
import com.profitgate.repository.UserRepository;
import com.profitgate.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserRepository repo;
    @Autowired
    private UserService userService;
    public UserController(UserRepository repo) {
        this.repo = repo;
    }

    @PostMapping
    public User create(@RequestBody @Valid User u) {
        u.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
        return repo.save(u);
    }

    @GetMapping
    public List<User> list() {
        return repo.findAll();
    }

    @DeleteMapping("/id/{id}")
    public boolean delete(@PathVariable("id") int id) {
        return userService.deleteUserById(id);

    }


}
