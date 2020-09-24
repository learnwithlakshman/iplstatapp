package com.careerit.iplstatapp.dao;

import java.util.List;

import com.careerit.iplstatapp.dto.IRoleAmountDTO;
import com.careerit.iplstatapp.dto.RoleCountDTO;
import com.careerit.iplstatapp.dto.LabelDTO;
import com.careerit.iplstatapp.dto.MaxAmountDTO;
import com.careerit.iplstatapp.dto.PlayerDTO;


public interface IplStatDao {
	
	
	public LabelDTO allTeamsLables();
	public List<PlayerDTO> getPlayersByTeam(String teamLabel);
	public List<RoleCountDTO> getCountByRole(String teamLabel);
	public List<IRoleAmountDTO> getAmountByRole(String teamLabel);

	public List<PlayerDTO> getByRole(String teamLabel, String role);
	public MaxAmountDTO getMaxAmountByRole();

	
}
