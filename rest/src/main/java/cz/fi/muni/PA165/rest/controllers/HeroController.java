package cz.fi.muni.PA165.rest.controllers;

import cz.fi.muni.PA165.api.dto.hero.HeroCreateDTO;
import cz.fi.muni.PA165.api.dto.hero.HeroDTO;
import cz.fi.muni.PA165.api.dto.hero.HeroUpdateDTO;
import cz.fi.muni.PA165.api.dto.role.RoleDTO;
import cz.fi.muni.PA165.api.facade.HeroFacade;
import cz.fi.muni.PA165.rest.assemblers.HeroResourceAssembler;
import cz.fi.muni.PA165.rest.assemblers.RoleResourceAssembler;
import cz.fi.muni.PA165.rest.exceptions.ExceptionSorter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * Hero rest controller
 * @author Boris Jadus
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/rest/heroes")
public class HeroController {

    private HeroFacade heroFacade;
    private HeroResourceAssembler heroResourceAssembler;
    private RoleResourceAssembler roleResourceAssembler;

    @Autowired
    public HeroController(HeroFacade heroFacade, HeroResourceAssembler heroResourceAssembler, RoleResourceAssembler roleResourceAssembler) {
        this.heroFacade = heroFacade;
        this.heroResourceAssembler = heroResourceAssembler;
        this.roleResourceAssembler = roleResourceAssembler;
    }


    /**
     * Get list of Heroes
     *
     * @return resource with list of heroes
     */
    @RolesAllowed("ROLE_USER")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Resources<Resource<HeroDTO>>> getAll(){
        try {
            List<HeroDTO> heroes = heroFacade.findAllHeroes();
            List<Resource<HeroDTO>> heroesResource = new ArrayList<>();
            for (HeroDTO heroDTO : heroes){
                heroesResource.add(heroResourceAssembler.toResource(heroDTO));
            }
            Resources<Resource<HeroDTO>> resultResources = new Resources<>(heroesResource);
            resultResources.add(linkTo(HeroController.class).withSelfRel().withType("GET"));
            return new ResponseEntity<>(resultResources, HttpStatus.OK);
        }catch (Exception ex){
            throw ExceptionSorter.throwException(ex);
        }
    }

    /**
     * Get list of roles that given hero does not have
     *
     * @param id identifier of hero
     * @return resource with list of roles
     */
    @RolesAllowed("ROLE_USER")
    @GetMapping(value = "/{id}/roles/other", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Resources<Resource<RoleDTO>>> listRolesNotInHero(@PathVariable Long id){
        try{
            List<RoleDTO> roles = heroFacade.listAllRolesNotInHero(id);
            List<Resource<RoleDTO>> rolesResource = new ArrayList<>();
            for (RoleDTO roleDTO : roles){
                rolesResource.add(roleResourceAssembler.toResource(roleDTO));
            }
            Resources<Resource<RoleDTO>> resultResource = new Resources<>(rolesResource);
            resultResource.add(linkTo(HeroController.class).withSelfRel().withType("GET"));
            return new ResponseEntity<>(resultResource, HttpStatus.OK);
        }catch (Exception ex){
            throw ExceptionSorter.throwException(ex);
        }
    }

    /**
     * Get hero by identifier id
     *
     * @param id identifier of user
     * @return Resource<HeroDTO>
     */
    @RolesAllowed("ROLE_USER")
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Resource<HeroDTO>> getById(@PathVariable Long id){
        try {
            return new ResponseEntity<>(heroResourceAssembler.toResource(heroFacade.findById(id)), HttpStatus.OK);
        }catch (Exception ex){
            throw ExceptionSorter.throwException(ex);
        }
    }

    /**
     * Create a new hero by POST method
     *
     * @param heroCreateDTO HeroCreateDTO with required fields for creation
     * @return id of registered hero
     */
    @RolesAllowed("ROLE_USER")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> createHero(@RequestBody @Valid HeroCreateDTO heroCreateDTO){
        try{
            return new ResponseEntity<>(heroFacade.createHero(heroCreateDTO), HttpStatus.CREATED);
        }catch (Exception ex){
            throw ExceptionSorter.throwException(ex);
        }
    }

    /**
     * Delete hero by identifier
     *
     * @param id identifier of hero
     */
    @RolesAllowed("ROLE_USER")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteHero(@PathVariable Long id){
        try {
            heroFacade.deleteHero(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception ex){
            throw ExceptionSorter.throwException(ex);
        }
    }

    /**
     * Updates given hero
     *
     * @param heroUpdateDTO HeroUpdateDTO with updated values
     */
    @RolesAllowed("ROLE_USER")
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateRole(@RequestBody @Valid HeroUpdateDTO heroUpdateDTO){
        try {
            heroFacade.updateHero(heroUpdateDTO);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (Exception ex){
            throw ExceptionSorter.throwException(ex);
        }
    }

    /**
     * Adds given role to given hero
     *
     * @param heroId identifier of hero
     * @param roleId identifier of role
     */
    @RolesAllowed("ROLE_USER")
    @PutMapping(value = "{heroId}/roles/{roleId}/add")
    public ResponseEntity<Void> addRoleToHero(@PathVariable Long heroId, @PathVariable Long roleId){
        try {
            heroFacade.addRole(heroId, roleId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception ex){
            throw ExceptionSorter.throwException(ex);
        }
    }

    /**
     * Removes given role from given hero
     *
     * @param heroId identifier of hero
     * @param roleId identifier of role
     */
    @RolesAllowed("ROLE_USER")
    @PutMapping(value = "{heroId}/roles/{roleId}/remove")
    public ResponseEntity<Void> removeRoleFromHero(@PathVariable Long heroId, @PathVariable Long roleId){
        try {
            heroFacade.removeRole(heroId, roleId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception ex){
            throw ExceptionSorter.throwException(ex);
        }
    }

    /**
     * Adds given hero to given troop
     *
     * @param heroId identifier of hero
     * @param troopId identifier of troop
     */
    @RolesAllowed("ROLE_USER")
    @PutMapping(value = "{heroId}/troops/{troopId}/add")
    public ResponseEntity<Void> joinTroop(@PathVariable Long heroId, @PathVariable Long troopId){
        try {
            heroFacade.joinTroop(heroId, troopId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception ex){
            throw ExceptionSorter.throwException(ex);
        }
    }

    /**
     * Removes troop from given hero
     *
     * @param heroId identifier of hero
     */
    @RolesAllowed("ROLE_USER")
    @PutMapping(value = "{heroId}/troops/remove")
    public ResponseEntity<Void> leaveTroop(@PathVariable Long heroId){
        try {
            heroFacade.leaveTroop(heroId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception ex){
            throw ExceptionSorter.throwException(ex);
        }
    }
}
