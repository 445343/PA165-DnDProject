package cz.fi.muni.PA165.rest.controllers;

import cz.fi.muni.PA165.api.dto.user.UserCreateDTO;
import cz.fi.muni.PA165.api.dto.user.UserDTO;
import cz.fi.muni.PA165.api.dto.user.UserUpdateDTO;
import cz.fi.muni.PA165.api.exceptions.DnDServiceException;
import cz.fi.muni.PA165.api.facade.UserFacade;
import cz.fi.muni.PA165.rest.assemblers.UserResourceAssembler;
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

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/rest/users")
public class UserController {

    private UserFacade userFacade;
    private UserResourceAssembler userResourceAssembler;

    @Autowired
    public UserController(UserFacade userFacade, UserResourceAssembler userResourceAssembler){
        this.userFacade = userFacade;
        this.userResourceAssembler = userResourceAssembler;
    }

    //@RolesAllowed("ROLE_ADMIN")
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
       }catch (Exception ex){
           throw ExceptionSorter.throwException(ex);
       }
    }

    @RolesAllowed("ROLE_ADMIN")
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Resource<UserDTO>> getById(@PathVariable Long id){
        try{
            return new ResponseEntity<>(userResourceAssembler.toResource(userFacade.findById(id)), HttpStatus.OK);
        }catch (Exception ex){
            throw ExceptionSorter.throwException(ex);
        }
    }


    @PostMapping
    public ResponseEntity<Long> registerNewUser(@RequestBody @Valid UserCreateDTO userCreateDTO){
        try{
            return new ResponseEntity<>(userFacade.createUser(userCreateDTO), HttpStatus.CREATED);
        }catch (DnDServiceException ex){
            throw ExceptionSorter.throwException(ex);
        }
    }

    //@RolesAllowed("ROLE_ADMIN")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        try{
            userFacade.deleteUser(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (DnDServiceException ex){
            throw ExceptionSorter.throwException(ex);
        }
    }
    @RolesAllowed("ROLE_ADMIN")
    @PutMapping
    public ResponseEntity<Void> updateUser(@RequestBody @Valid UserUpdateDTO userUpdateDTO){
        try{
            userFacade.updateUser(userUpdateDTO);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (DnDServiceException ex){
            throw ExceptionSorter.throwException(ex);
        }
    }

    @RolesAllowed("ROLE_ADMIN")
    @PutMapping("{userId}/heroes/{heroId}/add")
    public ResponseEntity<Void> addHeroToUser(@PathVariable Long userId,
                                              @PathVariable Long heroId){
        try{
            userFacade.addHeroToUser(userId, heroId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (DnDServiceException ex){
            throw ExceptionSorter.throwException(ex);
        }
    }

    @RolesAllowed("ROLE_ADMIN")
    @PutMapping("{userId}/heroes/{heroId}/remove")
    public ResponseEntity<Void> removeHeroFromUser(@PathVariable Long userId,
                                                   @PathVariable Long heroId){
        try{
            userFacade.removeHeroFromUser(userId, heroId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (DnDServiceException ex){
            throw ExceptionSorter.throwException(ex);
        }
    }

    @GetMapping("/login/{name}/password/{pass}")
    public ResponseEntity<Resource<UserDTO>> login(@PathVariable String name, @PathVariable String pass){
        try {
            UserDTO userDTO = userFacade.login(name, pass);
            return new ResponseEntity<>(userResourceAssembler.toResource(userDTO), HttpStatus.OK);
        }catch (DnDServiceException ex){
            throw ExceptionSorter.throwException(ex);
        }
    }

    @RolesAllowed("ROLE_USER")
    //@GetMapping("/logout")
    public ResponseEntity<Void> logout(){
        try {
            userFacade.logout();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (DnDServiceException ex){
            throw ExceptionSorter.throwException(ex);
        }
    }

    @RolesAllowed("ROLE_USER")
    @GetMapping("/current")
    public ResponseEntity<Resource<UserDTO>> getCurrentUser(){
        try{
            UserDTO userDTO = userFacade.getCurrentUser();
            return new ResponseEntity<>(userResourceAssembler.toResource(userDTO), HttpStatus.OK);
        }catch (DnDServiceException ex){
            throw ExceptionSorter.throwException(ex);
        }
    }
}
