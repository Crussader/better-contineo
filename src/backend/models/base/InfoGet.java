package backend.models.base;

import java.util.Date;

/*
This interface is implemented in general to all the present classes
methods that do not apply raise "UnsupportedOperationException"
 */

public interface InfoGet {
    String getName();

    String getId();

    Date getDOB();

    String getEmail();

    Semester getSemester();

    Attendance getAttendance(String courseId);

    Character getGrade();

    String getCourseId();

    String getCourseName();

    Integer getCurrentCredits();

}
