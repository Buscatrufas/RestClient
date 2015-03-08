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
public class UsersDTO {

    private String name;
    private String password;
    private String location;
    
    public UsersDTO(){}
    
    public UsersDTO(String name, String password, String location) {
        this.name = name;
        this.password = password;
        this.location = location;
    }

    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
    @Override
    public String toString() {
        if (location != null) {
            return this.name + " " + this.location;
        } else {
            return this.name;
        }
    }

}
