USE
`TypeTeam`;

DROP TABLE IF EXISTS `user_notes`;
DROP TABLE IF EXISTS `notes`;
DROP TABLE IF EXISTS `users`;
DROP TABLE IF EXISTS `roles`;
DROP TABLE IF EXISTS `members`;

CREATE TABLE `members`
(
    `user_id` VARCHAR(50) NOT NULL,
    `pw`      CHAR(75)    NOT NULL,
    `active`  TINYINT     NOT NULL,
    PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `members` (user_id, pw, active)
VALUES ('bluu', '{bcrypt}$2a$12$Lz/mcYZHc1D5kl1VaTYJouop8O/.MQvMRp2pexvZuY8v1cm9n55qu', 1),
       ('test', '{bcrypt}$2a$12$HOGVXlx090ksYZx5IjB/XuD752mpjkx2XZf7A0zT.7nkBlZvNtnvC', 1);

CREATE TABLE `roles`
(
    `user_id` VARCHAR(50) NOT NULL,
    `role`    VARCHAR(50) NOT NULL,
    UNIQUE KEY `authorities5_idx_1` (`user_id`, `role`),
    CONSTRAINT `authorities5_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `members` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `roles`
VALUES ('bluu', 'USER'),
       ('test', 'USER');

CREATE TABLE `users`
(
    `id`      INT AUTO_INCREMENT PRIMARY KEY,
    `user_id` VARCHAR(50) NOT NULL,
    CONSTRAINT `fk_user_id` FOREIGN KEY (`user_id`) REFERENCES `members` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `users` (user_id)
VALUES ('bluu'),
       ('test');

CREATE TABLE `notes`
(
    `id`      INT AUTO_INCREMENT PRIMARY KEY,
    `content` TEXT NOT NULL,
    `user_id` INT  NOT NULL,
    CONSTRAINT `fk_notes_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=10;

INSERT INTO `notes` (content, user_id)
VALUES ('This is a note from bluu', 1),
       ('This is a note from test', 2);

CREATE TABLE `user_notes`
(
    `user_id` INT NOT NULL,
    `note_id` INT NOT NULL,
    CONSTRAINT `fk_user_notes_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
    CONSTRAINT `fk_user_notes_note` FOREIGN KEY (`note_id`) REFERENCES `notes` (`id`),
    PRIMARY KEY (`user_id`, `note_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

