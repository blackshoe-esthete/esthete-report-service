CREATE TABLE IF NOT EXISTS users (
                                     user_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                     user_uuid BINARY(16) NOT NULL,
    nickname VARCHAR(50) NOT NULL,
    profile_cloudfront_url VARCHAR(50) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );

CREATE TABLE IF NOT EXISTS reports (
                                       report_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                       reporter_uuid BINARY(16) NOT NULL,
    writer_uuid BINARY(16) NOT NULL,
    definition VARCHAR(50) NOT NULL,
    type VARCHAR(50) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );

CREATE TABLE IF NOT EXISTS photos (
                                      photo_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                      exhibition_title VARCHAR(50) NOT NULL,
    report_id BIGINT NOT NULL,
    photo_uuid BINARY(16) NOT NULL,
    photo_cloudfront_url VARCHAR(500) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (report_id) REFERENCES reports(report_id)
    );

CREATE TABLE IF NOT EXISTS comments (
                                        comment_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                        comment_uuid BINARY(16) NOT NULL,
    report_id BIGINT NOT NULL,
    content VARCHAR(50) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (report_id) REFERENCES reports(report_id)
    );

CREATE TABLE IF NOT EXISTS admins (
                                      admin_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                      admin_uuid BINARY(16) NOT NULL,
    email VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );