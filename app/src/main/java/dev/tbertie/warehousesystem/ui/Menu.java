package dev.tbertie.warehousesystem.ui;

import lombok.Getter;

public abstract class Menu {

    protected final UIController uiController;

    @Getter
    protected final String name;

    protected Menu(String name, UIController uiController) {
        if(uiController == null) {
            throw new IllegalArgumentException("UIController must be set");
        }

        this.uiController = uiController;
        this.name = name;
    }

    public abstract void render();

    public abstract void command(String[] command);

}
