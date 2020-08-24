package com.pyruz.samotech.core.controller;

import com.pyruz.samotech.core.service.feature.FeatureService;
import com.pyruz.samotech.shared.model.domain.feature.NewFeature;
import com.pyruz.samotech.shared.model.domain.feature.UpdateFeature;
import com.pyruz.samotech.shared.model.dto.base.BaseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class FeatureController {

    final FeatureService featureService;

    public FeatureController(FeatureService featureService) {
        this.featureService = featureService;
    }

    @PostMapping("/v1/feature")
    public ResponseEntity<BaseDTO> addFeature(@Valid @RequestBody NewFeature newFeature) {
        return new ResponseEntity<>(featureService.addFeature(newFeature), HttpStatus.CREATED);
    }

    @PutMapping("/v1/feature")
    public ResponseEntity<BaseDTO> editFeature(@Valid @RequestBody UpdateFeature updateFeature) {
        return new ResponseEntity<>(featureService.editFeature(updateFeature), HttpStatus.OK);
    }

    @GetMapping("/v1/feature/{featureId}")
    public ResponseEntity<BaseDTO> getFeature(@PathVariable Integer featureId) {
        return new ResponseEntity<>(featureService.getFeature(featureId), HttpStatus.OK);
    }

    @GetMapping("/v1/features")
    public ResponseEntity<BaseDTO> getAllFeatures() {
        return new ResponseEntity<>(featureService.getAllFeatures(), HttpStatus.OK);
    }

    @GetMapping("/v1/features/{page}")
    public ResponseEntity<BaseDTO> getFeatures(@PathVariable Integer page) {
        return new ResponseEntity<>(featureService.getFeatures(page), HttpStatus.OK);
    }

    @DeleteMapping("/v1/feature")
    public ResponseEntity<BaseDTO> deleteFeature(@RequestParam Integer featureId) {
        return new ResponseEntity<>(featureService.deleteFeature(featureId), HttpStatus.OK);
    }

}
