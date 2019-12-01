package ru.dao.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.dao.repositories.CallRepository;
import ru.models.Call;
import ru.models.Worker;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class CallService {

    @Autowired
    private CallRepository callRepository;

    public void save(Call call) {
        callRepository.save(call);
    }

    public List<String> ex(Timestamp date, String callT) {
        return callRepository.ex(date, callT);
    }
}
