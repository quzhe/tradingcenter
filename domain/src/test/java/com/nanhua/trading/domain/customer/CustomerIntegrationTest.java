package com.nanhua.trading.domain.customer;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.roo.addon.test.RooIntegrationTest;

import com.nanhua.trading.domain.correlator.Correlator;
import com.nanhua.trading.domain.correlator.ServiceInstance;

@RooIntegrationTest(entity = Customer.class)
public class CustomerIntegrationTest {
	
	@Autowired
    private CustomerDataOnDemand dod;
	
    @Test
    public void testMarkerMethod() {
    }
    
    @Test
    public void testRemoveCorrelator() {
    	Customer obj = dod.getRandomCustomer();
    	
    	Assert.assertTrue("correlator is empty",!obj.getCorrelators().isEmpty());
        
    	Correlator correlator = obj.getCorrelators().iterator().next();
    	obj.getCorrelators().remove(correlator);
    	
    	obj.merge();
    	
        Assert.assertNotNull("Correlator not removed",Correlator.findCorrelator(correlator.getId()));
    }
    
}
