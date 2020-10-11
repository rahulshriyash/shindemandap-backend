package com.shindemandapdecorators.securiry;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shindemandapdecorators.entity.UserEntity;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Key;
import java.util.ArrayList;
import java.util.Date;
import static com.shindemandapdecorators.constants.SecurityConstants.*;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	private AuthenticationManager authenticationManager;

	public AuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
			throws AuthenticationException {
		Authentication authObj;
		try {
			System.out.println("in aut filter " + req.getParameter("username"));
			UserEntity applicationUser = new ObjectMapper().readValue(req.getInputStream(), UserEntity.class);
			System.out.println("applicationUser " + applicationUser.getUsername() + applicationUser.getPassword());
			authObj = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					applicationUser.getUsername(), applicationUser.getPassword(), new ArrayList<>()));
			System.out.println("authObj = " + authObj.toString());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return authObj;
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain,
			Authentication auth) throws IOException, ServletException {
		System.out.println("successfulAuthentication" + req + auth);
		Date exp = new Date(System.currentTimeMillis() + EXPIRATION_TIME);
		Key key = Keys.hmacShaKeyFor(KEY.getBytes());
		Claims claims = Jwts.claims().setSubject(((User) auth.getPrincipal()).getUsername());
		String token = Jwts.builder().setClaims(claims).signWith(key, SignatureAlgorithm.HS512).setExpiration(exp)
				.compact();
		System.out.println("token = " + token);
		res.addHeader("token", token);

	}
}
