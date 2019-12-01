package ru.dao.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import ru.dao.repositories.CallRepository;
import ru.dao.repositories.WorkerRepository;
import ru.models.Call;
import ru.models.Worker;

import java.sql.Timestamp;
import java.util.List;

@Service
public class WorkerService {

    @Autowired
    private WorkerRepository workerRepository;


    public List<Worker> findAll() {
        return workerRepository.findAllBy();
    }

    public Worker findWorkerById(Integer id) {
        return workerRepository.findAllById(id);
    }
}
