CREATE TABLE writingGroup (
	groupName   VARCHAR(20) NOT NULL,
	headWriter  VARCHAR(20),
	yearFormed  SMALLINT,
	subject     VARCHAR(20));
	
CREATE TABLE publisher (
	pubName     VARCHAR(20) NOT NULL,
	pubAddress  VARCHAR(20),
	pubPhone    VARCHAR(12),
	pubEmail    VARCHAR(20));
	
CREATE TABLE book (
	groupName       VARCHAR(20) NOT NULL,
	pubName         VARCHAR(20) NOT NULL,
	bookTitle       VARCHAR(40) NOT NULL,
	yearPublished   SMALLINT,
	numberPages     SMALLINT);
	

ALTER TABLE publisher
	ADD CONSTRAINT pub_pk
	PRIMARY KEY (pubName);
	
ALTER TABLE writingGroup
	ADD CONSTRAINT group_pk
	PRIMARY KEY (groupName);
	
ALTER TABLE book
	ADD CONSTRAINT book_pk
	PRIMARY KEY (groupName, bookTitle);
	
ALTER TABLE book
	ADD CONSTRAINT bookGroup_fk
	FOREIGN KEY (groupName)
	REFERENCES writingGroup (groupName);
	
ALTER TABLE book
	ADD CONSTRAINT bookPub_fk
	FOREIGN KEY (pubName)
	REFERENCES publisher (pubName);

--DROP TABLE publisher;
--DROP TABLE book;
--DROP TABLE writingGroup;