import java.util.*;

class CourseFullException extends Exception {
    public CourseFullException(String message) {
        super(message);
    }
}

class PrerequisiteNotMetException extends Exception {
    public PrerequisiteNotMetException(String message) {
        super(message);
    }
}

class Course {
    private String courseName;
    private int maxEnrollment;
    private List<Student> enrolledStudents;
    private String prerequisite;

    public Course(String courseName, int maxEnrollment, String prerequisite) {
        this.courseName = courseName;
        this.maxEnrollment = maxEnrollment;
        this.prerequisite = prerequisite;
        this.enrolledStudents = new ArrayList<>();
    }

    public String getCourseName() {
        return courseName;
    }

    public String getPrerequisite() {
        return prerequisite;
    }

    public boolean isCourseFull() {
        return enrolledStudents.size() >= maxEnrollment;
    }

    public void enrollStudent(Student student) throws CourseFullException, PrerequisiteNotMetException {
        // Check if the course is full
        if (isCourseFull()) {
            throw new CourseFullException("Error: The course " + courseName + " is full.");
        }

        // Check if the student has completed the prerequisite
        if (!student.hasCompletedPrerequisite(prerequisite)) {
            throw new PrerequisiteNotMetException("Error: " + student.getName() + " has not completed the prerequisite " + prerequisite + " for " + courseName + ".");
        }

        // Enroll student if no exceptions
        enrolledStudents.add(student);
        student.addCourse(courseName);
        System.out.println(student.getName() + " successfully enrolled in " + courseName + ".");
    }
}

class Student {
    private String name;
    private Set<String> completedCourses;
    private Set<String> enrolledCourses;

    public Student(String name) {
        this.name = name;
        this.completedCourses = new HashSet<>();
        this.enrolledCourses = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public void completeCourse(String courseName) {
        completedCourses.add(courseName);
    }

    public boolean hasCompletedPrerequisite(String prerequisite) {
        return completedCourses.contains(prerequisite);
    }

    public void addCourse(String courseName) {
        enrolledCourses.add(courseName);
    }

    public Set<String> getEnrolledCourses() {
        return enrolledCourses;
    }
}

public class UniversityEnrollmentSystem {
    public static void main(String[] args) {
        // Create students
        Student student1 = new Student("John Doe");
        Student student2 = new Student("Jane Smith");

        // Add completed courses to students
        student1.completeCourse("Core Java"); // John has completed Core Java
        student2.completeCourse("Basic Programming"); // Jane hasn't completed Core Java

        // Create courses
        Course course1 = new Course("Advanced Java", 2, "Core Java");
        Course course2 = new Course("Data Structures", 2, "Core Java");

        // Enroll students in courses
        try {
            // Try enrolling John in Advanced Java
            course1.enrollStudent(student1);

            // Try enrolling Jane in Advanced Java (should fail due to prerequisite not met)
            course1.enrollStudent(student2);
        } catch (CourseFullException | PrerequisiteNotMetException e) {
            System.out.println(e.getMessage());
        }

        // Enroll both students in Data Structures
        try {
            course2.enrollStudent(student1); // Should succeed
            course2.enrollStudent(student2); // Should fail due to prerequisite not met
        } catch (CourseFullException | PrerequisiteNotMetException e) {
            System.out.println(e.getMessage());
        }
    }
}
