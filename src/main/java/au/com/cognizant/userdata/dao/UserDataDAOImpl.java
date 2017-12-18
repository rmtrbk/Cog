package au.com.cognizant.userdata.dao;

import au.com.cognizant.userdata.databasetransactions.DatabaseTransactions;
import au.com.cognizantuserdata.model.UserData;
import java.util.Map;
import javax.naming.NamingException;
import javax.persistence.NoResultException;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;

/**
 * Implementation of UserDataDAO with basic functionality to handle data access.
 * 
 * @author theja
 */
public class UserDataDAOImpl extends DatabaseTransactions<UserData> 
        implements UserDataDAO {
    
    private static final UserDataDAOImpl userDataDao = new UserDataDAOImpl();
    
    private UserDataDAOImpl(){
        // enforcing singleton
    }
    
    /** 
     * Returns the singleton object of this class.
     * 
     * @return UserDataDAOImpl singleton instance
     */
    public static UserDataDAOImpl getInstance(){
        return userDataDao;
    }
    
    /** 
     * Calls to create a user data record.
     * 
     * @param userData User data record that is going to be saved.
     * @return States whether successful or unsuccessful.
     * @throws javax.naming.NamingException
     * @throws javax.transaction.NotSupportedException
     * @throws javax.transaction.SystemException
     * @throws javax.transaction.RollbackException
     * @throws javax.transaction.HeuristicMixedException
     * @throws javax.transaction.HeuristicRollbackException
     */
    @Override
    public boolean createUserData(UserData userData) throws NamingException, 
            NotSupportedException, 
            SystemException, 
            RollbackException, 
            HeuristicMixedException, 
            HeuristicRollbackException {
        
            return create(userData);
    }
    
    /** 
     * Finds THE matching user record based on given criteria.
     * 
     * @param criteria Search criteria (available in {@link UserData})
     * @param parameters Map of parameters used by the search criteria
     * @return Matched record
     * @throws javax.naming.NamingException
     */
    @Override
    public UserData retrieveUserData(String criteria, Map<String, Object> parameters) throws NamingException, NoResultException {
        
        return retrieve(criteria, parameters);
    }

    /** 
     * Calls to update an existing user data record.
     * 
     * @param userData User data record that is going to be updated.
     * @return States whether successful or unsuccessful.
     * @throws javax.naming.NamingException
     * @throws javax.transaction.NotSupportedException
     * @throws javax.transaction.SystemException
     * @throws javax.transaction.RollbackException
     * @throws javax.transaction.HeuristicMixedException
     * @throws javax.transaction.HeuristicRollbackException
     */
    @Override
    public boolean upadateUserData(UserData userData) throws NamingException, 
            NotSupportedException, 
            SystemException, 
            RollbackException, 
            HeuristicMixedException, 
            HeuristicRollbackException { 
        
        return update(userData);
    }

    /** 
     * Calls to delete an existing user data record.
     * 
     * @param userData User data record that is going to be deleted.
     * @return States whether successful or unsuccessful.
     * @throws javax.naming.NamingException
     * @throws javax.transaction.NotSupportedException
     * @throws javax.transaction.SystemException
     * @throws javax.transaction.RollbackException
     * @throws javax.transaction.HeuristicMixedException
     * @throws javax.transaction.HeuristicRollbackException
     */
    @Override
    public boolean deleteUserData(UserData userData) throws NamingException, 
            NotSupportedException, 
            SystemException, 
            RollbackException, 
            HeuristicMixedException, 
            HeuristicRollbackException {
        
        return delete(userData);
    }
}
