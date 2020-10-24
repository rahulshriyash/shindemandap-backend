package com.shindemandapdecorators.securiry;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.shindemandapdecorators.entity.UserEntity;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;

import static com.shindemandapdecorators.constants.SecurityConstants.*;

public class AuthorizationFilter extends BasicAuthenticationFilter {

	public AuthorizationFilter(AuthenticationManager authManager) {
		super(authManager);
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String header = request.getHeader(HEADER_NAME);
		System.out.println("header.." + header);
		if (header == null) {
			chain.doFilter(request, response);
			return;
		}

		UsernamePasswordAuthenticationToken authentication = authenticate(request);
		System.out.println("authentication.." + authentication);

		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		chain.doFilter(request, response);
	}

	private UsernamePasswordAuthenticationToken authenticate(HttpServletRequest request) {
		String token = request.getHeader(HEADER_NAME);
		System.out.println("token.." + token);

		if (token != null) {

			try {
				Claims user = Jwts.parser().setSigningKey(Keys.hmacShaKeyFor(KEY.getBytes())).parseClaimsJws(token)
						.getBody();
				System.out.println("claims user.." + user);
				if (user != null) {
					System.out.println("user not null.......");

					return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
				} else {
					System.out.println("user  null.......");
					return null;
				}
			} catch (SignatureException ex) {
				System.out.println("Invalid JWT signature");
			} catch (MalformedJwtException ex) {
				System.out.println("Invalid JWT token");
			} catch (ExpiredJwtException ex) {
				System.out.println("Expired JWT token");
			} catch (UnsupportedJwtException ex) {
				System.out.println("Unsupported JWT token");
			} catch (IllegalArgumentException ex) {
				System.out.println("JWT claims string is empty.");
			}

		}
		return null;
	}
}