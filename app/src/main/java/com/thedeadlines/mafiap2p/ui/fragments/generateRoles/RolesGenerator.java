package com.thedeadlines.mafiap2p.ui.fragments.generateRoles;

import com.thedeadlines.mafiap2p.common.Member;
import com.thedeadlines.mafiap2p.common.MemberList;
import com.thedeadlines.mafiap2p.data.db.role.RoleEntity;
import com.thedeadlines.mafiap2p.viewmodel.GameRoleJoinViewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class RolesGenerator {
    private GameRoleJoinViewModel mGameRoleJoinViewModel;

    private final MemberList gMemberList;
    private int gameId;

    private List<RoleEntity> rolesList;
    private List<Member> membersList;

    private HashMap<Member, RoleEntity> membersRoles;

    public RolesGenerator(GameRoleJoinViewModel mGameRoleJoinViewModel, int gameId) {
        gMemberList = MemberList.getInstance();

        this.mGameRoleJoinViewModel = mGameRoleJoinViewModel;
        this.gameId = gameId;

        this.rolesList = new ArrayList<>();
        this.membersList = new ArrayList<>();

        List<RoleEntity> roles = mGameRoleJoinViewModel.getRolesByGameId(gameId);
        this.rolesList.addAll(roles);

        List<Member> members = gMemberList.getMembersList();
        this.membersList.addAll(members);

        membersRoles = new HashMap<>();
    }

    private void setCheckedRoles() {
        List<RoleEntity> checked = mGameRoleJoinViewModel.getCheckedRoles();

        for (RoleEntity checkedRole : checked) {
            int randomMemberIndex = getRandomInt(0, membersList.size());

            Member randomMember = membersList.get(randomMemberIndex);
            membersRoles.put(randomMember, checkedRole);

            rolesList.remove(checkedRole);
            membersList.remove(randomMember);
        }
    }

    private void setMafiaRoles() {
        Integer mafias = mGameRoleJoinViewModel.getMafiasCount().getValue();

        if (mafias != null) {
            RoleEntity mafiaRole = new RoleEntity(0, "mafia");


            for (Integer i = 0; i < mafias; i++) {
                int randomMemberIndex = getRandomInt(0, membersList.size());

                Member randomMember = membersList.get(randomMemberIndex);
                membersRoles.put(randomMember, mafiaRole);

                for (RoleEntity role : rolesList) {
                    if (role.equals(mafiaRole)) {
                        rolesList.remove(role);
                    }
                }
                membersList.remove(randomMember);
            }
        }
    }

    private void setPlayerRoles() {
        Integer players = mGameRoleJoinViewModel.getPlayersCount().getValue();

        if (players != null) {
            RoleEntity playerRole = new RoleEntity(1, "player");


            for (Integer i = 0; i < players; i++) {
                int randomMemberIndex = getRandomInt(0, membersList.size());

                Member randomMember = membersList.get(randomMemberIndex);
                membersRoles.put(randomMember, playerRole);

                for (RoleEntity role : rolesList) {
                    if (role.equals(playerRole)) {
                        rolesList.remove(role);
                    }
                }
                membersList.remove(randomMember);
            }
        }
    }

    //    generate random int from {from} to {to - 1}
    private int getRandomInt(int from, int to) {
        Random random = new Random();

        return from + random.nextInt(to - from);
    }
}
