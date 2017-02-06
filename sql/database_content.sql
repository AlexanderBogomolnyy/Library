-- `library_db`.`user_role` data inserting

INSERT INTO `library_db`.`user_role` (`name`, `status`) VALUES ('LIBRARIAN', 'ACTIVE');
INSERT INTO `library_db`.`user_role` (`name`, `status`) VALUES ('CLIENT', 'ACTIVE');

-- `library_db`.`user` data inserting

INSERT INTO `library_db`.`user` (`login`, `f_name`, `l_name`, `email`, `password`, `role_id`, `status`)
VALUES ('librarian', 'Alexander', 'Bogomolnyy', 'post@allexb.kiev.ua', '698d51a19d8a121ce581499d7b701668', '1', 'ACTIVE'); -- password = 111
INSERT INTO `library_db`.`user` (`login`, `f_name`, `l_name`, `email`, `password`, `role_id`, `status`)
VALUES ('user', 'Ivan', 'Ivanov', 'ii@post.com', '698d51a19d8a121ce581499d7b701668', '2', 'ACTIVE'); -- password = 111
INSERT INTO `library_db`.`user` (`login`, `f_name`, `l_name`, `email`, `password`, `role_id`, `status`)
VALUES ('librarian1', 'John', 'Doe', 'jd@hidden.com', '202cb962ac59075b964b07152d234b70', '1', 'ACTIVE'); -- password = 123
INSERT INTO `library_db`.`user` (`login`, `f_name`, `l_name`, `email`, `password`, `role_id`, `status`)
VALUES ('user1', 'Alex', 'Brown', 'ab@post.com', '550a141f12de6341fba65b0ad0433500', '2', 'DEACTIVATED'); -- password = 444
INSERT INTO `library_db`.`user` (`login`, `f_name`, `l_name`, `email`, `password`, `role_id`, `status`)
VALUES ('user2', 'Sergio', 'Armani', 'bigboss@armani.com', '698d51a19d8a121ce581499d7b701668', '2', 'ACTIVE'); -- password = 111

-- `library_db`.`category` data inserting

INSERT INTO `library_db`.`category` (`name`) VALUES (_utf8'Классическая литература');
INSERT INTO `library_db`.`category` (`name`) VALUES (_utf8'Художественная литература');
INSERT INTO `library_db`.`category` (`name`) VALUES (_utf8'Научно-популярная литература');

-- `library_db`.`catalog` data inserting

INSERT INTO `library_db`.`catalog` (`title`, `author`, `year_of_publication`, `category_id`, `ISBN`, `amount_all`, `amount_available`, `status`)
VALUES ('Treasure island', 'Robert Stevenson', '2010', '2', '978-5-379-01591-6', '1', '1', 'ACTIVE');

INSERT INTO `library_db`.`catalog` (`title`, `author`, `year_of_publication`, `category_id`, `ISBN`, `amount_all`, `amount_available`, `status`)
VALUES ('Private Paris', 'James Patterson', '2016', '2', '978-0-09-959447-5', '1', '1', 'ACTIVE');

INSERT INTO `library_db`.`catalog` (`title`, `author`, `year_of_publication`, `category_id`, `ISBN`, `amount_all`, `amount_available`, `status`)
VALUES ('How The Grinch Stole Christmas', 'Seuss Dr.', '2016', '2', '978-0-00-818349-3', '1', '1', 'ACTIVE');

INSERT INTO `library_db`.`catalog` (`title`, `author`, `year_of_publication`, `category_id`, `ISBN`, `amount_all`, `amount_available`, `status`)
VALUES (_utf8'Таинственный остров', _utf8'Жюль Верн', '2016', '2', '978-5-9922-2369-9', '1', '1', 'ACTIVE');

INSERT INTO `library_db`.`catalog` (`title`, `author`, `year_of_publication`, `category_id`, `ISBN`, `amount_all`, `amount_available`, `status`)
VALUES (_utf8'Виконт де Бражелон', _utf8'Александр Дюма', '2016', '2', '978-5-9922-2223-4', '1', '1', 'ACTIVE');

INSERT INTO `library_db`.`catalog` (`title`, `author`, `year_of_publication`, `category_id`, `ISBN`, `amount_all`, `amount_available`, `status`)
VALUES (_utf8'Белый клык', _utf8'Джек Лондон', '2017', '2', '978-5-699-93104-0', '1', '1', 'ACTIVE');

INSERT INTO `library_db`.`catalog` (`title`, `author`, `year_of_publication`, `category_id`, `ISBN`, `amount_all`, `amount_available`, `status`)
VALUES (_utf8'Лучшие стихи и рассказы', _utf8'Эдгар По', '2017', '2', '978-5-906899-24-8', '1', '1', 'ACTIVE');

INSERT INTO `library_db`.`catalog` (`title`, `author`, `year_of_publication`, `category_id`, `ISBN`, `amount_all`, `amount_available`, `status`)
VALUES (_utf8'Пятнадцатилетний капитан', _utf8'Жюль Верн', '2015', '2', '978-5-4335-0170-6', '1', '1', 'ACTIVE');

INSERT INTO `library_db`.`catalog` (`title`, `author`, `year_of_publication`, `category_id`, `ISBN`, `amount_all`, `amount_available`, `status`)
VALUES (_utf8'Идиот', _utf8'Федор Достоевский', '2014', '2', '978-5-699-69362-7', '1', '1', 'ACTIVE');

INSERT INTO `library_db`.`catalog` (`title`, `author`, `year_of_publication`, `category_id`, `ISBN`, `amount_all`, `amount_available`, `status`)
VALUES (_utf8'Война и мир. Том I-II', _utf8'Лев Толстой', '2014', '2', '978-5-699-65494-9', '1', '1', 'ACTIVE');

INSERT INTO `library_db`.`catalog` (`title`, `author`, `year_of_publication`, `category_id`, `ISBN`, `amount_all`, `amount_available`, `status`)
VALUES (_utf8'Война и мир. Том III-IV', _utf8'Лев Толстой', '2014', '2', '978-5-699-65478-9', '1', '1', 'ACTIVE');

-- `library_db`.`order` data inserting

# INSERT INTO `library_db`.`order` (`user_id`, `catalog_id`, `date_of_issue`, `expected_date_of_return`, `order_type`, `expected_book_location`)
# VALUES ('5', '3', '2017.01.26', '2017.02.01', 'NEW', 'ON_HAND');
# INSERT INTO `library_db`.`order` (`user_id`, `catalog_id`, `date_of_issue`, `expected_date_of_return`, `order_type`, `expected_book_location`)
# VALUES ('5', '7', '2017.01.27', '2017.01.27', 'NEW', 'READING_ROOM');

-- `library_db`.`books` data inserting

# INSERT INTO `library_db`.`book` (`catalog_id`, `state`, `lib_identifier`, `location`, `status`) VALUES ('1', 'New book. No damage.', '1', 'LIBRARY', 'ACTIVE');
# UPDATE `library_db`.`book` SET `lib_identifier` = CONCAT('S ',`catalog_id`, ': I ', LAST_INSERT_ID()) WHERE `book`.`id` = LAST_INSERT_ID();
# INSERT INTO `library_db`.`book` (`catalog_id`, `state`, `lib_identifier`, `location`, `status`) VALUES ('1', 'New book. No damage.', '1', 'LIBRARY', 'ACTIVE');
# UPDATE `library_db`.`book` SET `lib_identifier` = CONCAT('S ',`catalog_id`, ': I ', LAST_INSERT_ID()) WHERE `book`.`id` = LAST_INSERT_ID();
# INSERT INTO `library_db`.`book` (`catalog_id`, `state`, `lib_identifier`, `location`, `status`) VALUES ('1', 'New book. No damage.', '1', 'LIBRARY', 'ACTIVE');
# UPDATE `library_db`.`book` SET `lib_identifier` = CONCAT('S ',`catalog_id`, ': I ', LAST_INSERT_ID()) WHERE `book`.`id` = LAST_INSERT_ID();
# INSERT INTO `library_db`.`book` (`catalog_id`, `state`, `lib_identifier`, `location`, `status`) VALUES ('1', 'New book. No damage.', '1', 'LIBRARY', 'ACTIVE');
# UPDATE `library_db`.`book` SET `lib_identifier` = CONCAT('S ',`catalog_id`, ': I ', LAST_INSERT_ID()) WHERE `book`.`id` = LAST_INSERT_ID();

INSERT INTO `library_db`.`book` (`catalog_id`, `state`, `lib_identifier`, `location`, `status`)
VALUES ('1', 'New book. No damage.', '1', 'LIBRARY', 'ACTIVE');
UPDATE `library_db`.`book`
SET `lib_identifier` = CONCAT('S',
                              (SELECT `category_id` FROM `catalog` WHERE `book`.`catalog_id` = `catalog`.`id` LIMIT 1),
                              ':C', `catalog_id`, ':I', LAST_INSERT_ID()) WHERE `book`.`id` = LAST_INSERT_ID();

INSERT INTO `library_db`.`book` (`catalog_id`, `state`, `lib_identifier`, `location`, `status`)
VALUES ('1', 'New book. No damage.', '1', 'LIBRARY', 'ACTIVE');
UPDATE `library_db`.`book`
SET `lib_identifier` = CONCAT('S',
                              (SELECT `category_id` FROM `catalog` WHERE `book`.`catalog_id` = `catalog`.`id` LIMIT 1),
                              ':C', `catalog_id`, ':I', LAST_INSERT_ID()) WHERE `book`.`id` = LAST_INSERT_ID();

INSERT INTO `library_db`.`book` (`catalog_id`, `state`, `lib_identifier`, `location`, `status`)
VALUES ('1', 'New book. No damage.', '1', 'LIBRARY', 'ACTIVE');
UPDATE `library_db`.`book`
SET `lib_identifier` = CONCAT('S',
                              (SELECT `category_id` FROM `catalog` WHERE `book`.`catalog_id` = `catalog`.`id` LIMIT 1),
                              ':C', `catalog_id`, ':I', LAST_INSERT_ID()) WHERE `book`.`id` = LAST_INSERT_ID();

INSERT INTO `library_db`.`book` (`catalog_id`, `state`, `lib_identifier`, `location`, `status`)
VALUES ('1', 'New book. No damage.', '1', 'LIBRARY', 'ACTIVE');
UPDATE `library_db`.`book`
SET `lib_identifier` = CONCAT('S',
                              (SELECT `category_id` FROM `catalog` WHERE `book`.`catalog_id` = `catalog`.`id` LIMIT 1),
                              ':C', `catalog_id`, ':I', LAST_INSERT_ID()) WHERE `book`.`id` = LAST_INSERT_ID();

UPDATE `library_db`.`catalog`
SET `amount_all` = 4, `amount_available` = 4
WHERE `catalog`.`id` = 1;

INSERT INTO `library_db`.`book` (`catalog_id`, `state`, `lib_identifier`, `location`, `status`)
VALUES ('2', 'New book.', '1', 'LIBRARY', 'ACTIVE');
UPDATE `library_db`.`book`
SET `lib_identifier` = CONCAT('S',
                              (SELECT `category_id` FROM `catalog` WHERE `book`.`catalog_id` = `catalog`.`id` LIMIT 1),
                              ':C', `catalog_id`, ':I', LAST_INSERT_ID()) WHERE `book`.`id` = LAST_INSERT_ID();

INSERT INTO `library_db`.`book` (`catalog_id`, `state`, `lib_identifier`, `location`, `status`)
VALUES ('2', 'New book.', '1', 'LIBRARY', 'ACTIVE');
UPDATE `library_db`.`book`
SET `lib_identifier` = CONCAT('S',
                              (SELECT `category_id` FROM `catalog` WHERE `book`.`catalog_id` = `catalog`.`id` LIMIT 1),
                              ':C', `catalog_id`, ':I', LAST_INSERT_ID()) WHERE `book`.`id` = LAST_INSERT_ID();

UPDATE `library_db`.`catalog`
SET `amount_all` = 2, `amount_available` = 2
WHERE `catalog`.`id` = 2;

INSERT INTO `library_db`.`book` (`catalog_id`, `state`, `lib_identifier`, `location`, `status`)
VALUES ('3', 'Book in good condition.', '1', 'LIBRARY', 'ACTIVE');
UPDATE `library_db`.`book`
SET `lib_identifier` = CONCAT('S',
                              (SELECT `category_id` FROM `catalog` WHERE `book`.`catalog_id` = `catalog`.`id` LIMIT 1),
                              ':C', `catalog_id`, ':I', LAST_INSERT_ID()) WHERE `book`.`id` = LAST_INSERT_ID();

UPDATE `library_db`.`catalog`
SET `amount_all` = 1, `amount_available` = 1
WHERE `catalog`.`id` = 3;

INSERT INTO `library_db`.`book` (`catalog_id`, `state`, `lib_identifier`, `location`, `status`)
VALUES ('4', 'Cover is damaged.', '1', 'LIBRARY', 'ACTIVE');
UPDATE `library_db`.`book`
SET `lib_identifier` = CONCAT('S',
                              (SELECT `category_id` FROM `catalog` WHERE `book`.`catalog_id` = `catalog`.`id` LIMIT 1),
                              ':C', `catalog_id`, ':I', LAST_INSERT_ID()) WHERE `book`.`id` = LAST_INSERT_ID();

INSERT INTO `library_db`.`book` (`catalog_id`, `state`, `lib_identifier`, `location`, `status`)
VALUES ('4', 'Book in good condition.', '1', 'LIBRARY', 'ACTIVE');
UPDATE `library_db`.`book`
SET `lib_identifier` = CONCAT('S',
                              (SELECT `category_id` FROM `catalog` WHERE `book`.`catalog_id` = `catalog`.`id` LIMIT 1),
                              ':C', `catalog_id`, ':I', LAST_INSERT_ID()) WHERE `book`.`id` = LAST_INSERT_ID();
UPDATE `library_db`.`catalog`
SET `amount_all` = 2, `amount_available` = 2
WHERE `catalog`.`id` = 4;

INSERT INTO `library_db`.`book` (`catalog_id`, `state`, `lib_identifier`, `location`, `status`)
VALUES ('5', 'New book.', '1', 'LIBRARY', 'ACTIVE');
UPDATE `library_db`.`book`
SET `lib_identifier` = CONCAT('S',
                              (SELECT `category_id` FROM `catalog` WHERE `book`.`catalog_id` = `catalog`.`id` LIMIT 1),
                              ':C', `catalog_id`, ':I', LAST_INSERT_ID()) WHERE `book`.`id` = LAST_INSERT_ID();
UPDATE `library_db`.`catalog`
SET `amount_all` = 1, `amount_available` = 1
WHERE `catalog`.`id` = 5;

INSERT INTO `library_db`.`book` (`catalog_id`, `state`, `lib_identifier`, `location`, `status`)
VALUES ('6', 'New book.', '1', 'LIBRARY', 'ACTIVE');
UPDATE `library_db`.`book`
SET `lib_identifier` = CONCAT('S',
                              (SELECT `category_id` FROM `catalog` WHERE `book`.`catalog_id` = `catalog`.`id` LIMIT 1),
                              ':C', `catalog_id`, ':I', LAST_INSERT_ID()) WHERE `book`.`id` = LAST_INSERT_ID();

INSERT INTO `library_db`.`book` (`catalog_id`, `state`, `lib_identifier`, `location`, `status`)
VALUES ('6', 'New book.', '1', 'LIBRARY', 'ACTIVE');
UPDATE `library_db`.`book`
SET `lib_identifier` = CONCAT('S',
                              (SELECT `category_id` FROM `catalog` WHERE `book`.`catalog_id` = `catalog`.`id` LIMIT 1),
                              ':C', `catalog_id`, ':I', LAST_INSERT_ID()) WHERE `book`.`id` = LAST_INSERT_ID();

UPDATE `library_db`.`catalog`
SET `amount_all` = 2, `amount_available` = 2
WHERE `catalog`.`id` = 6;

INSERT INTO `library_db`.`book` (`catalog_id`, `state`, `lib_identifier`, `location`, `status`)
VALUES ('7', 'New book.', '1', 'LIBRARY', 'ACTIVE');
UPDATE `library_db`.`book`
SET `lib_identifier` = CONCAT('S',
                              (SELECT `category_id` FROM `catalog` WHERE `book`.`catalog_id` = `catalog`.`id` LIMIT 1),
                              ':C', `catalog_id`, ':I', LAST_INSERT_ID()) WHERE `book`.`id` = LAST_INSERT_ID();

UPDATE `library_db`.`catalog`
SET `amount_all` = 1, `amount_available` = 1
WHERE `catalog`.`id` = 7;

INSERT INTO `library_db`.`book` (`catalog_id`, `state`, `lib_identifier`, `location`, `status`)
VALUES ('8', 'New book.', '1', 'LIBRARY', 'ACTIVE');
UPDATE `library_db`.`book`
SET `lib_identifier` = CONCAT('S',
                              (SELECT `category_id` FROM `catalog` WHERE `book`.`catalog_id` = `catalog`.`id` LIMIT 1),
                              ':C', `catalog_id`, ':I', LAST_INSERT_ID()) WHERE `book`.`id` = LAST_INSERT_ID();

UPDATE `library_db`.`catalog`
SET `amount_all` = 1, `amount_available` = 1
WHERE `catalog`.`id` = 8;

INSERT INTO `library_db`.`book` (`catalog_id`, `state`, `lib_identifier`, `location`, `status`)
VALUES ('9', 'New book.', '1', 'LIBRARY', 'ACTIVE');
UPDATE `library_db`.`book`
SET `lib_identifier` = CONCAT('S',
                              (SELECT `category_id` FROM `catalog` WHERE `book`.`catalog_id` = `catalog`.`id` LIMIT 1),
                              ':C', `catalog_id`, ':I', LAST_INSERT_ID()) WHERE `book`.`id` = LAST_INSERT_ID();


UPDATE `library_db`.`catalog`
SET `amount_all` = 1, `amount_available` = 1
WHERE `catalog`.`id` = 9;

INSERT INTO `library_db`.`book` (`catalog_id`, `state`, `lib_identifier`, `location`, `status`)
VALUES ('10', 'New book.', '1', 'LIBRARY', 'ACTIVE');
UPDATE `library_db`.`book`
SET `lib_identifier` = CONCAT('S',
                              (SELECT `category_id` FROM `catalog` WHERE `book`.`catalog_id` = `catalog`.`id` LIMIT 1),
                              ':C', `catalog_id`, ':I', LAST_INSERT_ID()) WHERE `book`.`id` = LAST_INSERT_ID();


UPDATE `library_db`.`catalog`
SET `amount_all` = 1, `amount_available` = 1
WHERE `catalog`.`id` = 10;

INSERT INTO `library_db`.`book` (`catalog_id`, `state`, `lib_identifier`, `location`, `status`)
VALUES ('11', 'New book.', '1', 'LIBRARY', 'ACTIVE');
UPDATE `library_db`.`book`
SET `lib_identifier` = CONCAT('S',
                              (SELECT `category_id` FROM `catalog` WHERE `book`.`catalog_id` = `catalog`.`id` LIMIT 1),
                              ':C', `catalog_id`, ':I', LAST_INSERT_ID()) WHERE `book`.`id` = LAST_INSERT_ID();


UPDATE `library_db`.`catalog`
SET `amount_all` = 1, `amount_available` = 1
WHERE `catalog`.`id` = 11;