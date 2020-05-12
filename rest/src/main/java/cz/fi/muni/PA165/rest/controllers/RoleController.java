package cz.fi.muni.PA165.rest.controllers;

import cz.fi.muni.PA165.api.dto.role.RoleCreateDTO;
import cz.fi.muni.PA165.api.dto.role.RoleDTO;
import cz.fi.muni.PA165.api.dto.role.RoleUpdateDTO;
import cz.fi.muni.PA165.api.exceptions.DnDServiceException;
import cz.fi.muni.PA165.api.facade.RoleFacade;
import cz.fi.muni.PA165.rest.assemblers.RoleResourceAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@RestController
@RequestMapping("/rest/roles")
public class RoleController {

    private RoleFacade roleFacade;
    private RoleResourceAssembler roleResourceAssembler;

    @Autowired
    public RoleController(RoleFacade roleFacade, RoleResourceAssembler roleResourceAssembler) {
        this.roleFacade = roleFacade;
        this.roleResourceAssembler = roleResourceAssembler;
    }

    @RolesAllowed("ROLE_USER")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Resources<Resource<RoleDTO>>> getAll(){
        try {
            List<RoleDTO> roles = roleFacade.findAllRoles();
            List<Resource<RoleDTO>> rolesResource = new ArrayList<>();
            for (RoleDTO roleDTO : roles){
                rolesResource.add(roleResourceAssembler.toResource(roleDTO));
            }
            Resources<Resource<RoleDTO>> resultResources = new Resources<>(rolesResource);
            resultResources.add(linkTo(RoleController.class).withSelfRel().withType("GET"));
            return new ResponseEntity<>(resultResources, HttpStatus.OK);
        }catch (DnDServiceException ex){
            throw new DnDServiceException("");
        }
    }

    @RolesAllowed("ROLE_USER")
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Resource<RoleDTO> getById(@PathVariable Long id){
        try {
            return roleResourceAssembler.toResource(roleFacade.findById(id));
        }catch (DnDServiceException ex){
            throw new DnDServiceException("");
        }
    }

    @RolesAllowed("ROLE_ADMIN")
    @PostMapping
    public ResponseEntity<Void> createRole(@RequestBody @Valid RoleCreateDTO roleCreateDTO){
        try{
            roleFacade.createRole(roleCreateDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (DnDServiceException ex){
            throw new DnDServiceException("");
        }
    }

    @RolesAllowed("ROLE_ADMIN")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteRole(@PathVariable Long id){
        try {
            roleFacade.deleteRole(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (DnDServiceException ex){
            throw new DnDServiceException("");
        }
    }

    @RolesAllowed("ROLE_ADMIN")
    @PutMapping
    public ResponseEntity<Void> updateRole(@RequestBody @Valid RoleUpdateDTO roleUpdateDTO){
        try {
            roleFacade.updateRole(roleUpdateDTO);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (DnDServiceException ex){
            throw new DnDServiceException("");
        }
    }

}
