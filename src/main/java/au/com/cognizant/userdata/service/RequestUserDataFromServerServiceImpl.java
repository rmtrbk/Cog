package au.com.cognizant.userdata.service;

import au.com.cognizant.userdata.dto.UserDataDTO;
import au.com.cognizant.userdata.exception.NoDataReceivedException;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

/**
 * This class is the implementation of RequestUserDataFromServerService that
 * deals with communication with third party back-ends.
 *
 * @author theja
 */
public class RequestUserDataFromServerServiceImpl implements RequestUserDataFromSereverService {

    private static final String PROPERTIES_FILE_NAME = "cognizant.properties";
    private static final String USER_DATA_SERVER_URL_PROPERTY_NAME = "userdataServerUrl";
    
    private static final RequestUserDataFromServerServiceImpl requestUserDataFromServerService 
            = new RequestUserDataFromServerServiceImpl();
    
    private RequestUserDataFromServerServiceImpl() {
        // enforcing singleton
    }
    
    /** 
     * Return a singleton object of this class.
     * 
     * @return RequestUserDataFromServerServiceImpl instance
     */
    public static RequestUserDataFromServerServiceImpl getInstance() {
        return requestUserDataFromServerService;
    }

    /**
     * Requests user data from a third party back-end.
     *
     * @param nameOfUser Name of the user who is being looked up in the third
     * party system
     * @return User data data transfer object
     * @throws java.io.IOException
     */
    @Override
    public UserDataDTO requestUserData(String nameOfUser) throws IOException, 
            NoDataReceivedException {

        String jsonRequest = "{\"name\":\"" + nameOfUser + "\"}";

        return requestUserDataFromServer(jsonRequest);
    }

    /**
     * Makes an HTTP request to an external server with a JSON message.
     *
     */
    private UserDataDTO requestUserDataFromServer(String jsonRequest) throws IOException, 
            NoDataReceivedException {

        UserDataDTO userDataDto;
        try (CloseableHttpClient closeableHttpClient = HttpClients.createDefault()) {
            
            HttpPost httpPost = new HttpPost(getUserDataServerUrl());
            
            StringEntity entity = new StringEntity(jsonRequest);
            httpPost.setEntity(entity);
            
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");
            
            CloseableHttpResponse response = closeableHttpClient.execute(httpPost);
            
            try {
                userDataDto = new Gson().fromJson(readContentFromStream(response.getEntity().getContent()), UserDataDTO.class);
                
            }catch(JsonSyntaxException ex) {
                ex.printStackTrace();
                Logger.getLogger(RequestUserDataFromServerServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                throw new NoDataReceivedException("User data not received from the server.");
            }
        }

        return userDataDto;
    }

    /**
     * Reads the property file to find the URL to the third party server.
     *
     */
    private String getUserDataServerUrl() throws FileNotFoundException, IOException {

        Properties properties = new Properties();
        properties.load(new FileInputStream(PROPERTIES_FILE_NAME));

        return properties.getProperty(USER_DATA_SERVER_URL_PROPERTY_NAME);
    }

    /** 
     * Converts an input stream to a string.
     * 
     */
    private String readContentFromStream(InputStream inputStream) throws IOException {
        
        BufferedReader br = new BufferedReader(
                new InputStreamReader(inputStream)
        );

        StringBuilder content = new StringBuilder();
        String line;
        while (null != (line = br.readLine())) {
            content.append(line);
        }
        return content.toString();
    }
}