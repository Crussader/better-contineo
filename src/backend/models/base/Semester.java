package backend.models.base;

import backend.database.SQLQueries;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Semester {

    private final int number;
    private final Date start;
    private final Date end;

    public Semester(int number, Date start, Date end) {
        this.number = number;
        this.start = start;
        this.end = end;
    }

    public static @Nullable Semester getInstance(@NotNull String studentId, @NotNull SQLQueries sql) throws RuntimeException {
        // Query the database for the semester information
        ResultSet result = null;
        Semester semester = null;
        try {
            result = sql.executeQuery("SELECT * FROM college.semester WHERE Student_ID = " + studentId);
        } catch (RuntimeException exception) {
            throw new RuntimeException(exception.getMessage());
        }

        if (result == null) {
            return null;
        }

        try {
            semester = new Semester(
                    result.getInt("number"),
                    result.getDate("start"),
                    result.getDate("end")
            );
        } catch (SQLException exception) {
            throw new RuntimeException(exception.getMessage());
        }

        return semester;
    }

    public final int getNumber() {
        return this.number;
    }

    public final Date getStart() {
        return this.start;
    }

    public final Date getEnd() {
        return this.end;
    }
}
