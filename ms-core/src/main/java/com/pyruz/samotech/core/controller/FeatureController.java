package com.pyruz.samotech.core.controller;

import com.pyruz.samotech.core.service.feature.FeatureService;
import com.pyruz.samotech.shared.model.domain.feature.NewFeature;
import com.pyruz.samotech.shared.model.domain.feature.UpdateFeature;
import com.pyruz.samotech.shared.model.dto.base.BaseDTO;
import org.springframework.http.HttpStatus;
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
    @ResponseStatus(HttpStatus.CREATED)
    public BaseDTO addFeature(@Valid @RequestBody NewFeature newFeature) {
        return featureService.addFeature(newFeature);
    }

    @PutMapping("/v1/feature")
    @ResponseStatus(HttpStatus.OK)
    public BaseDTO editFeature(@Valid @RequestBody UpdateFeature updateFeature) {
        return featureService.editFeature(updateFeature);
    }

    @GetMapping("/v1/feature/{featureId}")
    @ResponseStatus(HttpStatus.OK)
    public BaseDTO getFeature(@PathVariable Integer featureId) {
        return featureService.getFeature(featureId);
    }

    @GetMapping("/v1/features")
    @ResponseStatus(HttpStatus.OK)
    public BaseDTO getAllFeatures() {
        return featureService.getAllFeatures();
    }

    @GetMapping("/v1/features/{page}")
    @ResponseStatus(HttpStatus.OK)
    public BaseDTO getFeatures(@PathVariable Integer page) {
        return featureService.getFeatures(page);
    }

    @DeleteMapping("/v1/feature")
    @ResponseStatus(HttpStatus.OK)
    public BaseDTO deleteFeature(@RequestParam Integer featureId) {
        return featureService.deleteFeature(featureId);
    }

}
