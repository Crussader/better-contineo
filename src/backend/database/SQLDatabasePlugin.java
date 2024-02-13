package backend.database;

/*
Importing SQL Packages
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
Importing Logging Packages
 */
import java.util.logging.Level;
import java.util.logging.Logger;

/*
Importing Misc. Packages
 */
import java.util.Arrays;
import backend.logging.SetupLog;
import org.jetbrains.annotations.NotNull;


public class SQLDatabasePlugin {

    /*
    Database Connecting Attributes
     */
    private final String url;
    private final String user;
    private final String password;

    /*
    Database Connection Attributes
     */
    private Connection connection;
    private final Logger logger;

    public SQLDatabasePlugin(String url, String user, String password, Level logLevel) {
        // Set Attributes
        this.url = url;
        this.user = user;
        this.password = password;

        // Set up logging
        this.logger = new SetupLog(this, logLevel).setupLogging();
    }

    private static @NotNull String getExceptionString(@NotNull Exception exec) {
        return Arrays.toString(exec.getStackTrace());
    }

    public void close(boolean retry, int retries) throws InterruptedException {

        try {
            // Check if connection is a null pointer
            if (this.connection == null) {
                this.logger.info("Connection is NULL.");
                return;
            }
            /*
            Check if the connection is already closed
            if not then close and log.
             */
            if (this.connection.isClosed()) {
                this.logger.info("Connection is already closed.");
            } else {
                this.connection.close();

                this.logger.info("Connection is closed.");
            }


        } catch (SQLException e) {
            // Print the stack trace in log file and console
            this.logger.warning(getExceptionString(e));
            e.printStackTrace();

            /*
            Check if the retry is true and set a default retries
             */

            if (retry) {
                retries = Math.max(retries, 0);

                for (int i = 0; i < retries; i++) {
                    try {
                        this.connection.close();
                        return;
                    } catch (SQLException exec) {
                        Thread.sleep(3 * 1000); // 3 Seconds
                    }
                }
            }
        }
    }

    public void close() throws InterruptedException {
        this.close(true, 3);
    }


    public void connect(boolean retry, int retries) throws AlreadySQLConnectedException {
        if (this.isConnected() && !retry) {
            throw new AlreadySQLConnectedException("The Connection is already made.");
        }


        // Close the connection if made and connect again.
        if (this.isConnected() && retry) {

            /*
            Connection is not closed,
            since SQL Exception was raised.
             */
            try {
                this.close(true, retries);
            } catch (InterruptedException exception) {
                String exec = Arrays.toString(exception.getStackTrace());
                this.logger.warning("Error in closing database connection." + exec);
                return;
            }

        }

        // Connect to the database.
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection(this.url, this.user, this.password);
        } catch (ClassNotFoundException exec) {
            exec.printStackTrace();
            return;
        } catch (SQLException exception) {
            this.logger.severe("Can not connect to Database." + getExceptionString(exception));
            return;
        }

        if (this.isConnected()) {
            this.logger.info("Connection made to database");
        } else if (retry) {
            this.logger.info("Connection could not be made. retrying.");

            retries = Math.max(retries, 0);

            for (int i = 0; i < retries; i++) {
                this.connect(false, 0);
            }
        }

    }

    public boolean isConnected(int timeout) {
        try {
            if (this.connection != null) {
                return this.connection.isValid(timeout);
            }
        } catch (SQLException exception) {
            this.logger.warning(getExceptionString(exception));
        }

        return false;
    }

    public boolean isConnected() {
        return this.isConnected(3);
    }

    public static void main(String[] args) {
        SQLDatabasePlugin sql = new SQLDatabasePlugin("jdbc:mysql://localhost:3306", "root", "root", Level.INFO);

        try {
            sql.connect(true, 3);
            sql.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}


