CREATE TABLE `marks` (
  `id` int PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `student_id` int NOT NULL,
  `subject_id` int NOT NULL,
  `teacher_id` int NOT NULL,
  `value` int NOT NULL
);

CREATE TABLE `people` (
  `id` int PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `first_name` varchar(16) NOT NULL,
  `last_name` varchar(16) NOT NULL,
  `pather_name` varchar(16) NOT NULL,
  `group_id` int,
  `type` varchar(1)
);

CREATE TABLE `groups` (
  `id` int PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL
);

CREATE TABLE `subjects` (
  `id` int PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL
);

ALTER TABLE `marks` ADD FOREIGN KEY (`subject_id`) REFERENCES `subjects` (`id`);

ALTER TABLE `marks` ADD FOREIGN KEY (`student_id`) REFERENCES `people` (`id`);

ALTER TABLE `marks` ADD FOREIGN KEY (`teacher_id`) REFERENCES `people` (`id`);

ALTER TABLE `people` ADD FOREIGN KEY (`group_id`) REFERENCES `groups` (`id`);
