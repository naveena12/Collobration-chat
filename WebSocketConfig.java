/*package com.niit.colloboration.config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

@Configuration//mark the class as configuration for spring container.
@EnableWebSocketMessageBroker
@ComponentScan(basePackages="com.niit")
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer{
	private static final Logger Logger = LoggerFactory.getLogger(WebSocketConfig.class);

	@Override
	public void configureMessageBroker(MessageBrokerRegistry config) {
		Logger.debug("Starting method config message Broker");
		config.enableSimpleBroker("/topic", "/queue");//topic-group chat and queue for personal chat.
		config.setApplicationDestinationPrefixes("/app");
		Logger.debug("Ending method Config Message Broker");
	}
	
	public void registerStompEndpoints(StompEndpointRegistry arg0) {
		// TODO Auto-generated method stub
		Logger.debug("Starting method registerStompEndPoints");
		registery.addEndpoint("/chat").withSockJS();
		Logger.debug("Ending method registerStompEndPoints");
		registery.addEndpoint("/chat_forum").withSockJS();
		}

		
	}//override the methods to customise protocols and endpoints. {


*/