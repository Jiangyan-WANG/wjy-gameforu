package org.wjy.gameforu.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.wjy.gameforu.model.external.SteamGame;

import java.util.Map;

@Data
@ApiModel(description="game json db")
public class GameDBVo {
    private static final Long serialVersionUID = 1L;

    Map<String, SteamGame> steamGameMap;
}
