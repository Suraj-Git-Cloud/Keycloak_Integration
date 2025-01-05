
USE savvis2_read;

CREATE TABLE IF NOT EXISTS user_detail(
     id int(20)  PRIMARY KEY NOT NULL AUTO_INCREMENT,
	name VARCHAR(250),
    type VARCHAR(200)
);
INSERT  INTO user_detail(name, type) VALUES('Test_User_1', 'Admin');
INSERT  INTO user_detail(name, type) VALUES('Test_Read_2', 'Retailer');

SELECT * FROM user_detail;



