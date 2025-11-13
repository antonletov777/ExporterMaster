package com.reksoft.exporter.service.team;

import com.opencsv.CSVWriter;
import com.reksoft.exporter.model.Team;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamCsvReportService {

    private final TeamService teamService;

    public File generateReport(String filePath) throws IOException {
        List<Team> teams = teamService.getTeams();
        File file = new File(filePath);
        try (CSVWriter writer = new CSVWriter(new FileWriter(file))) {
            String[] header = {"ID", "Team Name", "Players"};
            writer.writeNext(header);

            for (Team team : teams) {
                String[] line = {
                        String.valueOf(team.getId()),
                        team.getTeamName(),
                        team.getPlayersNames(),
                };
                writer.writeNext(line);
            }
        }

        return file;
    }
}
