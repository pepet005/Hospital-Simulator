package hospSim;

import java.time.LocalDate;

public class Consultation {

	private Patient patient;
	private Doctor doctor;
	private final LocalDate registrationDate;
	private LocalDate consultationDate;
	private TreatmentRoom treatmentRoom;
	
	public Consultation(Patient patient, Doctor doctor, TreatmentRoom treatmentRoom, LocalDate registrationDate, LocalDate consultationDate) {
		this.patient = patient;
		this.doctor = doctor;
		this.treatmentRoom = treatmentRoom;
		this.registrationDate = registrationDate;
		this.consultationDate = consultationDate;

	}

	/*
	Adds a reference to this consultation to the patient, doctor, room, and hospital for easy access
	 */
	public void saveConsultation() {
		patient.addConsultation(this);
		doctor.addConsultation(this);
		treatmentRoom.addConsultation(this);
		Main.hospital.addConsultation(this);
	}

	/*
	Moves this consultation into archive for the patient, doctor, room.
	Hospital reference could be handled here as well, but right now it isn't
	 */
	public void archiveConsultation() {
		patient.archiveConsultation(this);
		doctor.archiveConsultation(this);
		treatmentRoom.archiveConsultation(this);
	}


	// -------------- Set- & get-methods ----------------
	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public LocalDate getConsultationDate() {
		return consultationDate;
	}

	public void setConsultationDate(LocalDate consultationDate) {
		this.consultationDate = consultationDate;
	}

	public TreatmentRoom getTreatmentRoom() {
		return treatmentRoom;
	}

	public void setTreatmentRoom(TreatmentRoom treatmentRoom) {
		this.treatmentRoom = treatmentRoom;
	}

	public LocalDate getRegistrationDate() {
		return registrationDate;
	}

	@Override
	public String toString(){
		return "Doctor: " + doctor.getName() + " Room: " + treatmentRoom.getName() + " Date: " + consultationDate.toString();
	}
	
}
