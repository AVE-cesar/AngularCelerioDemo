/*
 * Source code generated by Celerio, a Jaxio product.
 * Documentation: http://www.jaxio.com/documentation/celerio/
 * Follow us on twitter: @jaxiosoft
 * Need commercial support ? Contact us: info@jaxio.com
 * Template angular-lab:springboot/src/main/java/domain/Entity.e.vm.java
 */
package com.jaxio.demo.domain;

import java.io.Serializable;
import java.util.logging.Logger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.validator.constraints.NotEmpty;

import com.google.common.base.MoreObjects;
import com.jaxio.jpa.querybyexample.Identifiable;

@Entity
@Table(name = "APP_PARAMETER")
// elastic search index must be lowercase
@org.springframework.data.elasticsearch.annotations.Document(indexName = "appparameter")
@NamedQueries(value = {
	    @NamedQuery(name = "AppParameter.getAll", query = "SELECT ap FROM AppParameter ap")
	})
public class AppParameter implements Identifiable<Integer>, Serializable {

    private static final long serialVersionUID = 1L;

    private static final Logger log = Logger.getLogger(AppParameter.class.getName());

    // Raw attributes
    private Integer id;
    private String domain;
    private String key;
    private String value;

    @Override
    public String entityClassName() {
        return AppParameter.class.getSimpleName();
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

    public AppParameter id(Integer id) {
        setId(id);
        return this;
    }

    @Override
    @Transient
    @XmlTransient
    public boolean isIdSet() {
        return id != null;
    }

    // -- [domain] ------------------------

    @NotEmpty
    @Size(max = 250)
    @Column(name = "domain", nullable = false, length = 250)
    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public AppParameter domain(String domain) {
        setDomain(domain);
        return this;
    }

    // -- [key] ------------------------

    @NotEmpty
    @Size(max = 1000)
    @Column(name = "\"KEY\"", nullable = false, length = 1000)
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public AppParameter key(String key) {
        setKey(key);
        return this;
    }

    // -- [value] ------------------------

    @Size(max = 4000)
    @Column(name = "value", length = 4000)
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public AppParameter value(String value) {
        setValue(value);
        return this;
    }

    /**
     * Apply the default values.
     */
    public AppParameter withDefaults() {
        return this;
    }

    /**
     * Equals implementation using a business key.
     */
    @Override
    public boolean equals(Object other) {
        return this == other || (other instanceof AppParameter && hashCode() == other.hashCode());
    }

    private IdentifiableHashBuilder identifiableHashBuilder = new IdentifiableHashBuilder();

    @Override
    public int hashCode() {
        return identifiableHashBuilder.hash(log, this);
    }

    /**
     * Construct a readable string representation for this AppParameter instance.
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this) //
                .add("id", getId()) //
                .add("domain", getDomain()) //
                .add("key", getKey()) //
                .add("value", getValue()) //
                .toString();
    }
}