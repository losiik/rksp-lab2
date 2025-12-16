package ru.rksp.klosep.data_service.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.rksp.klosep.data_service.data.UserEvent;
import ru.rksp.klosep.data_service.data.UserEventRepository;
import ru.rksp.klosep.dataservice.api.UserEventDataApi;
import ru.rksp.klosep.dataservice.model.UserEventDataCreateRequest;
import ru.rksp.klosep.dataservice.model.UserEventDataResponse;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class UserEventController implements UserEventDataApi {

    private final UserEventRepository userEventRepository;

    @Override
    public ResponseEntity<UserEventDataResponse> createUserEventDataInData(UserEventDataCreateRequest request) {
        UserEvent userEvent = new UserEvent();
        userEvent.setEvent_type(request.getEventType());
        userEvent.setEvent_time(request.getEventTime());
        userEventRepository.save(userEvent);

        UserEventDataResponse response = new UserEventDataResponse();
        response.setId(userEvent.getId());
        response.setEventType(userEvent.getEvent_type());
        response.setEventTime(userEvent.getEvent_time());

        return ResponseEntity.status(200).body(response);
    }

    @Override
    public ResponseEntity<UserEventDataResponse> getUserEventDataByIdFromData(Long id) {
        Optional<UserEvent> optionalUserEvent = userEventRepository.findById(id);

        if (optionalUserEvent.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        UserEvent userEvent = optionalUserEvent.get();

        UserEventDataResponse response = new UserEventDataResponse();
        response.setId(userEvent.getId());
        response.setEventType(userEvent.getEvent_type());
        response.setEventTime(userEvent.getEvent_time());

        return ResponseEntity.status(200).body(response);
    }

    @Override
    public ResponseEntity<List<UserEventDataResponse>> getAllUserEventDataFromData() {
        List<UserEvent> userEvents = userEventRepository.findAll();

        List<UserEventDataResponse> responses = userEvents.stream()
                .map(userEvent -> {
                    UserEventDataResponse response = new UserEventDataResponse();
                    response.setId(userEvent.getId());
                    response.setEventType(userEvent.getEvent_type());
                    response.setEventTime(userEvent.getEvent_time());
                    return response;
                })
                .collect(Collectors.toList());

        return ResponseEntity.status(200).body(responses);
    }
}