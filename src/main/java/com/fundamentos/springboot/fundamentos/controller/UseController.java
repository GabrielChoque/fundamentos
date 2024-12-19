package com.fundamentos.springboot.fundamentos.controller;

import com.fundamentos.springboot.fundamentos.caseuse.CreateUser;
import com.fundamentos.springboot.fundamentos.caseuse.DeleteUser;
import com.fundamentos.springboot.fundamentos.caseuse.GetUser;
import com.fundamentos.springboot.fundamentos.caseuse.UpdateUser;
import com.fundamentos.springboot.fundamentos.dto.UserDto;
import com.fundamentos.springboot.fundamentos.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UseController {
    private GetUser getUser;
    private CreateUser createUser;
    private DeleteUser deleteUser;
    private UpdateUser updateUser;

    public UseController(GetUser getUser, CreateUser createUser, DeleteUser deleteUser, UpdateUser updateUser) {
        this.getUser = getUser;
        this.createUser = createUser;
        this.deleteUser = deleteUser;
        this.updateUser = updateUser;

    }

    @GetMapping("/list-user")
    List<User> getAllUser(){
        return getUser.getAll();
    }
    @PutMapping(value = "/create-user", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> createUser(@RequestBody UserDto newUser) {
        return new ResponseEntity<>(createUser.save(newUser), HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    ResponseEntity deleteUser(@PathVariable Long id){
        deleteUser.remove(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
    @PutMapping("/update-user/{id}")
    ResponseEntity<User> updateUser (@RequestBody UserDto newUser, @PathVariable Long id){
    return new ResponseEntity<>(updateUser.update(newUser , id), HttpStatus.OK);

    }
}
