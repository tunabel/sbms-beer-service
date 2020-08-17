package com.github.tunabel.sbmsbeerservice.web.mapper;

import com.github.tunabel.sbmsbeerservice.domain.Beer;
import com.github.tunabel.sbmsbeerservice.web.model.BeerDto;
import com.github.tunabel.sbmsbeerservice.services.inventory.BeerInventoryService;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BeerMapperDecorator implements BeerMapper {
    private BeerInventoryService beerInventoryService;
    private BeerMapper mapper;

    @Autowired
    public void setBeerInventoryService(BeerInventoryService beerInventoryService) {
        this.beerInventoryService = beerInventoryService;
    }

    @Autowired
    public void setMapper(BeerMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public BeerDto mapBeerToDtoWithInv(Beer beer) {
        BeerDto dto = mapper.mapBeerToDtoWithInv(beer);
        dto.setQuantityOnHand(beerInventoryService.getOnhandInventory(beer.getId()));
        return dto;
    }

    @Override
    public Beer mapDtoToBeerWithInv(BeerDto beerDto) {
        return mapper.mapDtoToBeerWithInv(beerDto);
    }

    @Override
    public BeerDto mapBeerToDto(Beer beer) {
        return mapper.mapBeerToDtoWithInv(beer);
    }

    @Override
    public Beer mapDtoToBeer(BeerDto dto) {
        return mapper.mapDtoToBeer(dto);
    }
}