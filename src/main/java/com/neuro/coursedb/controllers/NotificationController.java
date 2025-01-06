package com.neuro.coursedb.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.neuro.coursedb.models.Notification;
import com.neuro.coursedb.services.GenericReadWriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/notifications")
public class NotificationController {
    @Autowired private GenericReadWriteService genericReadWriteService;

    @GetMapping(value = "/get")
    public ResponseEntity<List<Notification>> getNotifications(){
        return ResponseEntity.of(Optional.ofNullable(genericReadWriteService.getData(Notification.class)));
    }

    @PutMapping(value = "/save")
    public ResponseEntity<String> saveNotifications(@RequestBody List<Notification> notifications){
        genericReadWriteService.writeData(notifications, Notification.class);
        return ResponseEntity.ok("Wrote list to file");
    }


}
