public class Trex extends Dinosaur{
    

    public Trex(String id, String name, int age) {
        super(id, name, age, FoodType.MEAT, 20);
    }

    @Override
    public String roar() {
        return "ROOOAAAR!";
    }

    @Override
    public boolean canCoexistWith(Dinosaur otherDinosaur) {
        if (otherDinosaur == this) {
            return true;
        }
        return false;
    }
}
