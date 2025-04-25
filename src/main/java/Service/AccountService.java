package Service;
import DAO.AccountDAO;
import Model.Account;
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
    public Account registerAccount(Account account) {
        if(account == null || account.username == null || account.password == null){
            return null;
        }
        if(account.username == "" || account.password.length() < 4){
            return null;
        }
        return this.accountDAO.registerAccount(account);
    }
    public Account mockLogin(Account account) {
        if(account == null || account.username == null || account.password == null){
            return null;
        }
        if(account.username == "" || account.password.length() < 4){
            return null;
        }
        return this.accountDAO.mockLogin(account);
    }
    
}
