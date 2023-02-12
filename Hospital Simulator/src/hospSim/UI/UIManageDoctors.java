package hospSim.UI;

import hospSim.Doctor;
import hospSim.Main;

import javax.swing.*;

public class UIManageDoctors extends JPanel {
    private JPanel mainPanel;
    private JComboBox doctorsComboBox;
    private JButton newDoctorButton;

    public UIManageDoctors() {
        fillDoctorsComboBox();
        newDoctorButton.addActionListener(e -> UIMainFrame.changePanel(Main.mainFrame, new UINewDoctor().getPanel()));
    }

    /*
    Adds all doctors in the hospital to the combo box.
     */
    private void fillDoctorsComboBox() {
        doctorsComboBox.removeAllItems();
        for (Doctor doctor : Main.hospital.getDoctors()) {
            doctorsComboBox.addItem(doctor);
        }
    }

    public JPanel getPanel(){
        return mainPanel;
    }

}
