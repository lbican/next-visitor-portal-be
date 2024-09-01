-- Create schema if it doesn't exist
CREATE SCHEMA IF NOT EXISTS app_users AUTHORIZATION postgres;

-- Create the 'users' table if it doesn't exist
DO $$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM information_schema.tables
                   WHERE table_schema = 'app_users'
                   AND table_name = 'users') THEN
CREATE TABLE app_users.users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password_hash TEXT NOT NULL,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);
CREATE INDEX idx_users_email ON app_users.users(email);
CREATE INDEX idx_users_username ON app_users.users(username);
ALTER TABLE app_users.users
    ADD CONSTRAINT chk_email_format CHECK (email ~* '^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,}$');
END IF;
END $$;

-- Create the 'user_profiles' table if it doesn't exist
DO $$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM information_schema.tables
                   WHERE table_schema = 'app_users'
                   AND table_name = 'user_profiles') THEN
CREATE TABLE app_users.user_profiles (
    user_id INT REFERENCES app_users.users(id) ON DELETE CASCADE,
    bio TEXT,
    profile_picture VARCHAR(255),
    website VARCHAR(255),
    location VARCHAR(100),
    phone_number VARCHAR(15),
    UNIQUE(user_id)
);
END IF;
END $$;


-- Create the 'roles' table if it doesn't exist
DO $$
BEGIN
IF NOT EXISTS (SELECT 1 FROM information_schema.tables
                   WHERE table_schema = 'app_users'
                   AND table_name = 'roles') THEN
CREATE TABLE app_users.roles (
    id SERIAL PRIMARY KEY,
    role_name VARCHAR(50) UNIQUE NOT NULL
);
END IF;
END $$;

DO $$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM information_schema.tables
                   WHERE table_schema = 'app_users'
                   AND table_name = 'user_roles') THEN
CREATE TABLE app_users.user_roles (
    user_id INT REFERENCES app_users.users(id) ON DELETE CASCADE,
    role_id INT REFERENCES app_users.roles(id) ON DELETE CASCADE,
    PRIMARY KEY(user_id, role_id)
);
END IF;
END $$;

-- Create the 'user_sessions' table if it doesn't exist
DO $$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM information_schema.tables
                   WHERE table_schema = 'app_users'
                   AND table_name = 'user_sessions') THEN
CREATE TABLE app_users.user_sessions (
    id SERIAL PRIMARY KEY,
    user_id INT REFERENCES app_users.users(id) ON DELETE CASCADE,
    session_token TEXT NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    expires_at TIMESTAMP WITH TIME ZONE NOT NULL,
    last_accessed TIMESTAMP WITH TIME ZONE
);
END IF;
END $$;