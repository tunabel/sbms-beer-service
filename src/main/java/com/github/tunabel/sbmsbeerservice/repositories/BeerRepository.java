package com.github.tunabel.sbmsbeerservice.repositories;

import com.github.tunabel.sbmsbeerservice.domain.Beer;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface BeerRepository extends PagingAndSortingRepository<Beer, UUID> {
    
}
