/*
 * Author: Arnav Joginapally
 * Email: joginapally@wisc.edu
 * Course: CS 300, Fall 2026
 * Assignment: Dino Park, Program 5
 * Citations: none
 */

/**
 * Represents a Triceratops dinosaur in the Dino Park.
 *
 * Triceratops is a FoodType.PLANTS dinosaur.
 * It can coexist with other herbivores but may be incompatible with carnivores.
 */
public class Triceratops extends Dinosaur {
  /**
   * Constructs a new Triceratops with the specified ID, name, and age.
   *
   * @param id The unique identifier for the Triceratops.
   * @param name The name of the Triceratops.
   * @param age The age of the Triceratops.
   * @throws IllegalArgumentException if id or name is null or blank, or age is negative.
   */
  public Triceratops(String id, String name, int age) {
    super(id, name, age, FoodType.PLANTS, 6);
  }

  /**
   * Returns the roar sound of the Triceratops.
   *
   * @return The roar sound of the Triceratops.
   */
  @Override
  public String roar() {
    return "HMMMPH!";
  }

  /**
   * Determines if the Triceratops can coexist with another dinosaur.
   *
   * @param otherDinosaur The dinosaur to check compatibility against.
   * @return true if the Triceratops can coexist with the other dinosaur, false otherwise.
   */
  @Override
  public boolean canCoexistWith(Dinosaur otherDinosaur) {
    if (otherDinosaur == this) {
      return true;
    }
    return otherDinosaur.getFoodType() != FoodType.MEAT;
  }
}
