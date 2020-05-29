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

/**
 * User rest controller
 * @author Boris Jadus
 */
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

    /**
     * Get list of users
     *
     * @return resource with list of users
     */
    @RolesAllowed("ROLE_ADMIN")
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

    /**
     * Get user by identifier id
     *
     * @param id identifier of user
     * @return Resource<UserDTO>
     */
    @RolesAllowed("ROLE_ADMIN")
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Resource<UserDTO>> getById(@PathVariable Long id){
        try{
            return new ResponseEntity<>(userResourceAssembler.toResource(userFacade.findById(id)), HttpStatus.OK);
        }catch (Exception ex){
            throw ExceptionSorter.throwException(ex);
        }
    }

    /**
     * Registers new user by POST method
     *
     * @param userCreateDTO UserCreateDTO with required fields for registration
     * @return id of registered user
     */
    @PostMapping
    public ResponseEntity<Long> registerNewUser(@RequestBody @Valid UserCreateDTO userCreateDTO){
        try{
            return new ResponseEntity<>(userFacade.createUser(userCreateDTO), HttpStatus.CREATED);
        }catch (Exception ex){
            throw ExceptionSorter.throwException(ex);
        }
    }

    /**
     * Delete user by identifier id
     *
     * @param id identifier of user
     */
    @RolesAllowed("ROLE_ADMIN")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        try{
            userFacade.deleteUser(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (Exception ex){
            throw ExceptionSorter.throwException(ex);
        }
    }

    /**
     * Updates given user
     *
     * @param userUpdateDTO UserUpdateDTO with updated values
     */
    @RolesAllowed("ROLE_ADMIN")
    @PutMapping
    public ResponseEntity<Void> updateUser(@RequestBody @Valid UserUpdateDTO userUpdateDTO){
        try{
            userFacade.updateUser(userUpdateDTO);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (Exception ex){
            throw ExceptionSorter.throwException(ex);
        }
    }

    /**
     * Adds given hero to given user
     *
     * @param userId identifier of user
     * @param heroId identifier of hero
     */
    @RolesAllowed("ROLE_ADMIN")
    @PutMapping("/{userId}/heroes/{heroId}/add")
    public ResponseEntity<Void> addHeroToUser(@PathVariable Long userId,
                                              @PathVariable Long heroId){
        try{
            userFacade.addHeroToUser(userId, heroId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (Exception ex){
            throw ExceptionSorter.throwException(ex);
        }
    }

    /**
     * Removes given hero to given user
     *
     * @param userId identifier of user
     * @param heroId identifier of hero
     */
    @RolesAllowed("ROLE_ADMIN")
    @PutMapping("/{userId}/heroes/{heroId}/remove")
    public ResponseEntity<Void> removeHeroFromUser(@PathVariable Long userId,
                                                   @PathVariable Long heroId){
        try{
            userFacade.removeHeroFromUser(userId, heroId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (Exception ex){
            throw ExceptionSorter.throwException(ex);
        }
    }

    /**
     * User login
     *
     * @param name username of user
     * @param pass password of hero
     * @return Resource<UserDTO>
     */
    @GetMapping("/login/{name}/password/{pass}")
    public ResponseEntity<Resource<UserDTO>> login(@PathVariable String name, @PathVariable String pass){
        try {
            UserDTO userDTO = userFacade.login(name, pass);
            return new ResponseEntity<>(userResourceAssembler.toResource(userDTO), HttpStatus.OK);
        }catch (Exception ex){
            throw ExceptionSorter.throwException(ex);
        }
    }

    /**
     * User logout
     */
    @RolesAllowed("ROLE_USER")
    @GetMapping("/logout")
    public ResponseEntity<Void> logout(){
        try {
            userFacade.logout();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (Exception ex){
            throw ExceptionSorter.throwException(ex);
        }
    }

    /**
     * Gets current logged in user
     *
     * @return Resource<UserDTO>
     */
    @RolesAllowed("ROLE_USER")
    @GetMapping("/current")
    public ResponseEntity<Resource<UserDTO>> getCurrentUser(){
        try{
            UserDTO userDTO = userFacade.getCurrentUser();
            return new ResponseEntity<>(userResourceAssembler.toResource(userDTO), HttpStatus.OK);
        }catch (Exception ex){
            throw ExceptionSorter.throwException(ex);
        }
    }

    /**
     * Checks whether current is usr is admin or not
     *
     * @return bool true if admin
     */
    @GetMapping("/admin")
    public boolean isAdmin(){
        try{
            return userFacade.isAdmin();
        }catch (Exception ex){
            throw ExceptionSorter.throwException(ex);
        }
    }
}
