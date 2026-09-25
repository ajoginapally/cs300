/*
 * Author: Arnav Joginapally
 * Email: joginapally@wisc.edu
 * Course: CS 300, Fall 2026
 * Assignment: Space Station, Program 2
 * Citations: None
 */

import processing.core.PImage;

/**
 * Represents the space station where the game is played.
 * This class is responsible for managing the crewmates,
 * drawing the background, and handling user interactions.
 */
public class SpaceStation {
  // Class variables, which persist for the entire run of the program
  private static PImage backgroundImage;    // the spaceship lobby image
  private static Crewmate[] crew;           // the crewmates in the spaceship
  private static final int NUM_PLAYERS = 8; // the maximum number of crewmates

  /**
   * This method is called by the GUI library one (1) time at the beginning of the program.
   * We use it to initialize any class variables for the program.
   */
  public static void setup() {
    // initialize the background image using a method from Utility
    backgroundImage = Utility.loadImage("images/background.jpeg");

    // initialize the crew array to hold a maximum of NUM_PLAYERS
    crew = new Crewmate[NUM_PLAYERS];
  }

  /**
   * This method is called by the GUI library repeatedly to draw the current state of the program.
   */
  public static void draw() {
    // draw the background image, centered in the application window
    Utility.image(backgroundImage, Utility.width() / 2, Utility.height() / 2);

    // Draw each crewmate currently in the station.
    for (int i = 0; i < crew.length; i++) {
      if (crew[i] != null) {
        crew[i].draw();
      }
    }

    // Check each impostor for collisions with other crewmates.
    for (int i = 0; i < crew.length; i++) {
      if (crew[i] != null && crew[i].isImpostor()) {
        for (int j = 0; j < crew.length; j++) {
          if (i != j && crew[j] != null && !crew[j].isImpostor() && overlap(crew[i], crew[j])) {
            // if the impostor overlaps a normal crewmate, set the normal crewmate to dead
            crew[j].unalive();
          }
        }
      }
    }
  }

  /**
   * This method is called by the GUI library when a key is pressed.
   * It is used to add or remove crewmates from the game.
   * @param key the character of the key that was pressed (a, i, r)
   */
  public static void keyPressed(char key) {
    // Add a crewmate or remove the first crewmate under the mouse.
    if (key == 'a' || key == 'i') {
      // find the first null index in the crew array and add the new crewmate there
      for (int i = 0; i < crew.length; i++) {
        if (crew[i] == null) {
          int color = (int) (Math.random() * 3) + 1; // random color between 1 and 3
          crew[i] = new Crewmate(color, Utility.mouseX(), Utility.mouseY(), key == 'i');
          break;
        }
      }
    } else if (key == 'r') {
      // Remove only the first matching crewmate.
      for (int i = 0; i < crew.length; i++) {
        if (crew[i] != null && isMouseOver(crew[i])) {
          crew[i] = null;
          break;
        }
      }
    }
  }

  /**
   * Checks if the mouse is currently over the specified crewmate.
   * @param mate the crewmate to check
   * @return true if the mouse is over the crewmate, false otherwise
   */
  public static boolean isMouseOver(Crewmate mate) {
    if (mate == null) {
      return false;
    }

    return Utility.mouseX() >= mate.getX() - mate.image().width / 2 &&
        Utility.mouseX() <= mate.getX() + mate.image().width / 2 &&
        Utility.mouseY() >= mate.getY() - mate.image().height / 2 &&
        Utility.mouseY() <= mate.getY() + mate.image().height / 2;
  }

  /**
   * This method is called by the GUI library when the mouse is pressed.
   * It is used to start dragging any crewmate that the mouse covers.
   */
  public static void mousePressed() {
    // Start dragging only the first crewmate under the mouse.
    for (int i = 0; i < crew.length; i++) {
      if (crew[i] != null && isMouseOver(crew[i])) {
        crew[i].startDragging();
        break;
      }
    }
  }

  /**
   * This method is called by the GUI library when the mouse is
   * released. It is used to stop dragging any crewmate that is currently being dragged.
   */
  public static void mouseReleased() {
    // Stop dragging all crewmates when the mouse is released.
    for (int i = 0; i < crew.length; i++) {
      if (crew[i] != null && crew[i].isDragging()) {
        crew[i].stopDragging();
      }
    }
  }

  /**
   * Checks if two crewmates overlap.
   * @param mate1 the first crewmate
   * @param mate2 the second crewmate
   * @return true if the crewmates overlap, false otherwise
   */
  public static boolean overlap(Crewmate mate1, Crewmate mate2) {
    if (mate1 == null || mate2 == null) {
      return false;
    }

    // Each crewmate is drawn centered at (x, y), so its image occupies a rectangle
    // from (x - width / 2, y - height / 2) to (x + width / 2, y + height / 2).
    // Two rectangles overlap if their horizontal and vertical ranges intersect.
    double left1 = mate1.getX() - mate1.image().width / 2;
    double right1 = mate1.getX() + mate1.image().width / 2;
    double top1 = mate1.getY() - mate1.image().height / 2;
    double bottom1 = mate1.getY() + mate1.image().height / 2;

    // Calculate the second crewmate's bounds for comparison.
    double left2 = mate2.getX() - mate2.image().width / 2;
    double right2 = mate2.getX() + mate2.image().width / 2;
    double top2 = mate2.getY() - mate2.image().height / 2;
    double bottom2 = mate2.getY() + mate2.image().height / 2;

    // Both horizontal and vertical ranges must intersect.
    return left1 < right2 && right1 > left2 && top1 < bottom2 && bottom1 > top2;
  }

  /**
   * Starts the space station application.
   * @param args command-line arguments
   */
  public static void main(String[] args) {
    Utility.runApplication();
  }
}
