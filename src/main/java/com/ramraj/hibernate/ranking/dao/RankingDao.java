package com.ramraj.hibernate.ranking.dao;

import java.util.Map;

import com.ramraj.hibernate.ranking.entity.Person;

public interface RankingDao {
	int getRankingFor(String subject, String skill);

	void addRanking(String subject, String observer, String skill, int ranking);

	void updateRanking(String subject, String observer, String skill, int ranking);

	void removeRanking(String subject, String observer, String skill);

	Map<String, Integer> findRankingsFor(String subject);

	Person findBestPersonFor(String skill);
}
