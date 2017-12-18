package au.com.cognizant.userdata.service;

import au.com.cognizant.userdata.dto.UserDataDTO;
import au.com.cognizant.userdata.exception.NoDataReceivedException;
import java.io.IOException;

/**
 * Interface for dealing with third party server communications.
 * 
 * @author theja
 */
public interface RequestUserDataFromSereverService {
    
    public UserDataDTO requestUserData(String userName) throws IOException,
            NoDataReceivedException;
}
