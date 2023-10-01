package com.lodo4ka.shopElectronics.persistance.service;

import com.lodo4ka.shopElectronics.persistance.model.DTO.ShowcaseDTO;
import com.lodo4ka.shopElectronics.persistance.model.DTO.ShowcaseSearchRequest;

import java.util.List;
import java.util.UUID;

public interface ShowcaseService {

    List<ShowcaseDTO> getShowcases(ShowcaseSearchRequest showcaseSearchRequest);

    ShowcaseDTO addShowcase(ShowcaseDTO showcaseAddRequest);

    ShowcaseDTO updateShowcase(ShowcaseDTO showcaseUpdateRequest);

    void deleteShowcaseById(UUID id);
}
