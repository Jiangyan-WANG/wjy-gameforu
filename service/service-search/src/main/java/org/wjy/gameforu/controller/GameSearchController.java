package org.wjy.gameforu.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jodd.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.wjy.gameforu.common.result.Result;
import org.wjy.gameforu.model.search.GameEs;
import org.wjy.gameforu.model.search.GameHintVo;
import org.wjy.gameforu.service.GameApiService;

import java.util.List;

@RestController
@Api(tags = "search controller(es)")
@RequestMapping("es/search")
public class GameSearchController {

    @Autowired
    GameApiService gameApiService;

    @ApiOperation("search name by keywords")
    @GetMapping("suggestGame")
    public Result suggestGame(GameHintVo gameHintVo){ // 注意将请求参数封装到VO类中不需要任何注解
        Pageable pageable = PageRequest.of(0,10);
        String keyword = gameHintVo.getKeyword();
        if(StringUtils.isEmpty(keyword)) {
            return Result.ok(null);
        }else {
            Page<GameEs> suggestGames = gameApiService.getSuggestGame(keyword, pageable);
            return Result.ok(suggestGames);
        }
    }
}
