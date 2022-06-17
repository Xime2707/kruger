package com.kruger.inventario.controller;

import com.kruger.inventario.model.Usr;
import com.kruger.inventario.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 - Description:Rest APIs
 */
@RestController
@RequestMapping("/users")
public class Usr_C {

    @Autowired
    private UserServiceImpl userService;

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public List<Usr> listUser() {
        return userService.findAll();
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public Usr create(@RequestBody Usr user) {
        return userService.save(user);
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable(value = "id") Long id) {
        userService.delete(id);
        return "success";
    }
}