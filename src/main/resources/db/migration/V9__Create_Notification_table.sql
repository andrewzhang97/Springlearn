CREATE TABLE Notification
(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    Notifier BIGINT NOT NULL,
    Receiver BIGINT NOT NULL,
    OuterId BIGINT NOT NULL,
    type INT NOT NULL,
    gmt_create BIGINT NOT NULL,
    status INT DEFAULT 0 NOT NULL
);