import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.DecimalFormat;

// ----------------------------------------------------
// ATM GUI Class (Inheritance: ATM extends JFrame)
// ----------------------------------------------------
public class ATM extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;

    // Polymorphism: references the abstract parent class
    private BankAccount currentAccount;

    // Temporary storage for login credentials before account selection
    private int sessionCustomerId;
    private int sessionPin;

    // Panel Names
    private static final String LOGIN_PANEL = "Login";
    private static final String SELECTION_PANEL = "Selection";
    private static final String TRANSACTION_PANEL = "Transaction";

    // Components
    private JTextField customerField;
    private JPasswordField pinField;
    private JLabel balanceLabel;
    private JLabel messageLabel;
    private JLabel accountTypeLabel;

    public ATM() {
        setTitle("Zensar ATM System - OOP & Recursion Edition");
        setSize(550, 450); // Slightly larger for new feature
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Create Panels
        createLoginPanel();
        createSelectionPanel();
        createTransactionPanel();

        add(mainPanel);
    }

    private void createLoginPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel title = new JLabel("Welcome to ATM", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(title, gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 1;
        gbc.gridx = 0;
        panel.add(new JLabel("Customer Number:"), gbc);

        gbc.gridx = 1;
        customerField = new JTextField(15);
        panel.add(customerField, gbc);

        gbc.gridy = 2;
        gbc.gridx = 0;
        panel.add(new JLabel("PIN Number:"), gbc);

        gbc.gridx = 1;
        pinField = new JPasswordField(15);
        panel.add(pinField, gbc);

        JButton loginButton = new JButton("Sign In");
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));
        loginButton.setBackground(new Color(70, 130, 180));
        loginButton.setForeground(Color.WHITE);

        gbc.gridy = 3;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        loginButton.addActionListener(e -> handleLogin());
        panel.add(loginButton, gbc);

        mainPanel.add(panel, LOGIN_PANEL);
    }

    private void createSelectionPanel() {
        JPanel panel = new JPanel(new GridLayout(5, 1, 10, 10)); // Increased rows
        panel.setBorder(BorderFactory.createEmptyBorder(40, 60, 40, 60));

        JLabel info = new JLabel("Select Account Type", JLabel.CENTER);
        info.setFont(new Font("Arial", Font.BOLD, 18));

        JButton checkingBtn = new JButton("Checking Account");
        JButton savingBtn = new JButton("Saving Account");
        JButton exitBtn = new JButton("Exit");

        // Polymorphism in action: Instantiating different subclasses
        checkingBtn.addActionListener(e -> {
            try {
                // Initialize as CheckingAccount
                currentAccount = new CheckingAccount(sessionCustomerId, sessionPin);
                transitionToTransaction("Checking");
            } catch (SQLException ex) {
                showError("Database Error: " + ex.getMessage());
            }
        });

        savingBtn.addActionListener(e -> {
            try {
                // Initialize as SavingAccount
                currentAccount = new SavingAccount(sessionCustomerId, sessionPin);
                transitionToTransaction("Saving");
            } catch (SQLException ex) {
                showError("Database Error: " + ex.getMessage());
            }
        });

        exitBtn.addActionListener(e -> logout());

        panel.add(info);
        panel.add(checkingBtn);
        panel.add(savingBtn);
        panel.add(exitBtn);

        mainPanel.add(panel, SELECTION_PANEL);
    }

    private void createTransactionPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Top Info
        JPanel topPanel = new JPanel(new GridLayout(3, 1));
        accountTypeLabel = new JLabel("Account", JLabel.CENTER);
        accountTypeLabel.setFont(new Font("Arial", Font.ITALIC, 14));

        balanceLabel = new JLabel("Balance: $0.00", JLabel.CENTER);
        balanceLabel.setFont(new Font("Arial", Font.BOLD, 16));

        messageLabel = new JLabel("Select Operation", JLabel.CENTER);

        topPanel.add(accountTypeLabel);
        topPanel.add(balanceLabel);
        topPanel.add(messageLabel);

        // Buttons
        JPanel btnPanel = new JPanel(new GridLayout(3, 2, 10, 10)); // 3 rows now
        JButton withdrawBtn = new JButton("Withdraw");
        JButton depositBtn = new JButton("Deposit");
        JButton viewBalanceBtn = new JButton("Refresh Balance");
        JButton investBtn = new JButton("Future Value (Recursive)"); // New Feature
        JButton backBtn = new JButton("Back");

        withdrawBtn.addActionListener(e -> handleTransaction("Withdraw"));
        depositBtn.addActionListener(e -> handleTransaction("Deposit"));
        viewBalanceBtn.addActionListener(e -> updateBalanceLabel());
        investBtn.addActionListener(e -> showInvestmentCalculator());
        backBtn.addActionListener(e -> cardLayout.show(mainPanel, SELECTION_PANEL));

        btnPanel.add(withdrawBtn);
        btnPanel.add(depositBtn);
        btnPanel.add(viewBalanceBtn);
        btnPanel.add(investBtn);
        btnPanel.add(backBtn);

        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(btnPanel, BorderLayout.CENTER);

        mainPanel.add(panel, TRANSACTION_PANEL);
    }

    private void handleLogin() {
        try {
            int id = Integer.parseInt(customerField.getText());
            int pin = Integer.parseInt(new String(pinField.getPassword()));

            // Use a temporary DB check just for login validation
            if (DBHelper.validateUser(id, pin)) {
                this.sessionCustomerId = id;
                this.sessionPin = pin;
                customerField.setText("");
                pinField.setText("");
                cardLayout.show(mainPanel, SELECTION_PANEL);
            } else {
                showError("Invalid Username or PIN");
            }
        } catch (NumberFormatException e) {
            showError("Please enter numeric values only.");
        } catch (Exception e) {
            showError("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void transitionToTransaction(String typeName) {
        accountTypeLabel.setText(typeName + " Account");
        updateBalanceLabel();
        cardLayout.show(mainPanel, TRANSACTION_PANEL);
    }

    private void updateBalanceLabel() {
        if (currentAccount == null)
            return;
        // OOP: getBalance() is defined in Abstract class
        DecimalFormat moneyFormat = new DecimalFormat("\u20B9###,##0.00");
        balanceLabel.setText("Balance: " + moneyFormat.format(currentAccount.getBalance()));
        messageLabel.setText("Ready");
    }

    private void handleTransaction(String type) {
        String input = JOptionPane.showInputDialog(this, "Enter Amount to " + type + ":");
        if (input == null || input.trim().isEmpty())
            return;

        try {
            double amount = Double.parseDouble(input);
            if (amount <= 0) {
                showError("Amount must be positive.");
                return;
            }

            // OOP: Polymorphism in action.
            // We don't know if it's Checking or Saving, we just call the abstract method.
            if (type.equals("Withdraw")) {
                currentAccount.withdraw(amount);
            } else if (type.equals("Deposit")) {
                currentAccount.deposit(amount);
            }

            updateBalanceLabel();
            JOptionPane.showMessageDialog(this, "Transaction Successful!");

        } catch (NumberFormatException e) {
            showError("Invalid Amount Entered.");
        } catch (IllegalArgumentException e) {
            showError(e.getMessage()); // Catch insufficient funds etc
        } catch (Exception e) {
            showError("Database Error: " + e.getMessage());
        }
    }

    // Recursion Feature
    private void showInvestmentCalculator() {
        if (currentAccount == null)
            return;

        JDialog dialog = new JDialog(this, "Recursive Future Value Calc", true);
        dialog.setLayout(new GridLayout(4, 2, 10, 10));
        dialog.setSize(300, 200);
        dialog.setLocationRelativeTo(this);

        dialog.add(new JLabel("Years to Invest:"));
        JTextField yearsField = new JTextField();
        dialog.add(yearsField);

        dialog.add(new JLabel("Annual Rate (%):"));
        JTextField rateField = new JTextField("5.0");
        dialog.add(rateField);

        JLabel resultLabel = new JLabel("Val: -");
        JButton calcBtn = new JButton("Calculate");

        calcBtn.addActionListener(e -> {
            try {
                int years = Integer.parseInt(yearsField.getText());
                double rate = Double.parseDouble(rateField.getText()) / 100.0;
                double currentBal = currentAccount.getBalance();

                // RECURSION CALL
                double futureVal = calculateFutureValueRecursive(currentBal, rate, years);

                DecimalFormat df = new DecimalFormat("\u20B9###,##0.00");
                resultLabel.setText("Val: " + df.format(futureVal));
            } catch (Exception ex) {
                resultLabel.setText("Error");
            }
        });

        dialog.add(calcBtn);
        dialog.add(resultLabel);
        dialog.setVisible(true);
    }

    // ----------------------------------------------------
    // RECURSIVE METHOD
    // ----------------------------------------------------
    private double calculateFutureValueRecursive(double balance, double rate, int years) {
        // Base Case
        if (years <= 0) {
            return balance;
        }
        // Recursive Step: Balance grows by rate, years decrease by 1
        return calculateFutureValueRecursive(balance * (1 + rate), rate, years - 1);
    }

    private void logout() {
        currentAccount = null;
        sessionCustomerId = 0;
        sessionPin = 0;
        cardLayout.show(mainPanel, LOGIN_PANEL);
    }

    private void showError(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public static void main(String[] args) {
        try {
            // Static method call from abstract class context (if needed), or here just
            // distinct
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            System.out.println("Driver check failed");
        }

        SwingUtilities.invokeLater(() -> new ATM().setVisible(true));
    }
}

// ----------------------------------------------------
// ABSTRACTION: Abstract Base Class
// ----------------------------------------------------
abstract class BankAccount {
    // Encapsulation: Protected/Private fields
    protected int customerNumber;
    protected int pinNumber;
    protected double balance;

    public BankAccount(int customerNumber, int pinNumber, double initialBalance) {
        this.customerNumber = customerNumber;
        this.pinNumber = pinNumber;
        this.balance = initialBalance;
    }

    // Encapsulation: Getter
    public double getBalance() {
        return balance;
    }

    // Abstraction: Abstract methods forcing implementation
    public abstract void deposit(double amount) throws SQLException;

    public abstract void withdraw(double amount) throws SQLException;

    // Helper for DB updates
    protected void updateDB(String column, double newVal) throws SQLException {
        try (Connection conn = DBHelper.getConnection();
                PreparedStatement stmt = conn.prepareStatement(
                        "UPDATE accounts SET " + column + " = ? WHERE customer_number = ?")) {
            stmt.setDouble(1, newVal);
            stmt.setInt(2, customerNumber);
            stmt.executeUpdate();
        }
    }
}

// ----------------------------------------------------
// INHERITANCE: Concrete Subclass 1
// ----------------------------------------------------
class CheckingAccount extends BankAccount {

    public CheckingAccount(int cid, int pin) throws SQLException {
        super(cid, pin, 0);
        // Load initial balance
        this.balance = DBHelper.getBalance(cid, "checking_balance");
    }

    @Override
    public void deposit(double amount) throws SQLException {
        if (amount < 0)
            throw new IllegalArgumentException("Negative deposit");
        this.balance += amount;
        updateDB("checking_balance", this.balance);
    }

    @Override
    public void withdraw(double amount) throws SQLException {
        if (amount > this.balance)
            throw new IllegalArgumentException("Insufficient Funds in Checking");
        this.balance -= amount;
        updateDB("checking_balance", this.balance);
    }
}

// ----------------------------------------------------
// INHERITANCE: Concrete Subclass 2
// ----------------------------------------------------
class SavingAccount extends BankAccount {

    public SavingAccount(int cid, int pin) throws SQLException {
        super(cid, pin, 0);
        this.balance = DBHelper.getBalance(cid, "saving_balance");
    }

    @Override
    public void deposit(double amount) throws SQLException {
        if (amount < 0)
            throw new IllegalArgumentException("Negative deposit");
        this.balance += amount;
        updateDB("saving_balance", this.balance);
    }

    @Override
    public void withdraw(double amount) throws SQLException {
        if (amount > this.balance)
            throw new IllegalArgumentException("Insufficient Funds in Saving");
        this.balance -= amount;
        updateDB("saving_balance", this.balance);
    }
}

// ----------------------------------------------------
// Database Helper Class (Separation of Concerns)
// ----------------------------------------------------
class DBHelper {
    private static final String URL = "jdbc:mysql://localhost:3306/zensar";
    private static final String USER = "root";
    private static final String PASS = "aditya";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }

    public static boolean validateUser(int id, int pin) {
        try (Connection conn = getConnection();
                PreparedStatement stmt = conn
                        .prepareStatement("SELECT * FROM accounts WHERE customer_number = ? AND pin_number = ?")) {
            stmt.setInt(1, id);
            stmt.setInt(2, pin);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static double getBalance(int id, String column) throws SQLException {
        try (Connection conn = getConnection();
                PreparedStatement stmt = conn
                        .prepareStatement("SELECT " + column + " FROM accounts WHERE customer_number = ?")) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getDouble(1);
            }
        }
        return 0.0;
    }
}