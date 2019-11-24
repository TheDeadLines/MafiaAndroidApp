package com.thedeadlines.mafiap2p.game;

import com.thedeadlines.mafiap2p.common.Member;

public class Host extends Member {
    private Game mGame;

    public Host(Member member) {
        super(member);
        this.mGame = new Game();
        mGame.addMember(this);
    }

    public void setGameLimit(int limitPlayers) {
        this.mGame.setMembersLimit(limitPlayers);
    }

    public boolean addPlayer(Player player) {
        return this.mGame.addMember(player);
    }

    public boolean kickPlayer(int index) {
        return this.mGame.kickPlayer(index);
    }


}
