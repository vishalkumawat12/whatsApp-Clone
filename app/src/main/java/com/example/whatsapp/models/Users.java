package com.example.whatsapp.models;

public class Users {
    String profilepic ,userName,mail,password,useId,lastMessage,status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Users(String profilepic, String userName, String mail, String password, String useId, String lastMessage) {
        this.profilepic = profilepic;
        this.userName = userName;
        this.mail = mail;
        this.password = password;
        this.useId = useId;
        this.lastMessage = lastMessage;
    }
    public Users(){

    }

    public Users(String status) {
        this.status = status;
    }
    //SignUp Constructor

    public Users (String userName, String mail, String password ) {

        this.userName = userName;
        this.mail = mail;
        this.password = password;

    }


    public String getProfilepic() {
        return profilepic;
    }

    public void setProfilepic(String profilepic) {
        this.profilepic = profilepic;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUseId() {
        return useId;
    }

    public void setUseId(String useId) {
        this.useId = useId;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }


//    public String getUseId(String userId){
//        return useId;
//    }

}
