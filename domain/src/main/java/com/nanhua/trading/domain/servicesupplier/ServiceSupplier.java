package com.nanhua.trading.domain.servicesupplier;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonManagedReference;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
@JsonIgnoreProperties(ignoreUnknown=true)
public class ServiceSupplier {

    @NotNull
    @Size(max = 200)
    private String name;

    @NotNull
    @Size(max = 50)
    private String serviceIdPrefix;

   
    @Size(max = 200)
    private String brokerid;
    
    //TODO set to lazy after upgrate to spring3.2
    @NotNull
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "serviceSupplier",orphanRemoval=true)
    @JsonManagedReference
    private Set<ServiceAddress> addresses = new HashSet<ServiceAddress>();
}
