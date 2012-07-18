TradeCenter
======
1.install jdk, set JAVA_HOME

2.install maven, set M2_HOME, add M2_HOME/bin into PATH

3.install spring roo, set ROO_HOME, add ROO_HOME/bin into PATH

4.git clone tc

5.cd tradingcenter

6.open a terminal, run roo. windows will have a character error, please comment them like:
	private void generateKSTestOMSServiceSuppliers(){
	
		/*
		ServiceSupplier ss = createServiceSupplier("金仕达交易测试","ks_trade_test","3695AE21");
		createAddress(ss,"tcp://122.224.197.22:15159","电信交易测试1",chinaTelecom);
		createAddress(ss,"tcp://124.160.44.166:15159","联通交易测试1",chinaUnicom);
		ss.persist();
		*/
	}
	private void generateKSTestMDServiceSuppliers(){
		/*
		ServiceSupplier ss = createServiceSupplier("金仕达行情测试","ks_md_test","3695AE21");
		createAddress(ss,"tcp://122.224.197.22:15159","电信交易测试1",chinaTelecom);
		createAddress(ss,"tcp://124.160.44.166:15159","联通交易测试1",chinaUnicom);
		ss.persist();
		*/
	}
	then move comment after roo generate code
	
7.open new terminal, run "mvn clean install", then "mvn eclipse:eclipse" after success

8.run "mvn jetty:run"

9."127.0.0.1:8080/ui" in firefox
