package com.bocanjis.gym.controller;

import com.bocanjis.gym.model.ExerciseDto;
import com.bocanjis.gym.service.ExerciseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/exercises")
public class ExerciseController {

    private final ExerciseService exerciseService;

    @GetMapping("/{id}")
    public ResponseEntity<Mono<ExerciseDto>> getExercise(String id) {
        return ResponseEntity.ok(exerciseService.getExercise(id));
    }

    @GetMapping
    public ResponseEntity<Flux<ExerciseDto>> getExercises() {
        return ResponseEntity.ok(exerciseService.getAllExercises());
    }

    @PostMapping
    public ResponseEntity<Mono<ExerciseDto>> createExercise(@RequestBody ExerciseDto exerciseDto) {
        return ResponseEntity.ok(exerciseService.insertExercise(exerciseDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Mono<ExerciseDto>> updateExercise(String id, @RequestBody ExerciseDto exerciseDto) {
        exerciseDto.setId(id);
        return ResponseEntity.ok(exerciseService.updateExercise(exerciseDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Mono<Void>> deleteExercise(String id) {
        return ResponseEntity.ok().body(exerciseService.deleteExercise(id));
    }
}
