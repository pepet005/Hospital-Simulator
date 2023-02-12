package hospSim;

import hospSim.enums.ConditionTopography;
import hospSim.enums.PatientCondition;

public class Condition {

	private PatientCondition conditionType;
	private ConditionTopography topography;

	public Condition(PatientCondition conditionType, ConditionTopography topography) {
		this.conditionType = conditionType;
		this.topography = topography;
	}

	public PatientCondition getConditionType() {
		return conditionType;
	}
	public ConditionTopography getTopography() {
		return topography;
	}

	public void setConditionType(PatientCondition conditionType) {
		this.conditionType = conditionType;
	}

	@Override
	public String toString(){
		return conditionType.toString();
	}

}
