package com.github.tunabel.sbmsbeerservice.web.controller;

import com.github.tunabel.sbmsbeerservice.web.model.BeerDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/beer")
public class BeerController {

    @GetMapping("/{beerID}")
    public ResponseEntity<BeerDto> get(@PathVariable UUID beerID) {
        return new ResponseEntity<>(new BeerDto(), HttpStatus.OK);
    }

    @PutMapping("/{beerID}")
    public ResponseEntity update(@PathVariable UUID beerID, @RequestBody BeerDto beerDto) {
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PostMapping
    public ResponseEntity create() {
        return new ResponseEntity(HttpStatus.CREATED);
    }
}
