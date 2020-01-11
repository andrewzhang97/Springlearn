CREATE TABLE comments
(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    parent_id BIGINT NOT NULL ,
    type INT NOT NULL,
    Commentator INT NOT NULL,
    Gmt_create BIGINT NOT NULL,
    Gmt_modified BIGINT NOT NULL,
    Like_count BIGINT DEFAULT 0
);