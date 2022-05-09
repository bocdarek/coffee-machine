package machine;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        CoffeeMachine coffeeMachine = new CoffeeMachine(400, 540, 120, 9, 550, scanner);
        coffeeMachine.setMachineTurnOn(true);

        while (coffeeMachine.isMachineTurnOn()) {
            System.out.println("Write action (buy, fill, take, remaining, exit):");
            String action = scanner.nextLine();
            coffeeMachine.selectAction(action);
        }

        scanner.close();
    }
}
