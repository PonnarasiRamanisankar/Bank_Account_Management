 package com.wipro.bank.main;

import com.wipro.bank.bean.TransferBean;
import com.wipro.bank.service.BankService;
import java.util.Date;

public class BankMain {

    public static void main(String[] args) {

        BankService bankService = new BankService();

        System.out.println(bankService.checkBalance("1298653498"));

        TransferBean transferBean = new TransferBean();
        transferBean.setFromAccountNumber("1298653498");
        transferBean.setToAccountNumber("1298765356");
        transferBean.setAmount(1500);

      
        Date utilDate = new Date();
        transferBean.setDateOfTransfer(new java.sql.Date(utilDate.getTime()));

        System.out.println(bankService.transfer(transferBean));
    }
}





























