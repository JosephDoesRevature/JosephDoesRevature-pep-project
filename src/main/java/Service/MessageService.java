package Service;
import java.util.List;

import DAO.MessageDAO;
import Model.Account;
import Model.Message;
public class MessageService {
    private MessageDAO messageDAO;
    public MessageService(){
        this.messageDAO = new MessageDAO();
    }
    public MessageService(MessageDAO messageDao){
        this.messageDAO = messageDao;
    }

    public List<Message> getAllMessages(){
        return messageDAO.getAllMessages();
    }
    public Message getMessageByID(int id){
        return messageDAO.getMessageByID(id);
    }
    public List<Message> getMessagesByAccount(int account_id){
        return messageDAO.getMessagesByAccount(account_id);
    }
    public List<Message> getMessagesByAccount(Account account){
        return messageDAO.getMessagesByAccount(account.account_id);
    }
    public Message deleteMessageByID(int mID) {
        return messageDAO.deleteMessageByID(mID);
    }
    public Message makeMessage(Message message){
        if(message == null || message.message_text == "" || message.message_text.length() > 255){
            return null;
        }
        return messageDAO.makeMessage(message);
    }
    public Message updateMessage(int mID, Message update) {
        try{
            if(update.message_text != "" && update.message_text.length() <= 255){
                return messageDAO.updateMessage(mID, update.message_text);
            }
        } catch (Exception e){

        }
        return null;
    }

}
