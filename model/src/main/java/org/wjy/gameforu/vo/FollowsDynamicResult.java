package org.wjy.gameforu.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.wjy.gameforu.model.entity.Comment;
import org.wjy.gameforu.model.entity.Game;
import org.wjy.gameforu.model.entity.User;

import java.util.List;

@Data
@ApiModel(description = "follows dynamic result")
public class FollowsDynamicResult {

    private static final long serialVersionUID = 1L;

    private List<User> users;

    private List<Comment> comments;

    private List<Game> games;
}
