DROP SCHEMA IF EXISTS `TypeTeam`;
CREATE SCHEMA `TypeTeam`;
USE
`TypeTeam`;

DROP TABLE IF EXISTS `users_notes`;
DROP TABLE IF EXISTS `notes`;
DROP TABLE IF EXISTS `users_roles`;
DROP TABLE IF EXISTS `roles`;
DROP TABLE IF EXISTS `users`;

CREATE TABLE `users`
(
    `user_id` VARCHAR(50)  NOT NULL,
    `pw`      CHAR(75)     NOT NULL,
    `email`   VARCHAR(100) NOT NULL,
    `active`  TINYINT      NOT NULL,
    PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `roles`
(
    `role_id` INT AUTO_INCREMENT PRIMARY KEY,
    `role`    VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `users_roles`
(
    `user_id` VARCHAR(50) NOT NULL,
    `role_id` INT         NOT NULL,
    PRIMARY KEY (`user_id`, `role_id`),
    CONSTRAINT `fk_user_role_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE,
    CONSTRAINT `fk_user_role_role` FOREIGN KEY (`role_id`) REFERENCES `roles` (`role_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `notes`
(
    `id`              INT AUTO_INCREMENT PRIMARY KEY,
    `title`           VARCHAR(100) NOT NULL,
    `content`         TEXT         NOT NULL,
    `creator_user_id` VARCHAR(50)  NOT NULL,
    CONSTRAINT `fk_note_creator` FOREIGN KEY (`creator_user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `users_notes`
(
    `user_id` VARCHAR(50) NOT NULL,
    `note_id` INT         NOT NULL,
    PRIMARY KEY (`user_id`, `note_id`),
    CONSTRAINT `fk_user_note_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE,
    CONSTRAINT `fk_user_note_note` FOREIGN KEY (`note_id`) REFERENCES `notes` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `users` (user_id, pw, email, active)
VALUES ('admin', '$2a$12$PFFSrm059uEhOoY6XL/n7ess0PnsmHNcsHc.SbDExR99PR0Tlj6Ju', 'mail@mail.com', 1),
       ('test', '$2a$12$HOGVXlx090ksYZx5IjB/XuD752mpjkx2XZf7A0zT.7nkBlZvNtnvC', 'mail2@mail.com', 1),
       ('test2', '$2a$12$P7JDTWcAd8n24AoL9o0on.6cdaYrH3eMhJDYEQFrZorgoiwd/ypsS', 'mail3@mail.com', 1);

INSERT INTO `roles` (role)
VALUES ('ROLE_USER'),
       ('ROLE_ADMIN');

INSERT INTO `users_roles` (user_id, role_id)
VALUES ('admin', 2),
       ('test', 1),
       ('test2', 1);

INSERT INTO `notes` (title, content, creator_user_id)
VALUES ('Biology Lecture Notes', 'This is a biology note, from the biology class', 'admin'),
       ('Psychology Notes', 'This is a note for test, about psychology.', 'admin'),
       ('TODO List', 'I need to do stuff', 'admin'),
       ('Social Psychology Exam Preparations', 'This is a note for test', 'test'),
       ('Math Lecture Notes', 'Some math notes', 'admin'),
       ('Plans for Japan trip', 'plans for japan go here', 'test2'),
       ('Random notes', 'Note made by test2', 'test2');

INSERT INTO `users_notes` (user_id, note_id)
VALUES ('admin', 1),
       ('admin', 2),
       ('admin', 3),
       ('admin', 5),
       ('admin', 6),
       ('admin', 7),
       ('test', 2),
       ('test', 1),
       ('test', 3),
       ('test', 4),
       ('test', 5),
       ('test', 6),
       ('test2', 7),
       ('test', 7);