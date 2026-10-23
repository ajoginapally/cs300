/*
 * Author: Arnav Joginapally
 * Email: joginapally@wisc.edu
 * Course: CS 300, Fall 2026
 * Assignment: Dino Park, Program 5
 * Citations: none
 */

import java.util.ArrayList;

/**
 * Represents a Dino Park with a collection of attractions.
 * Provides methods to add attractions and generate a safety report.
 */
public class DinoPark {
  private final ArrayList<SafetyCheckable> attractions;

  /**
   * Constructs a new DinoPark with an empty list of attractions.
   */
  public DinoPark() {
    this.attractions = new ArrayList<>();
  }

  /**
   * Adds an attraction to the Dino Park.
   *
   * @param attraction The attraction to add.
   * @throws IllegalArgumentException if the attraction is null.
   */
  public void addAttraction(SafetyCheckable attraction) {
    // Reject missing attractions before updating the park.
    if (attraction == null) {
      throw new IllegalArgumentException("Attraction cannot be null");
    }
    attractions.add(attraction);
  }

  /**
   * Generates a safety report for all attractions in the Dino Park.
   *
   * @return A string containing the safety report.
   */
  public String generateSafetyReport() {
    String report = "=== SAFETY REPORT ===";

    // Append the safety results for each attraction.
    for (SafetyCheckable attraction : attractions) {
      report += "\n" + attraction.toString()
          + "\nHuman Safety Violations: " + attraction.countHumanSafetyViolations()
          + "\nDinosaur Safety Violations: " + attraction.countDinoSafetyViolations()
          + "\n";
    }
    return report;
  }
}
