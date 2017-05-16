package decoratorpattern;

/**
 * Created by priyankvex on 16/5/17.
 *
 * Decorator Pattern:
 * The decorator pattern attaches additional responsibilities to an object dynamically.
 * Decorators provide a flexible alternative to sub-classing for extending functionality.
 *
 * Decorators can thought of as wrappers around components.
 * A component can be wrapped by many decorators.
 *
 * The decorator and component must have the same type.
 *
 * Design Principle :
 * Classes should be open for extension, but closed for modification.
 *
 * In this demo, we'll use the decorator pattern to make coffee and add add-ons to it.
 * Each coffee is a component of type {@link Beverage}.
 * Each add-on is decorator of type {@link Beverage}.
 * To add a add-on to a coffee component, we wrap the object with the add-on decorator.
 */
public class CoffeeDecoratorPattern {

    /**
     * Abstract component for the beverage
     */
    private static abstract class Beverage {

        String description = "Unknown beverage";

        public String getDescription(){
            return this.description;
        }

        public abstract float getCost();
    }

    /**
     * Abstract decorator. It extends {@link Beverage} class.
     * This will be extended by all decorators.
     * In turn, this will make type of components and decorators same to be of type
     * {@link Beverage}
     */
    private static abstract class AddOnDecorator extends Beverage {

        /**
         * We will implement this method again in the decorator to extend this functionality.
         * @return description of the beverage that we are making.
         */
        public abstract String getDescription();
    }

    /**
     * Concrete component for Espresso beverage.
     */
    private static class Espresso extends Beverage {

        public Espresso(){
            this.description = "Espresso";
        }

        @Override
        public float getCost() {
            return 50;
        }
    }

    /**
     * Concrete component for Dark Roast beverage.
     */
    private static class DarkRoast extends Beverage {

        public DarkRoast(){
            this.description = "Dark Roast";
        }

        @Override
        public float getCost() {
            return 75;
        }
    }

    /**
     * Concrete component for Decaf beverage.
     */
    private static class Decaf extends Beverage {

        public Decaf(){
            this.description = "Decaf";
        }

        @Override
        public float getCost() {
            return 100;
        }
    }

    /**
     * Decorator for the whipped cream add on.
     */
    private static class WhippedCream extends AddOnDecorator {

        // hold the reference to the component it is wrapping
        Beverage beverage;

        WhippedCream(Beverage beverage){
            this.beverage = beverage;
        }

        /**
         * Extend the functionality by using the wrapper
         * @return cost of component it is wrapping + it's own cost
         */
        @Override
        public float getCost() {
            return 25 + beverage.getCost();
        }

        /**
         * Extend the description functionality
         * @return wrapped object's description + wrapper's description
         */
        @Override
        public String getDescription() {
            return "Whipped Cream + " + beverage.getDescription();
        }
    }

    /**
     * Decorator for chocolate syrup add on.
     */
    private static class ChocolateSyrup extends AddOnDecorator {

        Beverage beverage;

        ChocolateSyrup(Beverage beverage){
            this.beverage = beverage;
        }

        @Override
        public float getCost() {
            return 30 + beverage.getCost();
        }

        @Override
        public String getDescription() {
            return "Chocolate Syrup + " + beverage.getDescription();
        }
    }

    /**
     * Decorator for ice cream add on.
     */
    private static class IceCream extends AddOnDecorator {

        Beverage beverage;

        IceCream(Beverage beverage){
            this.beverage = beverage;
        }

        @Override
        public float getCost() {
            return 50 + beverage.getCost();
        }

        @Override
        public String getDescription() {
            return "Ice Cream + " + beverage.getDescription();
        }
    }

    public static void main(String[] args) {

        // create a beverage that is an espresso
        Beverage b = new Espresso();
        // add chocolate syrup add on
        b = new ChocolateSyrup(b);
        // add whipped cream add on
        b = new WhippedCream(b);

        System.out.println(b.getDescription());
        System.out.println("Total cost of beverage : " + b.getCost());
    }

}
