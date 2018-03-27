package com.glens.pwCloudOs.websocket.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebMvc
@EnableWebSocket
public class AndroidWebSocketConfig extends WebMvcConfigurerAdapter implements
		WebSocketConfigurer {

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(androidSystemWebSocketHandler(),
				"/android/websocket").addInterceptors(
				new AndroidWebSocketHandshakeInterceptor());
		registry.addHandler(androidSystemWebSocketHandler(),
				"/android/websocket")
				.addInterceptors(new WebSocketHandshakeInterceptor())
				.withSockJS();
	}

	@Bean
	public WebSocketHandler androidSystemWebSocketHandler() {
		return new AndroidSystemWebSocketHandler();
	}

}
