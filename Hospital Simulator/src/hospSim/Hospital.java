package hospSim;

import java.time.LocalDate;
import java.util.ArrayList;

public class Hospital {
	private ArrayList<Patient> patients;
	private ArrayList<Doctor> doctors;
	private ArrayList<TreatmentRoom> treatmentRooms;
	private ArrayList<TreatmentMachine> treatmentMachines;
	private ArrayList<Consultation> consultations, archivedConsultations;
	private LocalDate currentDate;
	private final LocalDate establishedDate;

	public Hospital(){
		patients = new ArrayList<>();
		doctors = new ArrayList<>();
		treatmentRooms = new ArrayList<>();
		treatmentMachines = new ArrayList<>();
		consultations = new ArrayList<>();
		archivedConsultations = new ArrayList<>();
		currentDate = LocalDate.now();
		establishedDate = LocalDate.now();
	}

	/*
    Moves consultations to archive the day after they've been carried out
    */
	public void performConsultations() {
		ArrayList<Consultation> consultationsToArchive = new ArrayList<>();
		// Finds which consultations to archive
		for (Consultation consultation : consultations){
			if (consultation.getConsultationDate().isBefore(currentDate)) {
				consultationsToArchive.add(consultation);
			}
		}
		consultationsToArchive.forEach(consultations::remove); // Removes them from current consultation arrayList
		archivedConsultations.addAll(consultationsToArchive); // Adds them to archive
		consultationsToArchive.forEach(e -> e.archiveConsultation()); // Archives the references to the consultation in Patient, Doctor, and TreatmentRoom
	}

	/*
	Proceeds to the next day
	 */
	public void nextDay() {
		currentDate = currentDate.plusDays(1);
		performConsultations();
	}

	// ------------------------------ set-, get-, & add-methods ------------------------------
	public ArrayList<Patient> getPatients() {
		return patients;
	}

	public void addPatient(Patient newPatient) {
		patients.add(newPatient);
	}

	public void addPatients(ArrayList<Patient> newPatients) {
		patients.addAll(newPatients);
	}

	// Do not want to be able to directly set patients.

	public ArrayList<Doctor> getDoctors() {
		return doctors;
	}

	// Do not want to be able to directly set doctors
	public void addDoctors(ArrayList<Doctor> newDoctors) {
		doctors.addAll(newDoctors);
	}

	public void addDoctor(Doctor newDoctor) {
		doctors.add(newDoctor);
	}

	public ArrayList<TreatmentRoom> getTreatmentRooms() {
		return treatmentRooms;
	}

	// Do not want to be able to directly set TreatmentRooms
	public void addTreatmentRooms(ArrayList<TreatmentRoom> newTreatmentRooms) {
		this.treatmentRooms.addAll(newTreatmentRooms);
	}

	public void addTreatmentRoom(TreatmentRoom newTreatmentRoom) {
		this.treatmentRooms.add(newTreatmentRoom);
	}

	public ArrayList<TreatmentMachine> getTreatmentMachines() {
		return treatmentMachines;
	}

	public void addTreatmentMachines(ArrayList<TreatmentMachine> newTreatmentMachines) {
		this.treatmentMachines.addAll(newTreatmentMachines);
	}

	public void addTreatmentMachine(TreatmentMachine newTreatmentMachine) {
		this.treatmentMachines.add(newTreatmentMachine);
	}


	// Do not want to be able to directly set consultations or archived consultations
	public void addConsultation(Consultation consultation) {
		consultations.add(consultation);
	}
	public ArrayList<Consultation> getConsultations() {
		return consultations;
	}

	public ArrayList<Consultation> getArchivedConsultations() {
		return archivedConsultations;
	}

	public LocalDate getCurrentDate() {
		return currentDate;
	}

	public int getDaysSinceStart() {
		return (int) (currentDate.toEpochDay() - establishedDate.toEpochDay());
	}

	// Set functions for currentDate is not included
	// since this hospital isn't advanced enough for time travel other than proceeding to tomorrow
}
