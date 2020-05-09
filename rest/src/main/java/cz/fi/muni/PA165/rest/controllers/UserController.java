package cz.fi.muni.PA165.rest.controllers;

import cz.fi.muni.PA165.api.dto.user.UserCreateDTO;
import cz.fi.muni.PA165.api.dto.user.UserDTO;
import cz.fi.muni.PA165.api.dto.user.UserUpdateDTO;
import cz.fi.muni.PA165.api.exceptions.DnDServiceException;
import cz.fi.muni.PA165.api.facade.UserFacade;
import cz.fi.muni.PA165.rest.assemblers.UserResourceAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserFacade userFacade;
    private UserResourceAssembler userResourceAssembler;

    @Autowired
    public UserController(UserFacade userFacade, UserResourceAssembler userResourceAssembler){
        this.userFacade = userFacade;
        this.userResourceAssembler = userResourceAssembler;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Resources<Resource<UserDTO>>> getAll(){
       try{
           List<UserDTO> users = userFacade.findAllUsers();
           List<Resource<UserDTO>> usersResource = new ArrayList<>();
           for (UserDTO userDTO : users){
               usersResource.add(userResourceAssembler.toResource(userDTO));
           }
           Resources<Resource<UserDTO>> resultResources = new Resources<>(usersResource);
           resultResources.add(linkTo(UserController.class).withSelfRel().withType("GET"));
           return new ResponseEntity<>(resultResources, HttpStatus.OK);
       }catch (DnDServiceException ex){
            throw new DnDServiceException("");
       }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Resource<UserDTO> getById(@PathVariable Long id){
        try{
            return userResourceAssembler.toResource(userFacade.findById(id));
        }catch (DnDServiceException ex){
            throw new DnDServiceException("");
        }
    }

    @PostMapping
    public ResponseEntity<Void> createUser(@RequestBody @Valid UserCreateDTO userCreateDTO){
        try{
            userFacade.createUser(userCreateDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (DnDServiceException ex){
            throw new DnDServiceException("");
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        try{
            userFacade.deleteUser(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (DnDServiceException ex){
            throw new DnDServiceException("");
        }
    }

    @PutMapping
    public ResponseEntity<Void> updateUser(@RequestBody @Valid UserUpdateDTO userUpdateDTO){
        try{
            userFacade.updateUser(userUpdateDTO);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (DnDServiceException ex){
            throw new DnDServiceException("");
        }
    }

    @PutMapping("{userId}/heroes/{heroId}/add")
    public ResponseEntity<Void> addHeroToUser(@PathVariable Long userId,
                                              @PathVariable Long heroId){
        try{
            userFacade.addHeroToUser(userId, heroId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (DnDServiceException ex){
            throw new DnDServiceException("");
        }
    }

    @PutMapping("{userId}/heroes/{heroId}/remove")
    public ResponseEntity<Void> removeHeroFromUser(@PathVariable Long userId,
                                                   @PathVariable Long heroId){
        try{
            userFacade.removeHeroFromUser(userId, heroId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (DnDServiceException ex){
            throw new DnDServiceException("");
        }
    }
}