-- 1. Создание таблицы users
CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    country VARCHAR(255) NOT NULL,
    photo_url VARCHAR(255)
);

-- 2. Создание таблицы credentials
CREATE TABLE credentials (
    id BIGSERIAL PRIMARY KEY,
    login VARCHAR(255),
    email VARCHAR(255),
    password VARCHAR(255),
    user_id BIGINT UNIQUE,
    refresh_token VARCHAR(255),
    CONSTRAINT fk_credentials_user FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);