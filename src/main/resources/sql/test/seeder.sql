-- USERS -----------------------------------------------------------------------
-- Insert users
INSERT INTO
    `user` (
        id,
        first_name,
        last_name,
        email,
        profile_image_url,
        `password`,
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