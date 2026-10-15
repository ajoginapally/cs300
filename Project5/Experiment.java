/**
 * Represents a scientific experiment or research project within the facility.
 *
 * This class tracks the descriptive details of the experiment and the
 * number of personnel required to conduct or monitor the research safely.
 *
 */
public class Experiment {
  private String description;
  private int staff;

  /**
   * Constructs a new Experiment with the given details.
   * @param description A brief summary of the experiment's goals.
   * @param staff The number of staff members assigned to this project.
   * @throws IllegalArgumentException if staff is < 1
   */
  public Experiment(String description, int staff) {
    // TODO: Implement validation logic
    // TODO: Initialize fields with provided parameters
  }

  /**
   * Retrieves the description of the experiment.
   * @return The description string.
   */
  public String getDescription() {
    // TODO: Return the description field
    return null;
  }

  /**
   * Retrieves the total number of staff members assigned to the experiment.
   * @return The staff count.
   */
  public int getStaff() {
    // TODO: Return the staff field
    return 0;
  }

  /**
   * Updates the description of the experiment.
   * @param description The new description to set.
   */
  public void setDescription(String description) {
    // TODO: Update the description field
  }

  /**
   * Updates the number of staff members assigned to the experiment.
   * @param staff The new staff count to set.
   */
  public void setStaff(int staff) {
    // TODO: Update the staff field
  }

  /**
   * Returns a string representation of the experiment,
   * including its description and staff count.
   * @return A formatted string describing the experiment.
   */
  @Override
  public String toString() {
    return String.format("%s, staff=%d", this.description, this.staff);
  }
}
