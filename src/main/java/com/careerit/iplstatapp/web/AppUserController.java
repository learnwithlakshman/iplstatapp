package com.careerit.iplstatapp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.careerit.iplstatapp.domain.AppUser;
import com.careerit.iplstatapp.service.AppUserService;

@RestController
public class AppUserController {
	
	@Autowired
	private AppUserService appUserService;

	@PostMapping("/api/user/register")
	public String registerUser(@RequestBody AppUser appUser) {
		String id = appUserService.registerUser(appUser);
		return id;
	}

}
