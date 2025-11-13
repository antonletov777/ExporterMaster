package com.reksoft.exporter.service.player;

import com.reksoft.exporter.model.Player;
import com.reksoft.exporter.repository.player.PlayerApiRepository;
import com.reksoft.exporter.repository.dto.PlayerViewDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PlayerServiceImpl implements PlayerService {

    private final PlayerApiRepository playerApiRepository;

    @Override
    public List<Player> getPlayers() {
        List<PlayerViewDto> playerViewDtos = playerApiRepository.getPlayers();
        return playerViewDtos.stream().map(this::map).toList();
    }

    private Player map(PlayerViewDto playerViewDto) {
        Player player = new Player();
        player.setId(playerViewDto.getId());
        player.setCountry(playerViewDto.getCountry());
        player.setNickname(playerViewDto.getNickName());
        player.setCombinedName(playerViewDto.getCombinedName());
        player.setFullName(makeFullName(playerViewDto.getCombinedName(), playerViewDto.getNickName()));
        player.setTeamName(playerViewDto.getTeamName());
        return player;
    }

    String makeFullName(String combinedName, String nickName)
    {
        String[] nameParts = combinedName.split(" ", 2);
        return nameParts[0] + " " + nickName + " " + nameParts[1];
    }
}
