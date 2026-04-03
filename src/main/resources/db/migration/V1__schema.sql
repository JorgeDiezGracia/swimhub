CREATE TABLE IF NOT EXISTS communities (
                                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                           name VARCHAR(100) NOT NULL UNIQUE,
    code VARCHAR(2)
    );

CREATE TABLE IF NOT EXISTS federations (
                                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                           name VARCHAR(100) NOT NULL UNIQUE,
    website VARCHAR(100),
    community_id BIGINT NOT NULL,
    FOREIGN KEY (community_id) REFERENCES communities(id)
    );

CREATE TABLE IF NOT EXISTS leagues (
                                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                       name VARCHAR(100) NOT NULL,
    province VARCHAR(100),
    federation_id BIGINT NOT NULL,
    FOREIGN KEY (federation_id) REFERENCES federations(id)
    );

CREATE TABLE IF NOT EXISTS clubs (
                                     id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                     name VARCHAR(100) NOT NULL,
    city VARCHAR(100),
    email VARCHAR(100),
    league_id BIGINT NOT NULL,
    FOREIGN KEY (league_id) REFERENCES leagues(id)
    );

CREATE TABLE IF NOT EXISTS categories (
                                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                          name VARCHAR(50) NOT NULL UNIQUE,
    min_age INT,
    max_age INT
    );

CREATE TABLE IF NOT EXISTS swimmers (
                                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                        name VARCHAR(100) NOT NULL,
    surname VARCHAR(100) NOT NULL,
    birth_date DATE NOT NULL,
    gender VARCHAR(1) NOT NULL,
    license_number VARCHAR(20) UNIQUE,
    club_id BIGINT NOT NULL,
    category_id BIGINT NOT NULL,
    FOREIGN KEY (club_id) REFERENCES clubs(id),
    FOREIGN KEY (category_id) REFERENCES categories(id)
    );

CREATE TABLE IF NOT EXISTS races (
                                     id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                     style VARCHAR(50) NOT NULL,
    distance INT NOT NULL,
    pool_type VARCHAR(20) NOT NULL
    );

CREATE TABLE IF NOT EXISTS events (
                                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                      name VARCHAR(150) NOT NULL,
    date DATE NOT NULL,
    location VARCHAR(150),
    pool_type VARCHAR(20),
    federation_id BIGINT NOT NULL,
    FOREIGN KEY (federation_id) REFERENCES federations(id)
    );

CREATE TABLE IF NOT EXISTS time_records (
                                            id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                            time DOUBLE NOT NULL,
                                            date DATE NOT NULL,
                                            status VARCHAR(20),
    swimmer_id BIGINT NOT NULL,
    race_id BIGINT NOT NULL,
    event_id BIGINT NOT NULL,
    FOREIGN KEY (swimmer_id) REFERENCES swimmers(id),
    FOREIGN KEY (race_id) REFERENCES races(id),
    FOREIGN KEY (event_id) REFERENCES events(id)
    );

CREATE TABLE IF NOT EXISTS `records` (
                                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                         time DOUBLE NOT NULL,
                                         date DATE NOT NULL,
                                         gender VARCHAR(1) NOT NULL,
    race_id BIGINT NOT NULL,
    category_id BIGINT NOT NULL,
    federation_id BIGINT NOT NULL,
    swimmer_id BIGINT NOT NULL,
    FOREIGN KEY (race_id) REFERENCES races(id),
    FOREIGN KEY (category_id) REFERENCES categories(id),
    FOREIGN KEY (federation_id) REFERENCES federations(id),
    FOREIGN KEY (swimmer_id) REFERENCES swimmers(id)
    );