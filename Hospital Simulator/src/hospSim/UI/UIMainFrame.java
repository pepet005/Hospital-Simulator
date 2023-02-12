package hospSim.UI;

import hospSim.Main;
import hospSim.utils.UIUtils;

import javax.swing.*;
import java.awt.*;

public class UIMainFrame extends JFrame {
    private JPanel mainPanel;
    private JButton nextDayButton;
    private JButton managePatientsButton;
    private JButton manageDoctorsButton;
    private JButton manageRoomsButton;
    private JTextArea infoTextArea;
    private JButton manageConsultationsButton; // No functionality yet
    private JPanel centerPanel;
    private JButton manageMachinesButton;

    public UIMainFrame() {
        setTitle("Test");
        setVisible(true);
        setContentPane(mainPanel);
        setSize(810,500);
        setMinimumSize(new Dimension(810, 500));

        // Set action listeners for top-row buttons
        nextDayButton.addActionListener(e -> nextDay());
        managePatientsButton.addActionListener(e -> UIMainFrame.changePanel(Main.mainFrame, new UIManagePatients().getPanel()));
        manageDoctorsButton.addActionListener(e -> UIMainFrame.changePanel(Main.mainFrame, new UIManageDoctors().getPanel()));
        manageRoomsButton.addActionListener(e -> UIMainFrame.changePanel(Main.mainFrame, new UIManageRooms().getPanel()));
        manageMachinesButton.addActionListener(e -> UIMainFrame.changePanel(Main.mainFrame, new UIManageMachines().getPanel()));
    }

    public JPanel getCenterPanel(){
        return centerPanel;
    }

    /*
    Proceeds to the next day at the hospital.
    Moves consultations into archive if they're now in the past.
     */
    public void nextDay() {
        Main.hospital.nextDay();
        UIUtils.updateUI();
    }

    /*
    Pushes a message to the text field at the bottom of the frame
     */
    public void message(String newMessage){
        if (!infoTextArea.getText().equals(""))
            infoTextArea.append("\n\n");
        infoTextArea.append(newMessage);
    }

    /*
    Changes the center panel
     */
    public static void changePanel(UIMainFrame mainFrame, JPanel newPanel) {
        JPanel centerPanel = mainFrame.getCenterPanel();
        centerPanel.removeAll();
        centerPanel.add(newPanel, BorderLayout.CENTER);
        newPanel.setVisible(true);
        UIUtils.updateUI();
    }
}
