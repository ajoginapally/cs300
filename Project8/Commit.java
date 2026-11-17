// TODO: File Header

/**
 * Represents a single commit in a version control system.
 * Implemented as a simplified model that tracks only which files were changed.
 * Commits are immutable; merges require creating a *new* commit.
 * @author Max Phillips
 * @author [your name here when you finish the implementation]
 */

import java.util.ArrayList;

public class Commit {
  // Auto-generated commit ID (short-hash style, e.g., a1cb2f).
  private String id;

  // User-provided commit message (description of changes).
  private String message;

  // List of (String) filenames that were changed in this commit.
  private ArrayList<String> changedFiles;

  /**
   * Creates a new Commit with the specified message and change list.
   * @param message a String description of the changes in this Commit.
   * @param changedFiles the list of files changed in this Commit.
   */
  public Commit(String message, ArrayList<String> changedFiles) {
    this.id = HashGenerator.generateShortHash(message); // keep this line as is

    // TODO: initialize this Commit (remember to *defensive copy* changedFiles!)
  }

  // TODO: Method header
  public String getId() {
    // TODO: Implement
  }

  // TODO: Method header
  public String getMessage() {
    // TODO: Implement
  }

  // TODO: Method header
  public ArrayList<String> getChangedFiles() {
    // TODO: Implement (remember to *defensive copy* changedFiles!)
  }

  // PROVIDED METHOD
  /**
   * Convert this commit to a String representation.
   * @return a String representing this Commit
   */
  @Override
  public String toString() {
    return "" + this.id + " '" + this.message + "' : " + this.changedFiles;
  }
}