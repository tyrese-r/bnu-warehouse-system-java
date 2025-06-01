package dev.tbertie.warehousesystem.ui;

public class MenuSettings extends Menu {

    @Override
    public void render() {
        System.out.println("This is the settings menu");
    }

    @Override
    public void command(String[] command) {

    }

    public MenuSettings(UIController uiController) {
        super(uiController);
    }
}
