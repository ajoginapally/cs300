package Project4;

import java.util.ArrayList;

public class Student {

  // Grade scale constants
  public static final double A_THRESHOLD = 90.0;
  public static final double B_THRESHOLD = 80.0;
  public static final double C_THRESHOLD = 70.0;
  public static final double D_THRESHOLD = 60.0;
  public static final double MIN_GRADE = 0.0;
  public static final double MAX_GRADE = 100.0;

  private String name;
  private String studentId;
  private ArrayList<Double> grades;

  public Student(String name, String studentId) {
    this.name = name;
    this.studentId = studentId;
    this.grades = new ArrayList<>();
  }

  public void addGrade(double grade) throws InvalidGradeException {
    if (grade < MIN_GRADE || grade > MAX_GRADE) {
      throw new InvalidGradeException("Grade must be between " + MIN_GRADE + " and " + MAX_GRADE);
    }
    grades.add(grade);
  }

  public double getAverage() throws NoGradesException {
    if (grades.isEmpty()) {
      throw new NoGradesException("No grades available for student " + name);
    }
    double sum = 0.0;
    for (double grade : grades) {
      sum += grade;
    }
    return sum / grades.size();
  }

  public String getLetterGrade() throws NoGradesException {
    double average = getAverage();
    if (average >= A_THRESHOLD) {
      return "A";
    } else if (average >= B_THRESHOLD) {
      return "B";
    } else if (average >= C_THRESHOLD) {
      return "C";
    } else if (average >= D_THRESHOLD) {
      return "D";
    } else {
      return "F";
    }
  }
  
  public String getName() {
    return name;
  }

  public String getStudentId() {
    return studentId;
  }

  public ArrayList<Double> getGrades() {
    // Create a defensive copy to maintain encapsulation
    return new ArrayList<>(grades);
  }

  /**
   * Returns a string representation of the student including name, ID, average, and letter grade.
   * If no grades are recorded, indicates that no grades are available.
   *
   * @return a formatted string containing student information
   */
  @Override public String toString() {
    try {
      return String.format(
        "%s (ID: %s) - Average: %.2f (%s)",
        name,
        studentId,
        getAverage(),
        getLetterGrade()
      );
    } catch (NoGradesException e) {
      return String.format("%s (ID: %s) - No grades recorded", name, studentId);
    }
  }
}