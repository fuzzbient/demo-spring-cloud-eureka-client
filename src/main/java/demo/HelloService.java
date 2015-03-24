package demo;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestOperations;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Component
public class HelloService {
	private Log logger = LogFactory.getLog(getClass());
	
	@SuppressWarnings("unchecked")
	@HystrixCommand(fallbackMethod = "fallbackHello")
	public Map<String,String> remoteHello(RestOperations rest) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("HelloService.getRemoteHello()");
		}
		return rest.getForObject("http://demo-eureka-service/", Map.class);
	}
	
	public Map<String,String> fallbackHello(RestOperations rest) {
		if (logger.isDebugEnabled()) {
			logger.debug("HelloService.getHelloFallback()");
		}		
		Map<String,String> result = new HashMap<String,String>();
		result.put("fallbackMethod", "true");
		result.put("message", "Service is temporarily not available.");
		return result;
	} 
}
