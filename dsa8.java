// containment in java is called composition in java is an object oriented programming concept where one class contains an object of another class as a member
// it represents a has-a relationship unlike inheritance which represents an is-a relationship

/*
key points
has-a relationship: a class has another class object
code reusability: instead of inheriting you can reuse functionality by including 
flexibility: easier to change relationship compared to inheritance
*/

class Course {
    String cName;
    int credits;

    Course(String cName, int credits) {
        this.cName = cName;
        this.credits = credits;
    }

    void displayCourseInfo() {
        System.out.println("Course: " + cName + " (" + credits + " credits)");
    }
}

class Professor {
    String pName;
    Course course; // Composition: Professor has a Course

    Professor(String pName, Course course) {
        this.pName = pName;
        this.course = course;
    }

    void displayProfessorInfo() {
        System.out.println("Professor: " + pName);
        course.displayCourseInfo();
    }
}

class University {
    String uName;
    Professor professor; // Composition: University has a Professor

    University(String uName, Professor professor) {
        this.uName = uName;
        this.professor = professor;
    }

    void displayUniversityInfo() {
        System.out.println("University: " + uName);
        professor.displayProfessorInfo();
    }
}

public class dsa8 {
    public static void main(String[] args) {
        // Create a Course object
        Course javaCourse = new Course("Java Programming", 4);

        // Create a Professor object that HAS A Course
        Professor prof = new Professor("Dr. Smith", javaCourse);

        // Create a University object that HAS A Professor
        University myUni = new University("Zensar Academy", prof);

        // Display the hierarchy
        System.out.println("--- University Hierarchy (Containment) ---");
        myUni.displayUniversityInfo();
    }
}