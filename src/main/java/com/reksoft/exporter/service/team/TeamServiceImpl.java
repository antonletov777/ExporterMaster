package com.reksoft.exporter.service.team;


import com.reksoft.exporter.model.Player;
import com.reksoft.exporter.model.Team;
import com.reksoft.exporter.repository.team.TeamApiRepository;
import com.reksoft.exporter.repository.dto.TeamViewDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {

    private final TeamApiRepository teamApiRepository;

    @Override
    public List<Team> getTeams() {
        List<TeamViewDto> teamViewDtos = teamApiRepository.getTeams();
        return teamViewDtos.stream().map(this::map).toList();
    }

    private Team map(TeamViewDto teamViewDto) {
        Team team = new Team();
        team.setId(teamViewDto.getId());
        team.setTeamName(teamViewDto.getName());
        team.setPlayersNames(makeCorrectPlayersNames(teamViewDto.getPlayers()));
        return team;
    }

    String makeCorrectPlayersNames(List<Player> playersNames)
    {
        return playersNames.stream()
                .map(Player::getCombinedName)
                .filter(Objects::nonNull)
                .filter(name -> !name.isEmpty())
                .collect(Collectors.joining(", "));
    }
}