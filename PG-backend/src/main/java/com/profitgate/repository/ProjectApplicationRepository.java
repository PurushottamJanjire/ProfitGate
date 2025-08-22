package com.profitgate.repository;

import com.profitgate.entity.ProjectApplication;
import com.profitgate.entity.Project;
import com.profitgate.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProjectApplicationRepository extends JpaRepository<ProjectApplication, Integer> {
    List<ProjectApplication> findByProject(Project project);
    List<ProjectApplication> findByCompany(Company company);
}
