package com.pyruz.samotech.core.controller;

import com.pyruz.samotech.core.service.epic.EpicService;
import com.pyruz.samotech.shared.model.domain.epic.NewEpic;
import com.pyruz.samotech.shared.model.domain.epic.UpdateEpic;
import com.pyruz.samotech.shared.model.dto.base.BaseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class EpicController {

    final EpicService epicService;

    public EpicController(EpicService epicService) {
        this.epicService = epicService;
    }


    @PostMapping("/v1/epic")
    public ResponseEntity<BaseDTO> addEpic(@Valid @RequestBody NewEpic newEpic) {
        return new ResponseEntity<>(epicService.addEpic(newEpic), HttpStatus.CREATED);
    }

    @PutMapping("/v1/epic")
    public ResponseEntity<BaseDTO> editEpic(@Valid @RequestBody UpdateEpic updateEpic) {
        return new ResponseEntity<>(epicService.editEpic(updateEpic), HttpStatus.OK);
    }

    @GetMapping("/v1/epic/{epicId}")
    public ResponseEntity<BaseDTO> getEpic(@PathVariable Integer epicId) {
        return new ResponseEntity<>(epicService.getEpic(epicId), HttpStatus.OK);
    }

    @GetMapping("/v1/epics")
    public ResponseEntity<BaseDTO> getAllEpics() {
        return new ResponseEntity<>(epicService.getAllEpics(), HttpStatus.OK);
    }

    @GetMapping("/v1/epics/{page}")
    public ResponseEntity<BaseDTO> getEpics(@PathVariable Integer page) {
        return new ResponseEntity<>(epicService.getEpics(page), HttpStatus.OK);
    }

    @DeleteMapping("/v1/epic")
    public ResponseEntity<BaseDTO> deleteEpic(@RequestParam Integer epicId) {
        return new ResponseEntity<>(epicService.deleteEpic(epicId), HttpStatus.OK);
    }

}
