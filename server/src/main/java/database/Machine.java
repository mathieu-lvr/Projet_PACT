package database;

import structures.IDistributor;

public class Machine implements IDistributor {
	private int machineId;
	private String localisation;
	private int numberEcocup;
	private String password;
	
	public Machine(int machineId, String localisation, int numberEcocup,
			String password) {
		super();
		this.machineId = machineId;
		this.localisation = localisation;
		this.numberEcocup = numberEcocup;
		this.password = password;
	}
	
	public int getMachineId() {
		return machineId;
	}
	public void setMachineId(int machineId) {
		this.machineId = machineId;
	}
	public String getLocalisation() {
		return localisation;
	}
	public void setLocalisation(String localisation) {
		this.localisation = localisation;
	}
	public int getNumberEcocup() {
		return numberEcocup;
	}
	public void setNumberEcocup(int numberEcocup) {
		this.numberEcocup = numberEcocup;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
