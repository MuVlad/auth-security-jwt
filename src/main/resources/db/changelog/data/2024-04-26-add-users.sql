--liquibase formatted sql

--changeset muslimov_vlad:2
INSERT INTO users (id, email, username, password, role)
VALUES (1, 'mailadmin@mail.ru', 'admin', '$2a$10$QRWAbXveneW1d.EKZPnxV.D7hjqPfw9Ex4nJuJpLgiFCDFopflogC', 'ADMIN'),
       (2,'mailclient@mail.ru', 'client', '$2a$10$QRWAbXveneW1d.EKZPnxV.D7hjqPfw9Ex4nJuJpLgiFCDFopflogC', 'CLIENT');