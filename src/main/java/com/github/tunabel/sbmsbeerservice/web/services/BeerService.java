package com.github.tunabel.sbmsbeerservice.web.services;

import com.github.tunabel.sbmsbeerservice.web.model.BeerDto;

import java.util.UUID;

public interface BeerService {

    BeerDto getById(UUID beerID);

    BeerDto updateBeer(UUID beerID, BeerDto beerDto);

    BeerDto saveNewBeer(BeerDto beerDto);
}
