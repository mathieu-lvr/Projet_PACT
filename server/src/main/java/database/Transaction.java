package database;
import java.sql.Date;

public class Transaction {
	
	private int transactionId;
	private int userId;
	private int machineId;
	private Date date;
	private String type;
	
	public Transaction(int transactionId, int userId, int machineId, Date date,
			String type) {
		super();
		this.transactionId = transactionId;
		this.userId = userId;
		this.machineId = machineId;
		this.date = date;
		this.type = type;
	}
	
	public int getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getMachineId() {
		return machineId;
	}
	public void setMachineId(int machineId) {
		this.machineId = machineId;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
