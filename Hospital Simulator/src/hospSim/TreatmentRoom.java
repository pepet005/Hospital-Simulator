package hospSim;

import java.time.LocalDate;
import java.util.ArrayList;

public class TreatmentRoom {

	private String name;
	private TreatmentMachine treatmentMachine;
	private ArrayList<Consultation> consultations;
	private ArrayList<Consultation> archivedConsultations;
	
	public TreatmentRoom(String name, TreatmentMachine treatmentMachine) {
		this.name = name;
		this.treatmentMachine = treatmentMachine;
		this.consultations = new ArrayList<>();
		this.archivedConsultations = new ArrayList<>();
	}

	public TreatmentRoom(String name) {
		this(name, null);
	}

	public String getName() {
		return name;
	}

	public TreatmentMachine getTreatmentMachine() {
		return treatmentMachine;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setTreatmentMachine(TreatmentMachine treatmentMachine) {
		this.treatmentMachine = treatmentMachine;
	}

	public void addConsultation(Consultation newConsultation) {
		consultations.add(newConsultation);
	}

	/*
	Archives the given consultation
	 */
	public void archiveConsultation(Consultation consultation) {
		consultations.remove(consultation);
		archivedConsultations.add(consultation);
	}

	/*
	Returns any consultation booked in the room at the given date. null if there is none
	 */
	public Consultation getConsultation(LocalDate date){
		Consultation theConsultation = null;
		for (Consultation aConsultation : consultations){
			if (aConsultation.getConsultationDate().equals(date))
				theConsultation = aConsultation;
		}
		return theConsultation;
	}

	@Override
	public String toString() {
		return name + " " + treatmentMachine;
	}
}