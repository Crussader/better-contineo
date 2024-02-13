package backend.models.base;

import backend.database.SQLQueries;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Attendance {

    private final String courseId;
    private final float percent;
    private final String professorId;
    public Attendance(String courseId, float percent, String professorId) {
        this.courseId = courseId;
        this.percent = percent;
        this.professorId = professorId;
    }

    public static @Nullable Attendance getInstance(@NotNull String studentId, @NotNull SQLQueries sql) throws RuntimeException {
        ResultSet result = null;
        Attendance attendance = null;

        try {
            result = sql.executeQuery("SELECT * FROM college.attendance WHERE Student_ID = " + studentId);
        } catch (RuntimeException exception) {
            throw new RuntimeException(exception.getMessage());
        }

        if (result == null) {
            return null;
        }

        try {
            attendance = new Attendance(
                    result.getString("course_id"),
                    result.getFloat("attendance_percent"),
                    result.getString("professor_id")
            );
        } catch (SQLException exception) {
            throw new RuntimeException(exception.getMessage());
        }

        return attendance;
    }

    public final @NotNull String getCourseId() {
        return this.courseId;
    }

    public final Float getPercent() {
        return this.percent;
    }

    public final String getProfessorId() {
        return this.professorId;
    }
}
