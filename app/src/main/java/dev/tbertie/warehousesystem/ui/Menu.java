package dev.tbertie.warehousesystem.ui;

public abstract class Menu {

    protected final UIController uiController;

    String name = "";

    protected Menu(UIController uiController) {
        if(uiController == null) {
            throw new IllegalArgumentException("UIController must be set");
        }

        this.uiController = uiController;
    }

    public abstract void render();

    public abstract void command(String[] command);

}
