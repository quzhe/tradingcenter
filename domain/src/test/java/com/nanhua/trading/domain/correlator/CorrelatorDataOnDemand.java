package com.nanhua.trading.domain.correlator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.roo.addon.dod.RooDataOnDemand;

@RooDataOnDemand(entity = Correlator.class)
public class CorrelatorDataOnDemand {
	
	@Autowired
	private ServiceInstanceDataOnDemand serviceInstanceDod;
	
	public Correlator getNewTransientCorrelator(int index) {
        Correlator obj = new Correlator();
        setHostname(obj, index);
        setName(obj, index);
        setNetworkType(obj, index);
        setPort(obj, index);
        
        ServiceInstance si = serviceInstanceDod.getNewTransientServiceInstance(index);
        si.setCorrelator(obj);
        obj.getServiceInstances().add(si);
        return obj;
    }
	
}
