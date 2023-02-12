package hospSim.utils;

import hospSim.*;
import hospSim.enums.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class SetupResourcesJSON {

    /*
     * Reads a JSON file of doctors. Creates an arrayList containing Doctor objects.
     */
    public static ArrayList<Doctor> readDoctors(){
        ArrayList<Doctor> doctors = new ArrayList<>();
        // Reads data from a file and converts it to a JSONArray
        JSONArray jsonData = readDataFromFile("./config/Doctors.json");

        for (Object doctorObject : jsonData){
            // Casts object to JSONObject, so that info can be gotten using keys
            JSONObject jsonDoctor = (JSONObject) doctorObject;
            String name = (String) jsonDoctor.get("name");

            // Converts JSONArray of roles into String[]
            JSONArray jsonDoctorRoles = (JSONArray) jsonDoctor.get("roles");
            DoctorRole[] roles = new DoctorRole[jsonDoctorRoles.length()];

            // Error handling for doctor roles.
            // Bad role in JSON file is called out.
            try {
                for (int i = 0; i < jsonDoctorRoles.length(); i++){
                    roles[i] = DoctorRole.valueOf((String) jsonDoctorRoles.get(i));
                }
                doctors.add(new Doctor(name, roles));
            } catch (IllegalArgumentException e) {
                System.out.println(e);
                System.out.println("DOCTOR WITH NAME " + name + " HAS ILLEGAL ROLE");
            }
        }

        return doctors;
    }

    /*
     * Reads a JSON file of patients. Creates an arrayList containing Patient objects.
     */
    public static ArrayList<Patient> readPatients(){
        ArrayList<Patient> patients = new ArrayList<>();
        // Reads data from a file and converts it to a JSONArray
        JSONArray jsonData = readDataFromFile("./config/patients.json");

        for (Object patientObject : jsonData){
            // Casts object to JSONObject, so that info can be gotten using keys
            JSONObject jsonPatient = (JSONObject) patientObject;
            String name = (String) jsonPatient.get("name");

            // Error handling for patient conditions.
            // Bad condition or topography in JSON file is called out.
            try{
                PatientCondition condition = PatientCondition.valueOf((String) jsonPatient.get("condition"));

                // If the patients has a condition with a topography, include that topography
                ConditionTopography topography = null;
                if (jsonPatient.has("topography")){
                    String strTopography = (String) jsonPatient.get("topography");
                    if (strTopography.equals("Head & Neck"))
                        strTopography = "HeadAndNeck";
                    topography = ConditionTopography.valueOf(strTopography);
                }
                patients.add(new Patient(name, new Condition(condition, topography)));
            } catch (IllegalArgumentException e){
                System.out.println(e);
                System.out.println("PATIENT WITH NAME " + name + " HAS ILLEGAL CONDITION OR TOPOGRAPHY");
            }
        }

        return patients;
    }

    /*
    * Reads a JSON file of treatment machines. Creates an arrayList containing TreatmentMachine objects.
     */
    public static ArrayList<TreatmentMachine> readTreatmentMachines() {
        ArrayList<TreatmentMachine> treatmentMachines = new ArrayList<>();
        // Reads data from a file and converts it to a JSONArray
        JSONArray jsonData = readDataFromFile("./config/Treatmentmachines.json");

        for (Object treatmentMachineObject : jsonData){
            // Casts object to JSONObject, so that info can be gotten using keys
            JSONObject jsonTreatmentMachine = (JSONObject) treatmentMachineObject;
            String name = (String) jsonTreatmentMachine.get("name");

            // Error handling for machine capability.
            // Bad capability in JSON file is called out.
            try{
                MachineCapability capability = MachineCapability.valueOf((String) jsonTreatmentMachine.get("capability"));
                treatmentMachines.add(new TreatmentMachine(name, capability));
            } catch (IllegalArgumentException e){
                System.out.println(e);
                System.out.println("MACHINE WITH NAME " + name + " HAS ILLEGAL CAPABILITY");
            }
        }

        return treatmentMachines;
    }

    /*
    Reads a JSON file of treatmentrooms and creates an arrayList containing TreatmentRoom objects.
     */
    public static ArrayList<TreatmentRoom> readTreatmentRooms(ArrayList<TreatmentMachine> availableMachines) {
        ArrayList<TreatmentRoom> treatmentRooms = new ArrayList<>();
        // Reads data from a file and converts it to a JSONArray
        JSONArray jsonData = readDataFromFile("./config/Treatmentrooms.json");

        for (Object treatmentRoomObject : jsonData){
            // Casts object to JSONObject, so that info can be gotten using keys
            JSONObject jsonTreatmentRoom = (JSONObject) treatmentRoomObject;
            String name = (String) jsonTreatmentRoom.get("name");

            TreatmentRoom theTreatmentRoom = new TreatmentRoom(name);

            // If the TreatmentRoom has a machine, attempts to find that machine and add it to the room
            if (jsonTreatmentRoom.has("treatmentMachine")){
                TreatmentMachine theMachine = getTreatmentMachineByName((String) jsonTreatmentRoom.get("treatmentMachine"), availableMachines);
                theTreatmentRoom.setTreatmentMachine(theMachine);
            }

            treatmentRooms.add(theTreatmentRoom);
        }

        return treatmentRooms;
    }

    /*
    Gets a treatment machine by name, returns null if there is none with the specified name
     */
    private static TreatmentMachine getTreatmentMachineByName(String name, ArrayList<TreatmentMachine> treatmentMachines){
        TreatmentMachine theMachine = null;
        for (TreatmentMachine treatmentMachine : treatmentMachines){
            if (treatmentMachine.getName().equals(name)){
                theMachine = treatmentMachine;
                break;
            }
        }
        return theMachine;
    }

    /*
    Reads JSON data from a file, returns the data as a JSONArray
     */
    private static JSONArray readDataFromFile(String path){
        String data;
        Path filePath = Path.of(path);
        try {
            data = Files.readString(filePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return new JSONArray(data);
    }

}