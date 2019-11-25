package com.thedeadlines.mafiap2p.common;

enum Status {

    Client {
        @Override
        int getIntent() {
            return 0;
        }
    },

    GroupOwner {
        @Override
        int getIntent() {
            return 15;
        }
    };

    abstract int getIntent();

}
