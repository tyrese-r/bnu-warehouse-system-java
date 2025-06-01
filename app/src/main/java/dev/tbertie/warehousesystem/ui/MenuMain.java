package dev.tbertie.warehousesystem.ui;

public class MenuMain extends Menu {



    public MenuMain(String name, UIController uiController) {
        super(name, uiController);
    }


    @Override
    public void render() {
        System.out.println("\n--- Main Menu ---");
        System.out.println("1. View Items");
        System.out.println("2. Settings");
        System.out.println("At any time use exit or back");
        System.out.println("exit - Exit application");
        System.out.println("------------------");
    }

    @Override
    public void command(String[] command) {

        if (command.length == 0) return; // Should not happen if getInput is robust

        String action = command[0].toLowerCase();
        // Do something here
        switch (action) {
            case "1":
            case "view":
                System.out.println("Navigating to View Items Menu (not implemented yet)...");
//                uiController.pushMenuToStack(new ViewItemsMenu());
                break;
            case "2":
            case "settings":
                System.out.println("Navigating to Settings Menu (not implemented yet)...");
                 uiController.pushMenuToStack(new MenuSettings("settings", uiController));
                break;
            case "exit":
                uiController.exit();
                break;
            default:
                System.out.println("Unknown command: " + action);
                break;
        }
    }
}
