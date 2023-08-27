package com.lodo4ka.shopElectronics.controller;

import com.lodo4ka.shopElectronics.persistance.model.Showcase;
import com.lodo4ka.shopElectronics.persistance.service.ShowcaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class ShowcaseController {
    private ShowcaseService showcaseService;

    @Autowired
    public ShowcaseController(ShowcaseService showcaseService) {
        this.showcaseService = showcaseService;
    }

    public ShowcaseController() {
    }

    @GetMapping("/showcases")
    public List<Showcase> getAllShowcases() {
        return showcaseService.getAllShowcases();
    }

    @GetMapping("/showcases/{id}")
    public Optional<Showcase> getShowcaseById(@PathVariable("id") UUID id) {
        return showcaseService.getShowcaseById(id);
    }

    @PostMapping("/showcases/add")
    public void addShowcase(@ModelAttribute Showcase showcase) {
        showcaseService.addShowcase(showcase);
    }
}
