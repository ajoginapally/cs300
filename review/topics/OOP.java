public class OOP {
    static class Person {
        String name; int age;
        Person(String n,int a){name=n;age=a;}
        public String greet(){ return "Hi, I'm " + name + " (" + age + ")"; }
    }

    public static void main(String[] args){
        Person p = new Person("Alice", 21);
        System.out.println(p.greet());
    }
}