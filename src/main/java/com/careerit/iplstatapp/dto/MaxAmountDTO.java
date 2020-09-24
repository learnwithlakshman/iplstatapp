package com.careerit.iplstatapp.dto;

import java.util.List;
import java.util.Map;

import com.careerit.iplstatapp.domain.Player;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MaxAmountDTO {
	
	private Map<String,List<Player>> maxPlayersMap;
		
}
