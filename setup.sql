DROP DATABASE IF EXISTS ZENSAR_JAVA;
DROP DATABASE IF EXISTS ATM_DB;
CREATE DATABASE IF NOT EXISTS ZENSAR;
USE ZENSAR;

-- Create Accounts Table
CREATE TABLE IF NOT EXISTS accounts (
    customer_number INT PRIMARY KEY,
    pin_number INT NOT NULL,
    checking_balance DOUBLE DEFAULT 0,
    saving_balance DOUBLE DEFAULT 0
);

-- Insert Dummy Data for Accounts
INSERT INTO accounts VALUES (12345, 1234, 1000.0, 5000.0);
INSERT INTO accounts VALUES (67890, 5678, 2000.0, 10000.0);

-- Create Students Table
DROP TABLE IF EXISTS stds_nkocet;
CREATE TABLE IF NOT EXISTS stds_nkocet (
    stds_no INT PRIMARY KEY,
    stds_name VARCHAR(100),
    stds_stipend DOUBLE
);

-- Insert Data for Students
INSERT INTO stds_nkocet VALUES (401, 'omkar', 10000);
INSERT INTO stds_nkocet VALUES (402, 'aditya', 20000);
INSERT INTO stds_nkocet VALUES (403, 'pratik', 30000);
INSERT INTO stds_nkocet VALUES (404, 'anas', 40000);
INSERT INTO stds_nkocet VALUES (405, 'rohan', 50000);
INSERT INTO stds_nkocet VALUES (406, 'rohit', 60000);
INSERT INTO stds_nkocet VALUES (407, 'patange', 70000);
INSERT INTO stds_nkocet VALUES (408, 'tarang', 80000);
INSERT INTO stds_nkocet VALUES (409, 'suraj', 90000);
INSERT INTO stds_nkocet VALUES (410, 'chetan', 100000);
