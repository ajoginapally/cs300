package Project4;
import java.util.ArrayList;

public class GradeBook {
  private ArrayList<Student> students;

  public GradeBook() {
    this.students = new ArrayList<>();
  }
  
  public void addStudent(Student student) throws DuplicateStudentException{
    if (student == null) {
      throw new IllegalArgumentException("Student cannot be null");
    }

    for (Student s : students) {
      if (s.getStudentId().equals(student.getStudentId())) {
        throw new DuplicateStudentException("Student with ID " + student.getStudentId() + " already exists");
      }
    }
    
    students.add(student);
  }

  public Student findStudent(String studentId) throws StudentNotFoundException{
    if (studentId == null || studentId.isBlank()) {
      throw new IllegalArgumentException("Student ID cannot be null or blank");
    }
    for (Student s : students) {
      if (s.getStudentId().equals(studentId)) {
        return s;
      }
    }
    throw new StudentNotFoundException("Student with ID " + studentId + " not found");
  }

  public double getClassAverage() throws NoGradesException {
    if (students.isEmpty()) {
      throw new NoGradesException("No students in the grade book");
    }
    double total = 0.0;
    int count = 0;
    for (Student s : students) {
      try {
        total += s.getAverage();
        count++;
      } catch (NoGradesException e) {
        // Skip students with no grades
      }
    }
    if (count == 0) {
      throw new NoGradesException("No grades available for any student");
    }
    return total / count;
  }

  public ArrayList<Student> getAllStudents() {
    // Create a defensive copy to maintain encapsulation
    return new ArrayList<>(students);
  }

  public ArrayList<Student> getHonorsStudents() {
    ArrayList<Student> honorsStudents = new ArrayList<>();
    for (Student s : students) {
      try {
        if (s.getAverage() >= Student.A_THRESHOLD) {
          honorsStudents.add(s);
        }
      } catch (NoGradesException e) {
        // Skip students with no grades
      }
    }
    return honorsStudents;
  }

  public String generateReport() {
    String ans = "=== GRADEBOOK REPORT ===\n";
    if (students.isEmpty()) {
      ans += "No students in the grade book.";
    } else {
        ans += "Total Students: " + students.size() + "\n";
        ans += "Student Details:\n================\n";
        for (Student s : students) {
          ans += s.toString() + "\n";
        }
        try {
          double classAverage = getClassAverage();
          ans += "Class Average: " + classAverage + "\n";
        } catch (NoGradesException e) {
            //Skip
        }        
        ans += "Honors Students (A average): " + getHonorsStudents().size() + "\n";
        for (Student s : getHonorsStudents()) {
          ans += " - " + s.getName() + "\n";
        }
        ans += "================";
      }
    
    return ans;
  }
}