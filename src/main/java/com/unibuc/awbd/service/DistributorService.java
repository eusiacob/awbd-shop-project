package com.unibuc.awbd.service;

import com.unibuc.awbd.model.Distributor;
import java.util.List;

public interface DistributorService {
    List<Distributor> findAll();
    List<Distributor> getDistributorsByIds(List<Long> distributorIds);
}