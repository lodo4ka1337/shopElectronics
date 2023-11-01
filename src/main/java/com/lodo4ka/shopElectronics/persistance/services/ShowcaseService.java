package com.lodo4ka.shopElectronics.persistance.services;

import com.lodo4ka.shopElectronics.exceptions.customExceptions.ShopElectronicsException;
import com.lodo4ka.shopElectronics.persistance.model.DTO.ShowcaseDTO;
import com.lodo4ka.shopElectronics.persistance.model.DTO.searchRequests.ShowcaseSearchRequest;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

public interface ShowcaseService {

    List<ShowcaseDTO> getShowcases(@Valid ShowcaseSearchRequest showcaseSearchRequest)
            throws ShopElectronicsException;

    ShowcaseDTO addShowcase(ShowcaseDTO showcaseAddRequest);

    ShowcaseDTO updateShowcase(ShowcaseDTO showcaseUpdateRequest);

    void deleteShowcaseById(UUID id);
}
