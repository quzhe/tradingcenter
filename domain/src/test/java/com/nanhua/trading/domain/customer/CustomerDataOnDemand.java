package com.nanhua.trading.domain.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.roo.addon.dod.RooDataOnDemand;

import com.nanhua.trading.domain.correlator.CorrelatorDataOnDemand;

@RooDataOnDemand(entity = Customer.class)
public class CustomerDataOnDemand {
	
	
	@Autowired
	private CorrelatorDataOnDemand correlatorDod;
	
	public Customer getNewTransientCustomer(int index) {
        Customer obj = new Customer();
        setAddress(obj, index);
        setEmail(obj, index);
        setEnabled(obj, index);
        setMobile(obj, index);
        setName(obj, index);
        setPassword(obj, index);
        setPhone(obj, index);
        setUsername(obj, index);
        obj.getCorrelators().add(correlatorDod.getRandomCorrelator());
        return obj;
	}
	
	
	
}
