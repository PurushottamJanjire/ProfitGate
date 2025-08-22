package com.profitgate.service;

import com.profitgate.repository.IndustryRepository;
import com.profitgate.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class IndustryService {

    @Autowired
    private IndustryRepository industryRepository;
    public boolean deleteIndustry(int id){
        industryRepository.deleteById(id);
        return true;
    }
}
