package com.github.tunabel.sbmsbeerservice.web.mapper;

import com.github.tunabel.sbmsbeerservice.domain.Beer;
import com.github.tunabel.sbmsbeerservice.web.model.BeerDto;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

@Mapper(uses = {DateMapper.class})
@DecoratedWith(BeerMapperDecorator.class)
public interface BeerMapper {

    BeerDto mapBeerToDtoWithInv(Beer beer);
    Beer mapDtoToBeerWithInv(BeerDto dto);

    BeerDto mapBeerToDto(Beer beer);
    Beer mapDtoToBeer(BeerDto dto);
}
