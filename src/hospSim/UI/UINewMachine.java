package hospSim.UI;

import hospSim.Main;
import hospSim.TreatmentMachine;
import hospSim.enums.MachineCapability;

import javax.swing.*;

public class UINewMachine extends JPanel {
    private JComboBox capabilityComboBox;
    private JButton backButton;
    private JButton addTreatmentMachineButton;
    private JTextField machineNameTextField;
    private JPanel mainPanel;

    public UINewMachine() {
        loadCapabilities();

        addTreatmentMachineButton.addActionListener(e -> addMachine());
        backButton.addActionListener(e -> UIMainFrame.changePanel(Main.mainFrame, new UIManageMachines().getPanel()));
    }

    /*
    Adds the possible capabilities of a treatment machine to the combo box
     */
    private void loadCapabilities() {
        capabilityComboBox.removeAllItems();
        for (MachineCapability machineCapability : MachineCapability.values()) {
            capabilityComboBox.addItem(machineCapability);
        }
    }

    /*
    Adds the machine to the hospital
     */
    private void addMachine() {
        if (machineNameTextField.getText().equals("")) {
            Main.mainFrame.message("New Machine must have a name.");
            return;
        }

        TreatmentMachine newTreatmentMachine = new TreatmentMachine(machineNameTextField.getText(), (MachineCapability) capabilityComboBox.getSelectedItem());
        Main.hospital.addTreatmentMachine(newTreatmentMachine);
        Main.mainFrame.message("Added new machine: " + newTreatmentMachine);
    }

    public JPanel getPanel() {
        return mainPanel;
    }
}
