package com.driver;

public class Email {

    private String emailId;
    private String password;

    public Email(String emailId){
        this.emailId = emailId;
        this.password = "Accio@123";
    }

    public String getEmailId() {
        return emailId;
    }

    public String getPassword() {
        return password;
    }

    private boolean checkValid(String password){
        int lc = 0, uc = 0, sc = 0, digit = 0;

        for (int i = 0; i< password.length(); i++){
            if (password.charAt(i) >= 'a' && password.charAt(i) <= 'z') lc++;
           else if (password.charAt(i) >= 'A' && password.charAt(i) <= 'Z') uc++;
           else if (password.charAt(i) >= '0' && password.charAt(i) <= '9') digit++;
            else{
                sc++;
            }
        }
        if(lc != 0 && uc !=  0 && sc != 0 && digit != 0 ) return true;
        return false;
    }
    public void changePassword(String oldPassword, String newPassword){
        //Change password only if the oldPassword is equal to current password and the new password meets all of the following:
        // 1. It contains at least 8 characters
        // 2. It contains at least one uppercase letter
        // 3. It contains at least one lowercase letter
        // 4. It contains at least one digit
        // 5. It contains at least one special character. Any character apart from alphabets and digits is a special character
        if (oldPassword.equals(this.password)){
            //check for valid password
            if (newPassword.length() <8) {
                return;
            }
            if(checkValid(newPassword)){
                this.password = newPassword;
            } else {
            }
        }
    }
}
