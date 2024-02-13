package backend.database;

/*
Import java logging tools
 */
import backend.logging.SetupLog;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


import javax.xml.transform.Result;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
Import SQL tools
 */
import java.sql.Connection;

public class SQLQueries {

    private final Logger logger;
    private final Connection connection;

    public SQLQueries(Connection connection, Object classObject) {
        this.logger = new SetupLog(classObject, Level.INFO).setupLogging();
        this.connection = connection;
    }

    private static @NotNull String getExceptionString(@NotNull Exception exec) {
        return Arrays.toString(exec.getStackTrace());
    }

    public @Nullable ResultSet executeQuery(String query) throws RuntimeException {
        ResultSet result = null;

        try (Statement stmt = this.connection.createStatement()) {
            result = stmt.executeQuery(query);
        } catch (SQLException exception) {
            this.logger.severe("Could not access the database.");
            throw new RuntimeException(exception);
        } catch (Exception e) {
            this.logger.severe("Un-handled Exception: " + getExceptionString(e));
        }

        return result;
    }
}