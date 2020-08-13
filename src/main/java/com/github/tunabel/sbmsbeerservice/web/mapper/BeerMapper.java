package com.github.tunabel.sbmsbeerservice.web.mapper;

import com.github.tunabel.sbmsbeerservice.domain.Beer;
import com.github.tunabel.sbmsbeerservice.web.model.BeerDto;
import org.mapstruct.Mapper;

@Mapper(uses = {DateMapper.class})
public interface BeerMapper {

    BeerDto mapBeerToDto(Beer beer);

    Beer mapDtoToBeer(BeerDto dto);
}
