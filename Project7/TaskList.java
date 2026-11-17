/*
 * Author: Arnav Joginapally
 * Email: joginapally@wisc.edu
 * Course: CS 300, Fall 2026
 * Assignment: Linked, Program 7
 * Citations: none
 */

/**
 * TaskList is a linked list implementation of the SortedTaskListADT interface.
 * It maintains tasks in order of priority (HIGH, MEDIUM, LOW) and within the same
 * priority group, tasks are ordered by insertion.
 */
public class TaskList implements SortedTaskListADT<Task> {

  /** The first node in the linked list (highest priority, earliest task). */
  private LinkedTask head;

  /** The last node in the linked list (lowest priority, latest task). */
  private LinkedTask tail;

  /** The total number of tasks in the list. */
  private int size;

  /**
   * Creates an empty task list.
   */
  public TaskList() {
    // Reset both endpoints and the task count.
    head = null;
    tail = null;
    size = 0;
  }

  /**
   * Returns the number of tasks in the list.
   *
   * @return the number of stored tasks
   */
  public int size() {
    return size;
  }

  /**
   * Reports whether the list is empty.
   *
   * @return true if the list contains no tasks
   */
  public boolean isEmpty() {
    return size == 0;
  }

  /**
   * Adds a task in priority order, after existing tasks with the same priority.
   *
   * @param task the task to insert
   * @throws IllegalArgumentException if task is null
   */
  public void addTask(Task task) {
    // Validate the input before changing the list.
    if (task == null) {
      throw new IllegalArgumentException("Task cannot be null");
    }

    LinkedTask newNode = new LinkedTask(task);
    if (isEmpty()) {
      head = newNode;
      tail = newNode;
    } else if (task.getPriority().compareTo(head.getTask().getPriority()) < 0) {
      newNode.setNext(head);
      head = newNode;
    } else if (task.getPriority().compareTo(tail.getTask().getPriority()) >= 0) {
      tail.setNext(newNode);
      tail = newNode;
    } else {
      LinkedTask current = head;
      while (current.getNext() != null
          && current.getNext().getTask().getPriority().compareTo(task.getPriority()) <= 0) {
        current = current.getNext();
      }

      // Link the new node between its neighbors.
      newNode.setNext(current.getNext());
      current.setNext(newNode);
    }

    size++;
  }

  /**
   * Returns the task at an index without removing it.
   *
   * @param index the zero-based position to retrieve
   * @return the task at the given index
   * @throws IndexOutOfBoundsException if index is negative or at least size()
   */
  public Task get(int index) {
    // Reject indices outside the current list.
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException("Index out of bounds");
    }

    // Traverse from the head to locate the requested task.
    LinkedTask current = head;
    for (int i = 0; i < index; i++) {
      current = current.getNext();
    }
    return current.getTask();
  }

  /**
   * Checks whether an equal task exists in the list.
   *
   * @param task the task to find; may be null
   * @return true if a stored task equals the given task
   */
  public boolean contains(Task task) {
    // Traverse from the head to locate the requested task.
    LinkedTask current = head;
    // Compare task contents using equals().
    while (current != null) {
      if (current.getTask().equals(task)) {
        return true;
      }
      current = current.getNext();
    }
    return false;
  }

  /**
   * Removes a task while preserving the order of the remaining tasks.
   *
   * @param index the zero-based position to remove
   * @return the removed task
   * @throws IndexOutOfBoundsException if index is negative or at least size()
   */
  public Task remove(int index) {
    // Reject indices outside the current list.
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException("Index out of bounds");
    }

    
    LinkedTask current = head;
    Task removedTask;

    if (index == 0) {
      removedTask = current.getTask();
      head = current.getNext();
      if (head == null) {
        tail = null;
      }
    } else {
      for (int i = 0; i < index - 1; i++) {
        current = current.getNext();
      }

      LinkedTask nodeToRemove = current.getNext();
      removedTask = nodeToRemove.getTask();
      current.setNext(nodeToRemove.getNext());
      if (nodeToRemove == tail) {
        tail = current;
      }
    }

    size--;
    return removedTask;
  }

  /**
   * Removes all tasks and resets the list to its empty state.
   */
  public void clear() {
    head = null;
    tail = null;
    size = 0;
  }

  /**
   * Returns the linked-list representation from head to tail.
   *
   * @return the task strings joined by arrows and ending in END, or EMPTY
   */
  @Override
  public String toString() {
    if (isEmpty()) {
      return "EMPTY";
    }

    String listString = "";
    LinkedTask current = head;
    while (current != null) {
      listString += current.toString();
      current = current.getNext();
    }

    return listString;
  }

}
