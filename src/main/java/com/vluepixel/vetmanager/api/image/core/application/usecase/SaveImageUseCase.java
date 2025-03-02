package com.vluepixel.vetmanager.api.image.core.application.usecase;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Value;

import com.vluepixel.vetmanager.api.image.core.application.port.in.SaveImagePort;
import com.vluepixel.vetmanager.api.image.core.domain.model.enums.ImageMimeType;
import com.vluepixel.vetmanager.api.shared.application.annotation.UseCase;
import com.vluepixel.vetmanager.api.shared.domain.exception.InternalServerErrorException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Save image use case.
 */
@Slf4j
@UseCase
@RequiredArgsConstructor
public final class SaveImageUseCase implements SaveImagePort {
    @Value("${server.host}")
    private String host;
    @Value("${spring.image.path}")
    private String imagesPath;
    @Value("${spring.mvc.servlet.path}")
    private String apiPath;
    private String protocol;

    @Override
    public String save(byte[] image, ImageMimeType type) {
        MDC.put("operationId", "Image type " + type);
        log.info("Saving image");

        try {
            String imageId = UUID.randomUUID().toString();
            Path path = Path.of(System.getProperty("user.dir"), imagesPath, imageId + "." + type.name().toLowerCase());

            if (!Files.exists(path.getParent())) {
                Files.createDirectories(path.getParent());
            }

            Files.write(path, image);

            log.info("Image saved");

            return getProtocol() + "://" + host + apiPath + "/image/" + imageId + "." + type.name().toLowerCase();
        } catch (Exception e) {
            log.error("Error while saving image", e);

            throw new InternalServerErrorException(e);
        }
    }

    private String getProtocol() {
        if (protocol == null) {
            String[] splittedHost = host.split("\\.");
            String domain = splittedHost[splittedHost.length - 1];

            if (domain.startsWith("localhost") || domain.startsWith("127.0.0.1")) {
                protocol = "http";
            } else {
                protocol = "https";
            }
        }

        return protocol;
    }
}
