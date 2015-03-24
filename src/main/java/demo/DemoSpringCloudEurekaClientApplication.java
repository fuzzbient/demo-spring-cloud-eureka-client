package demo;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestOperations;

@SpringBootApplication
@RestController
@EnableDiscoveryClient
@EnableCircuitBreaker
@EnableHystrixDashboard
public class DemoSpringCloudEurekaClientApplication {
	private Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	RestOperations rest;
	
	@RequestMapping("/")
	public Map<String,String> home(HelloService service) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("DemoSpringCloudEurekaClientApplication.home()");
		}
		return service.remoteHello(rest);
	}
	
    public static void main(String[] args) {
        SpringApplication.run(DemoSpringCloudEurekaClientApplication.class, args);
    }

}
