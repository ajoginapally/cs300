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
}