package com.wipro.bank.bean;

import java.util.Date;

public class TransferBean {

    private int transactionId;
    private String fromAccountNumber;
    private String toAccountNumber;
    private Date dateOfTransfer;
    private float amount;

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public String getFromAccountNumber() {
        return fromAccountNumber;
    }

    public void setFromAccountNumber(String fromAccountNumber) {
        this.fromAccountNumber = fromAccountNumber;
    }

    public String getToAccountNumber() {
        return toAccountNumber;
    }

    public void setToAccountNumber(String toAccountNumber) {
        this.toAccountNumber = toAccountNumber;
    }

    public Date getDateOfTransfer() {
        return dateOfTransfer;
    }

    public void setDateOfTransfer(Date dateOfTransfer) {
        this.dateOfTransfer = dateOfTransfer;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }
}
