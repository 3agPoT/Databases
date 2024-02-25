import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static io.agroal.api.configuration.supplier.AgroalPropertiesReader.JDBC_URL;

class BaseTable {
    private String tableName;

    public BaseTable(String tableName) {
        this.tableName = tableName;
    }

    public void addRecord(String name, String address) {
        String sql = "INSERT INTO " + tableName + " (name, address) VALUES (?, ?)";
        try (Connection conn = DriverManager.getConnection(JDBC_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, address);
            pstmt.executeUpdate();
            System.out.println("Record added successfully to table: " + tableName);
        } catch (SQLException e) {
            System.out.println("Error adding record: " + e.getMessage());
        }
    }

    public void updateRecord(int id, String name, String address) {
        String sql = "UPDATE " + tableName + " SET name = ?, address = ? WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(JDBC_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, address);
            pstmt.setInt(3, id);
            pstmt.executeUpdate();
            System.out.println("Record updated successfully in table: " + tableName);
        } catch (SQLException e) {
            System.out.println("Error updating record: " + e.getMessage());
        }
    }

    public void deleteRecord(int id) {
        String sql = "DELETE FROM " + tableName + " WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(JDBC_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("Record deleted successfully from table: " + tableName);
        } catch (SQLException e) {
            System.out.println("Error deleting record: " + e.getMessage());
        }
    }
}
