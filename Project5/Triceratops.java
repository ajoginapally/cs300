public class Triceratops extends Dinosaur {
    public Triceratops(String id, String name, int age) {
        super(id, name, age, FoodType.PLANTS, 6);
    }

    @Override
    public String roar() {
        return "HMMMPH!";
    }

    @Override
    public boolean canCoexistWith(Dinosaur otherDinosaur) {
        if (otherDinosaur == this) {
            return true;
        }
        return otherDinosaur.getFoodType() != FoodType.MEAT;
    }
}
