package dev.tbertie.warehousesystem.ui;

import dev.tbertie.warehousesystem.ui.customer.MenuCustomer;

import java.util.List;

public class MenuMain extends Menu {



    public MenuMain(String name, UIController uiController) {
        super(name, uiController);
    }


    @Override
    public void render() {
        String menuString = UIUtils.generateMenu("Main Menu", List.of(
                "Customers",
                "Inventory",
                "Orders",
                "Suppliers",
                "Reports"
        ));

        System.out.println(menuString);
    }

    @Override
    public void command(String[] command) {

        if (command.length == 0) return;

        String action = command[0].toLowerCase();
        // Do something here
        switch (action) {
            case "1":
            case "customers":
                uiController.pushMenuToStack(new MenuCustomer("customer", uiController));
                break;
            case "2":
            case "inventory":
                System.out.println("Navigating to Settings Menu (not implemented yet)...");
                uiController.pushMenuToStack(new MenuSettings("settings", uiController));
                break;
            case "orders":
                uiController.exit();
                break;
            case "suppliers":
                uiController.exit();
                break;
            case "reports":
                uiController.exit();
                break;
            default:
                System.out.println("Unknown command: " + action);
                break;
        }
    }
}
