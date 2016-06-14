package com.prabhukonchada.popularmovies;

import com.squareup.otto.Bus;

/**
 * Created by Prabhu Konchada on 14/06/16
 * you can contact me at : prabhukonchada@gmail.com
 */
public class DataBus {

    private static final Bus BUS = new Bus();

    public static Bus getInstance()
    {
        return BUS;
    }
}
