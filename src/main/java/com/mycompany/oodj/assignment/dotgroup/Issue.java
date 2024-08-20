/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.oodj.assignment.dotgroup;

/**
 *
 * @author purrnama
 */
public class Issue {
    private String subject;
    private String body;
    private IssueStatus status;

    public Issue(String subject, String body, IssueStatus status) {
        this.subject = subject;
        this.body = body;
        this.status = status;
    }
    
    public Issue(String subject, String body) {
        this.subject = subject;
        this.body = body;
        this.status = IssueStatus.OPEN;
    }

    @Override
    public String toString() {
        return subject + "," + body + "," + status;
    }
    
    public static Issue parse(String line){
        String[] col = line.split(",");
        String subject = col[0];
        String body = col[1];
        String status = col[2];
        Issue i = new Issue(subject, body, IssueStatus.valueOf(status));
        return i;
    }
    

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public IssueStatus getStatus() {
        return status;
    }

    public void setStatus(IssueStatus status) {
        this.status = status;
    }
    
    
}
