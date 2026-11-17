/*
 * Author: Arnav Joginapally
 * Email: joginapally@wisc.edu
 * Course: CS 300, Fall 2026
 * Assignment: Linked, Program 7
 * Citations: none
 */

/**
 * Tester class for the TaskList implementation.
 * Demonstrates the functionality of the TaskList with various test cases.
 */
public class TaskListTester {

  /**
   * Tests both constructors, next references, and exact node string formatting.
   *
   * @return true if all checks pass; false otherwise
   */
  public static boolean testLinkedTask() {
    // Start with predictable task IDs and report unexpected exceptions as failures.
    Task.resetIDGenerator();
    try {
      // Create task data and check the single-argument constructor.
      Task task = new Task("Valid task", TaskPriority.MEDIUM);
      Task nextTask = new Task("Next task", TaskPriority.HIGH);
      LinkedTask next = new LinkedTask(nextTask);
      LinkedTask node = new LinkedTask(task);
      if (node.getTask() != task || node.getNext() != null
          || !node.toString().equals(task.toString() + " -> END")) {
        return false;
      }

      // Check that the two-argument constructor stores both references.
      node = new LinkedTask(task, next);
      if (node.getTask() != task || node.getNext() != next
          || !node.toString().equals(task.toString() + " -> ")) {
        return false;
      }

      // Check that the two-argument constructor accepts a null next node.
      LinkedTask last = new LinkedTask(task, null);
      if (last.getTask() != task || last.getNext() != null
          || !last.toString().equals(task.toString() + " -> END")) {
        return false;
      }

      // Disconnect the next node and verify the end marker.
      node.setNext(null);
      if (node.getNext() != null
          || !node.toString().equals(task.toString() + " -> END")) {
        return false;
      }

      // Reconnect the next node and verify the arrow format.
      node.setNext(next);
      if (node.getNext() != next || !node.toString().equals(task.toString() + " -> ")) {
        return false;
      }

      // Verify that invalid input throws the required exception.
      try {
        new LinkedTask(null);
        return false;
      } catch (IllegalArgumentException exception) {
        // Expected: every node must store a task.
      }

      // Verify that invalid input throws the required exception.
      try {
        new LinkedTask(null, next);
        return false;
      } catch (IllegalArgumentException exception) {
        // Expected for the two-argument constructor, too.
      }

      // Verify that invalid input throws the required exception.
      try {
        new LinkedTask(null, null);
        return false;
      } catch (IllegalArgumentException exception) {
        // A null next reference does not make a null task valid.
      }
      return true;
    } catch (Exception exception) {
      System.out.println("testLinkedTask unexpected exception: " + exception);
      return false;
    }
  }

  /**
   * Tests empty, front, back, and middle insertion, stable order, and null input.
   *
   * @return true if all checks pass; false otherwise
   */
  public static boolean testAddTask() {
    // Start with predictable task IDs and report unexpected exceptions as failures.
    Task.resetIDGenerator();
    try {
      TaskList list = new TaskList();
      if (!checkList(list, new Task[] {})) {
        return false;
      }

      // Verify that invalid input throws the required exception.
      try {
        list.addTask(null);
        return false;
      } catch (IllegalArgumentException exception) {
        // Reject null even when the list is empty.
      }
      if (!checkList(list, new Task[] {})) {
        return false;
      }

      // Create two tasks per priority to verify stable insertion order.
      Task medium1 = new Task("Medium first", TaskPriority.MEDIUM);
      Task high1 = new Task("High first", TaskPriority.HIGH);
      Task low1 = new Task("Low first", TaskPriority.LOW);
      Task medium2 = new Task("Medium second", TaskPriority.MEDIUM);
      Task high2 = new Task("High second", TaskPriority.HIGH);
      Task low2 = new Task("Low second", TaskPriority.LOW);

      // Check insertion into an empty list.
      list.addTask(medium1);
      if (!checkList(list, new Task[] {medium1})) {
        return false;
      }

      // Check insertion at the front.
      list.addTask(high1);
      if (!checkList(list, new Task[] {high1, medium1})) {
        return false;
      }

      // Check insertion at the back.
      list.addTask(low1);
      if (!checkList(list, new Task[] {high1, medium1, low1})) {
        return false;
      }

      // Check insertion at the end of the middle priority group.
      list.addTask(medium2);
      if (!checkList(list, new Task[] {high1, medium1, medium2, low1})) {
        return false;
      }

      // Check insertion after the existing high-priority task.
      list.addTask(high2);
      if (!checkList(list, new Task[] {high1, high2, medium1, medium2, low1})) {
        return false;
      }

      // Check insertion after the existing low-priority task.
      list.addTask(low2);
      Task[] expected = {high1, high2, medium1, medium2, low1, low2};
      if (!checkList(list, expected)) {
        return false;
      }

      // Verify that invalid input throws the required exception.
      try {
        list.addTask(null);
        return false;
      } catch (IllegalArgumentException exception) {
        // Rejection must leave the existing list unchanged.
      }
      return checkList(list, expected);
    } catch (Exception exception) {
      System.out.println("testAddTask unexpected exception: " + exception);
      return false;
    }
  }

  /**
   * Tests removal results, remaining order, list reuse, and invalid indices.
   *
   * @return true if all checks pass; false otherwise
   */
  public static boolean testRemove() {
    // Start with predictable task IDs and report unexpected exceptions as failures.
    Task.resetIDGenerator();
    try {
      TaskList list = new TaskList();

      // Create a list with distinct first, middle, and last tasks.
      Task first = new Task("First", TaskPriority.HIGH);
      Task second = new Task("Second", TaskPriority.HIGH);
      Task middle = new Task("Middle", TaskPriority.MEDIUM);
      Task last = new Task("Last", TaskPriority.LOW);
      list.addTask(first);
      list.addTask(second);
      list.addTask(middle);
      list.addTask(last);
      Task[] original = {first, second, middle, last};
      if (!checkList(list, original)) {
        return false;
      }

      // Verify that invalid input throws the required exception.
      try {
        list.remove(-1);
        return false;
      } catch (IndexOutOfBoundsException exception) {
        // Expected.
      }
      if (!checkList(list, original)) {
        return false;
      }

      // Verify that invalid input throws the required exception.
      try {
        list.remove(list.size());
        return false;
      } catch (IndexOutOfBoundsException exception) {
        // Expected.
      }
      if (!checkList(list, original)) {
        return false;
      }

      // Check the returned task and remaining order after head removal.
      if (list.remove(0) != first
          || !checkList(list, new Task[] {second, middle, last})) {
        return false;
      }

      // Check removal from the middle of a three-task list.
      if (list.remove(1) != middle || !checkList(list, new Task[] {second, last})) {
        return false;
      }

      // Check removal of the tail.
      if (list.remove(list.size() - 1) != last
          || !checkList(list, new Task[] {second})) {
        return false;
      }

      // Appending after tail removal checks that tail was updated correctly.
      list.addTask(last);
      if (!checkList(list, new Task[] {second, last}) || list.remove(1) != last
          || !checkList(list, new Task[] {second})) {
        return false;
      }

      // Check that removing the only task produces an empty list.
      if (list.remove(0) != second || !checkList(list, new Task[] {})) {
        return false;
      }

      // Verify that invalid input throws the required exception.
      try {
        list.remove(0);
        return false;
      } catch (IndexOutOfBoundsException exception) {
        // No index is valid on an empty list.
      }

      // Verify that invalid input throws the required exception.
      try {
        list.remove(-1);
        return false;
      } catch (IndexOutOfBoundsException exception) {
        // Negative indices are invalid on an empty list as well.
      }
      if (!checkList(list, new Task[] {})) {
        return false;
      }

      // Check that the list can be reused after becoming empty.
      list.addTask(first);
      return checkList(list, new Task[] {first});
    } catch (Exception exception) {
      System.out.println("testRemove unexpected exception: " + exception);
      return false;
    }
  }

  /**
   * Checks size, emptiness, exact task order, and the complete list string.
   *
   * @param list the list to check
   * @param expected the tasks in their expected order
   * @return true if the list matches all expectations
   */
  private static boolean checkList(TaskList list, Task[] expected) {
    if (list.size() != expected.length || list.isEmpty() != (expected.length == 0)) {
      return false;
    }

    // Build the expected string while checking each stored task.
    String expectedString = "EMPTY";
    if (expected.length > 0) {
      expectedString = "";

      // Check task identity so equal task contents cannot hide incorrect order.
      for (int i = 0; i < expected.length; i++) {
        if (list.get(i) != expected[i]) {
          return false;
        }
        expectedString += expected[i].toString();
        expectedString += i == expected.length - 1 ? " -> END" : " -> ";
      }
    }
    return list.toString().equals(expectedString) && list.size() == expected.length;
  }

  // ---- PROVIDED METHODS (do not modify) ----

  /**
   * Calls all test methods and returns true if and only if all tests pass.
   *
   * @return true if every test method returns true; false otherwise
   */
  public static boolean runAllTests() {
    // Run every test, even if an earlier test fails.
    boolean allPass = true;
    allPass &= testLinkedTask();
    allPass &= testAddTask();
    allPass &= testRemove();
    return allPass;
  }

  /**
   * Demonstrates the functionality of the TaskList with sample data.
   * See the assignment specification for expected output.
   */
  public static void demo() {
    Task.resetIDGenerator();
    TaskList list = new TaskList();

    // Add tasks in mixed priority order.
    list.addTask(new Task("Deploy to production", TaskPriority.MEDIUM));
    list.addTask(new Task("Fix login bug", TaskPriority.HIGH));
    list.addTask(new Task("Update README", TaskPriority.LOW));
    list.addTask(new Task("Code review PR #42", TaskPriority.MEDIUM));
    list.addTask(new Task("Patch security vuln", TaskPriority.HIGH));

    System.out.println("List Before Completion:");
    System.out.println(list);
    System.out.println("Size: " + list.size());

    // Mark selected tasks complete without changing their positions.
    list.get(0).markComplete();
    list.get(2).markComplete();
    list.get(4).markComplete();

    System.out.println("\nList After Completion:");
    System.out.println(list);

    // Remove completed tasks from back to front to preserve valid indices.
    list.remove(4);
    list.remove(2);
    list.remove(0);

    System.out.println("\nList After Deletion:");
    System.out.println(list);
  }

  /**
   * Runs all tests and prints their results. The optional demo call is commented out.
   *
   * @param args unused
   */
  public static void main(String[] args) {
    // Report each test result and the combined result.
    System.out.println("testLinkedTask: " + testLinkedTask());
    System.out.println("testAddTask: " + testAddTask());
    System.out.println("testRemove: " + testRemove());
    System.out.println("runAllTests: " + runAllTests());

    System.out.println("\n========== DEMO ==========\n");
    demo();
  }
}
