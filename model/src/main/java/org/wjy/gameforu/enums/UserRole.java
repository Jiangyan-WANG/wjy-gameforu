package org.wjy.gameforu.enums;

import com.alibaba.fastjson.annotation.JSONType;
import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.databind.deser.std.EnumDeserializer;
import com.fasterxml.jackson.databind.ser.std.EnumSerializer;
import lombok.Getter;

@JSONType(serializer = EnumSerializer.class, deserializer = EnumDeserializer.class, serializeEnumAsJavaBean = true)
@Getter
public enum UserRole {
    Admin(0, "admin"),
    User(1,"user");

    @EnumValue
    Integer code;
    String role;

    UserRole(Integer code, String role){
        this.code = code;
        this.role = role;
    }

}
