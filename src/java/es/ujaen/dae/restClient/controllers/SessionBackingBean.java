/*
 * Copyright 2014 Edu.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package es.ujaen.dae.restClient.controllers;

import es.ujaen.dae.restClient.dtos.CommentDTO;
import es.ujaen.dae.restClient.dtos.RequestFriendDTO;
import es.ujaen.dae.restClient.dtos.UsersDTO;
import es.ujaen.dae.restClient.dtos.TaskDTO;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;


/**
 *
 * @author miguel
 */
@ManagedBean(name = "sessionBB")
@SessionScoped
public class SessionBackingBean {

    //private static final Logger logger = LoggerFactory.getLogger(SessionBackingBean.class);
    private static final String URL = "http://localhost:8080/NotificatorTask/";

    private UsersDTO user;
    private boolean loggedIn;
    private boolean editTask;
    private List<CommentDTO> wall;
    private CommentDTO message;
    private String userToFind;
    private List<UsersDTO> usersFound;
    private List<TaskDTO> lTask;
    private boolean hasNotifications;
    private List<RequestFriendDTO> notifications;
    private RestTemplate template;
    private TaskDTO task;

    public SessionBackingBean() {
        user = new UsersDTO();
        wall = new ArrayList();
        message = new CommentDTO();
        task = new TaskDTO();
        loggedIn = false;
        usersFound = new ArrayList();
        editTask = false;
        lTask = new ArrayList();
        hasNotifications = false;
        DefaultHttpClient httpClient = new DefaultHttpClient();
        ClientHttpRequestFactory rf = new HttpComponentsClientHttpRequestFactory(httpClient);
        template = new RestTemplate(rf);
    }

    public UsersDTO getUser() {
        return user;
    }

    public void setUser(UsersDTO user) {
        this.user = user;
    }

    public List<CommentDTO> getWall() {
        return wall;
    }

    public void setWall(List<CommentDTO> wall) {
        this.wall = wall;
    }

    public CommentDTO getMessage() {
        return message;
    }

    public void setMessage(CommentDTO message) {
        this.message = message;
    }

    public TaskDTO getTask() {
        return task;
    }

    public void setTask(TaskDTO task) {
        this.task = task;
    }

    public String getUserToFind() {
        return userToFind;
    }

    public void setUserToFind(String userToFind) {
        this.userToFind = userToFind;
    }

    public List<UsersDTO> getUsersFound() {
        return usersFound;
    }

    public void setUsersFound(List<UsersDTO> usersFound) {
        this.usersFound = usersFound;
    }

    public List<RequestFriendDTO> getNotifications() {
        return notifications;
    }

    public List<TaskDTO> getLTask() {
        return lTask;
    }

    public void setNotifications(List<RequestFriendDTO> notifications) {
        this.notifications = notifications;
    }

    public void setTask(List<TaskDTO> lTask) {
        this.lTask = lTask;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public boolean isEdit() {
        return editTask;
    }

    public boolean isHasNotifications() {
        return hasNotifications;
    }

    public void editTask() {
        this.editTask = true;
    }

    public String login() {
        DefaultHttpClient httpClient = new DefaultHttpClient();
        httpClient.getCredentialsProvider().setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(user.getName(), user.getPassword()));
        ClientHttpRequestFactory rf = new HttpComponentsClientHttpRequestFactory(httpClient);
        template = new RestTemplate(rf);
        try {
            template.getForObject(URL + "users/loginTest", String.class);
            loggedIn = true;
            return "userMenu?faces-redirect=true";
        } catch (HttpClientErrorException e) {
            loggedIn = false;
            user.setPassword("");
            return "loginError?faces-redirect=true";
        }

    }

    public String createAccount() {
        try {
            template.put(URL + "users/new", user);
            return login();
        } catch (HttpClientErrorException e) {
            return "newAccountError?faces-redirect=true";
        }
    }

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "home.jsf?faces-redirect=true";

    }

    public String findUsers() {
        UsersDTO[] arrayU = template.getForObject(URL + "users/search/" + userToFind, UsersDTO[].class);
        if (arrayU != null) {
            usersFound = Arrays.asList(arrayU);
        }
        userToFind = null;
        return "foundUsers?faces-redirect=true";
    }

    public String loadWall() {
        if (isLoggedIn()) {
            loadNotifications();
            CommentDTO[] arrayM = template.getForObject(URL + "Comment/lookup", CommentDTO[].class);
            if (arrayM == null) {
                return "home?faces-redirect=true";
            }
            wall = Arrays.asList(arrayM);
            return "";
        } else {
            return "home?faces-redirect=true";
        }
    }

    public String newMessage() {
        if (isLoggedIn()) {
            try {
                template.put(URL + "Comment", message.getMessage());
                message.setMessage("");
                return "wall?faces-redirect=true";
            } catch (HttpClientErrorException e) {
                logout();
            }
        } else {
            message.setMessage("");
            return "home?faces-redirect=true";
        }
        return "home?faces-redirect=true";
    }

    public String createTask() {
        if (isLoggedIn()) {
            try {
                template.put(URL + "Task", task.getTaskTitle());
                task.setTaskTitle("");
                return "userMenu?faces-redirect=true";
            } catch (HttpClientErrorException e) {
                logout();
            }
        } else {
            task.setTaskTitle("");
            return "home?faces-redirect=true";
        }

        return "userMenu?faces-redirect=true";
    }

    public String newComment(long id) {
        if (isLoggedIn()) {
            try {
                template.put(URL + "Comment/addReply/" + id, message.getMessage());
                message.setMessage("");
                return "wall?faces-redirect=true";
            } catch (HttpClientErrorException e) {
                logout();
            }
        } else {
            message.setMessage("");
            return "home?faces-redirect=true";
        }
        return "home?faces-redirect=true";
    }

    public String loadNotifications() {
        RequestFriendDTO arrayP[] = template.getForObject(URL + "requestfriend/lookup", RequestFriendDTO[].class);
        if (arrayP != null) {
            notifications = Arrays.asList(arrayP);
        }
        hasNotifications = notifications != null && !notifications.isEmpty();
        return "";
    }

    public String listOfTask() {
        TaskDTO arrayP[] = template.getForObject(URL + "Task/lookupALL", TaskDTO[].class);
        if (arrayP != null) {
            lTask = Arrays.asList(arrayP);
        }
        return "";
    }

    public String sendPetition(String u) {
        try {
            template.put(URL + "requestfriend/sendfriendrequest/" + u, "");

        } catch (HttpClientErrorException e) {
            logout();
        }
        return "";
    }

    public String acceptPetition(String u) {
        try {
            template.postForObject(URL + "requestfriend/accept/" + u, "", Object.class);
        } catch (HttpClientErrorException e) {
            e.printStackTrace();
        }
        if (notifications.size() == 1) {
            return "wall?faces-redirect=true";
        } else {
            return "notifications?faces-redirect=true";
        }
    }
    
    public void deleteTask(String tid){
        try{
           template.delete(URL + "Task/" + tid, Object.class);
        } catch (HttpClientErrorException e){
            logout();
        }
    }

    public String rejectPetition(String u) {
        try {
            template.postForObject(URL + "requestfriend/reject/" + u, "", Object.class);
        } catch (HttpClientErrorException e) {
            return logout();
        }
        if (notifications.size() == 1) {
            return "wall?faces-redirect=true";
        } else {
            return "notifications?faces-redirect=true";
        }
    }

    public String likeMessage(long id, String targetUser) {
        try {
            template.postForObject(URL + "Comment/like/" + id, "", Object.class);
        } catch (HttpClientErrorException e) {
            return logout();
        }
        return "wall?faces-redirect=true";
    }
    
     public void saveTaskEdit(long id, String name) {
        try {
            template.postForObject(URL + "Task/" + id + "/" + name, "", Object.class);
            this.editTask = false;
        }catch(HttpClientErrorException e){
            logout();
        }
        
    }

    public String generateTask() {

        try {
            task = template.getForObject(URL + "Task/lookup", TaskDTO.class);
            return "userMenu?faces-redirect=true";
        } catch (HttpClientErrorException e) {
            return logout();
        }
    }

}
