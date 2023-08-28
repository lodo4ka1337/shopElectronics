package com.lodo4ka.shopElectronics.controller;

import com.lodo4ka.shopElectronics.persistance.model.Showcase;
import com.lodo4ka.shopElectronics.persistance.service.ShowcaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/showcases")
public class ShowcaseController {
    private ShowcaseService showcaseService;

    @Autowired
    public ShowcaseController(ShowcaseService showcaseService) {
        this.showcaseService = showcaseService;
    }

    public ShowcaseController() {
    }

    @GetMapping("/get/all")
    public List<Showcase> getAllShowcases() {
        return showcaseService.getAllShowcases();
    }

    @GetMapping("/get/type/{type}")
    public List<Showcase> getAllShowcasesFilteredByType(@PathVariable String type) {
        return showcaseService.getAllShowcasesFilteredByType(type);
    }

    @GetMapping("/get/address/{address}")
    public List<Showcase> getAllShowcasesFilteredByAddress(@PathVariable String address) {
        return showcaseService.getAllShowcasesFilteredByAddress(address);
    }

    @GetMapping("/get/date")
    public List<Showcase> getAllShowcasesCreatedBetween(@RequestParam Date date1, @RequestParam Date date2) {
        return showcaseService.getAllShowcasesCreatedBetween(date1, date2);
    }

    @PostMapping("/add")
    public void addShowcase(@RequestBody Showcase showcase) {
        showcaseService.addShowcase(showcase);
    }

    @PutMapping("/update")
    public void updateShowcase(@RequestBody Showcase showcase) {
        showcaseService.updateShowcase(showcase);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteShowcaseById(@PathVariable("id") UUID id) {
        showcaseService.deleteShowcaseById(id);
    }
}
