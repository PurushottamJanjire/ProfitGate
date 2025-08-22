package com.profitgate.controller;

import com.profitgate.dto.CreatePartnershipRequest;
import com.profitgate.entity.Company;
import com.profitgate.entity.PartnershipRequest;
import com.profitgate.repository.CompanyRepository;
import com.profitgate.repository.PartnershipRequestRepository;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/partnerships")
public class PartnershipController {

    private final PartnershipRequestRepository requestRepo;
    private final CompanyRepository companyRepo;

    public PartnershipController(PartnershipRequestRepository requestRepo, CompanyRepository companyRepo) {
        this.requestRepo = requestRepo;
        this.companyRepo = companyRepo;
    }

    @PostMapping
    public PartnershipRequest create(@RequestBody @Valid CreatePartnershipRequest req) {
        Company sender = companyRepo.findById(req.senderCompanyId())
                .orElseThrow(() -> new NoSuchElementException("Sender company not found"));
        Company receiver = companyRepo.findById(req.receiverCompanyId())
                .orElseThrow(() -> new NoSuchElementException("Receiver company not found"));
        PartnershipRequest pr = new PartnershipRequest();
        pr.setSenderCompany(sender);
        pr.setReceiverCompany(receiver);
        pr.setMessage(req.message());
        pr.setStatus(PartnershipRequest.Status.pending);
        return requestRepo.save(pr);
    }

    @GetMapping("/inbox/{companyId}")
    public List<PartnershipRequest> inbox(@PathVariable Integer companyId) {
        Company c = companyRepo.findById(companyId).orElseThrow(() -> new NoSuchElementException("Company not found"));
        return requestRepo.findByReceiverCompany(c);
    }

    @GetMapping("/sent/{companyId}")
    public List<PartnershipRequest> sent(@PathVariable Integer companyId) {
        Company c = companyRepo.findById(companyId).orElseThrow(() -> new NoSuchElementException("Company not found"));
        return requestRepo.findBySenderCompany(c);
    }
}
