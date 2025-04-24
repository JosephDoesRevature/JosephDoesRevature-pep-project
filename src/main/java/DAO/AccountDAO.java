package DAO;
import java.sql.Connection;

import Util.ConnectionUtil;

public class AccountDAO {
    private Connection connection;
    public AccountDAO(){
        connection = ConnectionUtil.getConnection();
    }
    public Object accountByID(int account_id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'accountByID'");
    }
}
