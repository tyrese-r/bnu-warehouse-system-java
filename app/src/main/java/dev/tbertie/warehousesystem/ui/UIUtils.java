package dev.tbertie.warehousesystem.ui;

import java.util.List;

public class UIUtils {
    public static String generateMenu(String title, List<String> options) {
        StringBuilder sb = new StringBuilder();
        sb.append("\n--- ").append(title).append(" ---\n");

        for (int i = 0; i < options.size(); i++) {
            sb.append(i + 1).append(". ").append(options.get(i)).append("\n");
        }

        sb.append("Other: exit, back\n");
        sb.append("------------------");

        return sb.toString();
    }
}
