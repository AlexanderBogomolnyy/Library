-- `library_db`.`user_role` data inserting

INSERT INTO `library_db`.`user_role` (`name`, `status`) VALUES ('LIBRARIAN', 'ACTIVE');
INSERT INTO `library_db`.`user_role` (`name`, `status`) VALUES ('CLIENT', 'ACTIVE');

-- `library_db`.`user` data inserting

INSERT INTO `library_db`.`user` (`login`, `f_name`, `l_name`, `email`, `password`, `role_id`, `status`) VALUES ('allexb', 'Alexander', 'Bogomolnyy', 'post@allexb.kiev.ua', '698d51a19d8a121ce581499d7b701668', '1', 'ACTIVE'); -- password = 111
INSERT INTO `library_db`.`user` (`login`, `f_name`, `l_name`, `email`, `password`, `role_id`, `status`) VALUES ('ivan', 'Ivan', 'Ivanov', 'ii@post.com', '698d51a19d8a121ce581499d7b701668', '2', 'ACTIVE'); -- password = 111
INSERT INTO `library_db`.`user` (`login`, `f_name`, `l_name`, `email`, `password`, `role_id`, `status`) VALUES ('admin', 'John', 'Doe', 'jd@hidden.com', '202cb962ac59075b964b07152d234b70', '1', 'ACTIVE'); -- password = 123
INSERT INTO `library_db`.`user` (`login`, `f_name`, `l_name`, `email`, `password`, `role_id`, `status`) VALUES ('alex', 'Alex', 'Brown', 'ab@post.com', '550a141f12de6341fba65b0ad0433500', '2', 'DEACTIVATED'); -- password = 444
INSERT INTO `library_db`.`user` (`login`, `f_name`, `l_name`, `email`, `password`, `role_id`, `status`) VALUES ('user', 'Sergio', 'Armani', 'bigboss@armani.com', '698d51a19d8a121ce581499d7b701668', '2', 'ACTIVE'); -- password = 111

