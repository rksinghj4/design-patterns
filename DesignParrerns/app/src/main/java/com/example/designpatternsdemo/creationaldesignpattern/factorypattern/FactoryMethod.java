package com.example.designpatternsdemo.creationaldesignpattern.factorypattern;

// Product Interface and subclasses
interface Burger3 {
    void prepare();
}

//In English, maida is called all-purpose flour, refined flour, or white flour
class BasicBurger3 implements Burger3 {
    public void prepare() {
        System.out.println("Preparing Basic Burger with bun, patty, and ketchup!");
    }
}

class StandardBurger3 implements Burger3 {
    public void prepare() {
        System.out.println("Preparing Standard Burger with bun, patty, cheese, and lettuce!");
    }
}

class PremiumBurger3 implements Burger3 {
    public void prepare() {
        System.out.println("Preparing Premium Burger with gourmet bun, premium patty, cheese, lettuce, and secret sauce!");
    }
}

class BasicWheatBurger implements Burger3 {
    public void prepare() {
        System.out.println("Preparing Basic Wheat Burger with bun, patty, and ketchup!");
    }
}

class StandardWheatBurger implements Burger3 {
    public void prepare() {
        System.out.println("Preparing Standard Wheat Burger with bun, patty, cheese, and lettuce!");
    }
}

class PremiumWheatBurger implements Burger3 {
    public void prepare() {
        System.out.println("Preparing Premium Wheat Burger with gourmet bun, premium patty, cheese, lettuce, and secret sauce!");
    }
}

// Factory Interface and Concrete Factories
interface BurgerFactory3 {
    Burger3 createBurger(String type);
}

class SinghBurger implements BurgerFactory3 {
    public Burger3 createBurger(String type) {
        if (type.equalsIgnoreCase("basic")) {
            return new BasicBurger3();
        } else if (type.equalsIgnoreCase("standard")) {
            return new StandardBurger3();
        } else if (type.equalsIgnoreCase("premium")) {
            return new PremiumBurger3();
        } else {
            System.out.println("Invalid burger type!");
            return null;
        }
    }
}

//Wheat Burger
class KingBurger implements BurgerFactory3{
    public Burger3 createBurger(String type) {
        if (type.equalsIgnoreCase("basic")) {
            return new BasicWheatBurger();
        } else if (type.equalsIgnoreCase("standard")) {
            return new StandardWheatBurger();
        } else if (type.equalsIgnoreCase("premium")) {
            return new PremiumWheatBurger();
        } else {
            System.out.println("Invalid burger type!");
            return null;
        }
    }
}

// Main Class
public class FactoryMethod {
    public static void main(String[] args) {
        String type = "basic";

        BurgerFactory3 myFactory = new SinghBurger();
        Burger3 burger = myFactory.createBurger(type);

        if (burger != null) {
            burger.prepare();
        }
    }
}

