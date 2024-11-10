CREATE DATABASE IF NOT EXISTS `TypeTeam2`;
USE `TypeTeam2`;

DROP TABLE IF EXISTS `users_roles`;
DROP TABLE IF EXISTS `users_notes`;
DROP TABLE IF EXISTS `notes`;
DROP TABLE IF EXISTS `roles`;
DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
                         `user_id` VARCHAR(50) NOT NULL,
                         `pw` CHAR(75) NOT NULL,
                         `email` CHAR(75) NOT NULL,
                         `active` TINYINT NOT NULL,
                         PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `users` (user_id, pw, email, active)
VALUES
    ('bluu', '$2a$12$Lz/mcYZHc1D5kl1VaTYJouop8O/.MQvMRp2pexvZuY8v1cm9n55qu', 'test1@mail.com', 1),
    ('test', '$2a$12$HOGVXlx090ksYZx5IjB/XuD752mpjkx2XZf7A0zT.7nkBlZvNtnvC', 'test2@mail.com', 1);

CREATE TABLE `roles` (
                         `role_id` INT AUTO_INCREMENT PRIMARY KEY,
                         `role` VARCHAR(50) NOT NULL UNIQUE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `roles` (role)
VALUES
    ('USER');

CREATE TABLE `notes` (
                         `id` INT AUTO_INCREMENT PRIMARY KEY,
                         `content` TEXT NOT NULL,
                         `creator_user_id` VARCHAR(50) NOT NULL,
                         CONSTRAINT `fk_notes_user` FOREIGN KEY (`creator_user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `notes` (content, creator_user_id)
VALUES
    ('This is a note from bluu', 'bluu'),
    ('This is a note from test', 'test');


CREATE TABLE `users_notes` (
                               `user_id` VARCHAR(50) NOT NULL,
                               `note_id` INT NOT NULL,
                               CONSTRAINT `fk_users_notes_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE,
                               CONSTRAINT `fk_users_notes_note` FOREIGN KEY (`note_id`) REFERENCES `notes` (`id`) ON DELETE CASCADE,
                               PRIMARY KEY (`user_id`, `note_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `users_notes` (user_id, note_id)
VALUES
    ('bluu', 1),
    ('test', 1);


CREATE TABLE `users_roles` (
                               `user_id` VARCHAR(50) NOT NULL,
                               `role_id` INT NOT NULL,
                               PRIMARY KEY (`user_id`, `role_id`),
                               FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE,
                               FOREIGN KEY (`role_id`) REFERENCES `roles` (`role_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `users_roles` (user_id, role_id)
VALUES
    ('bluu', 1),
    ('test', 1);
