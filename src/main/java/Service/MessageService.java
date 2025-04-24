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
    public void deleteMessageByID(int mID) {
        messageDAO.deleteMessageByID(mID);
    }
    

}
