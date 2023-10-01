package com.lodo4ka.shopElectronics.persistance.model.DTO.Mapper;

import com.lodo4ka.shopElectronics.persistance.model.DTO.ShowcaseDTO;
import com.lodo4ka.shopElectronics.persistance.model.Showcase;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ShowcaseDTOMapper implements Function<Showcase, ShowcaseDTO> {

    @Override
    public ShowcaseDTO apply(Showcase showcase) {
        return new ShowcaseDTO(
                showcase.getId(),
                showcase.getName(),
                showcase.getAddress(),
                showcase.getType(),
                showcase.getCreationDate(),
                showcase.getLastUpdate()
        );
    }
}
