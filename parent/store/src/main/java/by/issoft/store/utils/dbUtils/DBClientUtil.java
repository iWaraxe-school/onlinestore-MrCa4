package by.issoft.store.utils.dbUtils;

import lombok.SneakyThrows;

import java.sql.*;

public class DBClientUtil {
        private static DBClientUtil dbClientUtil= null;
        public static final String DB_URL = "jdbc:sqlite:test.sqlite";
        public static final String DB_DRIVER = "org.sqlite.JDBC";
        private Connection connection;
        private Statement statement;

        private DBClientUtil(){

        }

        public static DBClientUtil getDBClientUtil(){
                if(dbClientUtil==null){
                        dbClientUtil=new DBClientUtil();
                        dbClientUtil.connect();
                }
                return dbClientUtil;
        }
        @SneakyThrows
        private void connect() {
                Class.forName(DB_DRIVER);
                this.connection = DriverManager.getConnection(DB_URL);
                 this.statement = this.connection.createStatement();
        }


        @SneakyThrows
        public static ResultSet exec(String query){
                System.out.println(query);
                ResultSet resultSet = null;
                if(query.toLowerCase().startsWith("select")) {
                        resultSet = DBClientUtil.getDBClientUtil().statement.executeQuery(query);
                }
                else{
                     DBClientUtil.getDBClientUtil().statement.executeUpdate(query);
                }
            return resultSet;
        }
}

