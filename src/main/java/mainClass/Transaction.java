package mainClass;

import java.util.Date;

public class Transaction {

	private String transactionID;
	private String senderID;
	private String ReceiverID;
	private String amount;
	private Date loadSendingDate;
	private Date loanExpireDate;

	public Transaction() {
	}

	public Transaction(String transactionID, String senderID, String receiverID, String amount, Date loadSendingDate, Date loanExpireDate) {
		this.transactionID = transactionID;
		this.senderID = senderID;
		ReceiverID = receiverID;
		this.amount = amount;
		this.loadSendingDate = loadSendingDate;
		this.loanExpireDate = loanExpireDate;
	}

	public String getTransactionID() {
		return transactionID;
	}

	public void setTransactionID(String transactionID) {
		this.transactionID = transactionID;
	}

	public String getSenderID() {
		return senderID;
	}

	public void setSenderID(String senderID) {
		this.senderID = senderID;
	}

	public String getReceiverID() {
		return ReceiverID;
	}

	public void setReceiverID(String receiverID) {
		ReceiverID = receiverID;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public Date getLoadSendingDate() {
		return loadSendingDate;
	}

	public void setLoadSendingDate(Date loadSendingDate) {
		this.loadSendingDate = loadSendingDate;
	}

	public Date getLoanExpireDate() {
		return loanExpireDate;
	}

	public void setLoanExpireDate(Date loanExpireDate) {
		this.loanExpireDate = loanExpireDate;
	}


	@Override
	public String toString() {
		return "Transaction{" +
				"transactionID='" + transactionID + '\'' +
				", senderID='" + senderID + '\'' +
				", ReceiverID='" + ReceiverID + '\'' +
				", amount='" + amount + '\'' +
				", loadSendingDate=" + loadSendingDate +
				", loanExpireDate=" + loanExpireDate +
				'}';
	}
}
