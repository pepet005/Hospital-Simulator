package hospSim.UI;

import hospSim.Main;
import hospSim.TreatmentMachine;
import hospSim.TreatmentRoom;

import javax.swing.*;

public class UINewRoom extends JPanel {
    private JPanel mainPanel;
    private JButton backButton;
    private JButton addRoomButton;
    private JTextField roomNameTextField;
    private JComboBox treatmentMachineComboBox;

    public UINewRoom() {
        loadTreatmentMachines();
        addRoomButton.addActionListener(e -> addRoom());
        backButton.addActionListener(e -> UIMainFrame.changePanel(Main.mainFrame, new UIManageRooms().getPanel()));
    }

    /*
    Loads all treatment machines not currently in use in another room, to the combo box
     */
    private void loadTreatmentMachines() {
        treatmentMachineComboBox.removeAllItems();
        for (TreatmentMachine treatmentMachine : Main.hospital.getTreatmentMachines()) {
            // For a given machine, checks each room to see if it's in use or not
            // (would perhaps be useful to have a reference in the machine of which room it belongs to (or null)
            // instead of checking each room for it)
            boolean machineIsAvailable = true;
            for (TreatmentRoom treatmentRoom : Main.hospital.getTreatmentRooms()) {
                if (treatmentRoom.getTreatmentMachine() == treatmentMachine) {
                    machineIsAvailable = false;
                    break;
                }
            }
            if (machineIsAvailable)
                treatmentMachineComboBox.addItem(treatmentMachine);
        }
    }

    /*
    Adds a new treatment room to the hospital
     */
    private void addRoom() {
        if (roomNameTextField.getText().equals("")) {
            Main.mainFrame.message("New Room must have a name.");
            return;
        }

        TreatmentRoom newTreatmentRoom = new TreatmentRoom(roomNameTextField.getText(), (TreatmentMachine) treatmentMachineComboBox.getSelectedItem());
        Main.hospital.addTreatmentRoom(newTreatmentRoom);
        Main.mainFrame.message("Added new room: " + newTreatmentRoom);
    }

    public JPanel getPanel() {
        return mainPanel;
    }
}
