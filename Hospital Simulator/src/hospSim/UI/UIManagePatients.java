package hospSim.UI;

import hospSim.Consultation;
import hospSim.Main;
import hospSim.Patient;
import hospSim.utils.ConsultationScheduler;
import hospSim.utils.UIUtils;

import javax.swing.*;
import java.util.ArrayList;

public class UIManagePatients extends JPanel {
    private JPanel mainPanel;
    private JButton scheduleConsultationButton;
    private JButton newPatientButton;
    private JComboBox patientComboBox;
    private JPanel centerPanel;
    private JPanel patientDataPanel;
    private JPanel possibleConsultationsPanel;
    private JButton saveConsultationButton;
    private JList consultationList;
    private JLabel patientNameLabel;
    private JLabel patientConditionLabel;
    private JLabel consultationsLabel;

    public UIManagePatients() {
        fillPatientsComboBox();

        // Adds action listeners to buttons
        scheduleConsultationButton.addActionListener(e -> listPossibleConsultations());
        newPatientButton.addActionListener(e -> UIMainFrame.changePanel(Main.mainFrame, (new UINewPatient().getPanel())));
        patientComboBox.addActionListener(e -> listPatientData());
        saveConsultationButton.addActionListener(e -> saveConsultation());

        // Work-around to design 2 possible center panels while using the UI-designer
        centerPanel.removeAll();
        listPatientData();
    }

    /*
    Fill the combo box with all the patients registered to the hospital
     */
    private void fillPatientsComboBox() {
        patientComboBox.removeAllItems();
        for (Patient patient : Main.hospital.getPatients()){
            patientComboBox.addItem(patient);
        }
    }

    /*
    Lists the data of the patient selected in the combo box using labels
     */
    private void listPatientData() {
        centerPanel.removeAll();
        Patient patient = (Patient) patientComboBox.getSelectedItem();
        if (patient == null)
            return;

        // Patient's name and condition
        patientNameLabel.setText(patient.getName());
        patientConditionLabel.setText(patient.getCondition().getConditionType().toString());
        if (patient.getCondition().getTopography() != null) {
            // multiline label if patient's condition has a topography
            patientConditionLabel.setText("<html>" + patientConditionLabel.getText() + "<br/>" +
                    "Topography: " + patient.getCondition().getTopography().toString());
        }

        // Patients' booked consultations are listed, expected to be multiline
        consultationsLabel.setText("<html>");
        boolean firstLine = true;
        for (Consultation consultation : patient.getConsultations()) {
            String newLine = "";
            if (!firstLine)
                newLine = "<br/>";
            newLine += consultation.getConsultationDate().toString() + ", Doctor: " + consultation.getDoctor().getName() + ", Room: " + consultation.getTreatmentRoom().getName();

            consultationsLabel.setText(consultationsLabel.getText() + newLine);
            firstLine = false;
        }
        if (consultationsLabel.getText().equals("<html>"))
            consultationsLabel.setText("None");

        // Adds the panel to the center of the frame
        centerPanel.add(patientDataPanel);
        UIUtils.updateUI();
    }

    /*
    Finds possible consultations for the patient and lists them
     */
    private void listPossibleConsultations() {
        centerPanel.removeAll();

        if (patientComboBox.getSelectedItem() == null) {
            Main.mainFrame.message("A patient must be selected.");
            return;
        }

        // Finds 10 possible consultations within the next 30 days and adds them to the list
        ArrayList<Consultation> consultations = ConsultationScheduler.findConsultations((Patient) patientComboBox.getSelectedItem(), Main.hospital);
        consultationList.setListData(consultations.toArray());

        // Adds the panel to the center of the frame
        centerPanel.add(possibleConsultationsPanel);
        UIUtils.updateUI();
    }

    /*
    Books a consultation selected from the list of possible consultations
     */
    private void saveConsultation() {
        if (consultationList.getSelectedValue() == null)
            return;
        Consultation consultation = (Consultation) consultationList.getSelectedValue();
        // Adds a reference to the consultation where it's useful
        consultation.saveConsultation();

        listPatientData();
    }

    public JPanel getPanel(){
        return mainPanel;
    }
}
