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
        this.setSubject(subject);
        this.setBody(body);
        this.setStatus(status);
    }
    
    public Issue(String subject, String body) {
        this.setSubject(subject);
        this.setBody(body);
        this.setStatus(IssueStatus.OPEN);
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
