package com.nanhua.trading.domain.correlator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.roo.addon.dod.RooDataOnDemand;

@RooDataOnDemand(entity = ServiceInstance.class)
public class ServiceInstanceDataOnDemand {
	
	@Autowired
    private ServiceAccountDataOnDemand serviceAccountDataOnDemand;
	
	@Autowired
	private CorrelatorDataOnDemand correlatorDataOnDemand;
	
	public ServiceInstance getNewTransientServiceInstance(int index) {
        ServiceInstance obj = new ServiceInstance();
        //setCorrelator(obj, index);
        //obj.setCorrelator(correlatorDataOnDemand.getNewTransientCorrelator(index));
        setServiceid(obj, index);
        setSupplier(obj, index);
        
        ServiceAccount tc = serviceAccountDataOnDemand.getNewTransientServiceAccount(index);
        tc.setServiceInstance(obj);
        obj.getAccounts().add(tc);
        return obj;
    }
	
}
