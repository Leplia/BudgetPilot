CREATE TABLE users (
   id          UUID PRIMARY KEY DEFAULT gen_random_uuid(),
   first_name  VARCHAR(255) NOT NULL,
   last_name   VARCHAR(255) NOT NULL,
   country     VARCHAR(255) NOT NULL,
   photo_url   VARCHAR(255),

   created_at  TIMESTAMP NOT NULL DEFAULT now(),
   updated_at  TIMESTAMP,
   created_by  UUID REFERENCES users (id)
);

CREATE TABLE credentials (
    id             UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    login          VARCHAR(255),
    email          VARCHAR(255),
    password       VARCHAR(255),
    user_id        UUID UNIQUE,
    refresh_token  VARCHAR(255),
    CONSTRAINT fk_credentials_user FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);