package com.satwik.taskrbotjava.dataaccess.Views;

import com.satwik.taskrbotjava.dataaccess.document.Items;
import com.satwik.taskrbotjava.dataaccess.document.Lists;

import java.util.List;

public class PopulatedList {

    private Integer listID;
    private String name;
    private Long owner;
    private boolean isOpen;
    private List<Items> items;

    public PopulatedList(Integer listID, String name, Long owner, boolean isOpen, List<Items> items) {
        this.listID = listID;
        this.name = name;
        this.owner = owner;
        this.isOpen = isOpen;
        this.items = items;
    }

    public PopulatedList(Lists listsObject, List<Items> items) {
        this.listID = listsObject.getListID();
        this.name = listsObject.getName();
        this.owner = listsObject.getOwner();
        this.isOpen = listsObject.isOpen();
        this.items = items;
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

    public List<Items> getItems() {
        return items;
    }

    public void setItems(List<Items> items) {
        this.items = items;
    }
}
