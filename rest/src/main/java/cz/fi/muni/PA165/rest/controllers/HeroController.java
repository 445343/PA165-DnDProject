package cz.fi.muni.PA165.rest.controllers;

import cz.fi.muni.PA165.api.dto.hero.HeroCreateDTO;
import cz.fi.muni.PA165.api.dto.hero.HeroDTO;
import cz.fi.muni.PA165.api.dto.hero.HeroUpdateDTO;
import cz.fi.muni.PA165.api.exceptions.DnDServiceException;
import cz.fi.muni.PA165.api.facade.HeroFacade;
import cz.fi.muni.PA165.rest.assemblers.HeroResourceAssembler;
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

@RestController
@RequestMapping("/rest/heroes")
public class HeroController {

    private HeroFacade heroFacade;
    private HeroResourceAssembler heroResourceAssembler;

    @Autowired
    public HeroController(HeroFacade heroFacade, HeroResourceAssembler heroResourceAssembler) {
        this.heroFacade = heroFacade;
        this.heroResourceAssembler = heroResourceAssembler;
    }


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
        }catch (DnDServiceException ex){
            throw new DnDServiceException("");
        }
    }

    @RolesAllowed("ROLE_USER")
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Resource<HeroDTO>> getById(@PathVariable Long id){
        try {
            return new ResponseEntity<>(heroResourceAssembler.toResource(heroFacade.findById(id)), HttpStatus.OK);
        }catch (DnDServiceException ex){
            throw new DnDServiceException("");
        }
    }

    @RolesAllowed("ROLE_USER")
    @PostMapping
    public ResponseEntity<Void> createHero(@RequestBody @Valid HeroCreateDTO heroCreateDTO){
        try{
            heroFacade.createHero(heroCreateDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (DnDServiceException ex){
            throw new DnDServiceException("");
        }
    }

    @RolesAllowed("ROLE_USER")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteHero(@PathVariable Long id){
        try {
            heroFacade.deleteHero(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (DnDServiceException ex){
            throw new DnDServiceException("");
        }
    }

    @RolesAllowed("ROLE_USER")
    @PutMapping
    public ResponseEntity<Void> updateRole(@RequestBody @Valid HeroUpdateDTO heroUpdateDTO){
        try {
            heroFacade.updateHero(heroUpdateDTO);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (DnDServiceException ex){
            throw new DnDServiceException("");
        }
    }

    @RolesAllowed("ROLE_USER")
    @PutMapping(value = "{heroId}/roles/{roleId}/add")
    public ResponseEntity<Void> addRoleToHero(@PathVariable Long heroId, @PathVariable Long roleId){
        try {
            heroFacade.addRole(heroId, roleId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (DnDServiceException ex){
            throw new DnDServiceException("");
        }
    }

    @RolesAllowed("ROLE_USER")
    @PutMapping(value = "{heroId}/roles/{roleId}/remove")
    public ResponseEntity<Void> removeRoleFromHero(@PathVariable Long heroId, @PathVariable Long roleId){
        try {
            heroFacade.removeRole(heroId, roleId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (DnDServiceException ex){
            throw new DnDServiceException("");
        }
    }

    @RolesAllowed("ROLE_USER")
    @PutMapping(value = "{heroId}/troops/{troopId}/add")
    public ResponseEntity<Void> joinTroop(@PathVariable Long heroId, @PathVariable Long troopId){
        try {
            heroFacade.joinTroop(heroId, troopId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (DnDServiceException ex){
            throw new DnDServiceException("");
        }
    }

    @RolesAllowed("ROLE_USER")
    @PutMapping(value = "{heroId}/troops/remove")
    public ResponseEntity<Void> leaveTroop(@PathVariable Long heroId){
        try {
            heroFacade.leaveTroop(heroId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (DnDServiceException ex){
            throw new DnDServiceException("");
        }
    }
}
