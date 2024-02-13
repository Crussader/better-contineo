package backend.models.base;

import backend.database.SQLQueries;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.sql.ResultSet;
import java.util.Arrays;

class Course {
    public final String courseId;
    public final String courseName;
    public final int credits;

    public Course(String courseId, String courseName, int credits) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.credits = credits;
    }
}

public class Courses {

    public static @Nullable Courses getInstance(@NotNull String studentId, @NotNull SQLQueries sql) {
        // Query the database for the courses information for that student.
        ResultSet result = null;
        Courses courses = null;

        try {
            result = sql.executeQuery("SELECT * FROM college.course WHERE Student_ID = " + studentId);
        } catch (RuntimeException exception) {
            throw new RuntimeException(exception.getMessage());
        }

        if (result == null) {
            return null;
        }

        courses = new Courses();

        try {
            while (result.next()) {

            }
        }
    }
}
