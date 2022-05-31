package com.satwik.taskrbotjava.dataaccess.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document
public class Communities {

    @Id
    private Long communityID;
    private List<Integer> lists;
    private List<Long> users;

    public Communities(Long communityID, List<Integer> lists, List<Long> users){
        this.communityID = communityID;
        this.lists = lists;
        this.users = users;
    }

    public Long getCommunityID() {
        return communityID;
    }

    public void setCommunityID(Long communityID) {
        this.communityID = communityID;
    }

    public List<Integer> getLists() {
        return lists;
    }

    public void setLists(List<Integer> lists) {
        this.lists = lists;
    }

    public List<Long> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<Long> users) {
        this.users = users;
    }
}
