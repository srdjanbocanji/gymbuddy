package com.bocanjis.gym.controller;

import com.bocanjis.gym.document.SplitType;
import com.bocanjis.gym.model.GenerateTrainingSplitRequest;
import com.bocanjis.gym.model.TrainingSplitDto;
import com.bocanjis.gym.service.TrainingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/training")
@RequiredArgsConstructor
public class TrainingController {

    private final TrainingService trainingService;

    @PostMapping("/generate")
    public ResponseEntity<Mono<TrainingSplitDto>> generateTrainingSplit(@RequestBody GenerateTrainingSplitRequest request) {
        return ResponseEntity.ok(trainingService.generateTrainingSplit(request.getExercises(), request.getDaysInWeek(), request.getSplitType()));
    }
}
