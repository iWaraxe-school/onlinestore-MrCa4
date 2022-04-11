package by.issoft.store.utils;

import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DBClientUtil {

        public static final String DB_URL = "jdbc:sqlite:test.sqlite";
        public static final String DB_DRIVER = "org.sqlite.JDBC";

        private Connection connection;
        private Statement statement;

        @SneakyThrows
        public void connect() {
                Class.forName(DB_DRIVER);
                this.connection = DriverManager.getConnection(DB_URL);
                this.connection.close();
                this.statement = this.connection.createStatement();

        }


        @SneakyThrows
        public void exec(String query){
            this.statement.executeQuery(query);
            this.connection.commit();
        }
}

