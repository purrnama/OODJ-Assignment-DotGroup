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
    
    
}
