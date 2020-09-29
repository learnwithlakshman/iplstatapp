package com.careerit.iplstatapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.careerit.iplstatapp.domain.AppUser;
import com.careerit.iplstatapp.repo.AppUserRepo;

@Service
public class AppUserServiceImpl implements AppUserService {

	@Autowired
	private AppUserRepo appUserRepo;
	
	@Autowired
	private EmailService emailService;
	
	@Override
	public String registerUser(AppUser appUser) {
		//Logic
		
		
		// Success
		AppUser savedUser = appUserRepo.save(appUser);
		
		emailService.sendEmail(savedUser.getEmail());
		
		return savedUser.getId();
	}

	
	
}
