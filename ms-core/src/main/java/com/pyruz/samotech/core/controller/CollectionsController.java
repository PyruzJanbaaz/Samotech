package com.pyruz.samotech.core.controller;

import com.pyruz.samotech.core.service.collections.CollectionsService;
import com.pyruz.samotech.shared.model.domain.collection.NewCollection;
import com.pyruz.samotech.shared.model.domain.collection.UpdateCollection;
import com.pyruz.samotech.shared.model.dto.base.BaseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class CollectionsController {

    final CollectionsService collectionService;

    public CollectionsController(CollectionsService collectionService) {
        this.collectionService = collectionService;
    }

    @PostMapping("/v1/collection")
    @ResponseStatus(HttpStatus.CREATED)
    public BaseDTO addCollection(@Valid @RequestBody NewCollection newCollection) {
        return collectionService.addCollection(newCollection);
    }

    @PutMapping("/v1/collection")
    @ResponseStatus(HttpStatus.OK)
    public BaseDTO editCollection(@Valid @RequestBody UpdateCollection updateCollection) {
        return collectionService.editCollection(updateCollection);
    }

    @GetMapping("/v1/collection/{collectionId}")
    @ResponseStatus(HttpStatus.OK)
    public BaseDTO getCollection(@PathVariable Integer collectionId) {
        return collectionService.getCollection(collectionId);
    }

    @GetMapping("/v1/collections")
    @ResponseStatus(HttpStatus.OK)
    public BaseDTO getAllCollections() {
        return collectionService.getAllCollections();
    }

    @GetMapping("/v1/collections/{page}")
    @ResponseStatus(HttpStatus.OK)
    public BaseDTO getCollections(@PathVariable Integer page) {
        return collectionService.getCollections(page);
    }

    @DeleteMapping("/v1/collection")
    @ResponseStatus(HttpStatus.OK)
    public BaseDTO deleteCollection(@RequestParam Integer collectionId) {
        return collectionService.deleteCollection(collectionId);
    }

}
