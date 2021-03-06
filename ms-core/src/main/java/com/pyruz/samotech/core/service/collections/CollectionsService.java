package com.pyruz.samotech.core.service.collections;

import com.pyruz.samotech.core.model.entity.Collections;
import com.pyruz.samotech.core.repository.CollectionsRepository;
import com.pyruz.samotech.core.repository.ProjectRepository;
import com.pyruz.samotech.shared.handler.exception.ServiceException;
import com.pyruz.samotech.shared.model.domain.collection.NewCollection;
import com.pyruz.samotech.shared.model.domain.collection.UpdateCollection;
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
public class CollectionsService {

    final CollectionsRepository collectionsRepository;
    final ApplicationProperties applicationProperties;
    final ProjectRepository projectRepository;

    public CollectionsService(CollectionsRepository collectionsRepository, ApplicationProperties applicationProperties, ProjectRepository projectRepository) {
        this.collectionsRepository = collectionsRepository;
        this.applicationProperties = applicationProperties;
        this.projectRepository = projectRepository;
    }

    public BaseDTO<String> addCollection(NewCollection newCollection) {
        Collections collection = new Collections();
        collection.setCaption(newCollection.getCaption());
        collection.setTitle(newCollection.getTitle());
        collection.setCollectionCode(UUID.randomUUID().toString());
        collectionsRepository.save(collection);
        return new BaseDTO<>(MetaDTO.getInstance(applicationProperties), collection.getCollectionCode());
    }


    public BaseDTO editCollection(UpdateCollection updateCollection) {
        Collections collection = collectionsRepository.findById(updateCollection.getId()).orElseThrow(
                () -> new ServiceException(
                        applicationProperties.getCode("not-found-code"),
                        applicationProperties.getProperty("not-found-text"),
                        HttpStatus.NOT_FOUND
                )
        );
        collection.setCaption(updateCollection.getCaption());
        collection.setTitle(updateCollection.getTitle());
        collection.setProjects(projectRepository.findProjectByIdIn(updateCollection.getProjects()));
        return BaseDTO.builder()
                .meta(MetaDTO.getInstance(applicationProperties))
                .build();
    }


    public BaseDTO getCollection(Integer id) {

        return BaseDTO.builder()
                .meta(MetaDTO.getInstance(applicationProperties))
                .data(
                        collectionsRepository.findById(id).orElseThrow(
                                () -> new ServiceException(
                                        applicationProperties.getCode("not-found-code"),
                                        applicationProperties.getProperty("not-found-text"),
                                        HttpStatus.NOT_FOUND
                                )
                        )
                ).build();


    }


    public BaseDTO deleteCollection(Integer id) {

        Collections collection = collectionsRepository.findById(id).orElseThrow(
                () -> new ServiceException(
                        applicationProperties.getCode("not-found-code"),
                        applicationProperties.getProperty("not-found-text"),
                        HttpStatus.NOT_FOUND
                )
        );
        collection.setIsDeleted(true);
        collectionsRepository.save(collection);
        return BaseDTO.builder()
                .meta(MetaDTO.getInstance(applicationProperties))
                .build();
    }


    public BaseDTO getAllCollections() {
        return BaseDTO.builder()
                .meta(MetaDTO.getInstance(applicationProperties))
                .data(collectionsRepository.findAll())
                .build();
    }


    public BaseDTO getCollections(Integer page) {
        Pageable pageable = ApplicationUtilities.getInstance().pageable(page, applicationProperties);
        Page<Collections> collections = collectionsRepository.findAll(pageable);
        PagerDTO<Collections> collectionPagerDTO = new PagerDTO<>(collections);
        return BaseDTO.builder()
                .meta(MetaDTO.getInstance(applicationProperties))
                .data(collectionPagerDTO)
                .build();
    }
}
