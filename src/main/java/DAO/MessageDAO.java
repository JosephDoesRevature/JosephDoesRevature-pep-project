package DAO;

import java.util.ArrayList;
import java.util.List;

import Model.Message;
import Util.ConnectionUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MessageDAO {

    public List<Message> getAllMessages() {
       Connection connection = ConnectionUtil.getConnection();
       List<Message> myMessages = new ArrayList();
       try {
        String sql = "SELECT * FROM message";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet rs = preparedStatement.executeQuery();
        while(rs.next()){
            Message message = new Message(rs.getInt("message_id"), rs.getInt("posted_by"), rs.getString("message_text"), rs.getLong("time_posted_epoch"));
            myMessages.add(message);
        }
       } catch (SQLException e){
        System.out.println(e.getMessage());
       }
       return myMessages;
    }

    public Message getMessageByID(int id) {
        Connection connection = ConnectionUtil.getConnection();
        Message myMessage = null;
        try {
            String sql = "Select * FROM message WHERE message_id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
                myMessage = new Message(rs.getInt("message_id"), rs.getInt("posted_by"), rs.getString("message_text"), rs.getLong("time_posted_epoch"));
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return myMessage;
    }

    public List<Message> getMessagesByAccount(int account_id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getMessagesByAccount'");
    }
    
}
