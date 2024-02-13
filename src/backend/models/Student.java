package backend.models;

import backend.database.SQLQueries;
import backend.models.base.Attendance;
import backend.models.base.InfoGet;
import backend.models.base.Semester;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Date;
import java.util.Objects;


public class Student implements InfoGet {

    private final SQLQueries queryConnection;

    /*
    Use This variable as a reference for the object which can query in other tables.
     */
    private final String id;

    public Student(Connection connection, @NotNull String id) {
        this.queryConnection = new SQLQueries(connection, this);
        this.id = Objects.requireNonNull(id, "ID is required for creation of this class.");

    }

    @Override
    public @Nullable String getName() throws RuntimeException {
        ResultSet result;
        String value = null;

        try {
            result = this.queryConnection.executeQuery("SELECT name FROM college.student WHERE Student_ID = " + this.id);
            if (result != null && !result.first()) {
                value = result.getString("name");
            }
        } catch (RuntimeException exception) {
            throw new RuntimeException("Can not access the database.");
        } catch (Exception ignored) {
            // Do nothing.
        }

        return value;
    }

    @Override
    public @NotNull String getId() {
        return this.id;
    }

    @Override
    public @Nullable Date getDOB() throws RuntimeException {
        ResultSet result;
        Date value = null;

        try {
            result = this.queryConnection.executeQuery("SELECT DOB FROM college.student WHERE Student_ID = " + this.id);

            if (result != null && !result.first()) {
                value = result.getDate("DOB");
            }
        } catch (RuntimeException exception) {
            throw new RuntimeException("Can not access the database.");
        } catch (Exception ignored) {
            // Do nothing.
        }

        return value;
    }

    @Override
    public @Nullable String getEmail() throws RuntimeException {
        ResultSet result;
        String value = null;

        try {
            result = this.queryConnection.executeQuery("SELECT email FROM college.student WHERE Student_ID = " + this.id);
            if (result != null && !result.first()) {
                value = result.getString("email");
            }
        } catch (RuntimeException exception) {
            throw new RuntimeException(exception.getMessage());
        } catch (Exception ignored) {
            // Do nothing.
        }

        return value;
    }

    @Override
    public @Nullable Semester getSemester() throws RuntimeException {
        try {
            return Semester.getInstance(this.id, this.queryConnection);
        } catch (RuntimeException exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }

    @Override
    public @Nullable Attendance getAttendance(String course_id) throws RuntimeException {
        try {
            return Attendance.getInstance(this.id, this.queryConnection);
        } catch (RuntimeException exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }

    @Override
    public Character getGrade() {
        return null;
    }

    @Override
    public String getCourseId() {
        return null;
    }

    @Override
    public String getCourseName() {
        return null;
    }

    @Override
    public Integer getCurrentCredits() {
        return null;
    }

}
