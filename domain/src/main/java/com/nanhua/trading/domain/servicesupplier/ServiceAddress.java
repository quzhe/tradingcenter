package com.nanhua.trading.domain.servicesupplier;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.codehaus.jackson.annotate.JsonBackReference;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

import com.nanhua.trading.domain.datadict.NetworkType;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
@JsonIgnoreProperties(ignoreUnknown=true)
public class ServiceAddress {
	
	
    @NotNull
    @Size(max = 200)
    private String address;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "network_type_id")
    private NetworkType networkType;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "service_supplier_id")
    @JsonBackReference
    private ServiceSupplier serviceSupplier;

    @NotNull
    @Size(max = 200)
    private String description;
}
