SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS photos;
DROP TABLE IF EXISTS comments;
DROP TABLE IF EXISTS admins;
DROP TABLE IF EXISTS reports;
SET FOREIGN_KEY_CHECKS = 1;
-- test
CREATE TABLE IF NOT EXISTS users (
                                     user_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                     user_uuid BINARY(16) NOT NULL,
    nickname VARCHAR(50),
    profile_img_url VARCHAR(250),
    report_received_count BIGINT DEFAULT 0,
    report_made_count BIGINT DEFAULT 0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );

CREATE TABLE IF NOT EXISTS reports (
                                       report_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                       reporter_uuid BINARY(16) NOT NULL,
    writer_uuid BINARY(16) NOT NULL,
    description VARCHAR(100) NOT NULL,
    type VARCHAR(50) NOT NULL,
    reported_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );

CREATE TABLE IF NOT EXISTS photos (
                                      photo_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                      exhibition_title VARCHAR(50) NOT NULL,
    report_id BIGINT NOT NULL,
    photo_uuid BINARY(16) NOT NULL,
    photo_img_url VARCHAR(250) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (report_id) REFERENCES reports(report_id) ON DELETE CASCADE
    );

CREATE TABLE IF NOT EXISTS comments (
                                        comment_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                        comment_uuid BINARY(16) NOT NULL,
    report_id BIGINT NOT NULL,
    content VARCHAR(50) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (report_id) REFERENCES reports(report_id) ON DELETE CASCADE
    );

CREATE TABLE IF NOT EXISTS admins (
                                      admin_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                      admin_uuid BINARY(16) NOT NULL,
    email VARCHAR(50) NOT NULL,
    password VARCHAR(250) NOT NULL,
    `role` VARCHAR(20) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );