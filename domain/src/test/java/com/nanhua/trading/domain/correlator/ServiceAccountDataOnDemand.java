package com.nanhua.trading.domain.correlator;

import org.springframework.roo.addon.dod.RooDataOnDemand;

@RooDataOnDemand(entity = ServiceAccount.class)
public class ServiceAccountDataOnDemand {
	
	public ServiceAccount getNewTransientServiceAccount(int index) {
        ServiceAccount obj = new ServiceAccount();
        setDescription(obj, index);
        setPassword(obj, index);
        
        //setServiceInstance(obj, index);
        setUsername(obj, index);
        return obj;
    }
	
}
