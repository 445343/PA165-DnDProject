package cz.fi.muni.PA165.rest.assemblers;

import cz.fi.muni.PA165.api.dto.troop.TroopDTO;
import cz.fi.muni.PA165.rest.controllers.TroopController;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@Component
public class TroopResourceAssembler implements ResourceAssembler<TroopDTO, Resource<TroopDTO>> {

    @Override
    public Resource<TroopDTO> toResource(TroopDTO troopDTO) {
        Resource<TroopDTO> troopResource = new Resource<>(troopDTO);
        try {
            troopResource.add(linkTo(TroopController.class).slash(troopDTO.getId()).withSelfRel().withType("GET"));
        } catch (Exception ex){

        }
        return troopResource;
    }
}
