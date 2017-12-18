package au.com.cognizant.userdata.service;

import javax.servlet.http.HttpServletRequest;

/**
 * This is the interface handling all client requests.
 * 
 * @author theja
 */
public interface RequestProcessorService {
    
    public String processFind(HttpServletRequest request);
    public String processCreate(HttpServletRequest request);
    public String processRetrieve(HttpServletRequest request);
    public String processUpdate(HttpServletRequest request);
    public String processDelete(HttpServletRequest request);
}
