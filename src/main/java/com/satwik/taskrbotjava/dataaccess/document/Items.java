package com.satwik.taskrbotjava.dataaccess.document;

import com.satwik.taskrbotjava.dataaccess.document.util.TaskStatus;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.text.SimpleDateFormat;
import java.util.Date;

@Document
public class Items {

    @Id
    private Integer itemID;
    private Long user;
    private Date dueDate;
    private String description;
    private TaskStatus status;

    public Items(Integer itemID, Long user, Date dueDate, String description, TaskStatus status){
        this.itemID = itemID;
        this.user = user;
        this.dueDate = dueDate;
        this.description = description;
        this.status = status;
    }

    public Integer getItemID() {
        return itemID;
    }

    public void setItemID(Integer itemID) {
        this.itemID = itemID;
    }

    public Long getUser() {
        return user;
    }

    public void setUser(Long user) {
        this.user = user;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }


}
