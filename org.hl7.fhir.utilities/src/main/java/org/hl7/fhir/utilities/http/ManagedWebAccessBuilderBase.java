package org.hl7.fhir.utilities.http;

import lombok.Getter;
import org.hl7.fhir.utilities.settings.ServerDetailsPOJO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class ManagedWebAccessBuilderBase<B extends ManagedWebAccessBuilderBase<B>> {
  @Getter
  private final String userAgent;
  @Getter
  private HTTPAuthenticationMode authenticationMode;
  @Getter
  private String username;
  @Getter
  private String password;
  @Getter
  private String token;
  @Getter
  private String accept;
  @Getter
  private final List<ServerDetailsPOJO> serverAuthDetails;
  @Getter
  private Map<String, String> headers = new HashMap<String, String>();

  public ManagedWebAccessBuilderBase(String userAgent, List<ServerDetailsPOJO> serverAuthDetails) {
    this.userAgent = userAgent;
    this.serverAuthDetails = serverAuthDetails;
  }

  @SuppressWarnings("unchecked")
  final B self() {
    return (B) this;
  }

  public B withAccept(String accept) {
    this.accept = accept;
    return self();
  }

  public B withHeader(String name, String value) {
    headers.put(name, value);
    return self();
  }

  public B withBasicAuth(String username, String password) {
    this.authenticationMode = HTTPAuthenticationMode.BASIC;
    this.username = username;
    this.password = password;
    return self();
  }

  public B withToken(String token) {
    this.authenticationMode = HTTPAuthenticationMode.TOKEN;
    this.token = token;
    return self();
  }
}
