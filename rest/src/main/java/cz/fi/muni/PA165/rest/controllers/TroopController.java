package cz.fi.muni.PA165.rest.controllers;


import cz.fi.muni.PA165.api.dto.troop.TroopCreateDTO;
import cz.fi.muni.PA165.api.dto.troop.TroopDTO;
import cz.fi.muni.PA165.api.dto.troop.TroopUpdateDTO;
import cz.fi.muni.PA165.api.exceptions.DnDServiceException;
import cz.fi.muni.PA165.api.facade.TroopFacade;
import cz.fi.muni.PA165.rest.assemblers.TroopResourceAssembler;
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
     * Get list of Troops curl -i -X GET
     * http://localhost:8080/pa165/rest/troops
     *
     * @return resource with list of troops
     * @throws DnDServiceException
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
        }catch (DnDServiceException ex){
            throw new DnDServiceException("");
        }
    }

    /**
     *
     * Get Troop by identifier id curl -i -X GET
     * http://localhost:8080/pa165/rest/troops/1
     *
     * @param id identifier of troop
     * @return Resource<TroopDTO>
     * @throws DnDServiceException
     */
    @RolesAllowed("ROLE_USER")
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Resource<TroopDTO>> getById(@PathVariable Long id){
        try{
            return new ResponseEntity<>(troopResourceAssembler.toResource(troopFacade.findById(id)), HttpStatus.OK);
        }catch (DnDServiceException ex){
            throw new DnDServiceException("");
        }
    }

    /**
     * Create a new troop by POST method
     * curl -i -X POST -H "Content-Type: application/json" --data
     * '{"name":"test","mission":"test","gold":"50"}'
     * http://localhost:8080/pa165/rest/troops/create
     *
     * @param troopCreateDTO TroopCreateDTO with required fields for creation
     * @throws DnDServiceException
     */
    @RolesAllowed("ROLE_ADMIN")
    @PostMapping()
    public ResponseEntity<Void> createTroop(@RequestBody @Valid TroopCreateDTO troopCreateDTO){
        try{
            troopFacade.createTroop(troopCreateDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (DnDServiceException ex){
            throw new DnDServiceException("");
        }
    }

    /**
     * Update troop by PUT method
     * curl -i -X PUT -H "Content-Type: application/json" --data
     * '{"id":"1","name":"newName","gold":"1000","mission":"newMission"}'
     * http://localhost:8080/pa165/rest/troops/update
     *
     * @param troopUpdateDTO troop to be updated
     * @throws DnDServiceException
     */
    @RolesAllowed("ROLE_ADMIN")
    @PutMapping()
    public ResponseEntity<Void> updateTroop(@RequestBody @Valid TroopUpdateDTO troopUpdateDTO){
        try{
            troopFacade.updateTroop(troopUpdateDTO);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (DnDServiceException ex){
            throw new DnDServiceException("");
        }
    }

    /**
     * Disband troop by id curl -i -X DELETE
     * http://localhost:8080/pa165/rest/troops/1/delete
     *
     * @param id identifier of troop
     * @throws DnDServiceException
     */
    @RolesAllowed("ROLE_ADMIN")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> disbandTroop(@PathVariable Long id){
        try{
            troopFacade.disbandTroop(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (DnDServiceException ex){
            throw new DnDServiceException("");
        }
    }
}
