/*
 * Source code generated by Celerio, a Jaxio product.
 * Documentation: http://www.jaxio.com/documentation/celerio/
 * Follow us on twitter: @jaxiosoft
 * Need commercial support ? Contact us: info@jaxio.com
 * Template angular-lab:springboot/src/main/java/domain/Entity.e.vm.java
 */
package com.jaxio.demo.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import com.google.common.base.MoreObjects;
import com.jaxio.jpa.querybyexample.Identifiable;

@Entity
@Table(name = "APP_USER")
// elastic search index must be lowercase
@org.springframework.data.elasticsearch.annotations.Document(indexName = "appuser")
public class AppUser implements Identifiable<Integer>, Serializable {

    private static final long serialVersionUID = 1L;

    private static final Logger log = Logger.getLogger(AppUser.class.getName());

    // Raw attributes
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String language;
    private String login;
    private String password;
    private Integer enabled;

    // Many to many
    private List<AppAuthority> appAuthorities = new ArrayList<AppAuthority>();

    @Override
    public String entityClassName() {
        return AppUser.class.getSimpleName();
    }

    // -- [id] ------------------------

    @Override
    @Column(name = "ID", precision = 10)
    @GeneratedValue
    @Id
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public AppUser id(Integer id) {
        setId(id);
        return this;
    }

    @Override
    @Transient
    @XmlTransient
    public boolean isIdSet() {
        return id != null;
    }

    // -- [firstName] ------------------------

    @NotEmpty
    @Size(max = 250)
    @Column(name = "FIRST_NAME", nullable = false, length = 250)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public AppUser firstName(String firstName) {
        setFirstName(firstName);
        return this;
    }

    // -- [lastName] ------------------------

    @NotEmpty
    @Size(max = 250)
    @Column(name = "LAST_NAME", nullable = false, length = 250)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public AppUser lastName(String lastName) {
        setLastName(lastName);
        return this;
    }

    // -- [email] ------------------------

    @Email
    @NotEmpty
    @Size(max = 250)
    @Column(name = "EMAIL", nullable = false, length = 250)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public AppUser email(String email) {
        setEmail(email);
        return this;
    }

    // -- [language] ------------------------

    @NotEmpty
    @Size(max = 10)
    @Column(name = "LANGUAGE", nullable = false, length = 10)
    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public AppUser language(String language) {
        setLanguage(language);
        return this;
    }

    // -- [login] ------------------------

    @NotEmpty
    @Size(max = 250)
    @Column(name = "LOGIN", nullable = false, length = 250)
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public AppUser login(String login) {
        setLogin(login);
        return this;
    }

    // -- [password] ------------------------

    @NotEmpty
    @Size(max = 50)
    @Column(name = "PASSWORD", nullable = false, length = 50)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public AppUser password(String password) {
        setPassword(password);
        return this;
    }

    // -- [enabled] ------------------------

    @Digits(integer = 10, fraction = 0)
    @NotNull
    @Column(name = "ENABLED", nullable = false, precision = 10)
    public Integer getEnabled() {
        return enabled;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }

    public AppUser enabled(Integer enabled) {
        setEnabled(enabled);
        return this;
    }

    // -----------------------------------------------------------------
    // Many to Many
    // -----------------------------------------------------------------

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - 
    // many-to-many: appUser ==> appAuthorities
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - 

    /**
     * Returns the {@link #appAuthorities} list.
     */
    @JoinTable(name = "APP_USER_AUTHORITY", joinColumns = @JoinColumn(name = "APP_USER_ID"), inverseJoinColumns = @JoinColumn(name = "APP_AUTHORITY_ID"))
    @ManyToMany(fetch = FetchType.EAGER)
    @NotFound(action = NotFoundAction.IGNORE)
    public List<AppAuthority> getAppAuthorities() {
        return appAuthorities;
    }

    /**
     * Set the {@link #appAuthorities} list.
     *
     * @param appAuthorities the list of AppAuthority
     */
    public void setAppAuthorities(List<AppAuthority> appAuthorities) {
        this.appAuthorities = appAuthorities;
    }

    /**
     * Helper method to add the passed {@link AppAuthority} to the {@link #appAuthorities} list.
     */
    public boolean addAppAuthority(AppAuthority appAuthority) {
        return getAppAuthorities().add(appAuthority);
    }

    /**
     * Helper method to remove the passed {@link AppAuthority} from the {@link #appAuthorities} list.
     */
    public boolean removeAppAuthority(AppAuthority appAuthority) {
        return getAppAuthorities().remove(appAuthority);
    }

    /**
     * Helper method to determine if the passed {@link AppAuthority} is present in the {@link #appAuthorities} list.
     */
    public boolean containsAppAuthority(AppAuthority appAuthority) {
        return getAppAuthorities() != null && getAppAuthorities().contains(appAuthority);
    }

    /**
     * Apply the default values.
     */
    public AppUser withDefaults() {
        return this;
    }

    /**
     * Equals implementation using a business key.
     */
    @Override
    public boolean equals(Object other) {
        return this == other || (other instanceof AppUser && hashCode() == other.hashCode());
    }

    private IdentifiableHashBuilder identifiableHashBuilder = new IdentifiableHashBuilder();

    @Override
    public int hashCode() {
        return identifiableHashBuilder.hash(log, this);
    }

    /**
     * Construct a readable string representation for this AppUser instance.
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this) //
                .add("id", getId()) //
                .add("firstName", getFirstName()) //
                .add("lastName", getLastName()) //
                .add("email", getEmail()) //
                .add("language", getLanguage()) //
                .add("login", getLogin()) //
                .add("password", "XXXX") //
                .add("enabled", getEnabled()) //
                .toString();
    }
}