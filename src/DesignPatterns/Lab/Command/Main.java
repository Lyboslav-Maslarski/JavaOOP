package DesignPatterns.Lab.Command;

public class Main {
    public static void main(String[] args) {
        Product product = new Product("Phone", 500);

        execute(new IncreaseProductPriceCommand(product, 100));
        execute(new IncreaseProductPriceCommand(product, 50));
        execute(new DecreaseProductPriceCommand(product, 25));

        System.out.println(product);
    }

    private static void execute(Command productCommand) {
        System.out.print(productCommand.executeAction());
    }
}


