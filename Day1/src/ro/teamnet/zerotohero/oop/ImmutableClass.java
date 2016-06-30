package ro.teamnet.zerotohero.oop;

/**
 * Created by Eli Ionescu on 6/30/2016.
 */
public class ImmutableClass {

    String name;
    private int age;

    private ImmutableClass(String name, int age){
        this.name = name;
        this.age = age;
        System.out.println("An Immutable object was created");
    }

    public ImmutableClass getImmutableObject(String name, int age){
        return new ImmutableClass(name,age);
    }

    public int getAge(){
        int newAge = age;
        return newAge;
    }

    public String getName(){
        return name;
    }


}
