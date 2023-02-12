package hospSim.utils;

import hospSim.Main;

import javax.swing.*;

public class UIUtils {

    /*
    Reloads the UI
     */
    public static void updateUI() {
        SwingUtilities.updateComponentTreeUI(Main.mainFrame.getCenterPanel());
    }

}
