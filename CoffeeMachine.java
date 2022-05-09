package machine;

import java.util.InputMismatchException;
import java.util.Scanner;

public class CoffeeMachine {

    private boolean machineTurnOn;

    private int availableWater;
    private int availableMilk;
    private int availableBeans;
    private int availableCups;
    private int money;

    private final Scanner scanner;

    public CoffeeMachine(int water, int milk, int beans, int cups, int money, Scanner scanner) {
        this.availableWater = water;
        this.availableMilk = milk;
        this.availableBeans = beans;
        this.availableCups = cups;
        this.money = money;
        this.scanner = scanner;
    }

    public boolean isMachineTurnOn() {
        return machineTurnOn;
    }

    public void setMachineTurnOn(boolean machineTurnOn) {
        this.machineTurnOn = machineTurnOn;
    }

    public void selectAction(String selected_action) {
        Action machine_action = Action.ASK_FOR_ACTION;
        for(Action action: Action.values()) {
            if (action.callout.equals(selected_action)) {
                machine_action = action;
            }
        }
        System.out.println();
        switch (machine_action) {
            case INFORM_ABOUT_RESOURCES:
                this.informAboutResources();
                break;
            case SELL_COFFEE:
                this.sellCoffee();
                break;
            case FILL_MACHINE:
                this.fillMachine();
                break;
            case PAY_MONEY_OUT:
                this.payMoneyOut();
                break;
            case SHUTDOWN:
                this.setMachineTurnOn(false);
                break;
            case ASK_FOR_ACTION:
                System.out.println("Unknown action!");
                break;
        }
        System.out.println();
    }

    public void sellCoffee() {
        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
        int order;
        if(scanner.hasNextInt()) {
            order = scanner.nextInt();
            scanner.nextLine();
        } else {
            scanner.nextLine();
            return;
        }
        for (Coffee coffee : Coffee.values()) {
            if (order == coffee.id) {
                this.makeCoffee(coffee);
                break;
            }
        }
    }

    private void makeCoffee(Coffee coffee) {
        if (this.availableCups >= 1 && this.availableWater >= coffee.water
                && this.availableMilk >= coffee.milk && this.availableBeans >= coffee.beans) {
            System.out.println("I have enough resources, making you a coffee!");
            this.availableWater -= coffee.water;
            this.availableMilk -= coffee.milk;
            this.availableBeans -= coffee.beans;
            this.availableCups -= 1;
            this.money += coffee.cost;
        } else {
            StringBuilder requestForResources = new StringBuilder();
            requestForResources.append("Sorry");
            if (availableCups < 1) {
                requestForResources.append(", not enough cups");
            }
            if (availableWater < coffee.water) {
                requestForResources.append(", not enough water");
            }
            if (availableMilk < coffee.milk) {
                requestForResources.append(", not enough milk");
            }
            if (availableBeans < coffee.beans) {
                requestForResources.append(", not enough beans");
            }
            requestForResources.append("!");
            System.out.println(requestForResources);
        }
    }

    public void fillMachine() {
        try {
            System.out.println("Write how many ml of water you want to add:");
            availableWater += scanner.nextInt();
            System.out.println("Write how many ml of milk you want to add:");
            availableMilk += scanner.nextInt();
            System.out.println("Write how many grams of coffee beans you want to add:");
            availableBeans += scanner.nextInt();
            System.out.println("Write how many disposable cups of coffee you want to add:");
            availableCups += scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input!");
        }
        scanner.nextLine();
    }

    public void payMoneyOut() {
        System.out.printf("I gave you $%d\n", money);
        money = 0;
    }

    public void informAboutResources() {
        System.out.println("The coffee machine has:");
        System.out.printf("%d ml of water\n", this.availableWater);
        System.out.printf("%d ml of milk\n", this.availableMilk);
        System.out.printf("%d g of coffee beans\n", this.availableBeans);
        System.out.printf("%d disposable cups\n", this.availableCups);
        System.out.printf("$%d of money\n", this.money);
    }
}
