package com.profitgate.repository;

import com.profitgate.entity.PartnershipRequest;
import com.profitgate.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PartnershipRequestRepository extends JpaRepository<PartnershipRequest, Integer> {
    List<PartnershipRequest> findBySenderCompany(Company company);
    List<PartnershipRequest> findByReceiverCompany(Company company);
}
