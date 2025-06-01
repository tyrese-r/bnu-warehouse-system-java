package dev.tbertie.warehousesystem.ui;

public class MenuSettings extends Menu {

    @Override
    public void render() {
        System.out.println("This is the settings menu");
    }

    @Override
    public void command(String[] command) {

    }

    public MenuSettings(String name, UIController uiController) {
        super(name, uiController);
    }
}
