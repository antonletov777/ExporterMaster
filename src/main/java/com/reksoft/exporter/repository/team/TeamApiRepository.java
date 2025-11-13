package com.reksoft.exporter.repository.team;

import com.reksoft.exporter.properties.ApiProperties;
import com.reksoft.exporter.repository.dto.TeamViewDto;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.springframework.http.HttpMethod.GET;

@Repository
@RequiredArgsConstructor
public class TeamApiRepository implements TeamRepository {

    private final RestTemplate restTemplate;
    private final ApiProperties apiProperties;

    public List<TeamViewDto> getTeams() {
        ResponseEntity<List<TeamViewDto>> response = restTemplate.exchange(
                apiProperties.getBaseUrl() + "/api/Teams",
                GET,
                null,
                new ParameterizedTypeReference<>() {
                }
        );
        return response.getBody();
    }
}
