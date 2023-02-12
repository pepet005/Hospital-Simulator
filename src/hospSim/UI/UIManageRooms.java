package hospSim.UI;

import hospSim.Main;
import hospSim.TreatmentRoom;

import javax.swing.*;

public class UIManageRooms extends JPanel {
    private JPanel mainPanel;
    private JComboBox treatmentRoomComboBox;
    private JButton newRoomButton;

    public UIManageRooms() {
        fillRoomsComboBox();
        newRoomButton.addActionListener(e -> UIMainFrame.changePanel(Main.mainFrame, new UINewRoom().getPanel()));
    }

    /*
    Adds the rooms of the hospital to the combo box
     */
    private void fillRoomsComboBox() {
        treatmentRoomComboBox.removeAllItems();
        for (TreatmentRoom treatmentRoom : Main.hospital.getTreatmentRooms()) {
            treatmentRoomComboBox.addItem(treatmentRoom);
        }
    }

    public JPanel getPanel() {
        return mainPanel;
    }
}
