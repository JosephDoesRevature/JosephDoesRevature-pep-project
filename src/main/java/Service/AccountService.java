package Service;
import DAO.AccountDAO;
public class AccountService {
    private AccountDAO accountDAO;
    public AccountService(){
        accountDAO = new AccountDAO();
    }
    public AccountService(AccountDAO  accountDAO){
        this.accountDAO = accountDAO;
    }
    public boolean accountExists(int account_id){
        if(this.accountDAO.accountByID(account_id) != null){
            return true;
        }
        return false;
    }
    
}
