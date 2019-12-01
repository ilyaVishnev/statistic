package ru.dao.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.models.Subscriber;

@Repository
public interface SubscriberRepository extends CrudRepository<Subscriber,Integer> {
}
