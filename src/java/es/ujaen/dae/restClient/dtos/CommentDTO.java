/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ujaen.dae.restClient.dtos;

import java.util.Collection;
import java.util.Date;
import java.util.Map;
/**
 *
 * @author miguel
 */
public class CommentDTO {
    
    private int mid;
    private String message;
    private String owner;
    private Date creationMsg;
    private Collection<CommentDTO> replies;
    private Map<String, UsersDTO> likes;
    
    public CommentDTO(){ }
    
    public CommentDTO(int mid, String message, String owner, Date creationMsg, Collection<CommentDTO> replies, Map<String, UsersDTO> likes){
        this.mid = mid;
        this.message = message;
        this.owner = owner;
        this.creationMsg = (Date)creationMsg.clone();
        this.replies = replies;
        this.likes = likes;
    }
    
    public CommentDTO(int mid, String message, String owner, Date creationDate){
        this.mid = mid;
        this.message = message;
        this.owner = owner;
        this.creationMsg = creationDate;
    }
    
    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
    
    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Date getCreationMsg() {
        return creationMsg;
    }

    public void setCreationMsg(Date creationMsg) {
        this.creationMsg = creationMsg;
    }

    public Collection<CommentDTO> getReplies() {
        return replies;
    }

    public void setReplies(Collection<CommentDTO> replies) {
        this.replies = replies;
    }

    public Map<String, UsersDTO> getLikes() {
        return likes;
    }

    public void setLikes(Map<String, UsersDTO> likes) {
        this.likes = likes;
    }
    
}
