package hospSim.UI;

import hospSim.Main;
import hospSim.TreatmentMachine;

import javax.swing.*;

public class UIManageMachines extends JPanel {
    private JPanel mainPanel;
    private JButton newTreatmentMachineButton;
    private JComboBox treatmentMachineComboBox;

    public UIManageMachines() {
        fillMachinesComboBox();
        newTreatmentMachineButton.addActionListener(e -> UIMainFrame.changePanel(Main.mainFrame, new UINewMachine().getPanel()));
    }

    /*
    Fills combo box with the hospital's treatment machines
     */
    private void fillMachinesComboBox() {
        treatmentMachineComboBox.removeAllItems();
        for (TreatmentMachine treatmentMachine : Main.hospital.getTreatmentMachines()) {
            treatmentMachineComboBox.addItem(treatmentMachine);
        }
    }

    public JPanel getPanel() {
        return mainPanel;
    }
}
