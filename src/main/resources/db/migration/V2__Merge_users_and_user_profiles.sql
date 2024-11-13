-- V2__Merge_users_and_user_profiles.sql
-- This script merges `users` and `user_profiles` into a single table

-- Step 1: Create a new temporary `users_new` table with merged columns
CREATE TABLE app_users.users_new (
    id SERIAL PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password_hash TEXT NOT NULL,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    bio TEXT,
    profile_picture VARCHAR(255),
    website VARCHAR(255),
    location VARCHAR(100),
    phone_number VARCHAR(15),
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- Add indexes to the new `users_new` table
CREATE INDEX idx_users_new_email ON app_users.users_new(email);
CREATE INDEX idx_users_new_username ON app_users.users_new(username);

-- Add a check constraint for email format
ALTER TABLE app_users.users_new
    ADD CONSTRAINT chk_email_format CHECK (email ~* '^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,}$');

-- Step 2: Migrate data from the original `users` and `user_profiles` tables
INSERT INTO app_users.users_new (id, username, email, password_hash, first_name, last_name, bio, profile_picture, website, location, phone_number, created_at, updated_at)
SELECT u.id, u.username, u.email, u.password_hash, u.first_name, u.last_name, p.bio, p.profile_picture, p.website, p.location, p.phone_number, u.created_at, u.updated_at
FROM app_users.users u
         LEFT JOIN app_users.user_profiles p ON u.id = p.user_id;

-- Step 3: Drop foreign key constraints and the old `user_profiles` table
ALTER TABLE app_users.user_roles DROP CONSTRAINT user_roles_user_id_fkey;
ALTER TABLE app_users.user_sessions DROP CONSTRAINT user_sessions_user_id_fkey;

DROP TABLE IF EXISTS app_users.user_profiles;
DROP TABLE IF EXISTS app_users.users;

-- Step 4: Rename `users_new` to `users`
ALTER TABLE app_users.users_new RENAME TO users;

-- Step 5: Re-establish foreign key constraints on `user_roles` and `user_sessions` tables
ALTER TABLE app_users.user_roles
    ADD CONSTRAINT user_roles_user_id_fkey FOREIGN KEY (user_id) REFERENCES app_users.users(id) ON DELETE CASCADE;

ALTER TABLE app_users.user_sessions
    ADD CONSTRAINT user_sessions_user_id_fkey FOREIGN KEY (user_id) REFERENCES app_users.users(id) ON DELETE CASCADE;
