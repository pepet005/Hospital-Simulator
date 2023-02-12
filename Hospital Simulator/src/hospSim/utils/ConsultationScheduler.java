package hospSim.utils;

import hospSim.*;
import hospSim.enums.DoctorRole;
import hospSim.enums.PatientCondition;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

public class ConsultationScheduler {

    /*
    Given the patient and their condition (& topography), finds up to 10 possible consultations with the hospital
     */
    public static ArrayList<Consultation> findConsultations(Patient patient, Hospital hospital) {

        // A consultation may not be the same day as it's booked
        LocalDate searchDate = hospital.getCurrentDate().plusDays(1);
        TreatmentRoom theTreatmentRoom = null;
        Doctor theDoctor = null;
        ArrayList<Consultation> consultations = new ArrayList<>();

        // Looks a maximum of 30 days into the future, returning the first 10 consultations, all at different dates
        while(searchDate.toEpochDay() - hospital.getCurrentDate().toEpochDay() <= 30) {

            // The patient may not already be booked for a consultation
            if (patient.getConsultation(searchDate) == null) {
                // Looks for first available treatment room with minimum equipment
                for (TreatmentRoom aTreatmentRoom : hospital.getTreatmentRooms()) {
                    if (validateRoom(aTreatmentRoom, patient, searchDate)) {
                        theTreatmentRoom = aTreatmentRoom;
                        break;
                    }
                }

                // Looks for first available doctor with correct role
                for (Doctor aDoctor : hospital.getDoctors()) {
                    if (validateDoctor(aDoctor, patient, searchDate)) {
                        theDoctor = aDoctor;
                        break;
                    }
                }

                // Creates a new consultation if there is an available room & doctor
                if ((theTreatmentRoom != null) && (theDoctor != null)) {
                    consultations.add(new Consultation(patient, theDoctor, theTreatmentRoom, hospital.getCurrentDate(), searchDate));
                    // Stop looking if 10 consultations have been found
                    if (consultations.size() >= 10)
                        break;
                }
            }

            // To search for a new consultation at the next date
            searchDate = searchDate.plusDays(1);
            theTreatmentRoom = null;
            theDoctor = null;
        }

        return consultations;
    }

    /*
    Checks whether a room is available at the given date and has the correct equipment for the patient's condition
     */
    private static boolean validateRoom(TreatmentRoom treatmentRoom, Patient patient, LocalDate searchDate) {
        if (treatmentRoom.getConsultation(searchDate) != null)
            return false;

        // A patient with cancer needs a treatment machine
        if (patient.getCondition().getConditionType() == PatientCondition.Cancer) {
            if (treatmentRoom.getTreatmentMachine() == null)
                return false;

            // Checks that the machine has the right capability for the topography
            if (!hasCorrectCapability(treatmentRoom.getTreatmentMachine(), patient))
                return false;
        }

        return true;
    }

    /*
    Checks whether the machine has the right capability for the topography of the patient's cancer
     */
    private static boolean hasCorrectCapability(TreatmentMachine treatmentMachine, Patient patient) {
        if (Arrays.asList(patient.getCondition().getTopography().getCorrectMachineCapabilities()).contains(treatmentMachine.getCapability()))
            return true;
        return false;
    }

    /*
    Checks whether a doctor is available at the given date and has the right role for the patient's condition
     */
    private static boolean validateDoctor(Doctor doctor, Patient patient, LocalDate searchDate) {
        if (doctor.getConsultation(searchDate) != null)
            return false;

        if (!hasCorrectRole(doctor, patient))
            return false;

        return true;
    }

    /*
    Checks whether the doctor has the correct role that is required by the patient's condition
     */
    private static boolean hasCorrectRole(Doctor doctor, Patient patient) {
        for (DoctorRole doctorRole : doctor.getRoles()) {
            if (Arrays.asList(patient.getCondition().getConditionType().getCorrectRoles()).contains(doctorRole)){
                return true;
            }
        }
        return false;
    }
}
