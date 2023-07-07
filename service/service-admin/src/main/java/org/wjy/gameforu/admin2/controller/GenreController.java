package org.wjy.gameforu.admin2.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.wjy.gameforu.admin2.entity.Genre;
import org.wjy.gameforu.admin2.service.GenreService;
import org.wjy.gameforu.common.result.Result;
import org.wjy.gameforu.vo.gameforu.GenreQueryVo;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author J Wang
 * @since 2023-07-07
 */
@RestController
@RequestMapping("/admin/genre")
@Api(tags = "genre management")
@CrossOrigin
public class GenreController {

    @Autowired
    private GenreService genreService;

    //1 query the genres

    @ApiOperation("pagination query")
    @GetMapping("/{current}/{limit}")
    public Result pageList(@PathVariable Integer current,
                           @PathVariable Integer limit,
                           GenreQueryVo genreQueryVo){

        /**
         * pageparams contruct from PathVariable
         */
        Page<Genre> pageParams = new Page<>(current, limit);
        IPage<Genre> pageModel = genreService.selectGenrePage(pageParams, genreQueryVo);
        return Result.ok(pageModel);
    }

    //2 query by id
    @ApiOperation("query by id")
    @GetMapping("get/{id}")
    public Result get(@PathVariable Integer id){
        Genre genre = genreService.getById(id);
        return Result.ok(genre);
    }
    //3 add genre
    @ApiOperation("add genre")
    @PostMapping("add")
    public Result add(@RequestBody Genre genre){
        boolean is_succeed = genreService.save(genre);
        if(is_succeed){
            return Result.ok(null);
        }else{
            return Result.fail(null);
        }
    }

    //4 update genre
    @ApiOperation("update")
    @PutMapping("update")
    public Result update(@RequestBody Genre genre){
        boolean is_succeed = genreService.updateById(genre);
        if(is_succeed){
            return Result.ok(null);
        }else{
            return Result.fail(null);
        }
    }

    //5 delete by id
    @ApiOperation("delete by id")
    @DeleteMapping("remove/{id}")
    public Result delete(@PathVariable Integer id){
        boolean is_succeed = genreService.removeById(id);
        if(is_succeed){
            return Result.ok(null);
        }else{
            return Result.fail(null);
        }
    }

    //6 delete by ids
    @ApiOperation("batched delete")
    @DeleteMapping("batchRemove")
    public Result deletes(@RequestBody List<Integer> ids){
        boolean is_succeed = genreService.removeByIds(ids);
        if(is_succeed){
            return Result.ok(null);
        }else{
            return Result.fail(null);
        }
    }

}

