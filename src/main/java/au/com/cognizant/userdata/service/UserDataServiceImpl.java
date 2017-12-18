package au.com.cognizant.userdata.service;

import au.com.cognizant.userdata.dao.UserDataDAOImpl;
import au.com.cognizant.userdata.dto.UserDataDTO;
import au.com.cognizantuserdata.model.UserData;
import java.util.HashMap;
import java.util.Map;
import javax.naming.NamingException;
import javax.persistence.NoResultException;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;

/**
 * This class provides all the business services that are related to UserData 
 * manipulation.
 * 
 * @author theja
 */
public class UserDataServiceImpl implements UserDataService{
    private static final UserDataServiceImpl userDataService = new UserDataServiceImpl();
    
    private UserDataServiceImpl(){
        // enforcing singleton
    }
    
    /** 
     * Returns the singleton object of this class.
     * 
     * @return UserDataServiceImpl singleton instance
     */
    public static UserDataServiceImpl getInstance(){
        return userDataService;
    }
    
    private UserDataDAOImpl userDataDao = UserDataDAOImpl.getInstance();

    /** 
     * Creates a user data record.
     * 
     * @param userData User data data transfer object.
     * @return States whether successful or unsuccessful.
     * @throws javax.naming.NamingException
     * @throws javax.transaction.NotSupportedException
     * @throws javax.transaction.SystemException
     * @throws javax.transaction.RollbackException
     * @throws javax.transaction.HeuristicMixedException
     * @throws javax.transaction.HeuristicRollbackException
     */
    @Override
    public boolean createUserData(UserDataDTO userData) throws NamingException, 
            NotSupportedException, 
            SystemException, 
            RollbackException, 
            HeuristicMixedException, 
            HeuristicRollbackException {
        
        UserData userDataToSave = new UserData()
                .setId(userData.getId())
                .setFirstName(userData.getFirstName())
                .setLastName(userData.getLastName())
                .setEmail(userData.getEmail())
                .setAddressLine1(userData.getAddressLine1())
                .setAddressLine2(userData.getAddressLine2())
                .setTown(userData.getTown())
                .setPostalCode(userData.getPostalCode())
                .setCountry(userData.getCountry())
                .setPhoneNumber1(userData.getPhoneNumber1())
                .setPhoneNumber2(userData.getPhoneNumber2())
                .setPhoneNumber3(userData.getPhoneNumber3());
        
        return userDataDao.createUserData(userDataToSave);
    }
    
    /** 
     * Finds the matching user record based on given first name and last name.
     * 
     * @param firstName First name
     * @param lastName Last name
     * @return Matched record
     * @throws javax.naming.NamingException
     */
    @Override
    public UserData retrieveUserDataByName(String firstName, String lastName) 
            throws NamingException, NoResultException {
        
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("firstName", firstName);
        parameters.put("lastName", lastName);
        
        return userDataDao.retrieveUserData("UserData.findByName", parameters);
    }
    
    /** 
     * Updates an existing user data record.
     * 
     * @param userData User data data transfer object.
     * @return States whether successful or unsuccessful.
     * @throws javax.naming.NamingException
     * @throws javax.transaction.NotSupportedException
     * @throws javax.transaction.SystemException
     * @throws javax.transaction.RollbackException
     * @throws javax.transaction.HeuristicMixedException
     * @throws javax.transaction.HeuristicRollbackException
     */
    @Override
    public boolean upadteUserData(UserDataDTO userData) throws NamingException, 
            NotSupportedException, 
            SystemException, 
            RollbackException, 
            HeuristicMixedException, 
            HeuristicRollbackException {
        
        UserData userDataToUpdate = retrieveUserDataById(userData.getId())
                .setFirstName(userData.getFirstName())
                .setLastName(userData.getLastName())
                .setEmail(userData.getEmail())
                .setAddressLine1(userData.getAddressLine1())
                .setAddressLine2(userData.getAddressLine2())
                .setTown(userData.getTown())
                .setPostalCode(userData.getPostalCode())
                .setCountry(userData.getCountry())
                .setPhoneNumber1(userData.getPhoneNumber1())
                .setPhoneNumber2(userData.getPhoneNumber2())
                .setPhoneNumber3(userData.getPhoneNumber3());
        
        return userDataDao.upadateUserData(userDataToUpdate);
    }

    /** 
     * Deletes an existing user data record.
     * 
     * @param id Id of the user whose record is going to be deleted.
     * @return States whether successful or unsuccessful.
     * @throws javax.naming.NamingException
     * @throws javax.transaction.NotSupportedException
     * @throws javax.transaction.SystemException
     * @throws javax.transaction.RollbackException
     * @throws javax.transaction.HeuristicMixedException
     * @throws javax.transaction.HeuristicRollbackException
     */
    @Override
    public boolean deleteUserData(int id) throws NamingException, 
            NotSupportedException, 
            SystemException, 
            RollbackException, 
            HeuristicMixedException, 
            HeuristicRollbackException {
        
        return userDataDao.deleteUserData(retrieveUserDataById(id));
    }
    
    /** 
     * Finds the matching user record based on given id.
     * 
     * @param id ID of the user
     * @return Matched record
     * @throws javax.naming.NamingException
     */
    private UserData retrieveUserDataById(int id) throws NamingException, NoResultException {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("id", id);
        
        return userDataDao.retrieveUserData("UserData.findById", parameters);
    }
}