package com.madadipouya.springcustomserializer.rest;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(value = "/v1/users")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<CreateUserResponse> createUser(@Validated @RequestBody CreateUserRequest createUserRequest) {
        logger.info("Creating a user with firstName: {}, lastName: {} and email: {}",
                createUserRequest.getFirstName(), createUserRequest.getLastName(), createUserRequest.getEmail());

        return ResponseEntity.ok(CreateUserResponse.builder()
                .id(UUID.randomUUID().toString())
                .firstName(createUserRequest.getFirstName())
                .lastName(createUserRequest.getLastName())
                .emailAddress(createUserRequest.getEmail())
                .build());
    }

    @Builder
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CreateUserRequest {

        @NotBlank(message = "First name cannot be empty or not")
        private String firstName;

        @NotBlank(message = "Last name cannot be empty or not")
        private String lastName;

        @Email(message = "Provide a valid email address")
        @NotBlank(message = "Email address cannot be empty or not")
        private String email;
    }

    @Builder
    @Getter
    public static class CreateUserResponse {

        private String id;

        private String firstName;

        private String lastName;

        private String emailAddress;
    }
}
