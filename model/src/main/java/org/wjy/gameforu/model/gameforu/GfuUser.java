package org.wjy.gameforu.model.gameforu;

import com.alibaba.fastjson2.JSONArray;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.wjy.gameforu.model.base.BaseEntity;

@Data
@ApiModel(description = "User")
@TableName("user")
public class GfuUser{

	/**
	 * serialVersionUID促进序列化数据的版本控制。
	 * 序列化时其值与数据一起存储。
	 * 反序列化时，会检查相同版本，看看序列化数据与当前代码的匹配情况。
	 *
	 * 如果您想对数据进行版本控制，
	 * 通常从serialVersionUID0 开始，
	 * 并随着类的每次结构更改而改变序列化数据（添加或删除非瞬态字段）。
	 */
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "user id") //swagger: desc for attribute
	@TableId(type = IdType.AUTO) //mp: primary key and type (AUTO auto_increment)
	private Integer uid;

	@TableField("name")
	private String username;

	@TableField("password")
	private String password;

	@ApiModelProperty(value = "phone number")
	@TableField("phone")
	private String phoneNumber;

	@ApiModelProperty(value = "email")
	@TableField("email")
	private String photoUrl;

	@ApiModelProperty(value = "preferences")
	@TableField(value="preferences",typeHandler = JacksonTypeHandler.class)
	private JSONArray preferences;

	@ApiModelProperty(value = "role ['admin' or 'user']")
	@TableField("role")
	private String role;
}