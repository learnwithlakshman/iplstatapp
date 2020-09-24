package com.careerit.iplstatapp.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.aggregation.UnwindOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

import com.careerit.iplstatapp.dto.IRoleAmountDTO;
import com.careerit.iplstatapp.dto.RoleCountDTO;
import com.careerit.iplstatapp.dto.LabelDTO;
import com.careerit.iplstatapp.dto.MaxAmountDTO;
import com.careerit.iplstatapp.dto.PlayerDTO;

@Repository
public class IplStatDaoImpl implements IplStatDao {

	private Logger log = LoggerFactory.getLogger(IplStatDaoImpl.class);

	@Autowired
	private MongoOperations monogOperations;

	@Override
	public LabelDTO allTeamsLables() {

		GroupOperation group = Aggregation.group().addToSet("label").as("labels");
		ProjectionOperation project = Aggregation.project().andExclude("_id");

		Aggregation aggr = Aggregation.newAggregation(group, project);
		log.debug("Query :{}" + aggr);

		AggregationResults<LabelDTO> result = monogOperations.aggregate(aggr, "team", LabelDTO.class);

		LabelDTO labelDTO = result.getUniqueMappedResult();
		log.info("Total teams found in DB:{}", labelDTO.getLabels().size());
		return labelDTO;
	}

	@Override
	public List<PlayerDTO> getPlayersByTeam(String teamLabel) {
		MatchOperation match = Aggregation.match(Criteria.where("label").is(teamLabel));
		UnwindOperation unwind = Aggregation.unwind("players");
		ProjectionOperation project = Aggregation.project().and("players.name").as("name").and("players.role")
				.as("role").and("players.price").as("price").and("label").as("label").andExclude("_id");
		Aggregation aggr = Aggregation.newAggregation(match, unwind, project);
		log.debug("Query :{}" + aggr);

		AggregationResults<PlayerDTO> result = monogOperations.aggregate(aggr, "team", PlayerDTO.class);
		List<PlayerDTO> playerList = result.getMappedResults();
		log.info("Total players found for team {} is :{}", teamLabel, playerList.size());
		return playerList;
	}

	@Override
	public List<RoleCountDTO> getCountByRole(String teamLabel) {
		MatchOperation match = Aggregation.match(Criteria.where("label").is(teamLabel));
		UnwindOperation unwind = Aggregation.unwind("players");
		GroupOperation group = Aggregation.group("players.role").count().as("count");

		ProjectionOperation project = Aggregation.project().and("_id").as("role").and("count").as("count")
				.andExclude("_id");
		Aggregation aggr = Aggregation.newAggregation(match, unwind, group, project);
		log.debug("Query :{}" + aggr);

		AggregationResults<RoleCountDTO> result = monogOperations.aggregate(aggr, "team", RoleCountDTO.class);
		List<RoleCountDTO> roleCountList = result.getMappedResults();
		log.info("Total roles found for team {} is :{}", teamLabel, roleCountList.size());
		return roleCountList;
	}
	
	@Override
	public List<PlayerDTO> getByRole(String teamLabel, String role) {
		MatchOperation match1 = Aggregation.match(Criteria.where("label").is(teamLabel));
		UnwindOperation unwind = Aggregation.unwind("players");
		MatchOperation match2 = Aggregation.match(Criteria.where("players.role").is(role));
		ProjectionOperation project = Aggregation.project().and("players.name").as("name").and("players.role")
				.as("role").and("players.price").as("price").and("label").as("label").andExclude("_id");
		Aggregation aggr = Aggregation.newAggregation(match1, unwind, match2,project);
		log.debug("Query :{}" + aggr);

		AggregationResults<PlayerDTO> result = monogOperations.aggregate(aggr, "team", PlayerDTO.class);
		List<PlayerDTO> playerList = result.getMappedResults();
		log.info("Total players found for team {} is :{}", teamLabel, playerList.size());
		return playerList;
	}

	@Override
	public List<IRoleAmountDTO> getAmountByRole(String teamLabel) {
		// TODO Auto-generated method stub
		return null;
	}

	

	@Override
	public MaxAmountDTO getMaxAmountByRole() {
		// TODO Auto-generated method stub
		return null;
	}

}
