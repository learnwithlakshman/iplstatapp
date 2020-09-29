package com.careerit.iplstatapp.domain;

import org.springframework.data.annotation.Id;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppUser {

		@Id
		private String id;
		private String username;
		private String email;
		private String password;
}
