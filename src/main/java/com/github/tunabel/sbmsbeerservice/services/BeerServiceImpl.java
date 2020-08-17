package com.github.tunabel.sbmsbeerservice.services;

import com.github.tunabel.sbmsbeerservice.domain.Beer;
import com.github.tunabel.sbmsbeerservice.repositories.BeerRepository;
import com.github.tunabel.sbmsbeerservice.web.controller.NotFoundException;
import com.github.tunabel.sbmsbeerservice.web.mapper.BeerMapper;
import com.github.tunabel.sbmsbeerservice.web.model.BeerDto;
import com.github.tunabel.sbmsbeerservice.web.model.BeerPagedList;
import com.github.tunabel.sbmsbeerservice.web.model.BeerStyleEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BeerServiceImpl implements BeerService {
    private final BeerRepository beerRepository;
    private final BeerMapper beerMapper;

    @Override
    public BeerDto getById(UUID beerID, Boolean showInventoryOnHand) {
        if (!showInventoryOnHand) {
            return beerMapper.mapBeerToDto(beerRepository.findById(beerID).orElseThrow(NotFoundException::new));
        }
        return beerMapper.mapBeerToDtoWithInv(beerRepository.findById(beerID).orElseThrow(NotFoundException::new));
    }

    @Override
    public BeerDto updateBeer(UUID beerID, BeerDto beerDto) {
        Beer beer = beerRepository.findById(beerID).orElseThrow(NotFoundException::new);

        beer.setBeerName(beerDto.getBeerName());
        beer.setBeerStyle(beerDto.getBeerStyle().name());
        beer.setPrice(beerDto.getPrice());
        beer.setUpc(beerDto.getUpc());

        return beerMapper.mapBeerToDtoWithInv(beerRepository.save(beer));

    }

    @Override
    public BeerDto saveNewBeer(BeerDto beerDto) {
        return beerMapper.mapBeerToDtoWithInv(beerRepository.save(beerMapper.mapDtoToBeerWithInv(beerDto)));
    }

    @Override
    public BeerPagedList listBeers(String beerName, BeerStyleEnum beerStyle, PageRequest pageRequest, Boolean showInventoryOnHand) {
        BeerPagedList beerPagedList;
        Page<Beer> beerPage;

        if (!StringUtils.isEmpty(beerName) && !StringUtils.isEmpty(beerStyle)) {
            //search both
            beerPage = beerRepository.findAllByBeerNameAndBeerStyle(beerName, beerStyle, pageRequest);
        } else if (!StringUtils.isEmpty(beerName) && StringUtils.isEmpty(beerStyle)) {
            beerPage = beerRepository.findAllByBeerName(beerName, pageRequest);
        } else if (StringUtils.isEmpty(beerName) && !StringUtils.isEmpty(beerStyle)) {
            beerPage = beerRepository.findAllByBeerStyle(beerStyle, pageRequest);
        } else {
            beerPage = beerRepository.findAll(pageRequest);
        }

        beerPagedList = new BeerPagedList(beerPage
                .getContent()
                .stream()
                .map(showInventoryOnHand ? beerMapper::mapBeerToDtoWithInv : beerMapper::mapBeerToDto )
                .collect(Collectors.toList()),
                PageRequest.of(beerPage.getPageable().getPageNumber(), beerPage.getPageable().getPageSize()),
                beerPage.getTotalElements()
        );

        return beerPagedList;
    }
}
