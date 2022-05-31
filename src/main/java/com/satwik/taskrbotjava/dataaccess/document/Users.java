package com.satwik.taskrbotjava.dataaccess.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Document
public class Users {

    @Id
    private Long id;
    private String name;
    private List<Integer> listIds;
    private Integer currentList;

    public Users(Long id, String name, List<Integer> listIds, Integer currentList){
        this.id = id;
        this.name = name;
        this.listIds = listIds;
        this.currentList = currentList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Integer> getListIds() {
        return listIds;
    }

    public void setListIds(List<Integer> listIds) {
        this.listIds = listIds;
    }

    public Integer getCurrentList() {
        return currentList;
    }

    public void setCurrentList(Integer currentList) {
        this.currentList = currentList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
