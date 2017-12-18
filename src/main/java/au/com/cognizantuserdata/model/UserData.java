package au.com.cognizantuserdata.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This is the entity class that maps user_data table.
 * 
 * @author theja
 */
@Entity
@Table(name = "user_data")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserData.findAll", query = "SELECT u FROM UserData u")
    , @NamedQuery(name = "UserData.findById", query = "SELECT u FROM UserData u WHERE u.id = :id")
    , @NamedQuery(name = "UserData.findByFirstName", query = "SELECT u FROM UserData u WHERE u.firstName = :firstName")
    , @NamedQuery(name = "UserData.findByLastName", query = "SELECT u FROM UserData u WHERE u.lastName = :lastName")
    , @NamedQuery(name = "UserData.findByName", query = "SELECT u FROM UserData u WHERE lower(u.firstName) = lower(:firstName) AND lower(u.lastName) = lower(:lastName)")
    , @NamedQuery(name = "UserData.findByEmail", query = "SELECT u FROM UserData u WHERE u.email = :email")
    , @NamedQuery(name = "UserData.findByAddressLine1", query = "SELECT u FROM UserData u WHERE u.addressLine1 = :addressLine1")
    , @NamedQuery(name = "UserData.findByAddressLine2", query = "SELECT u FROM UserData u WHERE u.addressLine2 = :addressLine2")
    , @NamedQuery(name = "UserData.findByTown", query = "SELECT u FROM UserData u WHERE u.town = :town")
    , @NamedQuery(name = "UserData.findByPostalCode", query = "SELECT u FROM UserData u WHERE u.postalCode = :postalCode")
    , @NamedQuery(name = "UserData.findByCountry", query = "SELECT u FROM UserData u WHERE u.country = :country")
    , @NamedQuery(name = "UserData.findByPhoneNumber1", query = "SELECT u FROM UserData u WHERE u.phoneNumber1 = :phoneNumber1")
    , @NamedQuery(name = "UserData.findByPhoneNumber2", query = "SELECT u FROM UserData u WHERE u.phoneNumber2 = :phoneNumber2")
    , @NamedQuery(name = "UserData.findByPhoneNumber3", query = "SELECT u FROM UserData u WHERE u.phoneNumber3 = :phoneNumber3")})
public class UserData implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "first_name")
    private String firstName;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "last_name")
    private String lastName;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "email")
    private String email;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "address_line_1")
    private String addressLine1;
    
    @Size(max = 2147483647)
    @Column(name = "address_line_2")
    private String addressLine2;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "town")
    private String town;
    
    @Size(max = 2147483647)
    @Column(name = "postal_code")
    private String postalCode;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "country")
    private String country;
    
    @Column(name = "phone_number_1")
    private Integer phoneNumber1;
    
    @Column(name = "phone_number_2")
    private Integer phoneNumber2;
    
    @Column(name = "phone_number_3")
    private Integer phoneNumber3;

    public UserData() {
    }

    public UserData(Integer id) {
        this.id = id;
    }

    public UserData(Integer id, String firstName, String lastName, String email, String addressLine1, String town, String country) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.addressLine1 = addressLine1;
        this.town = town;
        this.country = country;
    }

    public Integer getId() {
        return id;
    }

    public UserData setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserData setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserData setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserData setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public UserData setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
        return this;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public UserData setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
        return this;
    }

    public String getTown() {
        return town;
    }

    public UserData setTown(String town) {
        this.town = town;
        return this;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public UserData setPostalCode(String postalCode) {
        this.postalCode = postalCode;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public UserData setCountry(String country) {
        this.country = country;
        return this;
    }

    public Integer getPhoneNumber1() {
        return phoneNumber1;
    }

    public UserData setPhoneNumber1(Integer phoneNumber1) {
        this.phoneNumber1 = phoneNumber1;
        return this;
    }

    public Integer getPhoneNumber2() {
        return phoneNumber2;
    }

    public UserData setPhoneNumber2(Integer phoneNumber2) {
        this.phoneNumber2 = phoneNumber2;
        return this;
    }

    public Integer getPhoneNumber3() {
        return phoneNumber3;
    }

    public UserData setPhoneNumber3(Integer phoneNumber3) {
        this.phoneNumber3 = phoneNumber3;
        return this;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserData)) {
            return false;
        }
        UserData other = (UserData) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "au.com.cognizant.userdata.model.UserData[ id=" + id + " ]";
    }
}
