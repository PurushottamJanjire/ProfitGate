package com.profitgate.repository;

import com.profitgate.entity.Project;
import com.profitgate.entity.Company;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Integer> {
    Page<Project> findByCompany(Company company, Pageable pageable);
}
