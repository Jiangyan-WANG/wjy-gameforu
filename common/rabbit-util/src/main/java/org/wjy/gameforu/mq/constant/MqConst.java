package org.wjy.gameforu.mq.constant;

/**
 * Mq constant
 */
public class MqConst {
    /**
     * prefix and retry times
     */
    public static final String MQ_KEY_PREFIX = "gameforu.mq:list";
    public static final int RETRY_COUNT = 3;

    /**
     * add and delete game
     * exchange and routing
     */
    public static final String EXCHANGE_GAMES = "gameforu.games";
    public static final String ROUTING_GAMES_ADD = "gameforu.games.add";
    public static final String ROUTING_GAMES_REMOVE = "gameforu.games.remove";

    //queue
    public static final String QUEUE_GAMES_ADD  = "gameforu.games.add";
    public static final String QUEUE__GAMES_REMOVE  = "gameforu.games.remove";

    /**
     * timer and task
     */
    public static final String EXCHANGE_TASK = "gameforu.task";
    public static final String ROUTING_TASK = "gameforu.routing.task";
    //队列
    public static final String QUEUE_TASK  = "gameforu.queue.task";
}