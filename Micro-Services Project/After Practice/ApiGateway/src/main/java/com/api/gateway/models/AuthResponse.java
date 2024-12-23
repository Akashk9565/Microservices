package com.api.gateway.models;

import java.util.Collection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {
	
	private String userId;
	private String acceToken;
	private String refreshToken;
	private Long expireAt;
	private Collection<String> authorities;

}
