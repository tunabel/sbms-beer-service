package com.github.tunabel.sbmsbeerservice.web.controller;

import com.github.tunabel.sbmsbeerservice.web.model.BeerDto;
import com.github.tunabel.sbmsbeerservice.web.services.BeerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/beer")
public class BeerController {

    @Autowired
    BeerService beerService;

    @GetMapping("/{beerID}")
    public ResponseEntity<BeerDto> get(@Valid @PathVariable UUID beerID) {
        BeerDto dto = beerService.getById(beerID);
        return new ResponseEntity<>(new BeerDto(), HttpStatus.OK);
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
