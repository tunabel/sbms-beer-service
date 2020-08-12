package com.github.tunabel.sbmsbeerservice.web.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BeerDto {
    private UUID beerID;

    private OffsetDateTime createdDate;
    private OffsetDateTime modifiedDate;

    private String beerName;
    private BeerStyleEnum beerStyle;
    private long upc;
}
