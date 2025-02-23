-- USERS -----------------------------------------------------------------------
-- Insert users
INSERT INTO
    `user` (
        id,
        first_name,
        last_name,
        email,
        profile_image_url,
        password,
        roles,
        deleted
    )
VALUES
    -- * Admin:
    -- JWT: eyJhbGciOiJIUzI1NiJ9.eyJ1c2VyIjp7ImlkIjoxLCJmaXJzdF9uYW1lIjoiQWRtaW4iLCJsYXN0X25hbWUiOiJBZG1pbiIsImVtYWlsIjoiYWRtaW5AYWRtaW4uY29tIiwicm9sZXMiOlsiQURNSU4iXSwicHJvZmlsZV9pbWFnZV91cmwiOm51bGx9LCJzdWIiOiIxIn0.mj6KdYCJbXmZbXts1HDG252mzT-VUX8WienLj0hh2WU
    -- Password: admin
    -- * User:
    -- JWT: eyJhbGciOiJIUzI1NiJ9.eyJ1c2VyIjp7ImlkIjoyLCJmaXJzdF9uYW1lIjoiVXNlciIsImxhc3RfbmFtZSI6IlVzZXIiLCJlbWFpbCI6InVzZXJAdXNlci5jb20iLCJyb2xlcyI6WyJVU0VSIl0sInByb2ZpbGVfaW1hZ2VfdXJsIjpudWxsfSwic3ViIjoiMiJ9.j43TE5dJHU1_DLME_7yj6Ux-wXrf_M7jYliaMBybsbE
    -- Password: user
    (
        2,
        'User',
        'User',
        'user@user.com',
        NULL,
        '$2a$10$rGBA6G7JkDQscH3KQD1no.wUUjm1jupdpiGYH1Ie2ejE2QO9crCX.',
        'USER',
        0
    );

INSERT INTO
    client (
        deleted,
        identification,
        first_name,
        last_name,
        address,
        identification_type
    )
VALUES
    (
        0,
        '12345678',
        'Juan',
        'Pérez',
        'Av. Santa Anita',
        'DNI'
    ),
    (
        0,
        '98765432',
        'María',
        'Gómez',
        'Calle Tutancamon',
        'FOREIGNER_CARNET'
    );

INSERT INTO
    client_emails (client_id, emails)
VALUES
    (1, 'firstclient@firstclient.com'),
    (2, 'secondclient@secondclient.com'),
    (1, 'firstclient@firstclient.com'),
    (2, 'secondclient@secondclient.com');

INSERT INTO
    client_phones (client_id, phones)
VALUES
    (1, '999999999'),
    (2, '922222222');

INSERT INTO
    species (id, name)
VALUES
    (1, 'Perro'),
    (2, 'Gato'),
    (3, 'Hámster');

INSERT INTO
    race (id, species_id, name)
VALUES
    (1, 1, 'Poodle'),
    (2, 1, 'Chihuahua'),
    (3, 2, 'Siamés'),
    (4, 2, 'Persa');

INSERT INTO
    patient (
        birth_date,
        deceased,
        deleted,
        race_id,
        id,
        owner_id,
        name,
        characteristics,
        gender
    )
VALUES
    (
        '2025-02-16',
        0,
        0,
        2,
        1,
        1,
        'Miausculus',
        'Es fuerte e inteligente',
        'MALE'
    ),
    (
        '2025-02-16',
        0,
        0,
        1,
        2,
        2,
        'Firulays',
        'Simplemente Firulays',
        'FEMALE'
    );

INSERT INTO
    appointment (
        deleted,
        created_at,
        created_by_id,
        id,
        patient_id,
        start_at,
        `description`
    )
VALUES
    (
        0,
        '2025-02-19T23:51:17.578',
        1,
        1,
        1,
        '2025-02-20T23:51:17.578',
        'Limpieza dental'
    ),
    (
        0,
        '2025-02-17T23:51:17.578',
        2,
        2,
        2,
        '2025-02-25T23:51:17.578',
        'Ta sucio'
    );

INSERT INTO
    appointment_type (
        duration_in_minutes,
        id,
        price,
        `name`
    )
VALUES 
    (
        30,
        1,
        100,
        'Limpieza'
    ),
    (
        20,
        2,
        150,
        'Operación'
    ),
    (
        60,
        3,
        350,
        'Paseo'
    );

INSERT INTO
    category (
        id,
        `name`
    )
VALUES 
    (
        1,
        'Salud'
    ),
    (
        2,
        'Higiene'
    );

INSERT INTO
    bill (
        deleted,
        discount,
        paid,
        total,
        total_paid,
        client_id,
        id,
        created_at,
        last_paid_at
    )
VALUES 
    (
        0,
        0.2,
        0,
        200.0,
        100.0,
        1,
        1,
        '2025-02-19T23:51:17.578',
        '2025-02-19T23:51:17.578'
    ),
    (
        0,
        0.2,
        0,
        200.0,
        100.0,
        2,
        2,
        '2025-02-19T23:51:17.578',
        '2025-02-19T23:51:17.578'
    );

INSERT INTO
    product (
        price,
        quantity,
        id,
        `name`,
        `description`,
        updated_at
    )
VALUES 
    (
        199.0,
        10,
        1,
        'Anti Pulgas',
        'Anti Pulgas para mascotas del hogar',
        '2025-02-19T23:51:17.578'
    ),
    (
        50.0,
        10,
        2,
        'Collar',
        'Collares para mascotas del hogar',
        '2025-02-19T23:51:17.578'
    );

INSERT INTO
    product_categories (
        category_id,
        product_id
    )
VALUES 
    (
        1,
        1
    ),
    (
        1,
        2
    );

INSERT INTO
    appointment_details (
        appointment_type_id,
        duration_in_minutes,
        price,
        id
    )
VALUES 
    (
        1,
        120,
        200,
        1
    ),
    (
        2,
        60,
        100,
        2
    );

INSERT INTO
    appointment_appointment_details (
        appointment_id,
        details_id
    )
VALUES 
    (
        1,
        1
    ),
    (
        2,
        2
    );

INSERT INTO
    appointment_sale (
        discount,
        price,
        appointment_id,
        bill_id,
        id,
        seller_id
    )
VALUES 
    (
        20,
        300,
        1,
        1,
        1,
        1
    ),
    (
        10,
        400,
        2,
        2,
        2,
        2
    );

INSERT INTO
    medical_history (
        deleted,
        created_at,
        id,
        patient_id,
        updated_at,
        content
    )
VALUES
    (
        0,
        '2025-02-19T23:51:17.578',
        1,
        1,
        '2025-02-19T23:51:17.578',
        'Primera cita'
    ),
    (
        0,
        '2025-02-19T23:51:17.578',
        2,
        1,
        '2025-02-19T23:51:17.578',
        'Segunda cita'
    ),
    (
        0,
        '2025-02-19T23:51:17.578',
        3,
        2,
        '2025-02-19T23:51:17.578',
        'Primera cita'
    );