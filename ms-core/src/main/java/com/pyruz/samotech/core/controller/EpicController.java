package com.pyruz.samotech.core.controller;

import com.pyruz.samotech.core.service.epic.EpicService;
import com.pyruz.samotech.shared.model.domain.epic.NewEpic;
import com.pyruz.samotech.shared.model.domain.epic.UpdateEpic;
import com.pyruz.samotech.shared.model.dto.base.BaseDTO;
import org.springframework.http.HttpStatus;
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
    @ResponseStatus(HttpStatus.CREATED)
    public BaseDTO addEpic(@Valid @RequestBody NewEpic newEpic) {
        return epicService.addEpic(newEpic);
    }

    @PutMapping("/v1/epic")
    @ResponseStatus(HttpStatus.OK)
    public BaseDTO editEpic(@Valid @RequestBody UpdateEpic updateEpic) {
        return epicService.editEpic(updateEpic);
    }

    @GetMapping("/v1/epic/{epicId}")
    @ResponseStatus(HttpStatus.OK)
    public BaseDTO getEpic(@PathVariable Integer epicId) {
        return epicService.getEpic(epicId);
    }

    @GetMapping("/v1/epics")
    @ResponseStatus(HttpStatus.OK)
    public BaseDTO getAllEpics() {
        return epicService.getAllEpics();
    }

    @GetMapping("/v1/epics/{page}")
    @ResponseStatus(HttpStatus.OK)
    public BaseDTO getEpics(@PathVariable Integer page) {
        return epicService.getEpics(page);
    }

    @DeleteMapping("/v1/epic")
    @ResponseStatus(HttpStatus.OK)
    public BaseDTO deleteEpic(@RequestParam Integer epicId) {
        return epicService.deleteEpic(epicId);
    }

}
