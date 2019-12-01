package ru.models;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.sql.Timestamp;

@Component
@Entity
@Table(name = "calls")
public class Call {

    private int id;
    private Timestamp date;
    private String typeOfCall;
    private String callComment;
    private PhoneNumber phoneNumber;
    private Worker worker;

    public Call() {
    }

    public Call(Timestamp date, String typeOfCall, String callComment, PhoneNumber phoneNumber, Worker worker) {
        this.date = date;
        this.typeOfCall = typeOfCall;
        this.callComment = callComment;
        this.phoneNumber = phoneNumber;
        this.worker = worker;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "dateandtime")
    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    @Column(name = "typeofcall")
    public String getTypeOfCall() {
        return typeOfCall;
    }

    public void setTypeOfCall(String typeOfCall) {
        this.typeOfCall = typeOfCall;
    }

    @Column(name = "callcomment")
    public String getCallComment() {
        return callComment;
    }

    public void setCallComment(String callComment) {
        this.callComment = callComment;
    }

    @ManyToOne(fetch = FetchType.EAGER,cascade = {CascadeType.ALL})
    @JoinColumn(name = "phonenumberid", referencedColumnName = "id")
    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(PhoneNumber phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "workerid", referencedColumnName = "id")
    public Worker getWorker() {
        return worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }

    @Override
    public int hashCode() {
        return this.id;
    }
}
