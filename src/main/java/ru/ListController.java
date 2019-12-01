package ru;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.dao.services.CallService;
import ru.dao.services.SubscriberService;
import ru.dao.services.WorkerService;
import ru.models.Call;
import ru.models.PhoneNumber;
import ru.models.Subscriber;
import ru.models.Worker;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ListController {

    @Autowired
    private CallService callService;

    @Autowired
    private WorkerService workerService;

    @Autowired
    private SubscriberService subscriberService;

    @GetMapping("/list")
    protected ModelAndView menu(@RequestParam(required = false, name = "period") String period,
                                @RequestParam(required = false, name = "calls") String call) {
        period = period == null ? "off" : period;
        call = call == null ? "off" : call;
        ModelAndView modelAndView = new ModelAndView();
        Calendar calendar = Calendar.getInstance();
        Timestamp workPeriod = new Timestamp(System.currentTimeMillis());
        switch (period) {
            case "day":
                calendar.set(Calendar.HOUR_OF_DAY, 0);
                calendar.set(Calendar.MINUTE, 0);
                calendar.set(Calendar.SECOND, 0);
                calendar.set(Calendar.MILLISECOND,0);
                break;
            case "week":
                calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
                break;
            case "month":
                calendar.set(Calendar.DAY_OF_MONTH, 1);
                break;
            default:
                break;
        }
        workPeriod = new Timestamp(calendar.getTimeInMillis());
        List<String[]> statistic = callService.ex(workPeriod, call).stream()
                .map(s -> s.split(",")).collect(Collectors.toList());
        modelAndView.setViewName("list");
        modelAndView.addObject("selPeriod", period);
        modelAndView.addObject("selCall", call);
        modelAndView.addObject("statistic", statistic);
        modelAndView.addObject("workers", workerService.findAll());
        return modelAndView;
    }

    @PostMapping("/list")
    protected void saveCall(@RequestParam(name = "phoneNumber") String phNumber,
                            @RequestParam(name = "firstName") String firstName,
                            @RequestParam(name = "middleName") String middleName,
                            @RequestParam(name = "secondName") String secondName,
                            @RequestParam(name = "createdcall") String typeOfCall,
                            @RequestParam(name = "comment") String comment,
                            @RequestParam(name = "workers") Integer workerId,
                            @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) java.util.Date date) {
        Subscriber subscriber = new Subscriber(firstName, middleName, secondName);
        PhoneNumber phoneNumber = new PhoneNumber(phNumber, subscriber);
        Worker worker = workerService.findWorkerById(workerId);
        Call call = new Call(new Timestamp(date.getTime()), typeOfCall, comment, phoneNumber, worker);
        callService.save(call);
    }

}
