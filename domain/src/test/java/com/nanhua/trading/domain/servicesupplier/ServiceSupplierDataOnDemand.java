package com.nanhua.trading.domain.servicesupplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.roo.addon.dod.RooDataOnDemand;

import com.nanhua.trading.domain.datadict.NetworkTypeDataOnDemand;

@RooDataOnDemand(entity = ServiceSupplier.class)
public class ServiceSupplierDataOnDemand {
	
	@Autowired
	private NetworkTypeDataOnDemand networkTypeDod;
	
	public ServiceSupplier getNewTransientServiceSupplier(int index) {
        ServiceSupplier obj = new ServiceSupplier();
        setServiceIdPrefix(obj, index);
        setMaxAccount(obj, index);
        
        ServiceAddress sa = new ServiceAddress();
        sa.setAddress("address");
        sa.setDescription("desc");
        sa.setNetworkType(networkTypeDod.getRandomNetworkType());
        sa.setServiceSupplier(obj);
        obj.getAddresses().add(sa);
        setName(obj, index);
        return obj;
    }
	
}
