package backend.models.base;

import java.util.Date;

public interface InfoSet {
    void setName(String name);

    void setDOB(Date date);

    void setEmail(String email);

    void setSemester(Integer semester);

    void setGrade(Character grade);

    void setCourseId(String courseId);

    void setCourseName(String courseName);

    void addCredits(Integer credits);
}
