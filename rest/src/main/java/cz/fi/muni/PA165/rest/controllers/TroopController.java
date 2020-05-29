package cz.fi.muni.PA165.rest.controllers;


import cz.fi.muni.PA165.api.dto.troop.TroopCreateDTO;
import cz.fi.muni.PA165.api.dto.troop.TroopDTO;
import cz.fi.muni.PA165.api.dto.troop.TroopUpdateDTO;
import cz.fi.muni.PA165.api.facade.TroopFacade;
import cz.fi.muni.PA165.rest.assemblers.TroopResourceAssembler;
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
 * REST Controller for Troops
 *
 * @author Jan Válka
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/rest/troops")
public class TroopController {

    private TroopFacade troopFacade;
    private TroopResourceAssembler troopResourceAssembler;

    @Autowired
    public TroopController(TroopFacade troopFacade, TroopResourceAssembler troopResourceAssembler){
        this.troopFacade = troopFacade;
        this.troopResourceAssembler = troopResourceAssembler;
    }

    /**
     * Get list of Troops
     *
     * @return resource with list of troops
     */
    @RolesAllowed("ROLE_USER")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Resources<Resource<TroopDTO>>> getAll(){
        try{
            List<TroopDTO> troops = troopFacade.findAllTroops();
            List<Resource<TroopDTO>> troopsResource = new ArrayList<>();
            for (TroopDTO troopDTO : troops){
                troopsResource.add(troopResourceAssembler.toResource(troopDTO));
            }
            Resources<Resource<TroopDTO>> resultResources = new Resources<>(troopsResource);
            resultResources.add(linkTo(TroopController.class).withSelfRel().withType("GET"));
            return new ResponseEntity<>(resultResources, HttpStatus.OK);
        }catch (Exception ex){
            throw ExceptionSorter.throwException(ex);
        }
    }

    /**
     * Get troop by identifier id
     *
     * @param id identifier of troop
     * @return Resource<TroopDTO>
     */
    @RolesAllowed("ROLE_USER")
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Resource<TroopDTO>> getById(@PathVariable Long id){
        try{
            return new ResponseEntity<>(troopResourceAssembler.toResource(troopFacade.findById(id)), HttpStatus.OK);
        }catch (Exception ex){
            throw ExceptionSorter.throwException(ex);
        }
    }

    /**
     * Create a new troop by POST method
     *
     * @param troopCreateDTO TroopCreateDTO with required fields for creation
     * @return id of created troop
     */
    @RolesAllowed("ROLE_ADMIN")
    @PostMapping()
    public ResponseEntity<Long> createTroop(@RequestBody @Valid TroopCreateDTO troopCreateDTO){
        try{
            return new ResponseEntity<>(troopFacade.createTroop(troopCreateDTO), HttpStatus.CREATED);
        }catch (Exception ex){
            throw ExceptionSorter.throwException(ex);
        }
    }

    /**
     * Update troop by PUT method
     *
     * @param troopUpdateDTO troop to be updated
     */
    @RolesAllowed("ROLE_ADMIN")
    @PutMapping()
    public ResponseEntity<Void> updateTroop(@RequestBody @Valid TroopUpdateDTO troopUpdateDTO){
        try{
            troopFacade.updateTroop(troopUpdateDTO);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (Exception ex){
            throw ExceptionSorter.throwException(ex);
        }
    }

    /**
     * Disband troop by id
     *
     * @param id identifier of troop
     */
    @RolesAllowed("ROLE_ADMIN")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> disbandTroop(@PathVariable Long id){
        try{
            troopFacade.disbandTroop(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (Exception ex){
            throw ExceptionSorter.throwException(ex);
        }
    }
}
