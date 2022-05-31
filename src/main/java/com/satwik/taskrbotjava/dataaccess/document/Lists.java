package com.satwik.taskrbotjava.dataaccess.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class Lists {

    @Id
    private Integer listID;
    private String name;
    private Long owner;
    private boolean isOpen;
    private List<Integer> itemIds;

    public Lists(Integer listID, String name, Long owner, boolean isOpen, List<Integer> itemIds){
        this.listID = listID;
        this.name = name;
        this.owner = owner;
        this.isOpen = isOpen;
        this.itemIds = itemIds;
    }

    public Integer getListID() {
        return listID;
    }

    public void setListID(Integer listID) {
        this.listID = listID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getOwner() {
        return owner;
    }

    public void setOwner(Long owner) {
        this.owner = owner;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public List<Integer> getItemIds() {
        return itemIds;
    }

    public void setItemIds(List<Integer> itemIds) {
        this.itemIds = itemIds;
    }
}
