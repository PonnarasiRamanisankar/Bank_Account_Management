package com.wipro.bank.service;

import java.util.Date;

import com.wipro.bank.bean.TransferBean;
import com.wipro.bank.dao.BankDao;

public class BankService {

    private BankDao bankDao = new BankDao();

    public String checkBalance(String accountNumber) {

        if (!bankDao.validateAccount(accountNumber)) {
            return "ACCOUNT NUMBER INVALID";
        }

        float balance = bankDao.findBalance(accountNumber);
        return String.format("BALANCE IS: %.2f", balance);
    }

    public String transfer(TransferBean transferBean) {

        if (transferBean == null) {
            return "INVALID";
        }

        if (!bankDao.validateAccount(transferBean.getFromAccountNumber()) ||
            !bankDao.validateAccount(transferBean.getToAccountNumber())) {
            return "INVALID ACCOUNT";
        }

        float balance = bankDao.findBalance(transferBean.getFromAccountNumber());

        if (balance < transferBean.getAmount()) {
            return "INSUFFICIENT FUNDS";
        }

        // Set transaction ID and date
        transferBean.setTransactionId(bankDao.generateSequenceNumber());
        transferBean.setDateOfTransfer(new Date());

        boolean result = bankDao.transferMoney(transferBean);

        return result ? "TRANSFER SUCCESSFUL" : "TRANSFER FAILED";
    }
}
