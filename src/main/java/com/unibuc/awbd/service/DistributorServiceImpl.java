package com.unibuc.awbd.service;

import com.unibuc.awbd.model.Distributor;
import com.unibuc.awbd.repository.DistributorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DistributorServiceImpl implements DistributorService {

    private final DistributorRepository distributorRepository;

    public DistributorServiceImpl(DistributorRepository distributorRepository) {
        this.distributorRepository = distributorRepository;
    }

    @Override
    public List<Distributor> findAll() {
        return distributorRepository.findAll();
    }

    public List<Distributor> getDistributorsByIds(List<Long> distributorIds) {
        return distributorRepository.findAllById(distributorIds);
    }
}
