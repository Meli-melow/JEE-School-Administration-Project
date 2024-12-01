CREATE database IF NOT EXISTS cy_e_admin;
USE cy_e_admin;

-- Drop first association tables or tables containing foreign keys or tables with no foreign keys
DROP TABLE IF EXISTS admin;
DROP TABLE IF EXISTS assessment;
DROP TABLE IF EXISTS result;
DROP TABLE IF EXISTS student_courses;
DROP TABLE IF EXISTS course;
DROP TABLE IF EXISTS teacher;
DROP TABLE IF EXISTS student;

-- Both CourseUnit and SchoolYear values will be associated to a String

CREATE TABLE admin(
	id INT AUTO_INCREMENT PRIMARY KEY,
	firstname VARCHAR(30) NOT NULL,
	lastname VARCHAR(30) NOT NULL,
    mail VARCHAR(25) NOT NULL UNIQUE,
    pswd VARCHAR(15) NOT NULL		  -- password
) ENGINE=InnoDB;

CREATE TABLE teacher(
	id INT AUTO_INCREMENT PRIMARY KEY,
	firstname VARCHAR(30) NOT NULL,
	lastname VARCHAR(30) NOT NULL,
    mail VARCHAR(25) NOT NULL UNIQUE,
    pswd VARCHAR(15) NOT NULL,		  -- password
    field VARCHAR(25) NOT NULL
) ENGINE=InnoDB;
    
CREATE TABLE student(
	id INT AUTO_INCREMENT PRIMARY KEY,
	firstname VARCHAR(30) NOT NULL,
	lastname VARCHAR(30) NOT NULL,
    mail VARCHAR(25) NOT NULL UNIQUE,
    pswd VARCHAR(15) NOT NULL,
    birth DATE NOT NULL,
    school_year varchar(30)
) ENGINE=InnoDB;

CREATE TABLE course(
	id INT AUTO_INCREMENT PRIMARY KEY,
	field VARCHAR(25) NOT NULL,
	day DATE NOT NULL,
    hour varchar(5),
    duration varchar(4),
    school_year varchar(30),
    teacher_id INT NOT NULL,
    
    INDEX (teacher_id),
    FOREIGN KEY (teacher_id) REFERENCES Teacher(id)
) ENGINE=InnoDB;

-- Association table for course and student
CREATE TABLE student_courses (
	course_id INT,
    student_id INT,
    
    INDEX (student_id),
    FOREIGN KEY (student_id) REFERENCES Student(id),
    INDEX (course_id),
    FOREIGN KEY (course_id) REFERENCES Course(id)
) ENGINE=InnoDB;

CREATE TABLE result(
	id INT AUTO_INCREMENT PRIMARY KEY,
	field VARCHAR(25) NOT NULL,
	course_unit VARCHAR(20) NOT NULL,				-- Will be associated to the enum values of the CourseUnit enum class
    unit_coeff DECIMAL(3,2),              -- 3 - 2 = 1 digit before the point
    student_id INT NOT NULL,
    
    INDEX (student_id),
    FOREIGN KEY (student_id) REFERENCES Student(id)
);

CREATE TABLE assessment(
	id INT AUTO_INCREMENT PRIMARY KEY,
	assess_name VARCHAR(15),
    coefficient DECIMAL(3,2),              -- 3 - 2 = 1 digit before the point
    test_date DATE,
    grade DECIMAL(4,2),			           -- 4 - 2 = 2 digits before the point
    result_id INT NOT NULL,
    
    INDEX (result_id),
    FOREIGN KEY (result_id) REFERENCES Result(id)
);