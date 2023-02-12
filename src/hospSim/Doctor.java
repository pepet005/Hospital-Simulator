package hospSim;

import hospSim.enums.DoctorRole;

import java.time.LocalDate;
import java.util.ArrayList;

public class Doctor {

	private String name;
	private DoctorRole[] roles;
	private ArrayList<Consultation> consultations;
	private ArrayList<Consultation> archivedConsultations;
	
	public Doctor(String name, DoctorRole[] roles) {
		this.name = name;
		this.roles = roles;
		this.consultations = new ArrayList<>();
		this.archivedConsultations = new ArrayList<>();
	}

	public String getName() {
		return name;
	}

	public DoctorRole[] getRoles() {
		return roles;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setRoles(DoctorRole[] roles) {
		this.roles = roles;
	}

	public void addConsultation(Consultation newConsultation) {
		consultations.add(newConsultation);
	}

	/*
	Archives a performed consultation
	 */
	public void archiveConsultation(Consultation consultation) {
		consultations.remove(consultation);
		archivedConsultations.add(consultation);
	}

	/*
	Returns any consultation the doctor has at the given date. null if there is none
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
		String roleString = "";
		for (DoctorRole aRole : roles)
			roleString += aRole.toString() + " ";
		return "name: " + name + " roles: " + roleString;
	}

}
