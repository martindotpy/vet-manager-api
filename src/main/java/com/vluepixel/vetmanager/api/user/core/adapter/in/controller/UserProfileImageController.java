package com.vluepixel.vetmanager.api.user.core.adapter.in.controller;

import static com.vluepixel.vetmanager.api.shared.adapter.in.util.ResponseShortcuts.error;
import static com.vluepixel.vetmanager.api.shared.adapter.in.util.ResponseShortcuts.ok;
import static com.vluepixel.vetmanager.api.shared.domain.validation.Validator.validate;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.vluepixel.vetmanager.api.auth.core.adapter.in.response.AuthenticationResponse;
import com.vluepixel.vetmanager.api.auth.core.application.port.out.GetCurrentUserPort;
import com.vluepixel.vetmanager.api.image.core.domain.model.enums.ImageMimeType;
import com.vluepixel.vetmanager.api.shared.adapter.in.response.FailureResponse;
import com.vluepixel.vetmanager.api.shared.application.annotation.RestControllerAdapter;
import com.vluepixel.vetmanager.api.shared.domain.exception.ValidationException;
import com.vluepixel.vetmanager.api.shared.domain.validation.ValidationError;
import com.vluepixel.vetmanager.api.shared.domain.validation.impl.EnumValidation;
import com.vluepixel.vetmanager.api.shared.domain.validation.impl.InvalidStateValidation;
import com.vluepixel.vetmanager.api.user.core.adapter.in.response.UserResponse;
import com.vluepixel.vetmanager.api.user.core.application.port.in.UpdateUserProfileImagePort;
import com.vluepixel.vetmanager.api.user.core.domain.request.UpdateUserProfileImageRequest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * User image profile controller
 */
@Slf4j
@Tag(name = "User Profile Image", description = "User profile image operations")
@RestControllerAdapter
@RequiredArgsConstructor
@RequestMapping("/user/profile-image")
public class UserProfileImageController {
    private final GetCurrentUserPort getCurrentUserPort;

    private final UpdateUserProfileImagePort updateUserImageProfilePort;

    /**
     * Update current user profile image
     *
     * @param imageFile The image file.
     * @return response with the updated user profile image
     * @throws IOException         If an error occurs while reading the image file.
     * @throws ValidationException If the image type is not valid or the image file
     *                             is empty.
     */
    @Operation(summary = "Update current user profile image", description = "Update the current user profile image", responses = {
            @ApiResponse(responseCode = "200", description = "User profile image updated successfully", content = @Content(schema = @Schema(implementation = AuthenticationResponse.class))),
            @ApiResponse(responseCode = "422", description = "Validation error", content = @Content(schema = @Schema(implementation = ValidationError.class))),
            @ApiResponse(responseCode = "500", description = "Unexpected error", content = @Content(schema = @Schema(implementation = FailureResponse.class)))
    })
    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updateCurrentUser(
            @RequestParam(name = "image_file") MultipartFile imageFile)
            throws ValidationException, IOException {
        validate(EnumValidation.of(
                ImageMimeType.class,
                imageFile.getContentType(),
                (type) -> type.getValue(),
                "param.image_file"),
                InvalidStateValidation.of(
                        imageFile.getBytes().length < 1,
                        "param.image_file",
                        "El archivo de imagen no puede estar vacío"));

        UpdateUserProfileImageRequest request;

        try {
            request = UpdateUserProfileImageRequest.builder()
                    .userId(getCurrentUserPort.get().getId())
                    .type(ImageMimeType.fromValue(imageFile.getContentType()))
                    .data(imageFile.getBytes())
                    .build();
        } catch (IOException e) {
            log.error("Error reading image file", e);

            return error("Error inesperado leyendo el archivo", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return ok(() -> updateUserImageProfilePort.updateCurrentUser(request),
                "Image del perfil de usuario ha sido actualizado correctamente");
    }

    /**
     * Update user profile image by id
     *
     * @param imageFile The image file.
     * @param id        User id
     * @return response with the updated user profile image
     * @throws IOException         If an error occurs while reading the image file.
     * @throws ValidationException If the image type is not valid or the image file.
     */
    @Operation(summary = "Update user profile image by id", description = "Update the user profile image by id", responses = {
            @ApiResponse(responseCode = "200", description = "User profile image updated successfully", content = @Content(schema = @Schema(implementation = UserResponse.class))),
            @ApiResponse(responseCode = "422", description = "Validation error", content = @Content(schema = @Schema(implementation = ValidationError.class))),
            @ApiResponse(responseCode = "500", description = "Unexpected error", content = @Content(schema = @Schema(implementation = FailureResponse.class)))
    })
    @PutMapping(path = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updateById(
            @RequestParam(name = "image_file") MultipartFile imageFile,
            @PathVariable Long id)
            throws ValidationException, IOException {
        validate(EnumValidation.of(
                ImageMimeType.class,
                imageFile.getContentType(),
                (type) -> type.getValue(),
                "param.image_file"),
                InvalidStateValidation.of(
                        imageFile.getBytes().length < 1,
                        "param.image_file",
                        "El archivo de imagen no puede estar vacío"));

        UpdateUserProfileImageRequest request;

        try {
            request = UpdateUserProfileImageRequest.builder()
                    .userId(id)
                    .type(ImageMimeType.fromValue(imageFile.getContentType()))
                    .data(imageFile.getBytes())
                    .build();
        } catch (Exception e) {
            log.error("Error reading image file", e);

            return error("Error inesperado leyendo el archivo", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return ok(() -> updateUserImageProfilePort.update(request),
                "Image del perfil de usuario ha sido actualizado correctamente");
    }
}
