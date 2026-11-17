/*
 * Author: Arnav Joginapally
 * Email: joginapally@wisc.edu
 * Course: CS 300, Fall 2026
 * Assignment: Dino Monitor, Program 6
 * Citations: none
 */

/**
 * This class contains methods to solve variants of the dinosaur monitoring coverage problem:
 * given a set of researchers with different monitoring capabilities,
 * determine which researchers to hire to maximize coverage subject to certain constraints.
 */
public class DinoMonitor {
  /**
   * Greedy monitoring approach: iteratively select the researcher that covers
   * the most previously-uncovered monitoring stations.
   *
   * This method works recursively: at each step, identify which remaining researcher
   * would increase the total coverage the most, add them to the hired list, and recurse.
   *
   * @param candidates available researchers not yet considered for hiring
   * @param hired currently hired researchers
   * @param hiresLeft number of additional researchers we can hire
   * @return the selected team of researchers (union of hired and greedily selected)
   */
  public static ResearcherList greedyMonitoring(ResearcherList candidates, ResearcherList hired,
      int hiresLeft) {
    // Stop when no more researchers can be hired.
    if (hiresLeft <= 0 || candidates.isEmpty()) {
      return hired;
    }

    Researcher bestCandidate = null;
    int maxNewCoverage = -1;

    int currentCoverage = hired.numCoveredStations();

    // Explore the coverage available from each remaining researcher.
    for (Researcher candidate : candidates) {
      ResearcherList newTeam = hired.copyAndAddResearcher(candidate);

      int newCoverage = newTeam.numCoveredStations() - currentCoverage;

      // Keep the best solution found so far.
      if (newCoverage > maxNewCoverage) {
        maxNewCoverage = newCoverage;
        bestCandidate = candidate;
      }
    }

    ResearcherList newHired = hired.copyAndAddResearcher(bestCandidate);
    ResearcherList newCandidates = candidates.copyAndRemoveResearcher(bestCandidate);

    return greedyMonitoring(newCandidates, newHired, hiresLeft - 1);
  }

  /**
   * Optimal monitoring approach: exhaustively search all possible teams
   * up to the hire limit to find the team covering the most unique stations.
   *
   * This method works recursively: try adding each remaining researcher to the hired set,
   * recurse, and keep track of the solution with the highest coverage.
   *
   * @param candidates available researchers not yet considered for hiring
   * @param hired currently hired researchers
   * @param hiresLeft number of additional researchers we can hire
   * @return the optimal team of researchers (union of hired and optimally selected)
   */
  public static ResearcherList optimalMonitoring(ResearcherList candidates, ResearcherList hired,
      int hiresLeft) {
    // Stop when no more researchers can be hired.
    if (hiresLeft <= 0 || candidates.isEmpty()) {
      return hired;
    }

    ResearcherList bestTeam = new ResearcherList(hired);

    // Explore the coverage available from each remaining researcher.
    for (Researcher candidate : candidates) {
      ResearcherList newHired = new ResearcherList(hired);
      newHired.add(candidate);

      ResearcherList newCandidates = new ResearcherList(candidates);
      newCandidates.remove(candidate);

      ResearcherList team = optimalMonitoring(newCandidates, newHired, hiresLeft - 1);

      if (team.numCoveredStations() > bestTeam.numCoveredStations()) {
        bestTeam = team;
      }
    }

    return bestTeam;
  }

  /**
   * Cost-aware monitoring: find the least expensive team that covers
   * at least the required number of monitoring stations.
   *
   * This method works recursively: try adding each remaining researcher to the hired set,
   * recurse until minimum coverage is achieved, and keep track of the solution with
   * the minimum total annual salary.
   *
   * @param candidates available researchers not yet considered for hiring
   * @param hired currently hired researchers
   * @param minStations minimum monitoring stations that must be covered
   * @return the lowest-cost team meeting the coverage requirement, or null if impossible
   */
  public static ResearcherList minCostMonitoring(ResearcherList candidates, ResearcherList hired,
      int minStations) {
    // Return a complete team, or reject an exhausted search.
    if (hired.numCoveredStations() >= minStations) {
      return hired;
    }

    if (candidates.isEmpty()) {
      return null;
    }

    ResearcherList bestTeam = null;

    // Explore the coverage available from each remaining researcher.
    for (Researcher candidate : candidates) {
      ResearcherList newHired = hired.copyAndAddResearcher(candidate);
      ResearcherList newCandidates = candidates.copyAndRemoveResearcher(candidate);
      ResearcherList team = minCostMonitoring(newCandidates, newHired, minStations);

      // Compare costs only for teams that satisfy the required coverage.
      if (team != null) {
        // Keep the best solution found so far.
        if (bestTeam == null || team.totalAnnualCost() < bestTeam.totalAnnualCost()) {
          bestTeam = team;
        }
      }
    }

    return bestTeam;
  }
}
