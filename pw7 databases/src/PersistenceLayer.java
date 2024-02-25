import java.sql.*;

import static io.agroal.api.configuration.supplier.AgroalPropertiesReader.JDBC_URL;

public class PersistenceLayer {

    private static final String JDBC_URL = "jdbc:sqlite:test.db";

    public void createTables() {
        try (Connection conn = DriverManager.getConnection(JDBC_URL);
             Statement statement = conn.createStatement()) {
            // Таблиця кафе
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS Cafe (id INTEGER PRIMARY KEY, name TEXT, address TEXT)");

            // Таблиця ресторану
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS Restaurant (id INTEGER PRIMARY KEY, name TEXT, address TEXT)");

            // Таблиця готелю
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS Hotel (id INTEGER PRIMARY KEY, name TEXT, address TEXT)");

            // Таблиця супермаркету
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS Supermarket (id INTEGER PRIMARY KEY, name TEXT, address TEXT)");

            // Таблиця магазину техніки
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS TechStore (id INTEGER PRIMARY KEY, name TEXT, address TEXT)");

            System.out.println("Tables created successfully.");
        } catch (SQLException e) {
            System.out.println("Error creating tables: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        PersistenceLayer persistenceLayer = new PersistenceLayer();
        persistenceLayer.createTables(); // Створюємо таблиці

        Cafe cafe = new Cafe();
        cafe.addRecord("Cafe 1", "123 Main St");

        Restaurant restaurant = new Restaurant();
        restaurant.addRecord("Restaurant 1", "456 Elm St");

        Hotel hotel = new Hotel();
        hotel.addRecord("Hotel 1", "789 Oak St");

        Supermarket supermarket = new Supermarket();
        supermarket.addRecord("Supermarket 1", "101 Maple St");

        TechStore techStore = new TechStore();
        techStore.addRecord("TechStore 1", "202 Pine St");

        cafe.updateRecord(1, "Updated Cafe Name", "Updated Cafe Address");

        restaurant.deleteRecord(1);
    }
}

