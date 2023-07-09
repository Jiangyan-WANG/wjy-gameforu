package org.wjy.gameforu.client.game;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.wjy.gameforu.model.entity.Game;

/**
 * feign client for rpc
 * value is application name: application.yml
 */

@FeignClient(value = "service-admin")
public interface GameFeignClient {

    /**
     ** define the api for rpc **
     * url should be add together
     * copy the controller method for rpc here
     * function: inject pathvariable to the mapping
     * @param gameId
     * @return
     */
    @GetMapping("/api/game/inner/getgameinfo/{gameId}")
    public Game getGameInfo(@PathVariable("gameId") Integer gameId);
}
