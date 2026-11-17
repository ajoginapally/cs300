/*
 * Author: Arnav Joginapally
 * Email: joginapally@wisc.edu
 * Course: CS 300, Fall 2026
 * Assignment: Dino Monitor, Program 6
 * Citations: none
 */

import java.util.ArrayList;
import java.util.Random;

/**
 * Test harness for the Dinosaur Monitoring implementation.
 */
public class DinoMonitorTesting {
  /**
   * Main method to run this tester class.
   *
   * @param args list of input arguments if any
   */
  public static void main(String[] args) {
    System.out.print("Test testGreedyMonitoring: ");
    boolean greedyPass = testGreedyMonitoring();
    System.out.println(greedyPass ? "Passed" : "Failed");

    System.out.print("\n\nTest testOptimalMonitoring: ");
    boolean optimalPass = testOptimalMonitoring();
    System.out.println(optimalPass ? "Passed" : "Failed");

    System.out.print("\n\nTest testMinCostMonitoring: ");
    boolean minCostPass = testMinCostMonitoring();
    System.out.println(minCostPass ? "Passed" : "Failed");

    boolean allPass = greedyPass && optimalPass && minCostPass;

    System.out.println("-----------------------------------------------");
    System.out.println("Overall: " + (allPass ? "Pass" : "Failed!"));
  }

  ////////////////////////////// GREEDY MONITORING TESTS ///////////////////////////////////
  /**
   * Runs the greedy monitoring tests.
   *
   * @return true if all checks pass; false otherwise
   */
  public static boolean testGreedyMonitoring() {
    return greedyMonitoringBaseTest() && greedyMonitoringRecursiveTest();
  }

  /**
   * Checks that no researchers are hired when no hires remain.
   *
   * @return true if all checks pass; false otherwise
   */
  public static boolean greedyMonitoringBaseTest() {
    // PROVIDED as a model for how to write tests with the provided utilities

    // Create a list of potential candidates with various station coverage
    ResearcherList candidates = DinoMonitorTestingUtilities.makeResearcherList(new boolean[][] {
        {true, false, false, false},    // Researcher 0: covers station 0
        {false, true, true, true},      // Researcher 1: covers stations 1,2,3
        {true, false, true, false},     // Researcher 2: covers stations 0,2
        {true, true, false, false}},    // Researcher 3: covers stations 0,1
        new int[] {20, 25, 30, 15});     // Researcher salaries: $20k, $25k, $30k, $15k

    // For the base case, we expect no one to be added to the list
    ResearcherList expectedSolution = new ResearcherList();

    // Call the method with hiresLeft at 0 (base case).
    ResearcherList actualSolution = DinoMonitor.greedyMonitoring(candidates, new ResearcherList(),
        0);

    // Use the testing utilities provided to compare the contents of these two lists
    return DinoMonitorTestingUtilities.compareResearcherLists(expectedSolution, actualSolution);
  }

  /**
   * Checks greedy selection and coverage with two available hires.
   *
   * @return true if all checks pass; false otherwise
   */
  public static boolean greedyMonitoringRecursiveTest() {
    // Set up researchers with known station coverage.
    Researcher firstResearcher = new Researcher(new boolean[] {false, true, true, false});

    Researcher secondResearcher = new Researcher(new boolean[] {true, true, false, true});

    Researcher thirdResearcher = new Researcher(new boolean[] {false, false, true, true});

    ResearcherList candidates = new ResearcherList();
    candidates.add(firstResearcher);
    candidates.add(secondResearcher);
    candidates.add(thirdResearcher);

    ResearcherList hired = new ResearcherList();

    // Run monitoring and compare the result with the expected solution.
    ResearcherList result = DinoMonitor.greedyMonitoring(candidates, hired, 2);

    boolean correctSize = result.size() == 2;
    boolean correctCoverage = result.numCoveredStations() == 4;
    boolean containsBest = result.contains(secondResearcher);

    return correctSize && correctCoverage && containsBest;
  }

  ////////////////////////////// OPTIMAL MONITORING TESTS //////////////////////////////////
  /**
   * Runs the optimal monitoring tests.
   *
   * @return true if all checks pass; false otherwise
   */
  public static boolean testOptimalMonitoring() {
    return optimalMonitoringBaseTest() && optimalMonitoringRecursiveTest() &&
        optimalMonitoringFuzzTest();
  }

  /**
   * Checks the zero-hire base case.
   *
   * @return true if all checks pass; false otherwise
   */
  public static boolean optimalMonitoringBaseTest() {
    ResearcherList candidates = DinoMonitorTestingUtilities.makeResearcherList(new boolean[][] {
        {true, false, false, false},    // Researcher 0: covers station 0
        {false, true, true, true},      // Researcher 1: covers stations 1,2,3
        {true, false, true, false},     // Researcher 2: covers stations 0,2
        {true, true, false, false}},    // Researcher 3: covers stations 0,1
        new int[] {20, 25, 30, 15});     // Researcher salaries: $20k, $25k, $30k, $15k

    // For the base case, we expect no one to be added to the list
    ResearcherList expectedSolution = new ResearcherList();

    // Call the method with hiresLeft at 0 (base case).
    ResearcherList actualSolution = DinoMonitor.greedyMonitoring(candidates, new ResearcherList(),
        0);

    // Use the testing utilities provided to compare the contents of these two lists
    return DinoMonitorTestingUtilities.compareResearcherLists(expectedSolution, actualSolution);
  }

  /**
   * Checks the selected team against all optimal solutions.
   *
   * @return true if all checks pass; false otherwise
   */
  public static boolean optimalMonitoringRecursiveTest() {
    // Set up researchers with known station coverage.
    Researcher firstResearcher = new Researcher(new boolean[] {true, true, true, false, false,
        false});

    Researcher secondResearcher = new Researcher(new boolean[] {true, true, false, true, false,
        false});

    Researcher thirdResearcher = new Researcher(new boolean[] {false, false, true, false, true,
        true});

    ResearcherList candidates = new ResearcherList();
    candidates.add(firstResearcher);
    candidates.add(secondResearcher);
    candidates.add(thirdResearcher);

    ResearcherList hired = new ResearcherList();
    int hiresLeft = 2;

    // Run monitoring and compare the result with the expected solution.
    ResearcherList result = DinoMonitor.optimalMonitoring(candidates, hired, hiresLeft);

    ArrayList<ResearcherList> expectedSolutions =
        DinoMonitorTestingUtilities.allOptimalSolutions(candidates, hiresLeft);

    // Accept any team that matches an optimal solution.
    for (ResearcherList expected : expectedSolutions) {
      if (DinoMonitorTestingUtilities.compareResearcherLists(expected, result)) {
        return true;
      }
    }

    return false;
  }

  /**
   * Checks optimal monitoring with randomized candidate lists.
   *
   * @return true if all checks pass; false otherwise
   */
  public static boolean optimalMonitoringFuzzTest() {
    // Use a fixed seed for reproducible test parameters.
    Random random = new Random(12345);

    // Compare monitoring results across randomized test cases.
    for (int i = 0; i < 150; i++) {
      int numStations = random.nextInt(5) + 1;
      int numResearchers = random.nextInt(10) + 1;
      int hiresLeft = random.nextInt(numResearchers) + 1;

      ResearcherList candidates = DinoMonitorTestingUtilities.generateRandomInput(numStations,
          numResearchers);

      // Run monitoring and compare the result with the expected solution.
      ResearcherList actual = DinoMonitor.optimalMonitoring(candidates, new ResearcherList(),
          hiresLeft);

      ArrayList<ResearcherList> expected =
          DinoMonitorTestingUtilities.allOptimalSolutions(candidates, hiresLeft);

      if (!DinoMonitorTestingUtilities.compareResearcherLists(expected, actual)) {
        return false;
      }
    }

    return true;
  }

  ////////////////////////////// MIN COST MONITORING TESTS //////////////////////////////////
  /**
   * Runs the minimum-cost monitoring tests.
   *
   * @return true if all checks pass; false otherwise
   */
  public static boolean testMinCostMonitoring() {
    return minCostMonitoringBaseTest() && minCostMonitoringRecursiveTest() &&
        minCostMonitoringFuzzTest();
  }

  /**
   * Checks satisfied coverage and exhausted candidate base cases.
   *
   * @return true if all checks pass; false otherwise
   */
  public static boolean minCostMonitoringBaseTest() {
    // Set up researchers with known station coverage.
    Researcher firstResearcher = new Researcher(new boolean[] {true, true, false}, 5);

    Researcher secondResearcher = new Researcher(new boolean[] {false, false, true}, 3);

    ResearcherList candidates = new ResearcherList();
    candidates.add(firstResearcher);
    candidates.add(secondResearcher);

    ResearcherList hired = new ResearcherList();

    ResearcherList alreadyHired = new ResearcherList();
    alreadyHired.add(firstResearcher);

    // Run monitoring and compare the result with the expected solution.
    ResearcherList actual1 = DinoMonitor.minCostMonitoring(new ResearcherList(), alreadyHired, 2);

    ArrayList<ResearcherList> expected1 =
        DinoMonitorTestingUtilities.allMinCostSolutions(alreadyHired, 2);

    if (!DinoMonitorTestingUtilities.compareResearcherLists(expected1, actual1)) {
      return false;
    }

    // Run monitoring and compare the result with the expected solution.
    ResearcherList actual2 = DinoMonitor.minCostMonitoring(new ResearcherList(), hired, 3);

    if (actual2 != null) {
      return false;
    }

    return true;
  }

  /**
   * Checks the selected team against all minimum-cost solutions.
   *
   * @return true if all checks pass; false otherwise
   */
  public static boolean minCostMonitoringRecursiveTest() {
    // Set up researchers with known station coverage.
    Researcher firstResearcher = new Researcher(new boolean[] {true, true, false, false}, 8);

    Researcher secondResearcher = new Researcher(new boolean[] {false, true, true, false}, 3);

    Researcher thirdResearcher = new Researcher(new boolean[] {false, false, true, true}, 4);

    ResearcherList candidates = new ResearcherList();
    candidates.add(firstResearcher);
    candidates.add(secondResearcher);
    candidates.add(thirdResearcher);

    ResearcherList hired = new ResearcherList();
    int minStations = 3;

    // Run monitoring and compare the result with the expected solution.
    ResearcherList actual = DinoMonitor.minCostMonitoring(candidates, hired, minStations);

    ArrayList<ResearcherList> expected =
        DinoMonitorTestingUtilities.allMinCostSolutions(candidates, minStations);

    return DinoMonitorTestingUtilities.compareResearcherLists(expected, actual);
  }

  /**
   * Checks minimum-cost monitoring with randomized candidate lists.
   *
   * @return true if all checks pass; false otherwise
   */
  public static boolean minCostMonitoringFuzzTest() {
    // Use a fixed seed for reproducible test parameters.
    Random random = new Random(12345);

    // Compare monitoring results across randomized test cases.
    for (int i = 0; i < 150; i++) {
      int numStations = random.nextInt(5) + 1;
      int numResearchers = random.nextInt(10) + 1;
      int minStations = random.nextInt(numStations) + 1;

      ResearcherList candidates = new ResearcherList();

      // Generate candidate researchers with random coverage and salaries.
      for (int j = 0; j < numResearchers; j++) {
        boolean[] capability = new boolean[numStations];

        // Randomly choose which stations this researcher covers.
        for (int k = 0; k < numStations; k++) {
          capability[k] = random.nextBoolean();
        }

        int maxSalary = Math.max(1, (numResearchers - 1) / 2);
        int annualCost = random.nextInt(maxSalary) + 1;

        candidates.add(new Researcher(capability, annualCost));
      }

      // Run monitoring and compare the result with the expected solution.
      ResearcherList actual = DinoMonitor.minCostMonitoring(candidates, new ResearcherList(),
          minStations);

      ArrayList<ResearcherList> expected =
          DinoMonitorTestingUtilities.allMinCostSolutions(candidates, minStations);

      // Check impossible coverage separately from valid minimum-cost teams.
      if (expected.isEmpty()) {
        if (actual != null) {
          System.out.println("Failed test " + i + ": expected null, got " + actual);
          return false;
        }
      } else {
        // Report details when the result differs from all expected solutions.
        if (actual == null || !DinoMonitorTestingUtilities.compareResearcherLists(expected,
            actual)) {
          System.out.println("Failed test " + i);
          System.out.println("Candidates: " + candidates);
          System.out.println("Minimum stations: " + minStations);
          System.out.println("Expected: " + expected);
          System.out.println("Actual: " + actual);

          return false;
        }
      }
    }

    return true;
  }
}
