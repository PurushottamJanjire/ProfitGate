package com.profitgate.controller;

import com.profitgate.entity.Industry;
import com.profitgate.repository.IndustryRepository;
import com.profitgate.service.IndustryService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/industries")
public class IndustryController {

    private final IndustryRepository repo;
    private  IndustryService industryService;
    public IndustryController(IndustryRepository repo) {
        this.repo = repo;
    }

    @PostMapping
    public Industry create(@RequestBody @Valid Industry industry) {
        return repo.save(industry);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        repo.deleteById(id);
    }


    @GetMapping
    public List<Industry> list() {
        return repo.findAll();
    }
}
