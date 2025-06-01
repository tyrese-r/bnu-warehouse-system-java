package dev.tbertie.warehousesystem.ui;

import dev.tbertie.warehousesystem.ui.customer.MenuCustomer;
import dev.tbertie.warehousesystem.ui.supplier.MenuSupplier;
import dev.tbertie.warehousesystem.ui.inventory.MenuInventory;

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
                uiController.pushMenuToStack(new MenuInventory("inventory", uiController));
                break;
            case "3":
            case "orders":
                System.out.println("Orders menu");
                break;
            case "4":
            case "suppliers":
                uiController.pushMenuToStack(new MenuSupplier("suppliers", uiController));
                break;
            case "5":
            case "reports":
                System.out.println("Reports menu");
                break;
            default:
                System.out.println("Unknown command: " + action);
                break;
        }
    }
}
