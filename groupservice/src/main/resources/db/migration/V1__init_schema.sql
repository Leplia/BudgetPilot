-- 1. Создание таблицы family_groups
CREATE TABLE family_groups (
                               id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                               name VARCHAR(255),

    -- Поля аудита (Auditable)
                               created_at TIMESTAMP NOT NULL DEFAULT now(),
                               updated_at TIMESTAMP,
                               created_by UUID
);

-- 2. Создание таблицы group_members
CREATE TABLE group_members (
                               id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                               group_id UUID NOT NULL,
                               user_id UUID,
                               role VARCHAR(50),
                               status VARCHAR(50),

    -- Поля аудита (Auditable)
                               created_at TIMESTAMP NOT NULL DEFAULT now(),
                               updated_at TIMESTAMP,
                               created_by UUID,

                               CONSTRAINT fk_group_members_group FOREIGN KEY (group_id) REFERENCES family_groups (id) ON DELETE CASCADE
);

-- 3. Создание таблицы invitations
CREATE TABLE invitations (
                             id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                             group_id UUID NOT NULL,
                             invited_email VARCHAR(255) NOT NULL,
                             invited_role VARCHAR(50) NOT NULL,
                             is_used BOOLEAN NOT NULL DEFAULT FALSE,
                             status VARCHAR(50) NOT NULL,
                             accepted_by_user_id UUID,

    -- Поля аудита (Auditable)
                             created_at TIMESTAMP NOT NULL DEFAULT now(),
                             updated_at TIMESTAMP,
                             created_by UUID,

                             CONSTRAINT fk_invitations_group FOREIGN KEY (group_id) REFERENCES family_groups (id) ON DELETE CASCADE
);

-- Индексы для внешних ключей и частых выборок по group_id
CREATE INDEX idx_group_members_group_id ON group_members(group_id);
CREATE INDEX idx_invitations_group_id ON invitations(group_id);