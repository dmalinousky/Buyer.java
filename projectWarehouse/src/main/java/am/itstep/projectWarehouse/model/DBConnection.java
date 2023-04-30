package am.itstep.projectWarehouse.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
@Getter
@AllArgsConstructor
public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/projectWH";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "123qwe";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }
}
