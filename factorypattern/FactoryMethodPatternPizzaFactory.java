package factorypattern;

/**
 * Created by priyankvex on 22/5/17.
 *
 * Factory Method Pattern :
 * Factory method pattern encapsulates and pushes the object creation logic down
 * to the subclasses. Thus the subclasses are responsible for creating the objects.
 * It promotes loose coupling between the client and object creation, as the client interacts solely
 * with the abstract interface.
 *
 * In this demo, we'll create a pizza store that'll create different pizzas using the factory pattern.
 */
public class FactoryMethodPatternPizzaFactory {

    /**
     * Abstract class for the product that the factory will create.
     */
    private static abstract class Pizza {

        String name;
    }

    /**
     * Abstract class that will have the abstracted factory method and other
     * method implementations.
     */
    private static abstract class PizzaStore {

        String pizzaStoreName;

        public void orderPizza(String type){
            Pizza pizza = createPizza(type);
            cutSliced(pizza);
            boxPizza(pizza);
            System.out.println(pizza.name + " Delivered!");
        }

        private void boxPizza(Pizza pizza){
            System.out.println("Pizza boxed!");
        }

        private void cutSliced(Pizza pizza){
            System.out.println("Pizza sliced!");
        }

        /**
         * This is the factory method that the sub classes has to implement.
         * Subclasses will implement this method and will create the concrete instance of Pizza.
         * @param type String to tell the type of pizza
         * @return Pizza that we just created!
         */
        protected abstract Pizza createPizza(String type);
    }

    /**
     * Pizza store implementation #1
     * Note how the subclasses are creating the Pizza instance and not the super class.
     * In fact, super class is abstract.
     */
    private static class DominosPizzaStore extends PizzaStore {

        DominosPizzaStore(){
            pizzaStoreName = "Dominos Pizza Store";
        }

        @Override
        public Pizza createPizza(String type) {

            Pizza pizza;

            switch (type){
                case "cheese":
                    pizza = new DominosCheesePizza();
                    break;
                case "pan":
                    pizza = new DominosPanPizza();
                    break;
                default:
                    pizza = new DominosPanPizza();
            }

            return pizza;
        }
    }

    /**
     * Pizza store implementation #2
     */
    private static class PizzaHutPizzaStore extends PizzaStore {

        PizzaHutPizzaStore(){
            pizzaStoreName = "Pizza Hut Pizza Store";
        }

        @Override
        public Pizza createPizza(String type) {

            Pizza pizza;

            switch (type){
                case "cheese":
                    pizza = new PizzaHutCheesePizza();
                    break;
                case "pan":
                    pizza = new PizzaHutPanPizza();
                    break;
                default:
                    pizza = new PizzaHutPanPizza();
            }

            return pizza;
        }
    }

    /**
     * Concrete product #1
     * All pizza implementation extend Pizza as to provide a common type.
     */
    private static class DominosCheesePizza extends Pizza{

        DominosCheesePizza(){
            name = "Dominos cheese pizza";
        }
    }

    /**
     * Concrete pizza #2
     */
    private static class DominosPanPizza extends Pizza {

        DominosPanPizza(){
            name = "Dominos Pan Pizza";
        }
    }

    /**
     * Concrete product #3
     */
    private static class PizzaHutCheesePizza extends Pizza {

        PizzaHutCheesePizza(){
            name = "Pizza Hut Cheese Pizza";
        }
    }

    /**
     * Concrete pizza #4
     */
    private static class PizzaHutPanPizza extends Pizza {

        PizzaHutPanPizza(){
            name = "Pizza hut pan pizza";
        }
    }

    public static void main(String[] args) {
        PizzaStore pizzaStore = new DominosPizzaStore();
        pizzaStore.orderPizza("cheese");

        pizzaStore = new PizzaHutPizzaStore();
        pizzaStore.orderPizza("pan");
    }

}
