CREATE TABLE writingGroup (
	groupName   VARCHAR(50) NOT NULL,
	headWriter  VARCHAR(50),
	yearFormed  SMALLINT,
	subject     VARCHAR(30),
    CONSTRAINT group_pk PRIMARY KEY (groupName));
	
CREATE TABLE publisher (
	pubName     VARCHAR(50) NOT NULL,
	pubAddress  VARCHAR(100),
	pubPhone    VARCHAR(12),
	pubEmail    VARCHAR(100),
    CONSTRAINT pub_pk PRIMARY KEY (pubName));
	
CREATE TABLE book (
	groupName       VARCHAR(50) NOT NULL,
	pubName         VARCHAR(50) NOT NULL,
	bookTitle       VARCHAR(50) NOT NULL,
	yearPublished   SMALLINT,
	numberPages     SMALLINT,
    CONSTRAINT book_pk PRIMARY KEY (groupName, bookTitle),
    CONSTRAINT bookGroup_fk FOREIGN KEY (groupName) REFERENCES writingGroup (groupName),
    CONSTRAINT bookPub_fk FOREIGN KEY (pubName) REFERENCES publisher (pubName));


-- DROP TABLE book;
-- DROP TABLE writingGroup;
-- DROP TABLE publisher;

--/*Data for writingGroup */
INSERT INTO writingGroup(groupName, headWriter, yearFormed, subject) values
('SoCal Writing Group', 'Jack Austin', 1950, 'Thriller'),
('NorthCal Writing Group', 'Charles Smith', 1932, 'Fantasy'),
('The Writers', 'Alice Wonder', 1920, 'Suspense'),
('Rainy Day Writers', 'Sarah Death', 1950, 'Tragedy'),
('English Writers', 'Jane Austin', 1800, 'Romance'),
('The Blue Pens', 'Anthony Hopkins', 1999, NULL);

--/*Data for publisher */
INSERT INTO publisher(pubName, pubAddress, pubPhone, pubEmail) values
('Penguin Publishers', '45000 W. Duke St. New York, New York', '910-102-3434', 'penguinpubs@penguinpublishers.com'),
('Bronx Pub', '1010 N. North St. Bronx, New York', '915-222-5656', 'bronxpub@gmail.com'),
('Donkey Kong Publishers', '3010 S. Orange St. Banana, California', '559-909-2121', 'donkeykong@donkeykongpubs.com'),
('CSULB Press', '4500 Bellflower Blvd. Long Beach, CA', '562-277-1010', 'csulbpress@csulb.com'),
('Bumble Bee Press', '202 W. Hive Drive Lakewood, CA', '562-102-3092', 'bumblebeepress@yahoo.com');


--/*Data for book */
INSERT INTO book(groupName, pubName, bookTitle, yearPublished, numberPages) values
('SoCal Writing Group', 'Penguin Publishers', 'Thrills From the Dark', 2017, 299),
('The Blue Pens', 'Bumble Bee Press', 'Read My Words', 1999, 333),
('English Writers', 'Penguin Publishers', 'Love', 1801, 502),
('Rainy Day Writers', 'Donkey Kong Publishers', 'The Death of Sarah', 1962, 202),
('The Writers', 'Bronx Pub', 'On The Edge', 1925, 101),
('NorthCal Writing Group', 'Penguin Publishers', 'Sailing Away', 1933, 299),
('SoCal Writing Group', 'Penguin Publishers', 'Chills', 1993, 250),
('Rainy Day Writers', 'Bumble Bee Press', 'Homer and Julie', 1950, 198),
('The Writers', 'CSULB Press', 'Around The Corner', 1958, 555),
('SoCal Writing Group', 'Penguin Publishers', 'Let Go!', 2000, 209),
('The Blue Pens', 'Bronx Pub', 'Outer Space', 2002, 666),
('English Writers', 'Penguin Publishers', 'Zombies and Roses', 1810, 609),
('Rainy Day Writers', 'CSULB Press', 'PI Without Berries', 1963, 1200),
('The Writers', 'Bronx Pub', 'Under The Tree', 1931, 401),
('NorthCal Writing Group', 'Penguin Publishers', 'Sailing Away II', 1935, 239),
('SoCal Writing Group', 'CSULB Press', 'Under the Table', 1952, 350),
('Rainy Day Writers', 'Bumble Bee Press', 'Shipspeare', 1966, 598),
('The Writers', 'Penguin Publishers', 'Down the Hole', 1968, 255);





