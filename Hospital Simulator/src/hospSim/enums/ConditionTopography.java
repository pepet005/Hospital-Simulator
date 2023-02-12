package hospSim.enums;

public enum ConditionTopography {
    HeadAndNeck ("Head and Neck", new MachineCapability[] {MachineCapability.Advanced}), // Requires an advanced machine
    Breast ("Breast", MachineCapability.values()); // Any machine capability is fine

    private final String name;
    private final MachineCapability[] correctMachineCapabilities;

    ConditionTopography(String name, MachineCapability[] correctMachineCapabilities) {
        this.name = name;
        this.correctMachineCapabilities = correctMachineCapabilities;
    }

    public MachineCapability[] getCorrectMachineCapabilities() {
        return correctMachineCapabilities;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
