package au.com.cognizant.userdata.service;

import au.com.cognizant.userdata.dto.UserDataDTO;
import au.com.cognizantuserdata.model.UserData;
import javax.naming.NamingException;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;

/**
 *
 * @author theja
 */
public interface UserDataService {

    public boolean createUserData(UserDataDTO userData) throws NamingException, 
            NotSupportedException, 
            SystemException, 
            RollbackException, 
            HeuristicMixedException, 
            HeuristicRollbackException;
    
    public UserData retrieveUserDataByName(String firstName, String lastName) 
            throws NamingException;
    
    public boolean upadteUserData(UserDataDTO userData) throws NamingException, 
            NotSupportedException, 
            SystemException, 
            RollbackException, 
            HeuristicMixedException, 
            HeuristicRollbackException;
    
    public boolean deleteUserData(int id) throws NamingException, 
            NotSupportedException, 
            SystemException, 
            RollbackException, 
            HeuristicMixedException, 
            HeuristicRollbackException;
}
