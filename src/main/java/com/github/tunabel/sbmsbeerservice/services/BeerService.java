package com.github.tunabel.sbmsbeerservice.services;

import com.github.tunabel.sbmsbeerservice.web.model.BeerDto;
import com.github.tunabel.sbmsbeerservice.web.model.BeerPagedList;
import com.github.tunabel.sbmsbeerservice.web.model.BeerStyleEnum;
import org.springframework.data.domain.PageRequest;

import java.util.UUID;

public interface BeerService {

    BeerDto getById(UUID beerID, Boolean showInventoryOnHand);

    BeerDto getByUpc(String upc, Boolean showInventoryOnHand);

    BeerDto updateBeer(UUID beerID, BeerDto beerDto);

    BeerDto saveNewBeer(BeerDto beerDto);

    BeerPagedList listBeers(String beerName, BeerStyleEnum beerStyle, PageRequest pageRequest, Boolean showInventoryOnHand);
}
