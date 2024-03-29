package ru.itmentor.spring.boot_security.demo.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itmentor.spring.boot_security.demo.model.User;
import ru.itmentor.spring.boot_security.demo.service.UserService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/admin/api")
public class AdminRestController {
    private final UserService userService;

    @GetMapping()
    public List<User> findAll() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public User findById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @PostMapping()
    public ResponseEntity<HttpStatus> create(@RequestBody User user, String[] selectedRoles) {
        userService.saveUser(user, selectedRoles);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<HttpStatus> update(@RequestBody User user, @PathVariable("id") Long id, String[] selectedRoles) {
        User newUser = userService.findById(id);
        userService.updateUser(user, newUser, selectedRoles);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        userService.deleteById(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }


}
