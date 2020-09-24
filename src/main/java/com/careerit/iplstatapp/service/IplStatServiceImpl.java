package com.careerit.iplstatapp.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.careerit.iplstatapp.dao.IplStatDao;
import com.careerit.iplstatapp.dto.IRoleAmountDTO;
import com.careerit.iplstatapp.dto.RoleCountDTO;
import com.careerit.iplstatapp.dto.LabelDTO;
import com.careerit.iplstatapp.dto.PlayerDTO;

@Service
public class IplStatServiceImpl implements IplStatService {

	@Autowired
	private IplStatDao iplStatDao;

	private Logger log = LoggerFactory.getLogger(IplStatServiceImpl.class);

	@Override
	public LabelDTO allTeamsLables() {

		LabelDTO labelDto = iplStatDao.allTeamsLables();
		log.info("Label details: {}" + labelDto);
		return labelDto;
	}

	@Override
	public List<PlayerDTO> getPlayersByTeam(String teamLabel) {
		Assert.notNull(teamLabel, "Team label can't be empty or null");
		List<PlayerDTO> players = iplStatDao.getPlayersByTeam(teamLabel);
		log.info("Player count is :{}", players.size());
		return players;
	}

	@Override
	public List<RoleCountDTO> getCountByRole(String teamLabel) {
		Assert.notNull(teamLabel, "Team label can't be empty or null");
		List<RoleCountDTO> roleCount = iplStatDao.getCountByRole(teamLabel);
		log.info("Total roles :{} found", roleCount.size());
		return roleCount;
	}

	@Override
	public List<IRoleAmountDTO> getAmountByRole(String teamLabel) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PlayerDTO> getByRole(String teamLabel, String role) {
		Assert.notNull(teamLabel, "Team label can't be empty or null");
		Assert.notNull(role, "Role can't be empty or null");
		List<PlayerDTO> players = iplStatDao.getByRole(teamLabel, role);
		log.info("Player count is :{} for team {} and role: {}", players.size(), teamLabel, role);
		return players;
	}

}
