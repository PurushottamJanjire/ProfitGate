package com.profitgate.controller;

import com.profitgate.dto.CreateCompanyRequest;
import com.profitgate.entity.Company;
import com.profitgate.entity.Industry;
import com.profitgate.entity.User;
import com.profitgate.repository.CompanyRepository;
import com.profitgate.repository.IndustryRepository;
import com.profitgate.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {

    private final CompanyRepository companyRepo;
    private final IndustryRepository industryRepo;
    private final UserRepository userRepo;

    public CompanyController(CompanyRepository companyRepo, IndustryRepository industryRepo, UserRepository userRepo) {
        this.companyRepo = companyRepo;
        this.industryRepo = industryRepo;
        this.userRepo = userRepo;
    }

    @PostMapping
    public Company create(@RequestBody @Valid CreateCompanyRequest req) {
        User owner = userRepo.findById(req.ownerUserId())
                .orElseThrow(() -> new NoSuchElementException("User not found"));
        Industry industry = industryRepo.findById(req.industryId())
                .orElseThrow(() -> new NoSuchElementException("Industry not found"));

        Company c = new Company();
        c.setName(req.name());
        c.setIndustry(industry);
        c.setLocation(req.location());
        c.setDescription(req.description());
        c.setOwner(owner);
        return companyRepo.save(c);
    }

    @GetMapping
    public Page<Company> list(@RequestParam(defaultValue = "0") int page,
                              @RequestParam(defaultValue = "10") int size,
                              @RequestParam(required = false) String q) {
        PageRequest pr = PageRequest.of(page, size);
        if (q != null && !q.isBlank()) {
            return companyRepo.findByNameContainingIgnoreCase(q, pr);
        }
        return companyRepo.findAll(pr);
    }

    @GetMapping("/{id}")
    public Company get(@PathVariable Integer id) {
        return companyRepo.findById(id).orElseThrow(() -> new NoSuchElementException("Company not found"));
    }
}
