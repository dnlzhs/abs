package com.dnlzhs.abs.security;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.oauth2.core.DelegatingOAuth2TokenValidator;
//import org.springframework.security.oauth2.core.OAuth2Error;
//import org.springframework.security.oauth2.core.OAuth2ErrorCodes;
//import org.springframework.security.oauth2.core.OAuth2TokenValidator;
//import org.springframework.security.oauth2.jwt.*;
import org.springframework.security.web.SecurityFilterChain;

import java.io.IOException;
import java.time.Instant;
import java.util.*;
import java.util.regex.Pattern;

//import static org.springframework.security.oauth2.jwt.JwtClaimNames.*;

@Configuration
public class SecurityConfiguration {
	private static final TypeReference<Map<String, Object>> MAP_TYPE = new TypeReference<>() {};
	private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
//	private static final List<String> TIMESTAMP_CLAIMS = List.of(EXP, IAT, NBF);
	private static final String PAYMENTTOOLS_CLAIM = "paymenttools";
	private static final String STORE_ID_CLAIM = "store_identifier";

	private static final String UUID_REGEX = "^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$";
	private static final Pattern UUID_PATTERN = Pattern.compile(UUID_REGEX, Pattern.CASE_INSENSITIVE);

/* 	@Bean
	JwtDecoder decoder(final Collection<OAuth2TokenValidator<Jwt>> validators) {
		final var validator = new DelegatingOAuth2TokenValidator<>(validators);

		return token -> {
			final var tokenParts = token.split("\\.");
			if (tokenParts.length < 2) throw new JwtException("JWT is invalid");

			final var header = Base64.getUrlDecoder().decode(tokenParts[0]);
			final var payload = Base64.getUrlDecoder().decode(tokenParts[1]);

			try {
				final var headers = OBJECT_MAPPER.readValue(header, MAP_TYPE);
				final var claims = OBJECT_MAPPER.readValue(payload, MAP_TYPE);
				convertTimestampClaims(claims);

				final var jwt = Jwt.withTokenValue(token)
					.headers(h -> h.putAll(headers))
					.claims(c -> c.putAll(claims))
					.build();

				final var result = validator.validate(jwt);
				if (result.hasErrors()) {
					throw new JwtValidationException(
						OAuth2ErrorCodes.INVALID_TOKEN,
						List.of(new OAuth2Error(OAuth2ErrorCodes.INVALID_TOKEN))
					);
				}

				return jwt;
			} catch (IOException e) {
				throw new JwtException("Could not parse claims");
			}
		};
	}
*/
	@Bean
	SecurityFilterChain tokenJwtSecurityChain(HttpSecurity http) throws Exception {
		return http.cors()
			.and()
	//		.csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	//		.and()
	//		.authorizeHttpRequests().requestMatchers("/actuator/**", "/swagger-ui*/**", "/v3/api-docs*/**").permitAll()
	//		.and()
	//		.authorizeHttpRequests().anyRequest().authenticated()
	//		.and()
	//		.oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
			.build();
	}

/*	@Bean
	JwtClaimValidator<Map<String, String>> paymenttoolsClaimValidator() {
		return new JwtClaimValidator<>(PAYMENTTOOLS_CLAIM, ptClaims -> {
			if (ptClaims == null) return false;
			final var storeId = ptClaims.get(STORE_ID_CLAIM);
			return storeId != null && UUID_PATTERN.matcher(storeId).matches();
		});
	}

	public static UUID getStoreId(Jwt jwt) {
		return UUID.fromString(jwt.getClaimAsMap(PAYMENTTOOLS_CLAIM).get(STORE_ID_CLAIM).toString());
	}

	private void convertTimestampClaims(Map<String, Object> claims) {
		TIMESTAMP_CLAIMS.forEach(claim -> claims.computeIfPresent(claim, (key, value) -> Instant.ofEpochSecond((int) value)));
	}
*/
}

