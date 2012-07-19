package com.nanhua.trading.web.ui.dev;

import org.apache.log4j.Logger;

import com.nanhua.trading.domain.account.Authority;
import com.nanhua.trading.domain.account.User;
import com.nanhua.trading.domain.datadict.NetworkType;
import com.nanhua.trading.domain.servicesupplier.ServiceAddress;
import com.nanhua.trading.domain.servicesupplier.ServiceSupplier;

//TODO make me configrable
public class DevHelper {
	private Logger logger=Logger.getLogger(this.getClass().getName());
	private NetworkType chinaTelecom;
	private NetworkType chinaUnicom;
	private NetworkType internal;
	
	public void generateInitData(){
		generateAdmin();
		generateNetworkType();
		chinaTelecom = NetworkType.findNetworkByName("电信");
		chinaUnicom  = NetworkType.findNetworkByName("联通");
		internal = NetworkType.findNetworkByName("内网");
		//gen CTP init data
		generateCTPOMSServiceSuppliers();
		generateCTPMDServiceSuppliers();
		generateCTPTestOMSServiceSuppliers();
		generateCTPTestMDServiceSuppliers();
		//KS init data
		generateKSTestOMSServiceSuppliers();
		generateKSTestMDServiceSuppliers();
	}
	 
	
	private void generateAdmin(){
		Authority au = new Authority();
		au.setAuthority("ROLE_ADMIN");
		
		User user = new User();
		user.setUsername("admin");
		user.setEnabled(true);
		user.setPassword("password");
		user.getAuthoritiesBySet().add(au);
		user.setEmail("zhe.qu@nawaa.com");
		user.persist();
		logger.info("init admin data");
	}
	
	private void generateNetworkType(){
		NetworkType nt = new NetworkType();
		nt.setName("电信");
		nt.persist();
		nt = new NetworkType();
		nt.setName("联通");
		nt.persist();
		nt = new NetworkType();
		nt.setName("内网");
		nt.persist();
	}
	
	private void generateKSTestOMSServiceSuppliers(){
	
		
		ServiceSupplier ss = createServiceSupplier("金仕达交易测试","ks_trade_test","3695AE21");
		createAddress(ss,"tcp://122.224.197.22:15159","电信交易测试1",chinaTelecom);
		createAddress(ss,"tcp://124.160.44.166:15159","联通交易测试1",chinaUnicom);
		ss.persist();
		
	}
	private void generateKSTestMDServiceSuppliers(){
		
		ServiceSupplier ss = createServiceSupplier("金仕达行情测试","ks_md_test","3695AE21");
		createAddress(ss,"tcp://122.224.197.22:15159","电信交易测试1",chinaTelecom);
		createAddress(ss,"tcp://124.160.44.166:15159","联通交易测试1",chinaUnicom);
		ss.persist();
		
	}
	private void generateCTPTestOMSServiceSuppliers(){
		ServiceSupplier ss = createServiceSupplier("CTP交易测试","ctp_trade_test","2030");
		createAddress(ss,"tcp://asp-sim2-dx-front1.financial-trading-platform.com:26205","电信交易测试1",chinaTelecom);
		createAddress(ss,"tcp://asp-sim2-front1.financial-trading-platform.com:26205","联通交易测试1",chinaUnicom);
		ss.persist();
	}
	private void generateCTPTestMDServiceSuppliers(){
		ServiceSupplier ss = createServiceSupplier("CTP行情测试","ctp_md_test","2030");
		createAddress(ss,"tcp://asp-sim2-dx-front1.financial-trading-platform.com:26213","电信行情测试1",chinaTelecom);
		createAddress(ss,"tcp://asp-sim2-front1.financial-trading-platform.com:26213","联通行情测试1",chinaUnicom);
		ss.persist();
	}
	private void generateCTPMDServiceSuppliers(){
		ServiceSupplier ss = createServiceSupplier("CTP行情","ctp_md","8050");
		createAddress(ss,"tcp://nhqh-front1.financial-trading-platform.com:41213","电信行情1",chinaTelecom);
		createAddress(ss,"tcp://nhqh-front2.financial-trading-platform.com:41213","电信行情2",chinaTelecom);
		createAddress(ss,"tcp://nhqh-front3.financial-trading-platform.com:41213","电信行情3",chinaTelecom);
		createAddress(ss,"tcp://nhqh-front4.financial-trading-platform.com:41213","电信行情4",chinaTelecom);
		createAddress(ss,"tcp://nhqh-front9.financial-trading-platform.com:41213","电信行情5",chinaTelecom);
		
		createAddress(ss,"tcp://nhqh-front5.financial-trading-platform.com:41213","联通行情1",chinaUnicom);
		createAddress(ss,"tcp://nhqh-front6.financial-trading-platform.com:41213","联通行情2",chinaUnicom);
		createAddress(ss,"tcp://nhqh-front7.financial-trading-platform.com:41213","联通行情3",chinaUnicom);
		createAddress(ss,"tcp://nhqh-front8.financial-trading-platform.com:41213","联通行情4",chinaUnicom);
		createAddress(ss,"tcp://nhqh-front10.financial-trading-platform.com:41213","联通行情5",chinaUnicom);
		
		createAddress(ss,"tcp://172.17.28.1:41213","内网行情1",internal);
		createAddress(ss,"tcp://172.17.28.2:41213","内网行情2",internal);
		createAddress(ss,"tcp://172.17.28.3:41213","内网行情3",internal);
		createAddress(ss,"tcp://172.17.28.4:41213","内网行情4",internal);
		ss.persist();
	}
	private void generateCTPOMSServiceSuppliers(){
		ServiceSupplier ss = createServiceSupplier("CTP交易","ctp_trade","8050");
		createAddress(ss,"tcp://nhqh-front1.financial-trading-platform.com:41205","电信交易1",chinaTelecom);
		createAddress(ss,"tcp://nhqh-front2.financial-trading-platform.com:41205","电信交易2",chinaTelecom);
		createAddress(ss,"tcp://nhqh-front3.financial-trading-platform.com:41205","电信交易3",chinaTelecom);
		createAddress(ss,"tcp://nhqh-front4.financial-trading-platform.com:41205","电信交易4",chinaTelecom);
		createAddress(ss,"tcp://nhqh-front9.financial-trading-platform.com:41205","电信交易5",chinaTelecom);
		
		createAddress(ss,"tcp://nhqh-front5.financial-trading-platform.com:41205","联通交易1",chinaUnicom);
		createAddress(ss,"tcp://nhqh-front6.financial-trading-platform.com:41205","联通交易2",chinaUnicom);
		createAddress(ss,"tcp://nhqh-front7.financial-trading-platform.com:41205","联通交易3",chinaUnicom);
		createAddress(ss,"tcp://nhqh-front8.financial-trading-platform.com:41205","联通交易4",chinaUnicom);
		createAddress(ss,"tcp://nhqh-front10.financial-trading-platform.com:41205","联通交易5",chinaUnicom);
		
		createAddress(ss,"tcp://172.17.28.1:41205","内网交易1",internal);
		createAddress(ss,"tcp://172.17.28.2:41205","内网交易2",internal);
		createAddress(ss,"tcp://172.17.28.3:41205","内网交易3",internal);
		createAddress(ss,"tcp://172.17.28.4:41205","内网交易4",internal);
		ss.persist();
	}
	private void createAddress(ServiceSupplier ss,String address,String desc,NetworkType network){
		ServiceAddress sa = new ServiceAddress();
		sa.setAddress(address);
		sa.setDescription(desc);
		sa.setNetworkType(network);
		sa.setServiceSupplier(ss);
		ss.getAddresses().add(sa);
	}
	private ServiceSupplier createServiceSupplier(String name,String prefix,String brokerid){
		ServiceSupplier ss = new ServiceSupplier();
		ss.setName(name);
		ss.setServiceIdPrefix(prefix);
		ss.setBrokerid(brokerid);
		return ss;
	}
}
