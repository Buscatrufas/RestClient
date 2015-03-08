/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ujaen.dae.restClient.dtos;

import java.util.Date;

/**
 *
 * @author miguel
 */
public class TaskDTO {
    
    private int tid;
    private String taskTitle;
    private String owner;
    private  Date date;
    private String type;
    
    public TaskDTO(){}
    
    public TaskDTO(int tid, String taskTitle, String owner, Date date, String type){
        this.tid = tid;
        this.taskTitle = taskTitle;
        this.owner = owner;
        this.date = (Date)date.clone();
        this.type = type;
    }
    
    public TaskDTO(int tid, String taskTitle, String owner, Date date){
        this.tid = tid;
        this.taskTitle = taskTitle;
        this.owner = owner;
        this.date = (Date)date.clone();
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    
    
}
