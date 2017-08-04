CREATE DATABASE  IF NOT EXISTS `db_example`;
USE `db_example`;

--
-- Table structure for table `movie`
--
DROP TABLE IF EXISTS `movie`;
CREATE TABLE `movie` (
  `id` BIGINT unsigned NOT NULL,
  `title` VARCHAR(255) NOT NULL,
  `description` VARCHAR(2047) NOT NULL,
  `release_date` DATE NOT NULL,
  `rating` FLOAT NOT NULL,
  `poster_path` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table `genre`
--

DROP TABLE IF EXISTS `genre`;
CREATE TABLE `genre` (
  `id` BIGINT unsigned NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table `review`
--

DROP TABLE IF EXISTS `review`;
CREATE TABLE `review` (
  `id` BIGINT unsigned NOT NULL AUTO_INCREMENT,
  `review_text` varchar(10239) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

--
-- Table structure for table `movie_genre`
--

DROP TABLE IF EXISTS `movie_genre`;
CREATE TABLE `movie_genre` (
  `movie_id` BIGINT unsigned NOT NULL,
  `genre_id` BIGINT unsigned NOT NULL,
  PRIMARY KEY (`movie_id`,`genre_id`)
--  CONSTRAINT `fk_moviegenre_movie` FOREIGN KEY (`movie_id`) REFERENCES `movie` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
--  CONSTRAINT `fk_moviegenre_genre` FOREIGN KEY (`genre_id`) REFERENCES `genre` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table `movie_review`
--

DROP TABLE IF EXISTS `movie_review`;
CREATE TABLE `movie_review` (
  `movie_id` BIGINT unsigned NOT NULL,
  `review_id` BIGINT unsigned NOT NULL,
  PRIMARY KEY (`movie_id`,`review_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` BIGINT unsigned NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(20) NOT NULL,
  `password` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

--
-- Table structure for table `moviecollection`
--

DROP TABLE IF EXISTS `moviecollection`;
CREATE TABLE `moviecollection` (
  `id` BIGINT unsigned NOT NULL AUTO_INCREMENT,
  `collection_name` VARCHAR(255) NOT NULL,
  `user_id` BIGINT unsigned NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT fk_user_moviecollection FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

--
-- Table structure for table `moviecollection_movie`
--

DROP TABLE IF EXISTS `moviecollection_movie`;
CREATE TABLE `moviecollection_movie` (
  `collection_id` BIGINT unsigned NOT NULL,
  `movie_id` BIGINT unsigned NOT NULL,
  PRIMARY KEY (`movie_id`,`collection_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

