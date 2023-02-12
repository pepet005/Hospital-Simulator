package hospSim.UI;

import hospSim.Condition;
import hospSim.Main;
import hospSim.Patient;
import hospSim.enums.ConditionTopography;
import hospSim.enums.PatientCondition;

import javax.swing.*;

public class UINewPatient extends JPanel {

    private JTextField patientNameTextField;
    private JComboBox conditionComboBox;
    private JPanel mainPanel;
    private JButton registerPatientButton;
    private JComboBox topographyComboBox;
    private JButton exitButton;


    public UINewPatient() {
        loadConditions();
        loadTopographies();

        conditionComboBox.addActionListener(e -> loadTopographies());
        registerPatientButton.addActionListener(e -> registerPatient());
        exitButton.addActionListener(e -> UIMainFrame.changePanel(Main.mainFrame, new UIManagePatients().getPanel()));
    }

    /*
    Loads the possible conditions this hospital currently supports
     */
    private void loadConditions() {
        conditionComboBox.removeAllItems();
        for (PatientCondition condition : PatientCondition.values()){
            conditionComboBox.addItem(condition);
        }
    }

    /*
    Loads the possible topographies of the selected condition
    None for some conditions
     */
    private void loadTopographies() {
        topographyComboBox.removeAllItems();
        if (conditionComboBox.getSelectedItem() == PatientCondition.Cancer){
            for (ConditionTopography topography : ConditionTopography.values()){
                topographyComboBox.addItem(topography);
            }
        } else {
            topographyComboBox.addItem(null);
        }
    }

    /*
    Registers the patient to the hospital, including their condition
     */
    private void registerPatient() {
        if (patientNameTextField.getText().equals("")) {
            Main.mainFrame.message("New Patient must have a name.");
            return;
        }

        Condition patientsCondition = new Condition((PatientCondition) conditionComboBox.getSelectedItem(), (ConditionTopography) topographyComboBox.getSelectedItem());
        Patient newPatient = new Patient(patientNameTextField.getText(), patientsCondition);
        Main.hospital.addPatient(newPatient);
        Main.mainFrame.message("Added new patient: " + newPatient);
    }

    public JPanel getPanel() {
        return mainPanel;
    }

}