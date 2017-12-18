package au.com.cognizant.userdata.dao;

import au.com.cognizantuserdata.model.UserData;
import java.util.Map;
import javax.naming.NamingException;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;

/**
 * Interface for User Data data access object (dao).
 * 
 * @author theja
 */
public interface UserDataDAO {
   
    public boolean createUserData(UserData userData) throws NamingException, 
            NotSupportedException, 
            SystemException, 
            RollbackException, 
            HeuristicMixedException, 
            HeuristicRollbackException;
    
    public UserData retrieveUserData(String criteria, Map<String, Object> parameters) 
            throws NamingException;
    
    public boolean upadateUserData(UserData userData) throws NamingException, 
            NotSupportedException, 
            SystemException, 
            RollbackException, 
            HeuristicMixedException, 
            HeuristicRollbackException;
    
    public boolean deleteUserData(UserData userData) throws NamingException, 
            NotSupportedException, 
            SystemException, 
            RollbackException, 
            HeuristicMixedException, 
            HeuristicRollbackException;
}
