package au.com.cognizant.userdata.service;

import au.com.cognizant.userdata.controller.CognizantUserDataController;
import au.com.cognizant.userdata.dto.UserDataDTO;
import au.com.cognizant.userdata.exception.NoDataReceivedException;
import au.com.cognizant.userdata.exception.UserDataValidationException;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;

/**
 * This is an implementation of RequestProcessorService interface to handle all
 * requests coming from front-end.
 *
 * All exceptions that occur in the application is bubbled up until this class
 * and handled here just before returning an appropriate response to the
 * front-end.
 *
 * @author theja
 */
public class RequestProcessorServiceImpl implements RequestProcessorService {

    private static final RequestProcessorServiceImpl requestProcessorServiceImpl = new RequestProcessorServiceImpl();
    private final RequestUserDataFromServerServiceImpl requestUserDataFromServerService = RequestUserDataFromServerServiceImpl.getInstance();
    private final UserDataServiceImpl userDataService = UserDataServiceImpl.getInstance();
    private String jsonString;

    private RequestProcessorServiceImpl() {
        // enforcing singleton
    }

    /**
     * Returns a singleton object of this class.
     *
     * @return Singleton RequestProcessorServiceImpl object
     */
    public static RequestProcessorServiceImpl getInstance() {

        return requestProcessorServiceImpl;
    }

    /**
     * Handles the request to find the given user in the third party back-end
     * server.
     *
     * @param request HTTP servlet request
     * @return User data string in JSON format
     */
    @Override
    public String processFind(HttpServletRequest request) {

        try {
            String name = request.getParameter("name");
            if(isEmpty(name)){
                jsonString = new Gson().toJson(false);
                
            } else {
                jsonString = new Gson().toJson(requestUserDataFromServerService
                    .requestUserData(name));
                
            }
        } catch (IOException | NoDataReceivedException ex) {
            ex.printStackTrace();
            Logger.getLogger(RequestProcessorServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            jsonString = new Gson().toJson(false);
        }
        return jsonString;
    }

    /**
     * Handles the request to create a new user in the client back-end (local
     * system)
     *
     * @param request HTTP servlet request
     * @return 'true' or 'false' in JSON format
     */
    @Override
    public String processCreate(HttpServletRequest request) {

        try {
            validateForMandatoryFields(request);
            jsonString = new Gson().toJson(userDataService.createUserData(getFilledUserDataDTO(request)));

        } catch (NamingException
                | NotSupportedException
                | SystemException
                | RollbackException
                | HeuristicMixedException
                | HeuristicRollbackException  
                | UserDataValidationException ex) {

            ex.printStackTrace();
            Logger.getLogger(CognizantUserDataController.class.getName()).log(Level.SEVERE, null, ex);
            jsonString = new Gson().toJson(false);
        }
        return jsonString;
    }

    /**
     * Handles the request to retrieve a user from the client back-end (local
     * system) with using first name and last name.
     *
     * @param request HTTP servlet request
     * @return User data string in JSON format
     */
    @Override
    public String processRetrieve(HttpServletRequest request) {
        try {
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            if(isEmpty(firstName) || isEmpty(lastName)) {
                jsonString = new Gson().toJson(false);
                
            } else {
                jsonString = new Gson().toJson(userDataService.retrieveUserDataByName(
                    firstName,
                    lastName));
                
            }
            

        } catch (NamingException | NoResultException ex) {
            ex.printStackTrace();
            Logger.getLogger(RequestProcessorServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            jsonString = new Gson().toJson(false);

        }
        return jsonString;
    }

    /**
     * Handles the request to update an existing user in the client back-end
     * (local system)
     *
     * @param request HTTP servlet request
     * @return 'true' or 'false' in JSON format
     */
    @Override
    public String processUpdate(HttpServletRequest request) {

        try {
            validateForMandatoryFields(request);
            
            UserDataDTO dto = getFilledUserDataDTO(request);
            jsonString = new Gson().toJson(userDataService.upadteUserData(dto));

        } catch (NamingException
                | NotSupportedException
                | SystemException
                | RollbackException
                | HeuristicMixedException
                | HeuristicRollbackException 
                | UserDataValidationException ex) {
            ex.printStackTrace();
            Logger.getLogger(RequestProcessorServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            jsonString = new Gson().toJson(false);
        }
        return jsonString;
    }

    /**
     * Handles the request to delete an existing user in the client back-end
     * (local system)
     *
     * @param request HTTP servlet request
     * @return 'true' or 'false' in JSON format
     */
    @Override
    public String processDelete(HttpServletRequest request) {

        try {
            String id = request.getParameter("id");
            if(isEmpty(id)){
               jsonString = new Gson().toJson(false); 
               
            } else {
                jsonString = new Gson().toJson(userDataService
                    .deleteUserData(Integer.parseInt(id)));
            }

        } catch (NamingException
                | NotSupportedException
                | SystemException
                | RollbackException
                | HeuristicMixedException
                | HeuristicRollbackException ex) {
            ex.printStackTrace();
            Logger.getLogger(RequestProcessorServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            jsonString = new Gson().toJson(false);
        }
        return jsonString;
    }

    /** 
     * Creates a user data data transfer object with using arguments in request.
     * 
     */
    private UserDataDTO getFilledUserDataDTO(HttpServletRequest request) {
        UserDataDTO userData = new UserDataDTO()
                .setId(Integer.parseInt(request.getParameter("id")))
                .setFirstName(request.getParameter("firstName"))
                .setLastName(request.getParameter("lastName"))
                .setEmail(request.getParameter("email"))
                .setAddressLine1(request.getParameter("addressLine1"))
                .setTown(request.getParameter("town"))
                .setCountry(request.getParameter("country"));

        String parameter = request.getParameter("addressLine2");
        if (parameter != null) {
            userData.setAddressLine2(parameter);
        }

        parameter = request.getParameter("postalCode");
        if (parameter != null) {
            userData.setPostalCode(parameter);
        }

        parameter = request.getParameter("phoneNumber1");
        
        if (parameter != null && !parameter.isEmpty()) {
            userData.setPhoneNumber1(Integer.parseInt(parameter));
        }

        parameter = request.getParameter("phoneNumber2");
        if (parameter != null && !parameter.isEmpty()) {
            userData.setPhoneNumber2(Integer.parseInt(parameter));
        }

        parameter = request.getParameter("phoneNumber3");
        if (parameter != null && !parameter.isEmpty()) {
            userData.setPhoneNumber3(Integer.parseInt(parameter));
        }
        
        return userData;
    }
    
    /**
     * Validates for the presence of mandatory fields.
     * 
     */
    private void validateForMandatoryFields(HttpServletRequest request) 
            throws UserDataValidationException {
        if(isEmpty(request.getParameter("id"))
                || isEmpty(request.getParameter("firstName"))
                || isEmpty(request.getParameter("lastName"))
                || isEmpty(request.getParameter("email"))
                || isEmpty(request.getParameter("addressLine1"))
                || isEmpty(request.getParameter("town"))
                || isEmpty(request.getParameter("country"))){
            
            throw new UserDataValidationException("Mandatory field missing.");
        }
    }
    
    /**
     * Convenience method to check for the existence of a string value.
     * 
     */
    private boolean isEmpty(String parameter){
        return parameter == null || parameter.isEmpty();
    }
}