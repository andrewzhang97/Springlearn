CREATE TABLE Questions
(
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(50),
    description TEXT,
    gmt_create BIGINT,
    gmt_modified BIGINT,
    creator_id INT,
    view_count INT,
    like_count INT,
    tags VARCHAR(256)
);