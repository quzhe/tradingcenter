package com.nanhua.trading.domain.correlator;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.roo.addon.test.RooIntegrationTest;

@RooIntegrationTest(entity = Correlator.class)
public class CorrelatorIntegrationTest {

    @Test
    public void testMarkerMethod() {
    }
    @Autowired
    private CorrelatorDataOnDemand dod;
    
    @Test
    public void testFindCorrelator() {
        Correlator obj = dod.getRandomCorrelator();
        Assert.assertNotNull("Data on demand for 'Correlator' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Correlator' failed to provide an identifier", id);
        obj = Correlator.findCorrelator(id);
        Assert.assertNotNull("Find method for 'Correlator' illegally returned null for id '" + id + "'", obj);
        Assert.assertEquals("Find method for 'Correlator' returned the incorrect identifier", id, obj.getId());
        
        Assert.assertTrue("service instances is empty",!obj.getServiceInstances().isEmpty());
        Assert.assertTrue("service account is empty",!obj.getServiceInstances().iterator().next().getAccounts().isEmpty());
    }
    
    @Test
    public void testRemoveAccount() {
        Correlator obj = dod.getRandomCorrelator();
        Assert.assertNotNull("Data on demand for 'Correlator' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Correlator' failed to provide an identifier", id);
        obj = Correlator.findCorrelator(id);
        ServiceAccount sa =  obj.getServiceInstances().iterator().next().getAccounts().iterator().next();
        Assert.assertNotNull(ServiceAccount.findServiceAccount(sa.getId()));
        obj.getServiceInstances().iterator().next().getAccounts().remove(sa);
        obj.merge();
        Assert.assertNull(ServiceAccount.findServiceAccount(sa.getId()));
    }
    
    @Test
    public void testRemoveServiceInstance() {
        Correlator obj = dod.getRandomCorrelator();
        Assert.assertNotNull("Data on demand for 'Correlator' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Correlator' failed to provide an identifier", id);
        obj = Correlator.findCorrelator(id);
        ServiceInstance si =  obj.getServiceInstances().iterator().next();
        Assert.assertNotNull(ServiceInstance.findServiceInstance(si.getId()));
        obj.getServiceInstances().remove(si);
        obj.merge();
        Assert.assertNull(ServiceInstance.findServiceInstance(si.getId()));
    }
    
}
