package com.thedeadlines.mafiap2p.game;

import com.thedeadlines.mafiap2p.common.Member;

import java.util.ArrayList;
import java.util.List;

import static com.thedeadlines.mafiap2p.game.GameConstants.DEFAULT_PLAYERS_COUNT;

public class Game {
    private static final String TAG = Game.class.getSimpleName();

    private int mMembersLimit;
    private List<Member> mMembers;

    public Game() {
        mMembersLimit = DEFAULT_PLAYERS_COUNT;
        this.mMembers = new ArrayList<>();
    }

    public Game(int membersCount) {
        if (validateCount(membersCount)) {
            this.mMembersLimit = membersCount;

        } else {
            this.mMembersLimit = DEFAULT_PLAYERS_COUNT;
        }
        this.mMembers = new ArrayList<>();
    }

    public boolean kickPlayer(int index) {
        if (index >= 1 && index < mMembers.size()) {
            mMembers.remove(index);
            return true;
        }
        return false;
    }

    public boolean addMember(Member member) {
        if (isFull()) {
            return false;
        }
        mMembers.add(member);
        return true;
    }

    private boolean isFull() {
        if (mMembers == null) {
            return false;
        }

        return mMembers.size() >= mMembersLimit;
    }

    private static boolean validateCount(int membersCount) {
        return membersCount >= DEFAULT_PLAYERS_COUNT;
    }

    public void setMembersLimit(int mMembersLimit) {
        if (validateCount(mMembersLimit))
            this.mMembersLimit = mMembersLimit;
    }
}
