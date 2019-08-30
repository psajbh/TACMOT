package com.jhart.json;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

public class ServiceAgencyEmail implements Serializable{
    private static final long serialVersionUID = 1L;
    private String saCode;
    private String emails;
    public String getSaCode() {
        return saCode;
    }
    public void setSaCode(String saCode) {
        this.saCode = saCode;
    }
    public String getEmails() {
        return emails;
    }
    public void setEmails(String emails) {
        this.emails = emails;
    }
    
    @Override
    public String toString() {
        return saCode + " - " + emails;
    }
    
    
    
    

}
