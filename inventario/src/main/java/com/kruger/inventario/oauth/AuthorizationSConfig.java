package com.kruger.inventario.oauth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationSConfig extends AuthorizationServerConfigurerAdapter {

	@Value("${authorization.client.id}")
	private String authorizationClientId;
	
	@Value("${authorization.secret}")
	private String authorizationSecret;

    public static final String GRANT_TYPE_PASSWORD = "password";
    public static final String AUTHORIZATION_CODE = "authorization_code";
    public static final String REFRESH_TOKEN = "refresh_token";
    public static final String IMPLICIT = "implicit";

    public static final String SCOPE_READ = "read";
    public static final String SCOPE_WRITE = "write";
    public static final String TRUST = "trust";

    public static final int ACCESS_TOKEN_VALIDITY_SECONDS = 5000;
    public static final int FREFRESH_TOKEN_VALIDITY_SECONDS = 5000;

    @Bean
    public TokenStore tokenStore() {
        return new InMemoryTokenStore();
    }

    @Autowired
    private AuthenticationManager authenticationManager;
       
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        super.configure(security);
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
               .withClient(authorizationClientId)
               .secret(authorizationSecret)
               .authorizedGrantTypes("client_credentials", "password")
               .authorities("ROLE_CLIENT","ROLE_TRUSTED_CLIENT")
               .scopes(SCOPE_READ, SCOPE_WRITE, TRUST)
               .resourceIds("oauth2-resource")
               .accessTokenValiditySeconds(ACCESS_TOKEN_VALIDITY_SECONDS)
               .refreshTokenValiditySeconds(FREFRESH_TOKEN_VALIDITY_SECONDS);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(tokenStore())
                 .authenticationManager(authenticationManager);
    }
}