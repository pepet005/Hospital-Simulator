package hospSim.enums;

public enum PatientCondition {
    Cancer ("Cancer", new DoctorRole[] {DoctorRole.Oncologist}), // Cancer patients need to see an oncologist
    Flu ("Flu", new DoctorRole[] {DoctorRole.GeneralPractitioner}); // Flu patients need to see a general practitioner

    private final String name;
    private final DoctorRole[] correctRoles;
    PatientCondition(String name, DoctorRole[] correctRoles) {
        this.name = name;
        this.correctRoles = correctRoles;
    }

    public DoctorRole[] getCorrectRoles() {
        return correctRoles;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
