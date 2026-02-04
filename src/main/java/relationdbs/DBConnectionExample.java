package relationdbs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBConnectionExample {

    private static final String URL_DB = "jdbc:postgresql://localhost:5432/happy";
    private static final String USER = "postgres";
    private static final String PASS = "admin";

    public static void main(String[] args) {

        // 1️⃣ Crear la base de datos si no existe
        try (Connection conn =
                     DriverManager.getConnection(
                             "jdbc:postgresql://localhost:5432/postgres",
                             USER, PASS);
             Statement statement = conn.createStatement()) {

            try {
                statement.execute("CREATE DATABASE happy");
                System.out.println("Base de datos 'happy' creada correctamente");
            } catch (SQLException e) {
                if ("42P04".equals(e.getSQLState())) {
                    System.out.println("La base de datos ya existía");
                } else {
                    throw e;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        // 2️⃣ Crear tabla y trabajar con datos
        try (Connection conn =
                     DriverManager.getConnection(URL_DB, USER, PASS)) {

            String createTable =
                    "CREATE TABLE IF NOT EXISTS users (" +
                            "id SERIAL PRIMARY KEY, " +
                            "username VARCHAR(100), " +
                            "password VARCHAR(100), " +
                            "vip BOOLEAN, " +
                            "balance FLOAT" +
                            ");";

            PreparedStatement pst = conn.prepareStatement(createTable);
            pst.execute();
            pst.close();

            System.out.println("Tabla users lista");

            // INSERT
            String insert =
                    "INSERT INTO users (username, password, vip, balance) " +
                            "VALUES (?, ?, ?, ?)";

            pst = conn.prepareStatement(insert);
            pst.setString(1, "Manolo");
            pst.setString(2, "1234");
            pst.setBoolean(3, true);
            pst.setFloat(4, 234.3f);
            pst.executeUpdate();
            pst.close();

            System.out.println("Usuario insertado");

            // DELETE
            String delete =
                    "DELETE FROM users WHERE username = ?";

            pst = conn.prepareStatement(delete);
            pst.setString(1, "Alejandro");
            pst.executeUpdate();
            pst.close();

            // SELECT
            String select =
                    "SELECT * FROM users WHERE username = ?";

            pst = conn.prepareStatement(select);
            pst.setString(1, "Manolo");

            ResultSet result = pst.executeQuery();

            while (result.next()) {
                System.out.println(
                        "ID: " + result.getInt("id") +
                                " | Usuario: " + result.getString("username") +
                                " | VIP: " + result.getBoolean("vip") +
                                " | Saldo: " + result.getFloat("balance")
                );
            }

            result.close();
            pst.close();
            pst.close();



        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
