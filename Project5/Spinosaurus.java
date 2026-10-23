/*
 * Author: Arnav Joginapally
 * Email: joginapally@wisc.edu
 * Course: CS 300, Fall 2026
 * Assignment: Dino Park, Program 5
 * Citations: none
 */

/**
 * Represents a Spinosaurus dinosaur in the Dino Park.
 *
 * Spinosaurus is a FoodType.MEAT dinosaur.
 * It can coexist with other Spinosaurus dinosaurs but may be incompatible with other species.
 */
public class Spinosaurus extends Dinosaur {
  /**
   * Constructs a new Spinosaurus with the specified ID, name, and age.
   *
   * @param id The unique identifier for the Spinosaurus.
   * @param name The name of the Spinosaurus.
   * @param age The age of the Spinosaurus.
   * @throws IllegalArgumentException if id or name is null or blank, or age is negative.
   */
  public Spinosaurus(String id, String name, int age) {
    super(id, name, age, FoodType.MEAT, 16);
  }

  /**
   * Returns the roar sound of the Spinosaurus.
   *
   * @return The roar sound of the Spinosaurus.
   */
  @Override
  public String roar() {
    return "GRRRRR!";
  }

  /**
   * Determines if the Spinosaurus can coexist with another dinosaur.
   *
   * @param otherDinosaur The dinosaur to check compatibility against.
   * @return true if the Spinosaurus can coexist with the other dinosaur, false otherwise.
   */
  @Override
  public boolean canCoexistWith(Dinosaur otherDinosaur) {
    // Accept this individual and other members of the same species.
    if (otherDinosaur == this) {
      return true;
    }
    if (otherDinosaur instanceof Spinosaurus) {
      return true;
    }

    // Use the other species' compatibility rule for remaining dinosaurs.
    return otherDinosaur.canCoexistWith(this);
  }
}
