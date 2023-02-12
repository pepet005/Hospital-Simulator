package hospSim;

import hospSim.enums.MachineCapability;

public class TreatmentMachine {

	private String name;
	private MachineCapability capability;
	
	public TreatmentMachine(String name, MachineCapability capability) {
		this.name = name;
		this.capability = capability;
	}

	public String getName() {
		return name;
	}

	public MachineCapability getCapability() {
		return capability;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCapability(MachineCapability capability) {
		this.capability = capability;
	}

	@Override
	public String toString(){
		return name + " " + capability;
	}

}
