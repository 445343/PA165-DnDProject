package cz.fi.muni.PA165.rest.assemblers;

import cz.fi.muni.PA165.api.dto.role.RoleDTO;
import cz.fi.muni.PA165.api.dto.user.UserDTO;
import cz.fi.muni.PA165.rest.controllers.UserController;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@Component
public class RoleResourceAssembler implements ResourceAssembler<RoleDTO, Resource<RoleDTO>> {
    @Override
    public Resource<RoleDTO> toResource(RoleDTO roleDTO) {
        Resource<RoleDTO> roleResource = new Resource<>(roleDTO);
        try {
            roleResource.add(linkTo(UserController.class).slash(roleDTO.getId()).withSelfRel().withType("GET"));
        } catch (Exception ex){

        }
        return roleResource;
    }
}
