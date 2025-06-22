BEGIN TRANSACTION;

DROP TABLE IF EXISTS profiles;
DROP TABLE IF EXISTS images
DROP TABLE IF EXISTS users;


CREATE TABLE users (
	user_id SERIAL PRIMARY KEY,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	email varchar(100) NOT NULL,
);

CREATE TABLE images (
    image_id SERIAL PRIMARY KEY,
    user_id INT NOT NULL REFERENCES users(user_id) ON DELETE CASCADE,
    image_path varchar(255) NOT NULL,
);

CREATE TABLE profiles (
    profile_id SERIAL PRIMARY KEY,
    user_id INT NOT NULL REFERENCES users(user_id) ON DELETE CASCADE,
    profile_picture_id INT,
    first_name varchar(50),
    last_name varchar(50),
    gender_identity varchar(50),
    interested_in varchar(50),
    location varchar(100),
    bio varchar(5000),
    skills varchar(500),
    criminal_record varchar(500),
    education varchar(500),
);

COMMIT TRANSACTION;
