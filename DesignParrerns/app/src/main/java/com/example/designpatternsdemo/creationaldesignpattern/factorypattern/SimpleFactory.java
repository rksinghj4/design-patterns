package com.example.designpatternsdemo.creationaldesignpattern.factorypattern;



// --- Burger Interface ---
interface BurgerI {
    void prepare();
}

// --- Concrete Burger Implementations ---
class BasicBurger implements BurgerI {
    @Override
    public void prepare() {
        System.out.println("Preparing Basic Burger with bun, patty, and ketchup!");
    }
}

class StandardBurger implements BurgerI {
    @Override
    public void prepare() {
        System.out.println("Preparing Standard Burger with bun, patty, cheese, and lettuce!");
    }
}

class PremiumBurger implements BurgerI {
    @Override
    public void prepare() {
        System.out.println("Preparing Premium Burger with gourmet bun, premium patty, cheese, lettuce, and secret sauce!");
    }
}

//Note: In Simple factory we don't have interface/abstract class for Factory.
// Direct Concrete class(i.e Burger_Factory) .
// --- Burger Factory ---
class Burger_Factory {
    public BurgerI createBurger(String type) {
        if (type.equalsIgnoreCase("basic")) {
            return new BasicBurger();
        } else if (type.equalsIgnoreCase("standard")) {
            return new StandardBurger();
        } else if (type.equalsIgnoreCase("premium")) {
            return new PremiumBurger();
        } else {
            System.out.println("Invalid burger type!");
            return null;
        }
    }
}

// --- Main Class ---
public class SimpleFactory {
    public static void main(String[] args) {
        String type = "standard";

        Burger_Factory myBurgerFactory = new Burger_Factory();

        BurgerI burger = myBurgerFactory.createBurger(type);

        if (burger != null) {
            burger.prepare();
        }
    }
}