package com.github.tunabel.sbmsbeerservice.web.controller;

import com.github.tunabel.sbmsbeerservice.web.model.BeerDto;
import com.github.tunabel.sbmsbeerservice.web.model.BeerPagedList;
import com.github.tunabel.sbmsbeerservice.web.model.BeerStyleEnum;
import com.github.tunabel.sbmsbeerservice.services.BeerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/beer")
public class BeerController {

    private static final Integer DEFAULT_PAGE_NUMBER = 0;
    private static final Integer DEFAULT_PAGE_SIZE = 25;

    private final BeerService beerService;

    @GetMapping(value = "/all", produces = {"application/json"})
    public ResponseEntity<BeerPagedList> listBeers(@RequestParam(value = "pageNumber", required = false) Integer pageNumber,
                                                   @RequestParam(value = "pageSize", required = false) Integer pageSize,
                                                   @RequestParam(value = "beerName", required = false) String beerName,
                                                   @RequestParam(value = "beerStyle", required = false) BeerStyleEnum beerStyle,
                                                   @RequestParam(value = "showInventoryOnHand", required = false) Boolean showInventoryOnHand) {
        if (showInventoryOnHand == null) {
            showInventoryOnHand = false;
        }


        if (pageNumber == null || pageNumber < 0) {
            pageNumber = DEFAULT_PAGE_NUMBER;
        }

        if (pageSize == null || pageSize < 0) {
            pageSize = DEFAULT_PAGE_SIZE;
        }

        BeerPagedList beerList = beerService.listBeers(beerName, beerStyle, PageRequest.of(pageNumber, pageSize), showInventoryOnHand);

        return new ResponseEntity<>(beerList, HttpStatus.OK);
    }



    @GetMapping("/{beerID}")
    public ResponseEntity<BeerDto> get(@Valid @PathVariable UUID beerID,
                                       @RequestParam(value = "showInventoryOnHand", required = false) Boolean showInventoryOnHand) {
        if (showInventoryOnHand == null) {
            showInventoryOnHand = false;
        }
        BeerDto dto = beerService.getById(beerID, showInventoryOnHand);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PutMapping("/{beerID}")
    public ResponseEntity update(@PathVariable UUID beerID, @Valid @RequestBody BeerDto beerDto) {
        beerService.updateBeer(beerID, beerDto);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PostMapping
    public ResponseEntity create(@RequestBody BeerDto beerDto) {
        beerService.saveNewBeer(beerDto);
        return new ResponseEntity(HttpStatus.CREATED);
    }
}
