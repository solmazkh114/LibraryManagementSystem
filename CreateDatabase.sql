
USE Library;

CREATE SCHEMA users;

CREATE TABLE users.students(
student_id VARCHAR(12) PRIMARY KEY,
first_name VARCHAR(50),
last_name VARCHAR(50),
email VARCHAR(100),
);



-- Insert data into students table
INSERT INTO users.students(student_id,first_name, last_name, email)
VALUES
('p-001', 'Alice', 'Johnson', 'alice.johnson@example.com'),
('m-002', 'Bob','Smith', 'bob.smith@example.com'),
('b-003', 'Charlie', 'Davis', 'charlie.davis@example.com'),
('p-004', 'David','Harris', 'david.harris@example.com'),
('m-005', 'Eva', 'White', 'eva.white@example.com'),
('b-006', 'Frank', 'Lee', 'frank.lee@example.com'),
('p-007', 'Grace', 'Miller', 'grace.miller@example.com');

-- Create faculties table
CREATE TABLE users.faculties(
faculty_id VARCHAR(12) PRIMARY KEY,
first_name VARCHAR(50),
last_name VARCHAR(50),
email VARCHAR(100),
);

-- Insert data into faculties table
INSERT INTO users.faculties (faculty_id, first_name,last_name, email)
VALUES
('f-001', 'James', 'Wilson', 'james.wilson@example.com'),
('f-002', 'Sarah', 'Clark', 'sarah.clark@example.com'),
('f-003', 'John', 'Turner', 'john.turner@example.com'),
('f-004', 'Linda', 'Green', 'linda.green@example.com'),
('f-005', 'William' ,'Adams', 'william.adams@example.com'),
('f-006', 'Patricia', 'King', 'patricia.king@example.com'),
('f-007', 'Thomas', 'Lee', 'thomas.lee@example.com');

-- Create another schema for saving books info
CREATE SCHEMA catalog;

CREATE TABLE catalog.books (
    book_id VARCHAR(10) PRIMARY KEY,
    isbn VARCHAR(20),
    title VARCHAR(100),
    author VARCHAR(100),
    published_year INT
);
-- Insert data into books table
INSERT INTO catalog.books (book_id, isbn, title, author, published_year)
VALUES
('100110-1', '978-123456789', 'Calculus', 'James Stewart', 2018),
('100111-2', '978-987654321', 'Linear Algebra', 'Gilbert Strang', 2019),
('100112-3', '978-135792468', 'Introduction to Probability', 'Dimitri P. Bertsekas', 2020),
('100113-4', '978-246801357', 'Discrete Mathematics', 'Kenneth H. Rosen', 2017),
('200110-1', '978-543216789', 'Physics for Scientists and Engineers', 'Raymond A. Serway', 2021),
('300110-1', '978-159753159', 'Statistical Learning', 'Trevor Hastie', 2018),
('400110-1', '978-112233445', 'Data Structures and Algorithms', 'Robert Lafore', 2016);


CREATE TABLE catalog.copies (
    copy_id INT IDENTITY(1,1) PRIMARY KEY,
    book_id VARCHAR(10),
    status VARCHAR(20),
    student_id VARCHAR(12) NULL,  -- Nullable, for students
    faculty_id VARCHAR(12) NULL,  -- Nullable, for faculty members
    FOREIGN KEY (book_id) REFERENCES catalog.books(book_id),
    FOREIGN KEY (student_id) REFERENCES users.students(student_id),
    FOREIGN KEY (faculty_id) REFERENCES users.faculties(faculty_id)
);
-- Insert data into copies table
INSERT INTO catalog.copies (book_id, status, student_id, faculty_id)
VALUES
('100110-1', 'Available', NULL, NULL), 
('100110-1', 'Checked Out', 'p-001', NULL), 
('100110-1', 'Available', NULL, NULL), 
('100110-1', 'Checked Out', 'p-004', NULL),
('100111-2', 'Checked Out', 'p-001',NULL),
('100111-2', 'Checked Out', 'b-003',NULL), 
('100111-2', 'Checked Out', 'p-004',NULL), 
('100111-2', 'Available',  NULL,NULL),       
('100112-3', 'Checked Out', NULL,'f-003'),        
('100112-3', 'Available',  NULL,NULL),       
('100113-4', 'Checked Out', 'm-005', NULL),  
('100113-4', 'Checked Out', 'b-003', NULL),      
('200110-1', 'Checked Out', 'p-007', NULL),  
('200110-1', 'Available', NULL, NULL),
('200110-1', 'Available', NULL, NULL), 
('200110-1', 'Available', NULL,NULL), 
('300110-1', 'Checked Out', 'p-001',NULL),      
('300110-1', 'Available',  NULL,NULL),       
('400110-1', 'Checked Out', 'b-006',NULL), 
('400110-1', 'Checked Out', 'b-003',NULL);     

