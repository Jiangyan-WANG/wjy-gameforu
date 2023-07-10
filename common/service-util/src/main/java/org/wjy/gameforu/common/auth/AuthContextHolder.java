package org.wjy.gameforu.common.auth;

/**
 * login persistence context
 */
public class AuthContextHolder {
    // user id
    private static ThreadLocal<Integer>  userId = new ThreadLocal<>();
    // username
    private static ThreadLocal<String>  username = new ThreadLocal<>();

    public static void setUserId(Integer _userId){
        userId.set(_userId);
    }

    public static Integer getUserId(){
        return userId.get();
    }


    public static void setUsername(String _username){
        username.set(_username);
    }

    public static String getUsername(){
        return username.get();
    }
}
