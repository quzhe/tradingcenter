package com.nanhua.trading.domain.correlator;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonManagedReference;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

import com.nanhua.trading.domain.datadict.NetworkType;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
@JsonIgnoreProperties(ignoreUnknown=true)
public class Correlator {

    @NotNull
    private String hostname;

    @NotNull
    private String port;

    @NotNull
    private String name;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "network_type_id")
    private NetworkType networkType;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "correlator",orphanRemoval=true)
    @JsonManagedReference
    private Set<ServiceInstance> serviceInstances = new HashSet<ServiceInstance>();
}
