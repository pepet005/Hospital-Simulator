package hospSim.enums;

public enum DoctorRole {
    Oncologist ("Oncologist"),
    GeneralPractitioner ("General Practitioner");

    private final String name;
    DoctorRole(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}