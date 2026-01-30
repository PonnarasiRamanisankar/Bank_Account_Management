package com.wipro.bank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.wipro.bank.bean.TransferBean;
import com.wipro.bank.util.DBUtil;

public class BankDao {

    public int generateSequenceNumber() {
        int seq = 0;
        try (Connection connection = DBUtil.getDBConnection()) {
            String query = "SELECT transactionID_seq1.NEXTVAL FROM dual";
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                seq = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return seq;
    }

    public boolean validateAccount(String accountNumber) {
        try (Connection connection = DBUtil.getDBConnection()) {
            String sql = "SELECT 1 FROM ACCOUNT_TBL WHERE ACCOUNT_NUMBER = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, accountNumber);
            ResultSet rs = ps.executeQuery();

            return rs.next();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public float findBalance(String accountNumber) {
        try (Connection connection = DBUtil.getDBConnection()) {
            String sql = "SELECT BALANCE FROM ACCOUNT_TBL WHERE ACCOUNT_NUMBER = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, accountNumber);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getFloat(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public boolean updateBalance(String accountNumber, float newBalance) {
        try (Connection connection = DBUtil.getDBConnection()) {
            String query = "UPDATE ACCOUNT_TBL SET BALANCE = ? WHERE ACCOUNT_NUMBER = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setFloat(1, newBalance);
            ps.setString(2, accountNumber);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean transferMoney(TransferBean transferBean) {
        Connection connection = null;
        try {
            connection = DBUtil.getDBConnection();
            connection.setAutoCommit(false);  

            float fromBalance = findBalance(transferBean.getFromAccountNumber());
            float toBalance = findBalance(transferBean.getToAccountNumber());

            if (fromBalance < transferBean.getAmount()) {
                throw new RuntimeException("Insufficient funds");
            }

          
            if (!updateBalance(transferBean.getFromAccountNumber(), fromBalance - transferBean.getAmount()) ||
                !updateBalance(transferBean.getToAccountNumber(), toBalance + transferBean.getAmount())) {
                connection.rollback();
                return false;
            }

        
            String sql = "INSERT INTO TRANSFER_TBL (TRANSACTION_ID, ACCOUNT_NUMBER, BENEFICIARY_ACCOUNT_NUMBER, TRANSACTION_DATE, TRANSACTION_AMOUNT) VALUES (?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, transferBean.getTransactionId());
            ps.setString(2, transferBean.getFromAccountNumber());
            ps.setString(3, transferBean.getToAccountNumber());
            ps.setTimestamp(4, new Timestamp(transferBean.getDateOfTransfer().getTime()));
            ps.setFloat(5, transferBean.getAmount());

            boolean result = ps.executeUpdate() > 0;

            connection.commit();
            return result;

        } catch (Exception e) {
            e.printStackTrace();
            try {
                if (connection != null)
                    connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            return false;
        } finally {
            try {
                if (connection != null)
                    connection.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
