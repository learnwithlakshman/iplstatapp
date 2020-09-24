package com.careerit.iplstatapp.domain;

import java.util.List;

import org.springframework.data.annotation.Id;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Team {
	
		@Id
		private String id;
		private String name;
		private String coach;
		private String home;
		private String label;
		private String city;
		private List<Player> players;
}
