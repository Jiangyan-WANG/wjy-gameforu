package org.wjy.gameforu.vo.gameforu;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.wjy.gameforu.model.gameforu.SteamGame;

import java.util.Map;

@Data
@ApiModel(description="game json db")
public class GameDBVo {
    private static final Long serialVersionUID = 1L;

    Map<String, SteamGame> steamGameMap;
}
