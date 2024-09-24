package com.mycompany.oodj.assignment.dotgroup;

import java.util.Optional;

public class Issue {
    
    private String issueId;
    private String customerName;
    private String subject;
    private String body;
    
    private IssueState state;
    private String confirmation;
    
    private String reporterName;
    private String assigneeName;

    private IssueMaintenanceStatus status;
    
    public Issue(String issueId, String customerName, String subject, String body, 
                IssueState state, String confirmation, String reporterName, String assigneeName, IssueMaintenanceStatus status) {
        this.setIssueId(issueId);
        this.setCustomerName(customerName);
        this.setSubject(subject);
        this.setBody(body);
        this.setState(state); // ! Here not sure why not working: IssueState::state
        this.setConfirmation(confirmation); 
        this.setReporterName(reporterName); 
        this.setAssigneeName(assigneeName); 
        this.setStatus(status); 
    }
    
    public Issue(String subject, String body, IssueState state) {
        this.setSubject(subject);
        this.setBody(body);
        this.setState(state);
    }
    
    public Issue(String subject, String body) {
        this.setSubject(subject);
        this.setBody(body);
        this.setState(IssueState.OPEN);
    }

    @Override
    public String toString() {
        return "Issue: " + issueId + ", " + customerName + ", " + 
            subject + ", " + body + ", " + state + ", " +  confirmation + ", " +
            reporterName + ", " + assigneeName + "\n";
    }
    
//    public static Issue parse(String line){
//        String[] col = line.split(",");
//        String subject = col[0];
//        String body = col[1];
//        String status = col[2];
//        Issue i = new Issue(subject, body, IssueState.valueOf(status));
//        return i;
//    }
    
    public static Issue parse(String line){
        String[] col = line.split(",");
//        Optional<String> optional = Optional.of(col[0]).orElse;
        
//        if (col.length < 8)

//System.out.println(Optional.of(col[5]).isEmpty());
//        String issueId = Optional.of(col[0]).orElse("EMPTY");
        String issueId = col[0];
        
        String customerName = col[1];
        String subject = col[2];
        String body = col[3];
        
//        System.out.println("KKKKK" + col[4]);
//        System.out.println(IssueState.OPEN);
//        System.out.println(IssueState.valueOf(state));
//        IssueState state = IssueState.valueOf(state);
       
        IssueState state = IssueState.valueOf(col[4]);
                
        String confirmation = col[5];
        String reporterName = col[6];
        String assigneeName = col[7];
        
        IssueMaintenanceStatus status = IssueMaintenanceStatus.valueOf(col[8]);
        
        
//        if (IssueState.valueOf(status) == )
        
//        switch (status) {
//            case IssueState.OPEN:
//                issueStatus = IssueState.OPEN;
//                break;
//        }
        
        Issue issue = new Issue(
            issueId, customerName, subject, body, state, 
            confirmation, reporterName, assigneeName, status
        );
        
        return issue;
    }
    
    public String getIssueId() {
        return issueId;
    }

    public void setIssueId(String issueId) {
        this.issueId = issueId;
    }
    
//    public String getCustomerName() {
//        return customerName.getUsername();
//    }
    
//    public void setCustomerName(String customerName) {
//        this.customerName.setUsername(customerName);
//    }
    
    public String getCustomerName() {
        return customerName;
    }
    
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
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

    public IssueState getState() {
        return state;
    }

    public void setState(IssueState state) {
        this.state = state;
    }    
    
    public String getReporterName() {
        return reporterName;
    }
    
    public void setReporterName(String reporterName) {
        this.reporterName = reporterName;
    }
    
    public String getConfirmation() {
        return confirmation;
    }

    public void setConfirmation(String confirmation) {
        this.confirmation = confirmation;
    }
        
    public String getAssigneeName() {
        return assigneeName;
    }

    public void setAssigneeName(String assigneeName) {
        this.assigneeName = assigneeName;
    }
    
//    public void setAssigneeName(Scheduler assigneeName) {
//        this.assigneeName = assigneeName;
//    }
    
    public IssueMaintenanceStatus getStatus() {
        return status;
    }

    public void setStatus(IssueMaintenanceStatus status) {
        this.status = status;
    }
}
