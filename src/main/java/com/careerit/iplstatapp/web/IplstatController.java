package com.careerit.iplstatapp.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.careerit.iplstatapp.dto.RoleCountDTO;
import com.careerit.iplstatapp.dto.LabelDTO;
import com.careerit.iplstatapp.dto.PlayerDTO;
import com.careerit.iplstatapp.service.IplStatService;

@RestController
@RequestMapping("/api/v1/iplstat")
public class IplstatController {

	@Autowired
	private IplStatService iplStatService;

	@GetMapping("/labels")
	public LabelDTO getAllLables() {

		return iplStatService.allTeamsLables();
	}
	
	@GetMapping("/players/{label}")
	public List<PlayerDTO> getPlayersByLabels(@PathVariable("label")String teamLabel){
		return iplStatService.getPlayersByTeam(teamLabel);
	}
	
	@GetMapping("/rolecount/{label}")
	public List<RoleCountDTO> getRoleCountByLabels(@PathVariable("label")String teamLabel){
		return iplStatService.getCountByRole(teamLabel);
	}
	
	@GetMapping("/players/{label}/{role}")
	public List<PlayerDTO> getPlayersByRoleAndTeam(@PathVariable("label")String teamLabel,@PathVariable("role")String role){
		return iplStatService.getByRole(teamLabel, role);
	}
}
