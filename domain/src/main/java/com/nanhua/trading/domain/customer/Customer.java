package com.nanhua.trading.domain.customer;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Query;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

import com.nanhua.trading.domain.account.User;
import com.nanhua.trading.domain.correlator.Correlator;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
@JsonIgnoreProperties(ignoreUnknown=true)
@PrimaryKeyJoinColumn(name="id", referencedColumnName="id")
public class Customer extends User{

    @NotNull
    private String address;

    @NotNull
    private String name;

    @NotNull
    private String mobile;

    @NotNull
    private String phone;
    
    
    @ManyToMany(cascade = CascadeType.PERSIST,fetch=FetchType.EAGER)
    @JoinTable(
            joinColumns = @JoinColumn( name="customer_id"),
            inverseJoinColumns = @JoinColumn( name="correlator_id"),
            uniqueConstraints = {@UniqueConstraint(
                    columnNames = {"customer_id", "correlator_id"})}
    )
    private Set<Correlator>  correlators = new HashSet<Correlator>();
 	
    //@OneToMany(cascade = CascadeType.ALL,fetch=FetchType.EAGER,orphanRemoval=true)
    
    @Override
    public void remove(){
    	Set<Correlator> sis = this.getCorrelators();
        
        for (Correlator si : sis) {
        	sis.remove(si);
		}
    	
    	if (entityManager().contains(this)) {
            entityManager().remove(this);
        } else {
            User attached = User.findUser(this.getId());
            entityManager().remove(attached);
        }
    }
    
    public static Customer findCustomerByUniqueIdentify(String identify){
    	
		Query q = entityManager().createQuery("SELECT  s FROM Customer AS s WHERE s.username= :identify or s.email=:identify");
        q.setParameter("identify", identify);
        return (Customer)(q.getSingleResult());
    
	}
    
    
    
}
