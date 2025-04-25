package DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Model.Account;
import Util.ConnectionUtil;

public class AccountDAO {
    private Connection connection;
    public AccountDAO(){
        connection = ConnectionUtil.getConnection();
    }
    public Account accountByID(int account_id) {
        Connection connnection = ConnectionUtil.getConnection();
        Account account = null;
        try{
            String sql = "SELECT * FROM account WHERE account_id=?";
            PreparedStatement preparedStatement = connnection.prepareStatement(sql);
            preparedStatement.setInt(1, account_id);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
                account = new Account(rs.getInt(1), rs.getString(2), rs.getString(3));
            }
        } catch (SQLException e){
            System.out.println(e);
        }
        return account;
    }
    public Account registerAccount(Account account) {
        Connection connection = ConnectionUtil.getConnection();
        try{
            String sql = "INSERT INTO account (username, password) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, account.username);
            preparedStatement.setString(2, account.password);
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if(rs.next()){
                account = new Account(rs.getInt(1), account.username, account.password);
                return account; 
            }
        } catch (SQLException e){
            System.out.println(e);
        }
        return null;
    }
    public Account mockLogin(Account account) {
        Connection connection = ConnectionUtil.getConnection(); 
        try {
            String sql = "SELECT * FROM account WHERE username=? AND password=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, account.username);
            preparedStatement.setString(2, account.password);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
                account = new Account(rs.getInt(1), account.username, account.password);
                return account; 
            }
        } catch (SQLException e){
            System.out.println(e);
        }
        return null;
    }
}
