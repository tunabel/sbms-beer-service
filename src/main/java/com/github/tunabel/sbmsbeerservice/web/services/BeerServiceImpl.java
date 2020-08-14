package com.github.tunabel.sbmsbeerservice.web.services;

import com.github.tunabel.sbmsbeerservice.domain.Beer;
import com.github.tunabel.sbmsbeerservice.repositories.BeerRepository;
import com.github.tunabel.sbmsbeerservice.web.controller.NotFoundException;
import com.github.tunabel.sbmsbeerservice.web.mapper.BeerMapper;
import com.github.tunabel.sbmsbeerservice.web.model.BeerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service("BeerService")
public class BeerServiceImpl implements BeerService {

    @Autowired
    BeerRepository beerRepository;
    BeerMapper beerMapper;

    @Override
    public BeerDto getById(UUID beerID) {
        return beerMapper.mapBeerToDto(beerRepository.findById(beerID).orElseThrow(NotFoundException::new));
    }

    @Override
    public BeerDto updateBeer(UUID beerID, BeerDto beerDto) {
        Beer beer = beerRepository.findById(beerID).orElseThrow(NotFoundException::new);

        beer.setBeerName(beerDto.getBeerName());
        beer.setBeerStyle(beerDto.getBeerStyle().name());
        beer.setPrice(beerDto.getPrice());
        beer.setUpc(beerDto.getUpc());

        return beerMapper.mapBeerToDto(beerRepository.save(beer));

    }

    @Override
    public BeerDto saveNewBeer(BeerDto beerDto) {
        return beerMapper.mapBeerToDto(beerRepository.save(beerMapper.mapDtoToBeer(beerDto)));
    }
}
