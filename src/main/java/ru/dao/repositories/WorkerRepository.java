package ru.dao.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.models.Call;
import ru.models.Worker;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface WorkerRepository extends CrudRepository<Worker,Integer> {

   List<Worker> findAllBy();
   Worker findAllById(Integer id);

}
