package com.lodo4ka.shopElectronics.persistance.service;

import com.lodo4ka.shopElectronics.persistance.model.Showcase;
import com.lodo4ka.shopElectronics.persistance.repository.ShowcaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class ShowcaseServiceImpl implements ShowcaseService {

    private ShowcaseRepository showcaseRepository;

    @Autowired
    public ShowcaseServiceImpl(ShowcaseRepository showcaseRepository) {
        this.showcaseRepository = showcaseRepository;
    }

    @Override
    public List<Showcase> getAllShowcases() {
        return showcaseRepository.findAll();
    }

    @Override
    public Optional<Showcase> getShowcaseById(UUID id) {
        return showcaseRepository.findById(id);
    }

    @Override
    @Transactional
    public void addShowcase(Showcase showcase) {
        showcaseRepository.save(showcase);
    }

    @Override
    @Transactional
    public void deleteShowcaseById(UUID id) {
        showcaseRepository.deleteById(id);
    }

    @Transactional
    @Override
    public void updateShowcase(Showcase showcase) {
        showcaseRepository.save(showcase);
    }

}
