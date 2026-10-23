/*
 * Author: Arnav Joginapally
 * Email: joginapally@wisc.edu
 * Course: CS 300, Fall 2026
 * Assignment: Dino Park, Program 5
 * Citations: none
 */

/**
 * Test class for DinoPark system functionality.
 * Tests DinoPark, Habitat, and LabTour classes with comprehensive coverage
 * including edge cases, exception handling and normal cases.
 */
public class DinoParkTester {
  /**
   * Calls the testing methods and prints out the results.
   *
   * @param args unused
   */
  public static void main(String[] args) {
    System.out.println("=== DINOPARK TESTING SUITE ===\n");

    boolean allPassed = allTests();

    // Summarize the combined test results.
    System.out.println("\n=== FINAL TEST RESULTS ===");
    if (allPassed) {
      System.out.println("ALL TESTS PASSED!");
    } else {
      System.out.println("Some tests failed. Review output above.");
    }
  }

  /**
   * Calls all the individual testing methods.
   *
   * @return true if all tests passed, false if any test failed.
   */
  public static boolean allTests() {
    // Run every test even if an earlier test fails.
    boolean allPassed = true;
    allPassed &= testLabTourSafetyChecks();
    allPassed &= testVelociraptorCoexist();
    allPassed &= testTRexCoexist();
    allPassed &= testTriceratopsCoexist();
    allPassed &= testSpinosaurusCoexist();
    allPassed &= testHabitatAddValidation();
    allPassed &= testHabitatRemoveValidation();
    allPassed &= testHabitatHumanSafetyChecks();
    allPassed &= testHabitatDinoSafetyChecks();
    allPassed &= testCompleteWorkflow();
    return allPassed;
  }

  /**
   * Checks laboratory safety below, at, and above capacity.
   *
   * @return true if all checks pass, false otherwise.
   */
  public static boolean testLabTourSafetyChecks() {
    System.out.println("Testing LabTour safety checks...");
    boolean allTestsPassed = true;

    // PROVIDED SCENARIO (Normal Case): Test with total people UNDER capacity

    // Total people is 9, capacity is 10. Safety violations should be 0
    LabTour labTourUnderCapacity = new LabTour("Mr. DNA", 4);
    labTourUnderCapacity.addExperiment(new Experiment("Hatching raptors", 3));
    labTourUnderCapacity.addExperiment(new Experiment("Genome splicing", 2));

    int dinoViolationsUnderCapacity = labTourUnderCapacity.countDinoSafetyViolations();
    int humanViolationsUnderCapacity = labTourUnderCapacity.countHumanSafetyViolations();

    // Note that dino safety violations should *always* be zero,

    // since there should not be any dinos in the lab.
    // Report a mismatch and mark this test as failed.
    if (dinoViolationsUnderCapacity != 0) {
      System.out.println("LabTour countDinoSafetyViolations incorrect for case: "
          + "Total people under capacity.\n"
          + "Expected: 0\n"
          + "Actual: " + dinoViolationsUnderCapacity);
      allTestsPassed = false;
    }
    // Report a mismatch and mark this test as failed.
    if (humanViolationsUnderCapacity != 0) {
      System.out.println("LabTour countHumanSafetyViolations incorrect for case: "
          + "Total people under capacity.\n"
          + "Expected: 0\n"
          + "Actual: " + humanViolationsUnderCapacity);
      allTestsPassed = false;
    }

    // Check occupancy above the laboratory capacity.
    LabTour labTourOverCapacity = new LabTour("Mr. DNA", 4);
    labTourOverCapacity.addExperiment(new Experiment("Hatching raptors", 3));
    labTourOverCapacity.addExperiment(new Experiment("Genome splicing", 2));
    labTourOverCapacity.addExperiment(new Experiment("Cloning", 2));

    int dinoViolationsOverCapacity = labTourOverCapacity.countDinoSafetyViolations();
    int humanViolationsOverCapacity = labTourOverCapacity.countHumanSafetyViolations();

    // Report a mismatch and mark this test as failed.
    if (dinoViolationsOverCapacity != 0) {
      System.out.println("LabTour countDinoSafetyViolations incorrect for case: "
          + "Total people over capacity.\n"
          + "Expected: 0\n"
          + "Actual: " + dinoViolationsOverCapacity);
      allTestsPassed = false;
    }
    // Report a mismatch and mark this test as failed.
    if (humanViolationsOverCapacity != 1) {
      System.out.println("LabTour countHumanSafetyViolations incorrect for case: "
          + "Total people over capacity.\n"
          + "Expected: 1\n"
          + "Actual: " + humanViolationsOverCapacity);
      allTestsPassed = false;
    }

    // Check occupancy exactly at the laboratory capacity.
    LabTour labTourExactlyCapacity = new LabTour("Mr. DNA", 5);
    labTourExactlyCapacity.addExperiment(new Experiment("Hatching raptors", 3));
    labTourExactlyCapacity.addExperiment(new Experiment("Genome splicing", 2));

    int dinoViolationsExactlyCapacity = labTourExactlyCapacity.countDinoSafetyViolations();
    int humanViolationsExactlyCapacity = labTourExactlyCapacity.countHumanSafetyViolations();

    // Report a mismatch and mark this test as failed.
    if (dinoViolationsExactlyCapacity != 0) {
      System.out.println("LabTour countDinoSafetyViolations incorrect for case: "
          + "Total people exactly equal to capacity.\n"
          + "Expected: 0\n"
          + "Actual: " + dinoViolationsExactlyCapacity);
      allTestsPassed = false;
    }
    if (humanViolationsExactlyCapacity != 0) {
      allTestsPassed = false;
    }

    System.out.println();
    return allTestsPassed;
  }

  /**
   * Checks velociraptor compatibility with carnivores and herbivores.
   *
   * @return true if all checks pass, false otherwise.
   */
  public static boolean testVelociraptorCoexist() {
    System.out.println("Testing Velociraptor canCoexistWith...");
    boolean allTestsPassed = true;

    Velociraptor velociraptor = new Velociraptor("raptor1", "Alan", 8);

    // PROVIDED: Test where otherDinosaur has food type MEAT. Should return true
    Velociraptor meatDinosaur = new Velociraptor("raptor2", "Radia", 12);
    boolean velociraptorCoexistWithMeat = velociraptor.canCoexistWith(meatDinosaur);
    // Report a mismatch and mark this test as failed.
    if (!velociraptorCoexistWithMeat) {
      System.out.println("Velociraptor canCoexistWith incorrect for case: "
          + "Other Dinosaur has food type MEAT.\n"
          + "Expected: true\n"
          + "Actual: false");
      allTestsPassed = false;
    }

    // PROVIDED: Test where otherDinosaur has food type PLANTS. Should return false
    Brachiosaurus plantsDinosaur = new Brachiosaurus("longneck1", "Littlefoot", 4);
    boolean velociraptorCoexistWithPlants = velociraptor.canCoexistWith(plantsDinosaur);
    // Report a mismatch and mark this test as failed.
    if (velociraptorCoexistWithPlants) {
      System.out.println("Velociraptor canCoexistWith incorrect for case: "
          + "Other Dinosaur has food type PLANTS.\n"
          + "Expected: false\n"
          + "Actual: true");
      allTestsPassed = false;
    }

    System.out.println();
    return allTestsPassed;
  }

  /**
   * Checks that a TRex accepts itself and rejects another dinosaur.
   *
   * @return true if all checks pass, false otherwise.
   */
  public static boolean testTRexCoexist() {
    System.out.println("Testing TRex canCoexistWith...");
    boolean allTestsPassed = true;

    // Create a TRex to check its solitary behavior.
    TRex trex = new TRex("trex1", "Sue", 24);

    Velociraptor otherDinosaur = new Velociraptor("raptor1", "Alan", 8);
    if (trex.canCoexistWith(otherDinosaur)) {
      allTestsPassed = false;
    }

    // Check that a dinosaur can share its own habitat.
    boolean trexCoexistWithItself = trex.canCoexistWith(trex);
    // Report a mismatch and mark this test as failed.
    if (!trexCoexistWithItself) {
      System.out.println("TRex canCoexistWith incorrect for case: "
          + "Can coexist with itself.\n"
          + "Expected: true\n"
          + "Actual: false");
      allTestsPassed = false;
    }

    System.out.println();
    return allTestsPassed;
  }

  /**
   * Checks triceratops compatibility with carnivores and herbivores.
   *
   * @return true if all checks pass, false otherwise.
   */
  public static boolean testTriceratopsCoexist() {
    System.out.println("Testing Triceratops canCoexistWith...");
    boolean allTestsPassed = true;

    Triceratops triceratops = new Triceratops("triceratops1", "Cera", 10);
    TRex meatDinosaur = new TRex("trex1", "Sue", 24);
    if (triceratops.canCoexistWith(meatDinosaur)) {
      allTestsPassed = false;
    }

    Brachiosaurus plantsDinosaur = new Brachiosaurus("longneck1", "Littlefoot", 4);
    if (!triceratops.canCoexistWith(plantsDinosaur)) {
      allTestsPassed = false;
    }

    System.out.println();
    return allTestsPassed;
  }

  /**
   * Checks spinosaurus compatibility with itself and other species.
   *
   * @return true if all checks pass, false otherwise.
   */
  public static boolean testSpinosaurusCoexist() {
    System.out.println("Testing Spinosaurus canCoexistWith...");
    boolean allTestsPassed = true;

    Spinosaurus spinosaurus = new Spinosaurus("spino1", "Arnav", 15);
    if (!spinosaurus.canCoexistWith(spinosaurus)) {
      allTestsPassed = false;
    }

    Spinosaurus otherSpinosaurus = new Spinosaurus("spino2", "Joginapally", 15);
    if (!spinosaurus.canCoexistWith(otherSpinosaurus)) {
      allTestsPassed = false;
    }

    Velociraptor velociraptor = new Velociraptor("raptor1", "Alan", 8);
    if (!spinosaurus.canCoexistWith(velociraptor)) {
      allTestsPassed = false;
    }

    Triceratops triceratops = new Triceratops("triceratops1", "Cera", 10);
    if (spinosaurus.canCoexistWith(triceratops)) {
      allTestsPassed = false;
    }

    System.out.println();
    return allTestsPassed;
  }

  /**
   * Checks that habitats accept valid dinosaurs and reject null.
   *
   * @return true if all checks pass, false otherwise.
   */
  public static boolean testHabitatAddValidation() {
    System.out.println("Testing Habitat addDinosaur validation...");
    boolean allTestsPassed = true;

    Habitat habitat = new Habitat("habitat1", 1, 1);

    // PROVIDED SCENARIO (Normal Case): Test adding a valid dinosaur to a habitat
    try {
      // Code that should NOT throw exception
      habitat.addDinosaur(new Velociraptor("raptor1", "Alan", 2));

      // Check that the dino was actually added
      if (habitat.getDinosaurs().size() != 1) {
        allTestsPassed = false;
      } else if (!habitat.getDinosaurs().get(0).getId().equals("raptor1")) {
        allTestsPassed = false;
      }
    } catch (Exception exception) {
      // Should not throw any exception
      System.out.println("Habitat addDinosaur with a valid dinosaur threw exception: "
          + exception.getMessage());
      allTestsPassed = false;
    }

    // Verify that a null dinosaur is rejected.
    try {
      habitat.addDinosaur(null);
      allTestsPassed = false;
    } catch (IllegalArgumentException exception) {
      // Expected exception
    } catch (Exception exception) {
      allTestsPassed = false;
    }

    System.out.println();
    return allTestsPassed;
  }

  /**
   * Checks removal with valid, null, and missing dinosaur IDs.
   *
   * @return true if all checks pass, false otherwise.
   */
  public static boolean testHabitatRemoveValidation() {
    System.out.println("Testing Habitat removeDinosaur validation...");
    boolean allTestsPassed = true;

    Habitat habitat = new Habitat("habitat1", 50, 10);
    habitat.addDinosaur(new Velociraptor("raptor1", "Alan", 2));

    // Remove the existing dinosaur and verify the habitat is empty.
    try {
      habitat.removeDinosaur("raptor1");
      if (habitat.getDinosaurs().size() != 0) {
        allTestsPassed = false;
      }
    } catch (DinoNotFoundException exception) {
      allTestsPassed = false;
    }

    // Verify that a null ID is rejected.
    try {
      habitat.removeDinosaur(null);
      allTestsPassed = false;
    } catch (IllegalArgumentException exception) {
      // Expected exception
    } catch (Exception exception) {
      allTestsPassed = false;
    }

    // Verify that a missing ID produces the expected exception.
    try {
      habitat.removeDinosaur("nonexistentid");
      allTestsPassed = false;
    } catch (DinoNotFoundException exception) {
      // Expected exception
    } catch (Exception exception) {
      allTestsPassed = false;
    }

    System.out.println();
    return allTestsPassed;
  }

  /**
   * Checks ranger requirements for habitat area and dinosaur count.
   *
   * @return true if all checks pass, false otherwise.
   */
  public static boolean testHabitatHumanSafetyChecks() {
    System.out.println("Testing Habitat human safety checks...");
    boolean allTestsPassed = true;

    // PROVIDED SCENARIO (Normal Case): Test with sufficient rangers. Should return 0
    Habitat habitatEnoughRangers = new Habitat("habitat1", 4, 3);
    habitatEnoughRangers.addDinosaur(new Velociraptor("raptor1", "Alan", 2));
    habitatEnoughRangers.addDinosaur(new Velociraptor("raptor2", "Ellie", 2));
    habitatEnoughRangers.addDinosaur(new Velociraptor("raptor3", "Ian", 2));

    int violationsEnoughRangers = habitatEnoughRangers.countHumanSafetyViolations();

    // Report a mismatch and mark this test as failed.
    if (violationsEnoughRangers != 0) {
      System.out.println("Habitat countHumanSafetyViolations incorrect for case: "
          + "Enough rangers for area and dinosaurs.\n"
          + "Expected: 0\n"
          + "Actual: " + violationsEnoughRangers);
      allTestsPassed = false;
    }

    // PROVIDED SCENARIO (Edge Case): Test with enough rangers for the number of dinosaurs,

    // but not enough for area size
    Habitat habitatAreaTooLarge = new Habitat("habitat2", 40, 3);

    int violationsAreaTooLarge = habitatAreaTooLarge.countHumanSafetyViolations();

    // Report a mismatch and mark this test as failed.
    if (violationsAreaTooLarge != 1) {
      System.out.println("Habitat countHumanSafetyViolations incorrect for case: "
          + "Not too many dinosaurs but area too large.\n"
          + "Expected: 1\n"
          + "Actual: " + violationsAreaTooLarge);
      allTestsPassed = false;
    }

    // Test with enough rangers for habitat area, but not enough for the number of dinosaurs
    Habitat habitatEnoughRangersNotEnoughDinos = new Habitat("habitat3", 10, 3);
    habitatEnoughRangersNotEnoughDinos.addDinosaur(new Velociraptor("raptor1", "Alan", 2));
    habitatEnoughRangersNotEnoughDinos.addDinosaur(new Velociraptor("raptor2", "Ellie", 2));
    habitatEnoughRangersNotEnoughDinos.addDinosaur(new Velociraptor("raptor3", "Ian", 2));
    habitatEnoughRangersNotEnoughDinos.addDinosaur(new Velociraptor("raptor4", "Owen", 2));

    int violationsEnoughRangersNotEnoughDinos =
        habitatEnoughRangersNotEnoughDinos.countHumanSafetyViolations();

    if (violationsEnoughRangersNotEnoughDinos != 1) {
      allTestsPassed = false;
    }

    // Test with not enough rangers for habitat area AND not enough for the number of dinosaurs
    Habitat habitatNotEnoughRangersAndDinos = new Habitat("habitat4", 80, 2);
    habitatNotEnoughRangersAndDinos.addDinosaur(new Velociraptor("raptor1", "Alan", 2));
    habitatNotEnoughRangersAndDinos.addDinosaur(new Velociraptor("raptor2", "Ellie", 2));
    habitatNotEnoughRangersAndDinos.addDinosaur(new Velociraptor("raptor3", "Ian", 2));
    habitatNotEnoughRangersAndDinos.addDinosaur(new Velociraptor("raptor4", "Owen", 2));

    int violationsNotEnoughRangersAndDinos =
        habitatNotEnoughRangersAndDinos.countHumanSafetyViolations();

    if (violationsNotEnoughRangersAndDinos != 2) {
      allTestsPassed = false;
    }

    System.out.println();
    return allTestsPassed;
  }

  /**
   * Checks habitat space requirements and dinosaur compatibility.
   *
   * @return true if all checks pass, false otherwise.
   */
  public static boolean testHabitatDinoSafetyChecks() {
    System.out.println("Testing Habitat Dinosaur safety checks...");
    boolean allTestsPassed = true;

    // PROVIDED SCENARIO (Normal Case): Test with sufficient area and all dinos can coexist.

    // Should return 0
    Habitat habitatEnoughAreaAndCoexist = new Habitat("habitat1", 16, 0);
    habitatEnoughAreaAndCoexist.addDinosaur(new Velociraptor("raptor1", "Alan", 2));
    habitatEnoughAreaAndCoexist.addDinosaur(new Velociraptor("raptor2", "Ellie", 2));

    int dinoSafetyEnoughAreaAndCoexist = habitatEnoughAreaAndCoexist.countDinoSafetyViolations();

    // Report a mismatch and mark this test as failed.
    if (dinoSafetyEnoughAreaAndCoexist != 0) {
      System.out.println("Habitat countDinoSafetyViolations incorrect for case: "
          + "Enough area and all dinos can coexist.\n"
          + "Expected: 0\n"
          + "Actual: " + dinoSafetyEnoughAreaAndCoexist);
      allTestsPassed = false;
    }

    // Test with all dinos can coexist but not enough area
    Habitat habitatNotEnoughAreaButCoexist = new Habitat("habitat2", 10, 2);
    habitatNotEnoughAreaButCoexist.addDinosaur(new Velociraptor("raptor1", "Alan", 2));
    habitatNotEnoughAreaButCoexist.addDinosaur(new Velociraptor("raptor2", "Ellie", 2));

    int dinoSafetyNotEnoughAreaButCoexist =
        habitatNotEnoughAreaButCoexist.countDinoSafetyViolations();

    // Report a mismatch and mark this test as failed.
    if (dinoSafetyNotEnoughAreaButCoexist != 1) {
      System.out.println("Habitat countDinoSafetyViolations incorrect for case: "
          + "All dinos can coexist but not enough area.\n"
          + "Expected: 1\n"
          + "Actual: " + dinoSafetyNotEnoughAreaButCoexist);
      allTestsPassed = false;
    }

    // Test with enough area but not all dinos can coexist
    Habitat habitatEnoughAreaButNotCoexist = new Habitat("habitat3", 40, 4);
    habitatEnoughAreaButNotCoexist.addDinosaur(new Velociraptor("raptor1", "Alan", 2));
    habitatEnoughAreaButNotCoexist.addDinosaur(new Triceratops("triceratops1", "Ellie", 2));

    int dinoSafetyEnoughAreaButNotCoexist =
        habitatEnoughAreaButNotCoexist.countDinoSafetyViolations();

    // Report a mismatch and mark this test as failed.
    if (dinoSafetyEnoughAreaButNotCoexist != 1) {
      System.out.println("Habitat countDinoSafetyViolations incorrect for case: "
          + "Enough area but not all dinos can coexist.\n"
          + "Expected: 1\n"
          + "Actual: " + dinoSafetyEnoughAreaButNotCoexist);
      allTestsPassed = false;
    }

    // Test where not enough area AND not all dinos can coexist
    Habitat habitatNotEnoughAreaAndNotCoexist = new Habitat("habitat4", 10, 3);
    habitatNotEnoughAreaAndNotCoexist.addDinosaur(new Velociraptor("raptor1", "Alan", 2));
    habitatNotEnoughAreaAndNotCoexist.addDinosaur(new Triceratops("triceratops1", "Ellie", 2));

    int dinoSafetyNotEnoughAreaAndNotCoexist =
        habitatNotEnoughAreaAndNotCoexist.countDinoSafetyViolations();

    // Report a mismatch and mark this test as failed.
    if (dinoSafetyNotEnoughAreaAndNotCoexist != 2) {
      System.out.println("Habitat countDinoSafetyViolations incorrect for case: "
          + "Not enough area AND not all dinos can coexist.\n"
          + "Expected: 2\n"
          + "Actual: " + dinoSafetyNotEnoughAreaAndNotCoexist);
      allTestsPassed = false;
    }

    System.out.println();
    return allTestsPassed;
  }

  /**
   * Checks safety report generation for a park with several attractions.
   *
   * @return true if all checks pass, false otherwise.
   */
  public static boolean testCompleteWorkflow() {
    System.out.println("Testing complete DinoPark workflow integration...");
    boolean allTestsPassed = true;

    try {
      // Create a DinoPark
      DinoPark park = new DinoPark();

      // Create a lab tour with 2 experiments
      LabTour tour = new LabTour("tour1", 4);
      tour.addExperiment(new Experiment("exp1", 2));
      tour.addExperiment(new Experiment("exp2", 3));

      // Create 2 habitats with a variety of dinosaurs
      Habitat habitat1 = new Habitat("habitat1", 50, 5);
      habitat1.addDinosaur(new Velociraptor("raptor1", "Alan", 2));
      habitat1.addDinosaur(new Triceratops("triceratops1", "Ellie", 2));

      Habitat habitat2 = new Habitat("habitat2", 60, 6);
      habitat2.addDinosaur(new Velociraptor("raptor2", "John", 2));
      habitat2.addDinosaur(new Triceratops("triceratops2", "Jane", 2));

      // Add your lab tour and 2 habitats to the park
      park.addAttraction(tour);
      park.addAttraction(habitat1);
      park.addAttraction(habitat2);

      // Call generateSafetyReport and verify no crashes
      park.generateSafetyReport();
    } catch (Exception exception) {
      System.out.println("Complete workflow threw exception: "
          + exception.getMessage());
      allTestsPassed = false;
    }

    return allTestsPassed;
  }
}
