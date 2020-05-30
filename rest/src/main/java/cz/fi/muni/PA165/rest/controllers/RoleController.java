package cz.fi.muni.PA165.rest.controllers;

import cz.fi.muni.PA165.api.dto.role.RoleCreateDTO;
import cz.fi.muni.PA165.api.dto.role.RoleDTO;
import cz.fi.muni.PA165.api.dto.role.RoleUpdateDTO;
import cz.fi.muni.PA165.api.facade.RoleFacade;
import cz.fi.muni.PA165.rest.assemblers.RoleResourceAssembler;
import cz.fi.muni.PA165.rest.exceptions.ExceptionSorter;
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

/**
 * Role rest controller
 * @author Boris Jadus
 */
@CrossOrigin(origins = "*")
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

    /**
     * Get list of Roles
     *
     * @return resource with list of roles
     */
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
        }catch (Exception ex){
            throw ExceptionSorter.throwException(ex);
        }
    }

    /**
     * Get role by identifier id
     *
     * @param id identifier of role
     * @return Resource<RoleDTO>
     */
    @RolesAllowed("ROLE_USER")
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Resource<RoleDTO>> getById(@PathVariable Long id){
        try {
            return new ResponseEntity<>(roleResourceAssembler.toResource(roleFacade.findById(id)), HttpStatus.OK);
        }catch (Exception ex){
            throw ExceptionSorter.throwException(ex);
        }
    }

    /**
     * Create a new role by POST method
     *
     * @param roleCreateDTO RoleCreateDTO with required fields for creation
     * @return id of created role
     */
    @RolesAllowed("ROLE_ADMIN")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> createRole(@RequestBody @Valid RoleCreateDTO roleCreateDTO){
        try{
            return new ResponseEntity<>(roleFacade.createRole(roleCreateDTO), HttpStatus.CREATED);
        }catch (Exception ex){
            throw ExceptionSorter.throwException(ex);
        }
    }

    /**
     * Delete role by identifier
     *
     * @param id identifier of role
     */
    @RolesAllowed("ROLE_ADMIN")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteRole(@PathVariable Long id){
        try {
            roleFacade.deleteRole(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception ex){
            throw ExceptionSorter.throwException(ex);
        }
    }

    /**
     * Updates given role
     *
     * @param roleUpdateDTO RoleUpdateDTO with updated values
     */
    @RolesAllowed("ROLE_ADMIN")
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateRole(@RequestBody @Valid RoleUpdateDTO roleUpdateDTO){
        try {
            roleFacade.updateRole(roleUpdateDTO);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (Exception ex){
            throw ExceptionSorter.throwException(ex);
        }
    }

}
