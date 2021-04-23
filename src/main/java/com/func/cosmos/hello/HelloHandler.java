package com.func.cosmos.hello;

import com.microsoft.azure.functions.*;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;
import org.springframework.cloud.function.adapter.azure.AzureSpringBootRequestHandler;

import com.func.cosmos.user.Greeting;
import com.func.cosmos.user.User;

import java.util.Optional;

@SuppressWarnings("deprecation")
public class HelloHandler extends AzureSpringBootRequestHandler<User, Greeting> {

  @FunctionName("hello")
  public HttpResponseMessage execute(
      @HttpTrigger(
        name = "request", methods = { HttpMethod.GET, HttpMethod.POST }, 
        authLevel = AuthorizationLevel.ANONYMOUS) HttpRequestMessage<Optional<User>> request,
      ExecutionContext context) {

    User user = request.getBody().filter((u -> u.getLastName() != null))
        .orElseGet(() -> new User("1",request.getQueryParameters().getOrDefault("name", "world"),"",""));

    context.getLogger().info("Greeting user name: " + user.getLastName());
    return request.createResponseBuilder(HttpStatus.OK).body(handleRequest(user, context)).header("Content-Type", "application/json").build();
  }
}
