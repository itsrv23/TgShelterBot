package com.tgshelterbot.controller;

import com.tgshelterbot.model.dto.UserStateDto;
import com.tgshelterbot.service.impl.UserStateService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user/state")
public class UserStateController {
    private final UserStateService stateService;

    private final UserStateService service;

    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Get all",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Collection.class)
                    )
            )
    })
    @GetMapping
    public List<UserStateDto> getAll() {
        return service.getAll();
    }

    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Create",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = UserStateDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "not found"
            )
    })
    @PostMapping
    public ResponseEntity<UserStateDto> create(@Valid @RequestBody UserStateDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Find by id",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = UserStateDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "not found"
            )
    })
    @GetMapping("/{id}")
    public UserStateDto read(@PathVariable Long id) {
        return service.read(id);
    }

    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Update by id",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = UserStateDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "not found"
            )
    })
    @PutMapping("/{id}")
    public UserStateDto update(@PathVariable Long id, @Valid @RequestBody UserStateDto dto) {
        return service.update(id, dto);
    }

    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Delete animal by id",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = UserStateDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "not found"
            )
    })
    @DeleteMapping("/{id}")
    public UserStateDto delete(@PathVariable Long id) {
        return service.delete(id);
    }
}
