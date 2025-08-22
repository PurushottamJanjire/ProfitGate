package com.profitgate.repository;

import com.profitgate.entity.Company;
import com.profitgate.entity.Industry;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Integer> {
    Page<Company> findByIndustry(Industry industry, Pageable pageable);
    Page<Company> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
