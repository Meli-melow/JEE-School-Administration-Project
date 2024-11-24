CREATE database IF NOT EXISTS cy_e_admin;
USE cy_e_admin;

-- Drop first tables containing foreign keys
DROP TABLE IF EXISTS assessment;
DROP TABLE IF EXISTS result;
DROP TABLE IF EXISTS course;
DROP TABLE IF EXISTS teacher;
DROP TABLE IF EXISTS student;


-- Both CourseUnit and SchoolYear enum values will be associated to a String 

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
    school_year VARCHAR(25) NOT NULL				-- Will be associated to the enum values (String) of the SchoolYear enum class
) ENGINE=InnoDB;

CREATE TABLE course(
	id INT AUTO_INCREMENT PRIMARY KEY,
	field VARCHAR(25) NOT NULL,
	slot DATE NOT NULL,
    duration VARCHAR(4) NOT NULL,					-- Format : nb_hours[h]nb_minutes
    course_unit VARCHAR(20) NOT NULL, 				-- Will be associated to the enum values (String) of the CourseUnit enum class
    students VARCHAR(25) NOT NULL,				-- Will be associated to the enum values of the SchoolYear enum class
    id_teacher INT NOT NULL,
    
    INDEX (id_teacher),
    FOREIGN KEY (id_teacher) REFERENCES Teacher(id)
) ENGINE=InnoDB;

CREATE TABLE result(
	id INT AUTO_INCREMENT PRIMARY KEY,
	field VARCHAR(25) NOT NULL,
	course_unit VARCHAR(20) NOT NULL,				-- Will be associated to the enum values of the CourseUnit enum class
    unit_coeff DECIMAL(3,2),              -- 3 - 2 = 1 digit before the point
    id_student INT NOT NULL,
    
    INDEX (id_student),
    FOREIGN KEY (id_student) REFERENCES Student(id)
);

CREATE TABLE assessment(
	id INT AUTO_INCREMENT PRIMARY KEY,
	assess_name VARCHAR(15),
    coefficient DECIMAL(3,2),              -- 3 - 2 = 1 digit before the point
    test_date DATE,
    grade DECIMAL(4,2),			-- 4 - 2 = 2 digits before the point
    id_result INT NOT NULL,
    
    INDEX (id_result),
    FOREIGN KEY (id_result) REFERENCES Result(id)
);