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

--DROP TABLE publisher;
--DROP TABLE book;
--DROP TABLE writingGroup;