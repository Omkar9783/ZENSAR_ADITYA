USE zensar;

-- Drop existing tables if they exist (order matters due to foreign keys)
DROP TABLE IF EXISTS employee;
DROP TABLE IF EXISTS project;
DROP TABLE IF EXISTS department;
DROP TABLE IF EXISTS nko_emp;


-- Create department table
CREATE TABLE department (
    dept_id INT PRIMARY KEY,
    dept_name VARCHAR(50)
);

-- Insert data into department
INSERT INTO department (dept_id, dept_name) VALUES
(201, 'HR'),
(202, 'Finance'),
(203, 'Operations'),
(204, 'Sales'),
(205, 'IT');

-- Create project table
CREATE TABLE project (
    project_id INT PRIMARY KEY,
    project_name VARCHAR(100),
    dept_id INT,
    FOREIGN KEY (dept_id) REFERENCES department(dept_id)
);

-- Insert data into project
INSERT INTO project (project_id, project_name, dept_id) VALUES
(301, 'Hiring & Event', 201),
(302, 'Finance', 202),
(303, 'Urban & Rural', 203),
(304, 'Social Media & Ad', 204),
(305, 'Frontend', 205),
(306, 'Backend', 205),
(307, 'Data Science', 205),
(308, 'Mobile App', 205),
(309, 'Cloud Infrastructure', 205),
(310, 'Cybersecurity', 205);

-- Create employee table
CREATE TABLE employee (
    employee_id INT PRIMARY KEY,
    employee_name VARCHAR(50),
    manager_id INT,
    dept_id INT,
    FOREIGN KEY (dept_id) REFERENCES department(dept_id)
);

-- Insert data into employee
INSERT INTO employee (employee_id, employee_name, manager_id, dept_id) VALUES
(401, 'aaa', 501, 201),
(402, 'bbb', 502, 201),
(403, 'ccc', 504, 201),
(404, 'ddd', 501, 202),
(405, 'eee', 503, 202),
(406, 'fff', 504, 202),
(407, 'ggg', 505, 202),
(408, 'hhh', 501, 203),
(409, 'iii', 502, 203),
(410, 'jjj', 501, 203),
(411, 'kkk', 506, 203),
(412, 'lll', 506, 203),
(413, 'mmm', 501, 204),
(414, 'nnn', 503, 204),
(415, 'ooo', 504, 204),
(416, 'ppp', 505, 204),
(417, 'qqq', 501, 205),
(418, 'rrr', 506, 205),
(419, 'sss', 502, 205),
(420, 'ttt', 505, 205);

-- Create nko_emp table
CREATE TABLE nko_emp (
    emp_id INT PRIMARY KEY,
    emp_name VARCHAR(50),
    dept VARCHAR(20),
    salary INT,
    join_date DATE
);

-- Insert data into nko_emp
INSERT INTO nko_emp (emp_id, emp_name, dept, salary, join_date) VALUES
(1, 'AAA', 'IT', 50000, '2021-01-10'),
(2, 'BBB', 'IT', 60000, '2020-03-15'),
(3, 'CCC', 'IT', 60000, '2022-07-01'),
(4, 'DDD', 'HR', 40000, '2019-05-21'),
(5, 'EEE', 'HR', 45000, '2021-11-11'),
(6, 'FFF', 'HR', 45000, '2020-02-18'),
(7, 'GGG', 'Sales', 55000, '2021-08-08'),
(8, 'HHH', 'Sales', 50000, '2022-04-04'),
(9, 'III', 'Sales', 60000, '2020-12-12'),
(10, 'JJJ', 'Sales', 60000, '2019-06-06'),
(11, 'KKK', 'Finance', 65000, '2018-09-09'),
(12, 'LLL', 'Finance', 62000, '2021-10-10'),
(13, 'MMM', 'Finance', 62000, '2022-02-02'),
(14, 'NNN', 'IT', 52000, '2023-01-01'),
(15, 'OOO', 'HR', 48000, '2022-09-09');
