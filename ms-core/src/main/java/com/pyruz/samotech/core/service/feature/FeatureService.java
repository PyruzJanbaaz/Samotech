package com.pyruz.samotech.core.service.feature;

import com.pyruz.samotech.core.model.entity.Feature;
import com.pyruz.samotech.core.repository.FeatureRepository;
import com.pyruz.samotech.core.repository.StateRepository;
import com.pyruz.samotech.shared.handler.exception.ServiceException;
import com.pyruz.samotech.shared.model.domain.feature.NewFeature;
import com.pyruz.samotech.shared.model.domain.feature.UpdateFeature;
import com.pyruz.samotech.shared.model.dto.base.BaseDTO;
import com.pyruz.samotech.shared.model.dto.base.MetaDTO;
import com.pyruz.samotech.shared.model.dto.base.PagerDTO;
import com.pyruz.samotech.shared.utility.ApplicationProperties;
import com.pyruz.samotech.shared.utility.ApplicationUtilities;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class FeatureService {

    final ApplicationProperties applicationProperties;
    final StateRepository stateRepository;
    final FeatureRepository featureRepository;

    public FeatureService(ApplicationProperties applicationProperties, StateRepository stateRepository, FeatureRepository featureRepository) {
        this.applicationProperties = applicationProperties;
        this.stateRepository = stateRepository;
        this.featureRepository = featureRepository;
    }

    public BaseDTO addFeature(NewFeature newFeature) {
        Feature feature = new Feature();
        feature.setTitle(newFeature.getTitle());
        feature.setStates(stateRepository.findById(newFeature.getState()).orElseThrow(
                () -> new ServiceException(
                        applicationProperties.getCode("not-found-code"),
                        applicationProperties.getProperty("not-found-text"),
                        HttpStatus.NOT_FOUND
                )
        ));
        feature.setFeatureCode(UUID.randomUUID().toString());
        featureRepository.save(feature);
        return BaseDTO.builder()
                .meta(MetaDTO.getInstance(applicationProperties))
                .build();
    }


    public BaseDTO editFeature(UpdateFeature updateFeature) {
        Feature feature = featureRepository.findById(updateFeature.getId()).orElseThrow(
                () -> new ServiceException(
                        applicationProperties.getCode("not-found-code"),
                        applicationProperties.getProperty("not-found-text"),
                        HttpStatus.NOT_FOUND
                )
        );
        feature.setTitle(updateFeature.getTitle());
        feature.setStates(stateRepository.findById(updateFeature.getState()).orElseThrow(
                () -> new ServiceException(
                        applicationProperties.getCode("not-found-code"),
                        applicationProperties.getProperty("not-found-text"),
                        HttpStatus.NOT_FOUND
                )
        ));
        featureRepository.save(feature);
        return BaseDTO.builder()
                .meta(MetaDTO.getInstance(applicationProperties))
                .build();
    }


    public BaseDTO getFeature(Integer id) {
        return BaseDTO.builder()
                .meta(MetaDTO.getInstance(applicationProperties))
                .data(
                        featureRepository.findById(id).orElseThrow(
                                () -> new ServiceException(
                                        applicationProperties.getCode("not-found-code"),
                                        applicationProperties.getProperty("not-found-text"),
                                        HttpStatus.NOT_FOUND
                                )
                        )
                ).build();

    }


    public BaseDTO deleteFeature(Integer id) {
        Feature feature = featureRepository.findById(id).orElseThrow(
                () -> new ServiceException(
                        applicationProperties.getCode("not-found-code"),
                        applicationProperties.getProperty("not-found-text"),
                        HttpStatus.NOT_FOUND
                )
        );
        feature.setIsDeleted(true);
        featureRepository.save(feature);
        return BaseDTO.builder()
                .meta(MetaDTO.getInstance(applicationProperties))
                .build();
    }


    public BaseDTO getAllFeatures() {
        return BaseDTO.builder()
                .meta(MetaDTO.getInstance(applicationProperties))
                .data(featureRepository.findAll())
                .build();
    }


    public BaseDTO getFeatures(Integer page) {
        Pageable pageable = ApplicationUtilities.getInstance().pageable(page, applicationProperties);
        Page<Feature> features = featureRepository.findAll(pageable);
        PagerDTO<Feature> featurePagerDTO = new PagerDTO<>(features);
        return BaseDTO.builder()
                .meta(MetaDTO.getInstance(applicationProperties))
                .data(featurePagerDTO)
                .build();
    }
}
