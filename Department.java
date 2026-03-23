package studentgradingsystemaleyna;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Course {

    private int courseId;
    private String courseCode;
    private String courseName;
    private int departmentId;

  
    public Course(int courseId, String courseCode, String courseName, int departmentId) {
        this.courseId = courseId;
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.departmentId = departmentId;
    }

    
    public int getCourseId() {
        return courseId;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public int getDepartmentId() {
        return departmentId;
    }

  
    public static void addCourseDB(String code, String name, int deptId) {

        String sql = "INSERT INTO course (course_code, course_name, dept_id) VALUES (?,?,?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, code);
            ps.setString(2, name);
            ps.setInt(3, deptId);

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<Course> getAllCoursesDB() {

        List<Course> list = new ArrayList<>();
        String sql = "SELECT * FROM course";

        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Course c = new Course(
                        rs.getInt("course_id"),
                        rs.getString("course_code"),
                        rs.getString("course_name"),
                        rs.getInt("dept_id")
                );
                list.add(c);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

 
    public static void deleteCourseDB(int id) {

        String sql = "DELETE FROM course WHERE course_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
