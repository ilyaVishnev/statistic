package ru.dao.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.models.Call;
import ru.models.Worker;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface CallRepository extends CrudRepository<Call, Integer> {

    @Query("select call.worker.firstName, call.worker.middleName, call.worker.secondName, count (call) from Call call where call.date>= :date and call.typeOfCall= :callT group by call.worker.firstName, call.worker.middleName, call.worker.secondName")
    List<String> ex(@Param("date") Timestamp date, @Param("callT") String callT);
}
