import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Vector;

public class DBViewer extends JFrame {
    private JComboBox<String> tableList;
    private JTable dataTable;
    private DefaultTableModel tableModel;
    private Connection conn;

    // Configuration
    private static final String URL = "jdbc:mysql://localhost:3306/zensar"; // Add database name if known, else default
    private static final String USER = "root";
    private static final String PASS = "omkar@123";

    public DBViewer() {
        setTitle("Antigravity DB Viewer");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Top Query Panel
        JPanel topPanel = new JPanel();
        tableList = new JComboBox<>();
        JButton loadButton = new JButton("Load Table");

        topPanel.add(new JLabel("Select Table:"));
        topPanel.add(tableList);
        topPanel.add(loadButton);
        add(topPanel, BorderLayout.NORTH);

        // Table
        tableModel = new DefaultTableModel();
        dataTable = new JTable(tableModel);
        add(new JScrollPane(dataTable), BorderLayout.CENTER);

        // Usage Logic
        loadButton.addActionListener(e -> loadTableData((String) tableList.getSelectedItem()));

        connectAndLoadMetadata();
    }

    private void connectAndLoadMetadata() {
        try {
            conn = DriverManager.getConnection(URL, USER, PASS);
            DatabaseMetaData meta = conn.getMetaData();

            // Get all databases/schemas first, but for simplicity let's list tables from
            // the current catalog
            // If URL doesn't specify DB, we might see nothing or system tables.
            // Let's try to get catalogs
            ResultSet catalogs = meta.getCatalogs();
            while (catalogs.next()) {
                String dbName = catalogs.getString("TABLE_CAT");
                // System.out.println("Found DB: " + dbName);
            }

            // For now, let's just list all tables we can see
            ResultSet tables = meta.getTables(null, null, "%", new String[] { "TABLE" });
            while (tables.next()) {
                tableList.addItem(tables.getString("TABLE_NAME"));
            }
            if (tableList.getItemCount() > 0) {
                tableList.setSelectedIndex(0);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Connection Failed: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void loadTableData(String tableName) {
        if (tableName == null)
            return;
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM " + tableName);
            ResultSetMetaData meta = rs.getMetaData();
            int columnCount = meta.getColumnCount();

            // Columns
            Vector<String> columns = new Vector<>();
            for (int i = 1; i <= columnCount; i++) {
                columns.add(meta.getColumnName(i));
            }

            // Data
            Vector<Vector<Object>> data = new Vector<>();
            while (rs.next()) {
                Vector<Object> row = new Vector<>();
                for (int i = 1; i <= columnCount; i++) {
                    row.add(rs.getObject(i));
                }
                data.add(row);
            }

            tableModel.setDataVector(data, columns);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error loading table: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new DBViewer().setVisible(true));
    }
}
