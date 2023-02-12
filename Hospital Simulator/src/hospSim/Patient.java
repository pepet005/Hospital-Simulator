package hospSim;

import java.time.LocalDate;
import java.util.ArrayList;

public class Patient {

	private String name;
	private Condition condition;
	private ArrayList<Consultation> consultations;
	private ArrayList<Consultation> archivedConsultations;


	public Patient(String name, Condition condition) {
		this.name = name;
		this.condition = condition;
		this.consultations = new ArrayList<>();
		this.archivedConsultations = new ArrayList<>();
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public Condition getCondition() {
		return condition;
	}

	public void setCondition(Condition condition) {
		this.condition = condition;
	}

	public void addConsultation(Consultation newConsultation) {
		consultations.add(newConsultation);
	}

	public void archiveConsultation(Consultation consultation) {
		consultations.remove(consultation);
		archivedConsultations.add(consultation);
	}

	public ArrayList<Consultation> getConsultations() {
		return consultations;
	}

	/*
	Returns any consultation the patient has at the given date. null if there is none
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
	public String toString(){
		String theStr = name + ", " + condition.toString() + ". Consultations:";
		if (consultations.size() == 0)
			theStr += " None";
		else {
			theStr += " " + consultations.size();
		}

		return theStr;
	}
}
