package ru.dao.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.dao.repositories.SubscriberRepository;
import ru.models.Subscriber;

@Service
public class SubscriberService {

    @Autowired
    private SubscriberRepository subscriberRepository;

    public void save(Subscriber subscriber) {
        subscriberRepository.save(subscriber);
    }
}
