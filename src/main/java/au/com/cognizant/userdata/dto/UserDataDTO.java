package au.com.cognizant.userdata.dto;

import java.sql.Timestamp;

/**
 * Data transfer object that is used to pass on user data between different layers.
 * 
 * @author theja
 */
public class UserDataDTO {
    
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String addressLine1;
    private String addressLine2;
    private String town;
    private String postalCode;
    private String country;
    private int phoneNumber1;
    private int phoneNumber2;
    private int phoneNumber3;

    public int getId() {
        return id;
    }

    public UserDataDTO setId(int id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserDataDTO setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserDataDTO setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserDataDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public UserDataDTO setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
        return this;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public UserDataDTO setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
        return this;
    }

    public String getTown() {
        return town;
    }

    public UserDataDTO setTown(String town) {
        this.town = town;
        return this;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public UserDataDTO setPostalCode(String postalCode) {
        this.postalCode = postalCode;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public UserDataDTO setCountry(String country) {
        this.country = country;
        return this;
    }

    public int getPhoneNumber1() {
        return phoneNumber1;
    }

    public UserDataDTO setPhoneNumber1(int phoneNumber1) {
        this.phoneNumber1 = phoneNumber1;
        return this;
    }

    public int getPhoneNumber2() {
        return phoneNumber2;
    }

    public UserDataDTO setPhoneNumber2(int phoneNumber2) {
        this.phoneNumber2 = phoneNumber2;
        return this;
    }

    public int getPhoneNumber3() {
        return phoneNumber3;
    }

    public UserDataDTO setPhoneNumber3(int phoneNumber3) {
        this.phoneNumber3 = phoneNumber3;
        return this;
    }
}