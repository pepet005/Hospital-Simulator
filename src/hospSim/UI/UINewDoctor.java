package hospSim.UI;

import hospSim.Doctor;
import hospSim.Main;
import hospSim.enums.DoctorRole;

import javax.swing.*;
import java.awt.*;

public class UINewDoctor extends JPanel {

    private JTextField doctorNameTextField;
    private JPanel mainPanel;
    private JPanel rolesPanel;
    private JButton registerDoctorButton;
    private JButton exitButton;
    private JCheckBox[] roleBoxes;

    public UINewDoctor() {
        this.roleBoxes = new JCheckBox[DoctorRole.values().length];

        // Layout for panel where checkboxes for roles is listed
        GridLayout roleLayout = new GridLayout(0, 1);
        rolesPanel.setLayout(roleLayout);

        loadRoles();
        exitButton.addActionListener(e -> UIMainFrame.changePanel(Main.mainFrame, (new UIManageDoctors().getPanel())));
        registerDoctorButton.addActionListener(e -> registerDoctor());
    }

    /*
    Registers a new doctor to the hospital
     */
    private void registerDoctor() {
        if (doctorNameTextField.getText().equals("")) {
            Main.mainFrame.message("New Doctor must have a name");
            return;
        }

        // Finds how many of the boxes that are checked
        int nrCheckedBoxes = 0;
        for (int i = 0; i < roleBoxes.length; i++) {
            if (roleBoxes[i].isSelected()) {
                nrCheckedBoxes++;
            }
        }
        // At least one of the boxes must be checked
        if (nrCheckedBoxes == 0) {
            Main.mainFrame.message("New Doctor must have a role.");
            return;
        }

        // Puts the checked roles into an array
        DoctorRole[] roles = new DoctorRole[nrCheckedBoxes];
        int rolesAssigned = 0;
        for (int i = 0; i < roleBoxes.length; i++) {
            if (roleBoxes[i].isSelected()) {
                roles[rolesAssigned] = getDoctorRoleByString(roleBoxes[i].getText());
                rolesAssigned++;
            }
        }

        // Creates the new doctor and registers them to the hospital
        Doctor newDoctor = new Doctor(doctorNameTextField.getText(), roles);
        Main.hospital.addDoctor(newDoctor);
        Main.mainFrame.message("Added new Doctor: " + newDoctor.toString());
    }

    /*
    Adds checkboxes of the different possible roles for a doctor
     */
    private void loadRoles() {
        for (int i = 0; i < DoctorRole.values().length; i++) {
            roleBoxes[i] = new JCheckBox(DoctorRole.values()[i].toString());
        }

        for (JCheckBox aBox : roleBoxes){
            rolesPanel.add(aBox);
        }
    }

    /*
    Returns a doctor role (enum value) by its name
     */
    private DoctorRole getDoctorRoleByString(String str) {
        for (DoctorRole doctorRole : DoctorRole.values()) {
            if (str.equals(doctorRole.toString())) {
                return doctorRole;
            }
        }
        return null;
    }

    public JPanel getPanel() {
        return mainPanel;
    }

}
