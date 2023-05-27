package com.example.gcdkeulengspring.controller;

import com.example.gcdkeulengspring.domain.AppUser;
import com.example.gcdkeulengspring.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Tag(name = "App User", description = "Management of the application Users")
@RestController
@AllArgsConstructor
@RequestMapping("api/user")
public class UserController {
    private UserService userService;

    @Operation(
            summary = "Retrieve all users in the database",
            description = "Get all users by simple calling the endpoint. The response is User object with all the user details",
            tags = {"User", "get"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = AppUser.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema())}),
            @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})})
    @GetMapping()
    public ResponseEntity<List<AppUser>> getAllUsers() {
        return ResponseEntity.ok().body(userService.getAllUsers());
    }

    @Operation(
            summary = "Create new user in the database",
            description = "Create new user by simple calling the endpoint. Make user to put in the appropriate information",
            tags = {"User", "Post"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = AppUser.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema())}),
            @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})})
    @PostMapping()
    public ResponseEntity<AppUser> createUser(@RequestBody AppUser appUser) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("api/user").toUriString());
        return ResponseEntity.created(uri).body(userService.addUser(appUser));
    }

    @Operation(
            summary = "Update the user details in the database",
            description = "Update a user found in the database by simple calling the endpoint. specify the user Id you want to update",
            tags = {"User", "Put"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = AppUser.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema())}),
            @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})})
    @PutMapping("{id}")
    public ResponseEntity<AppUser> updateUser(@PathVariable("id") Long userId, @RequestBody AppUser appUser) {
        appUser.setId(userId);
        AppUser updateAppUser = userService.updateUser(appUser);
        return new ResponseEntity<>(updateAppUser, HttpStatus.OK);
    }


    @Operation(
            summary = "Delete a user from the database",
            description = "Delete a user found in the database by simple calling the endpoint. specify the user Id you want to delete",
            tags = {"User", "delete"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = AppUser.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema())}),
            @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})})
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long userId) {
        userService.deleteUser(userId);
        return new ResponseEntity<>("User successfully deleted!", HttpStatus.OK);
    }
}
