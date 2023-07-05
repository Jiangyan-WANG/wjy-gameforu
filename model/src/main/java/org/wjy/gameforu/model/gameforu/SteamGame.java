package org.wjy.gameforu.model.gameforu;

import lombok.Data;

@Data
public class SteamGame {
    /**
     * "appid": 1619450,
     *         "name": "Heart of a Warrior",
     *         "developer": "Techworld Communication",
     *         "publisher": "Techworld Communication",
     *         "score_rank": "",
     *         "positive": 0,
     *         "negative": 1,
     *         "userscore": 0,
     *         "owners": "2,000,000 .. 5,000,000",
     *         "average_forever": 0,
     *         "average_2weeks": 0,
     *         "median_forever": 0,
     *         "median_2weeks": 0,
     *         "price": "1099",
     *         "initialprice": "1099",
     *         "discount": "0",
     *         "ccu": 0
     */
     Integer appid;
     String name;
     String developer;
     String publisher;
     String score_rank;
     Integer positive;
     Integer negative;
     Integer userscore;
     String owners;
     Integer average_forever;
     Integer average_2weeks;
     Integer median_forever;
     Integer median_2weeks;
     String price;
     String initialprice;
     String discount;
     Integer ccu;
}
