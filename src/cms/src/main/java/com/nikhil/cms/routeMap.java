package com.nikhil.cms;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class routeMap {
    @GetMapping("/")
    public String hello() {
        return "home"; // Return the template name to render
    }

    @GetMapping("/login")
    public String login() {
        return "login"; // Return the template name to render
    }

    @GetMapping("/dashboard_stud")
    public String dashboardStud() {
        return "dashboard_stud"; // Return the template name to render
    }

    @GetMapping("/grades_stud")
    public String gradesStud() {
        return "grades_stud"; // Return the template name to render
    }

    @GetMapping("/attendance")
    public String attendance() {
        return "attendance"; // Return the template name to render
    }

    @GetMapping("/fee_stud")
    public String feeStud() {
        return "fee_stud"; // Return the template name to render
    }

    @GetMapping("/dashboard_prof")
    public String dashboardProf() {
        return "dashboard_prof"; // Return the template name to render
    }

    @GetMapping("/upload_marksfile")
    public String uploadMarksFile() {
        return "upload_marksfile"; // Return the template name to render
    }
    @GetMapping("/upload_attendance")
    public String uploadAttendance() {
        return "upload_attendance"; // Return the template name to render
    }
    
}
