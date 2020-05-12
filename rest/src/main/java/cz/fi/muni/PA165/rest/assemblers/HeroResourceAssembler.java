package cz.fi.muni.PA165.rest.assemblers;

import cz.fi.muni.PA165.api.dto.hero.HeroDTO;
import cz.fi.muni.PA165.rest.controllers.UserController;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@Component
public class HeroResourceAssembler implements ResourceAssembler<HeroDTO, Resource<HeroDTO>> {

    @Override
    public Resource<HeroDTO> toResource(HeroDTO heroDTO) {
        Resource<HeroDTO> heroResource = new Resource<>(heroDTO);
        try {
            heroResource.add(linkTo(UserController.class).slash(heroDTO.getId()).withSelfRel().withType("GET"));
        } catch (Exception ex){

        }
        return heroResource;
    }
}
