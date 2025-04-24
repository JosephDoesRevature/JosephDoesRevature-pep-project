package DAO;
import java.sql.Connection;

import Util.ConnectionUtil;

public class AccountDAO {
    private Connection connection;
    public AccountDAO(){
        connection = ConnectionUtil.getConnection();
    }
}
