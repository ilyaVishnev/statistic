package ru.models;

import org.springframework.stereotype.Component;

import javax.persistence.*;

@Component
@Entity
@Table(name = "phonenumbers")
public class PhoneNumber {

    private int id;
    private String phoneNumber;
    private Subscriber subscriber;

    public PhoneNumber() {
    }

    public PhoneNumber(String phoneNumber, Subscriber subscriber) {
        this.phoneNumber = phoneNumber;
        this.subscriber = subscriber;
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

    @Column(name = "phonenumber")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @ManyToOne(fetch = FetchType.EAGER,cascade = {CascadeType.ALL})
    @JoinColumn(name = "subscriberId", referencedColumnName = "id")
    public Subscriber getSubscriber() {
        return subscriber;
    }

    public void setSubscriber(Subscriber subscriber) {
        this.subscriber = subscriber;
    }

    @Override
    public int hashCode() {
        return this.id;
    }
}
