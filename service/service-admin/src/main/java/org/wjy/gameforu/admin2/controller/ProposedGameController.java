package org.wjy.gameforu.admin2.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.wjy.gameforu.admin2.service.ProposedGameService;
import org.wjy.gameforu.admin2.service.RoleService;
import org.wjy.gameforu.common.result.Result;
import org.wjy.gameforu.model.entity.ProposedGame;
import org.wjy.gameforu.model.entity.Role;
import org.wjy.gameforu.vo.RoleQueryVo;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author J Wang
 * @since 2023-07-07
 */
@Api(tags = "Proposed Game management")
@RestController
@RequestMapping("/proposedgame/admin")
public class ProposedGameController {

    @Autowired
    private ProposedGameService proposedGameService;

    @ApiOperation("add proposed game")
    @PostMapping("add")
    public Result add(@RequestBody ProposedGame proposedGame){
        Boolean is_succeed = proposedGameService.save(proposedGame);
        if(is_succeed){
            return Result.ok(null);
        }else{
            return Result.fail(null);
        }
    }

    //3 delete role by id
    @ApiOperation("delete proposedGame")
    @DeleteMapping("remove/{id}")
    public Result delete(@PathVariable Integer id){
        boolean is_succeed = proposedGameService.removeById(id);
        if(is_succeed){
            return Result.ok(null);
        }else{
            return Result.fail(null);
        }
    }

    //4 delete rold by ids
    @ApiOperation("batch delete")
    @DeleteMapping("batchRemove")
    public Result deletes(@RequestBody List<Integer> ids){
        boolean is_succeed = proposedGameService.removeByIds(ids);
        if(is_succeed){
            return Result.ok(null);
        }else{
            return Result.fail(null);
        }
    }

    //5 update role
    @ApiOperation("update")
    @PutMapping("update")
    public Result update(@RequestBody ProposedGame proposedGame){
        boolean is_succeed = proposedGameService.updateById(proposedGame);
        if(is_succeed) {
            return Result.ok(null);
        }else{
            return Result.fail(null);
        }
    }

    //6 query role by id
    @ApiOperation("query role by id")
    @GetMapping("get/{id}")
    public Result get(@PathVariable Integer id){
        ProposedGame role = proposedGameService.getById(id);
        return Result.ok(role);
    }

    @PutMapping("needadd")
    public Result needAdd(){
        LambdaQueryWrapper<ProposedGame> proposedGameLambdaQueryWrapper = new LambdaQueryWrapper<>();
        proposedGameLambdaQueryWrapper.eq(ProposedGame::getAdded,false);
        List<ProposedGame> needAddGames = proposedGameService.list(proposedGameLambdaQueryWrapper);
        return Result.ok(needAddGames);
    }

    @GetMapping("getmyproposed/{uid}")
    public Result getMyProposed(@PathVariable Integer uid){
        LambdaQueryWrapper<ProposedGame> proposedGameLambdaQueryWrapper = new LambdaQueryWrapper<>();
        proposedGameLambdaQueryWrapper.eq(ProposedGame::getUid,uid);
        proposedGameLambdaQueryWrapper.orderByDesc(ProposedGame::getProposedTime);
        List<ProposedGame> myProposedGames = proposedGameService.list(proposedGameLambdaQueryWrapper);
        return Result.ok(myProposedGames);
    }
}

