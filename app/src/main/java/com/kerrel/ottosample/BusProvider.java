package com.kerrel.ottosample;

import com.squareup.otto.Bus;

/**
 * Created by 이주영 on 2017-06-20.
 */

public class BusProvider {
    private static final Bus BUS = new Bus();

    public static Bus getInstance() {
        return BUS;
    }

    private BusProvider() {

    }
}
