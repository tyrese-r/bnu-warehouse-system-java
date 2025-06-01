package dev.tbertie.warehousesystem.ui;

import java.util.Objects;
import java.util.Scanner;
import java.util.Stack;
import java.util.stream.Collectors;

public class UIController {

    public Stack<Menu> menuStack = new Stack<>();

    public boolean isExit = false;

    private final Scanner scanner;


    public UIController() {
        Menu initialMenu = new MenuMain("main menu", this);

        this.scanner = new Scanner(System.in);
        menuStack.push(initialMenu);
    }

    public void start() {
        while (!isExit) {
            // Render current menu and get input
            menuStack.peek().render();
            getInput();

            if(menuStack.isEmpty()) {
                System.out.println("Exiting application due to no menu");
                isExit = true;
            }
        }

        if (scanner != null) {
            scanner.close();
        }
        System.out.println("Goodbye");
    }
    public void navigateBack() {
        if (menuStack.size() <= 1) {
            System.out.println("You are at the main menu");
            return;
        }
        menuStack.pop();


    }

    public void exit() {
        isExit = true;
    }

    private void getInput() {
        boolean validInput = false;
        while (!validInput) {
            if (menuStack.isEmpty()) {
                System.err.println("Error: input with no menu in stack");
                isExit = true;
                return;
            }

            System.out.print(getFormattedPath() + ">> ");
            String line = scanner.nextLine().trim();
            if (line.isEmpty()) {
                System.out.println("Invalid input");
                continue;
            }
            String[] commandParts = line.split("\\s+");

            if (commandParts.length == 0) {
                System.out.println("Invalid input");
                continue;
            }

            if(commandParts[0].equalsIgnoreCase("back")) {
                navigateBack();
                return;
            }

            if(commandParts[0].equalsIgnoreCase("exit")) {
                exit();
                return;
            }

            try {
                menuStack.peek().command(commandParts);
                validInput = true;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }


    }

    private String getFormattedPath() {
        if (menuStack.size() <= 1) {
            return "";
        }

        return menuStack.stream()
                .skip(1)
                .map(Menu::getName)
                .collect(Collectors.joining(" > ")) + " ";
    }

    public void pushMenuToStack(Menu menu) {
        if (menu == null) {
            System.err.println("Error: Invalid menu");
            return;
        }

        menuStack.push(menu);
    }
}
