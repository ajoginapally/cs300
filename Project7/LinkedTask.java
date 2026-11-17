/*
 * Author: Arnav Joginapally
 * Email: joginapally@wisc.edu
 * Course: CS 300, Fall 2026
 * Assignment: Linked, Program 7
 * Citations: none
 */

/**
 * Represents a single node in a linked list of tasks.
 * LinkedTask is used as a building block for the TaskList implementation.
 */
public class LinkedTask {

  /** The task data stored in this node. */
  private Task task;

  /**
   * A reference to the next LinkedTask in the list, or null if this is the last
   * node.
   */
  private LinkedTask next;

  /**
   * Creates a node with no next node.
   *
   * @param task the task to store
   * @throws IllegalArgumentException if task is null
   */
  public LinkedTask(Task task) {
    // Reject missing task data before initializing the node.
    if (task == null) {
      throw new IllegalArgumentException("Task cannot be null");
    }

    // Store the task and initialize the next reference.
    this.task = task;
    this.next = null;
  }

  /**
   * Creates a node with the given task and next reference.
   *
   * @param task the task to store
   * @param next the next node, or null for the last node
   * @throws IllegalArgumentException if task is null
   */
  public LinkedTask(Task task, LinkedTask next) {
    // Reject missing task data before initializing the node.
    if (task == null) {
      throw new IllegalArgumentException("Task cannot be null");
    }

    // Store the task and initialize the next reference.
    this.task = task;
    this.next = next;
  }

  // ---- PROVIDED ACCESSORS (do not modify) ----

  /**
   * Returns the Task stored in this node.
   *
   * @return the task data
   */
  public Task getTask() {
    return this.task;
  }

  /**
   * Returns the next LinkedTask in the list.
   *
   * @return the next node, or null if this is the last node
   */
  public LinkedTask getNext() {
    return this.next;
  }

  // ---- END OF PROVIDED CODE ----

  /**
   * Sets the next LinkedTask in the list.
   *
   * @param next the next LinkedTask in the list, or null if this is the last node
   */
  public void setNext(LinkedTask next) {
    this.next = next;
  }

  /**
   * Returns this task followed by an arrow or the end marker.
   *
   * @return the task string followed by " -> " or " -> END"
   */
  @Override
  public String toString() {
    String taskString = this.task.toString();

    
    if (this.next != null) {
      taskString += " -> ";
    } else {
      taskString += " -> END";
    }

    return taskString;
  }

}
