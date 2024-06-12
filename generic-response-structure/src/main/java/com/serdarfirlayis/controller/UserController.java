package com.serdarfirlayis.controller;

import com.serdarfirlayis.model.GenericResponse;
import com.serdarfirlayis.model.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.serdarfirlayis.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Get user name by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User name retrieved successfully",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = GenericResponse.class))}),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = GenericResponse.class))})
    })
    @GetMapping("/name")
    public ResponseEntity<GenericResponse<String>> getUserName(@RequestParam(value = "id") Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .header("custom-header", "custom-value")
                .body(GenericResponse.success(userService.getUserName(id), "User name retrieved successfully"));
    }

    @Operation(summary = "Get all users with pagination")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Users retrieved successfully",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = GenericResponse.class))})
    })
    @GetMapping("/all")
    public ResponseEntity<GenericResponse<List<User>>> getAllUsers(@RequestParam(value = "page") Integer page,
                                                                   @RequestParam(value = "size") Integer size) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .header("custom-header", "custom-value")
                .body(GenericResponse
                        .success(userService.getAllUsers(page, size), "Users retrieved successfully"));
    }
}
