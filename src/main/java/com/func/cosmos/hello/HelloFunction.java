package com.func.cosmos.hello;

import org.springframework.stereotype.Component;
//import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Function;

import com.func.cosmos.user.Greeting;
import com.func.cosmos.user.User;

@Component
public class HelloFunction implements Function<Mono<User>, Mono<Greeting>> {

	public Mono<Greeting> apply(Mono<User> mono) {
		return mono.map(user -> new Greeting("Hello, " + user.getLastName() + "!\n"));
	}
}
