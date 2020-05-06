package cz.fi.muni.PA165.rest.assemblers;

import cz.fi.muni.PA165.api.dto.user.UserDTO;
import cz.fi.muni.PA165.rest.controllers.UserController;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@Component
public class UserResourceAssembler implements ResourceAssembler<UserDTO, Resource<UserDTO>> {

    @Override
    public Resource<UserDTO> toResource(UserDTO userDTO) {
        Resource<UserDTO> userResource = new Resource<>(userDTO);
        try {
            userResource.add(linkTo(UserController.class).slash(userDTO.getId()).withSelfRel().withType("GET"));
        } catch (Exception ex){

        }
        return userResource;
    }
}
