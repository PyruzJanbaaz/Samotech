package com.pyruz.samotech.core.controller;

import com.pyruz.samotech.core.service.collections.CollectionsService;
import com.pyruz.samotech.shared.model.domain.collection.NewCollection;
import com.pyruz.samotech.shared.model.domain.collection.UpdateCollection;
import com.pyruz.samotech.shared.model.dto.base.BaseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class CollectionsController  {

    final CollectionsService collectionService;

    public CollectionsController(CollectionsService collectionService) {
        this.collectionService = collectionService;
    }

    @PostMapping("/v1/collection")

    public ResponseEntity<BaseDTO> addCollection(@Valid @RequestBody NewCollection newCollection) {
        return new ResponseEntity<>(collectionService.addCollection(newCollection), HttpStatus.CREATED);
    }

    @PutMapping("/v1/collection")
    public ResponseEntity<BaseDTO> editCollection(@Valid @RequestBody UpdateCollection updateCollection) {
        return new ResponseEntity<>(collectionService.editCollection(updateCollection), HttpStatus.OK);
    }

    @GetMapping("/v1/collection/{collectionId}")
    public ResponseEntity<BaseDTO> getCollection(@PathVariable Integer collectionId) {
        return new ResponseEntity<>(collectionService.getCollection(collectionId), HttpStatus.OK);
    }

    @GetMapping("/v1/collections")
    public ResponseEntity<BaseDTO> getAllCollections() {
        return new ResponseEntity<>(collectionService.getAllCollections(), HttpStatus.OK);
    }

    @GetMapping("/v1/collections/{page}")
    public ResponseEntity<BaseDTO> getCollections(@PathVariable Integer page) {
        return new ResponseEntity<>(collectionService.getCollections(page), HttpStatus.OK);
    }

    @DeleteMapping("/v1/collection")
    public ResponseEntity<BaseDTO> deleteCollection(@RequestParam Integer collectionId) {
        return new ResponseEntity<>(collectionService.deleteCollection(collectionId), HttpStatus.OK);
    }

}
