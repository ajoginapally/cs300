/*
 * Author: Arnav Joginapally
 * Email: joginapally@wisc.edu
 * Course: CS 300, Fall 2026
 * Assignment: Dino Park, Program 5
 * Citations: none
 */

/**
 * Represents a TRex dinosaur in the Dino Park.
 *
 * TRex is a FoodType.MEAT dinosaur.
 * It cannot coexist with other dinosaurs.
 */
public class TRex extends Dinosaur {
  /**
   * Constructs a new TRex with the specified ID, name, and age.
   *
   * @param id The unique identifier for the TRex.
   * @param name The name of the TRex.
   * @param age The age of the TRex.
   * @throws IllegalArgumentException if id or name is null or blank, or age is negative.
   */
  public TRex(String id, String name, int age) {
    super(id, name, age, FoodType.MEAT, 20);
  }

  /**
   * Returns the roar sound of the TRex.
   *
   * @return The roar sound of the TRex.
   */
  @Override
  public String roar() {
    return "ROOOAAAR!";
  }

  /**
   * Determines if the TRex can coexist with another dinosaur.
   *
   * @param otherDinosaur The dinosaur to check compatibility against.
   * @return true if the TRex can coexist with the other dinosaur, false otherwise.
   */
  @Override
  public boolean canCoexistWith(Dinosaur otherDinosaur) {
    if (otherDinosaur == this) {
      return true;
    }
    return false;
  }
}
