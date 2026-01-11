# Zensar ATM Project 🏧

A robust, Java-based ATM simulation application demonstrating advanced Object-Oriented Programming (OOP) principles, Swing GUI implementation, and MySQL database integration.

![Verified](https://img.shields.io/badge/Commits-Verified-brightgreen)

## 🚀 Features

- **Graphical User Interface (GUI)**: Built with Java Swing for a modern, responsive user experience.
- **Advanced OOP Design**:
  - **Abstraction**: Base `BankAccount` class defining core contracts.
  - **Polymorphism**: Unified handling of `CheckingAccount` and `SavingAccount`.
  - **Inheritance**: Specialized account behaviors extending the base logic.
- **Database Integration**: Real-time persistence using MySQL (`ZENSAR` database).
- **Investment Calculator**: A recursive feature to calculate the future value of investments with compound interest.
- **Security**: PIN-based authentication.

## 🛠️ Tech Stack

- **Language**: Java (JDK 8+)
- **UI Framework**: Swing / AWT
- **Database**: MySQL 8.0
- **Connectivity**: JDBC (`mysql-connector-j-8.4.0`)

## 📋 Prerequisites

1.  **Java Development Kit (JDK)** installed.
2.  **MySQL Server** running locally.
3.  **MySQL Connector/J** library (included in `lib/`).

## ⚙️ Setup & Installation

1.  **Database Setup**:

    - Create a database named `zensar`.
    - Import the `setup.sql` script (if provided) or create the `accounts` table manually.
    - _Default Credentials_: User `root`, Password `aditya`.

2.  **Compilation**:

    ```bash
    javac -cp ".;lib/mysql-connector-j-8.4.0.jar" ATM.java
    ```

3.  **Running the Application**:
    - **Windows**: Double-click `LaunchATM.bat` (if created) or run:
      ```bash
      java -cp ".;lib/mysql-connector-j-8.4.0.jar" ATM
      ```
    - **Default Login**:
      - Customer ID: `12345`
      - PIN: `1234`

## 📂 Project Structure

- `ATM.java`: Main application entry point and GUI logic.
- `setup.sql`: Database schema and initial data.
- `LaunchDBViewer.bat`: Utility to view database records.
- `lib/`: Contains external JAR dependencies.

## 🤝 Contribution

Feel free to fork this repository and submit pull requests for new features or bug fixes.

---

_Developed for ZENSAR Training Module._
