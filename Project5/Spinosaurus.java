public class Spinosaurus extends Dinosaur {
    
    public Spinosaurus(String id, String name, int age) {
        super(id, name, age, FoodType.MEAT, 16);
    }

    @Override
    public String roar() {
        return "GRRRRR!";
    }

    @Override
    public boolean canCoexistWith(Dinosaur otherDinosaur) {
        if (otherDinosaur == this) {
            return true;
        }
        if (otherDinosaur instanceof Spinosaurus) {
            return true;
        }
        return otherDinosaur.canCoexistWith(this);
    }
}
