package backend;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;

import java.util.logging.Logger;
import java.util.logging.LogRecord;


public class SQLDatabasePlugin extends Base {
    private Connection connection;

    private final String url;
    private final String user;
    private final String password;
    private final Logger log;

    private boolean connected;

    public SQLDatabasePlugin(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
        this.connected = false;
        this.connection = null;
        this.log = SQLDatabasePlugin.setupLogging("database");
    }

    public void connect() throws SQLException {
        if (this.connected) {
            throw new SQLException("Already Connected to database.");
        }

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection(this.url, this.user, this.password);
        } catch (Exception e) {
            e.printStackTrace();
            this.connection = null;
            return;
        }

        try {
            /* Wait for 5 seconds */
            this.connected = this.connection.isValid(3);
        } catch (SQLException e) {
            e.printStackTrace();
            this.connected = false;

        }

    }

    public boolean isConnected() {
        return this.connected;
    }

    public String getUrl() {
        return this.url;
    }

    public String getUser() {
        return this.user;
    }

    public String getPassword() {
        return this.password;
    }

    public static void main(String[] args) {
        SQLDatabasePlugin plugin = new SQLDatabasePlugin("jdbc:mysql://localhost:3306", "root", "");

        try {
            plugin.connect();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            System.out.println("Connection: " + plugin.isConnected());
        }
    }
}

//
//public class SQLDatabasePulgin {
//    private Connection connection;
//    private final String url;
//    private final String user;
//    private final String password;
//
//
//    public SQLDatabasePulgin(String url, String user, String password) {
//        this.url = url;
//        this.user = user;
//        this.password = password;
//    }
//
//    public void connect() {
//        Class.forName("java.lang.Thread");
//        this.connection = DriverManager.getConnection(this.url, this.user, this.password);
//    }
//}
//package backend;
//import java.sql.*;
//public class SQLDatabasePlugin {
//    public static void main(String args[]){
//        try{
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            Connection con=DriverManager.getConnection(
//                    "jdbc:mysql://localhost:3306", "root", "");
////here sonoo is database name, root is username and password
//            Statement stmt=con.createStatement();
//            ResultSet rs=stmt.executeQuery("select * from emp");
//            while(rs.next())
//                System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));
//            con.close();
//        }catch(Exception e){ System.out.println(e);}
//    }
//}