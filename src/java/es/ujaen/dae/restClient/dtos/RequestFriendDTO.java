/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ujaen.dae.restClient.dtos;

/**
 *
 * @author miguel
 */
public class RequestFriendDTO {
    
    String userTo;
    String userFrom;
    
    public RequestFriendDTO(){}
    
    public RequestFriendDTO(String userTo, String userFrom){
        
        this.userFrom = userTo;
        this.userFrom = userFrom;
    }

    public String getUserTo() {
        return userTo;
    }

    public void setUserTo(String userTo) {
        this.userTo = userTo;
    }

    public String getUserFrom() {
        return userFrom;
    }

    public void setUserFrom(String userFrom) {
        this.userFrom = userFrom;
    }
    
    
}
