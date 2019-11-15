package com.thedeadlines.mafiap2p.ui.fragments.host;

import com.thedeadlines.mafiap2p.ui.fragments.host.hostList.HostListElement;

import java.util.ArrayList;
import java.util.List;

// to delete
public class DataGenerator {
    List<HostListElement> list;

    private static final DataGenerator sInstance = new DataGenerator();

    static DataGenerator getInstance() {
        return sInstance;
    }

    List<HostListElement> getList() {
        return list;
    }

    DataGenerator() {
        list = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            HostListElement e = new HostListElement(i, "simple name" + 1, "role" + 1);
            list.add(e);
        }
    }
}

