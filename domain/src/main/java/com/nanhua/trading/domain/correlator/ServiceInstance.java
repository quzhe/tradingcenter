package com.nanhua.trading.domain.correlator;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.codehaus.jackson.annotate.JsonBackReference;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonManagedReference;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

import com.nanhua.trading.domain.servicesupplier.ServiceSupplier;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
@JsonIgnoreProperties(ignoreUnknown=true)
public class ServiceInstance {

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "service_supplier_id")
    private ServiceSupplier supplier;

    @NotNull
    private String serviceid;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "correlator_id")
    @JsonBackReference
    private Correlator correlator;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "serviceInstance",orphanRemoval=true)
    @JsonManagedReference
    private Set<ServiceAccount> accounts = new HashSet<ServiceAccount>();
}
