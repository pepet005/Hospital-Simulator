package hospSim;

import hospSim.UI.UIMainFrame;
import hospSim.utils.SetupResourcesJSON;

import javax.swing.*;
import java.util.ArrayList;

// Dependent on library "JSON in Java".
// Is included in the project, but if any error occurs: https://github.com/stleary/JSON-java
public class Main {

    public static Hospital hospital;
    public static UIMainFrame mainFrame;

    public static void main(String[] args){
        setupResources();
        mainFrame = new UIMainFrame();
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.message("Hello and welcome to my hospital");
    }

    /*
    Sets up resources for the hospital from JSON-files
     */
    private static void setupResources(){
        hospital = new Hospital();
        hospital.addDoctors(SetupResourcesJSON.readDoctors());
        ArrayList<TreatmentMachine> treatmentMachines = SetupResourcesJSON.readTreatmentMachines();
        hospital.addTreatmentMachines(treatmentMachines);
        hospital.addTreatmentRooms(SetupResourcesJSON.readTreatmentRooms(treatmentMachines));
        hospital.addPatients(SetupResourcesJSON.readPatients());
    }

}
