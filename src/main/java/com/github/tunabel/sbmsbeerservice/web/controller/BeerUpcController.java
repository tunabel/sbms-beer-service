package com.github.tunabel.sbmsbeerservice.web.controller;

import com.github.tunabel.sbmsbeerservice.services.BeerService;
import com.github.tunabel.sbmsbeerservice.web.model.BeerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/beerUpc")
public class BeerUpcController {

    @Autowired
    BeerService beerService;

    @GetMapping("/{beerUpc}")
    public ResponseEntity<BeerDto> getByUPC(@Valid @PathVariable(value="beerUpc") String upc,
                                            @RequestParam(value = "showInventoryOnHand", required = false) Boolean showInventoryOnHand) {

        if (showInventoryOnHand == null) {
            showInventoryOnHand = false;
        }
        BeerDto dto = beerService.getByUpc(upc, showInventoryOnHand);
        return new ResponseEntity<>(dto, HttpStatus.OK);

    }

}
