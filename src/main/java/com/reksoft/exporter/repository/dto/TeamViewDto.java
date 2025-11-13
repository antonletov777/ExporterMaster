package com.reksoft.exporter.repository.dto;

import com.reksoft.exporter.model.Player;
import lombok.Data;

import java.util.List;

@Data
public class TeamViewDto {
    private Integer id;
    private String name;
    private List<Player> players;
}
