package com.bocanjis.gym.service;

import com.bocanjis.gym.model.ExerciseDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface ExerciseService {

    Flux<ExerciseDto> getAllExercises();
    Mono<ExerciseDto> insertExercise(ExerciseDto exerciseDto);
    Mono<ExerciseDto> updateExercise(ExerciseDto exerciseDto);
    Mono<Void> deleteExercise(String exerciseId);
    Mono<ExerciseDto> getExercise(String exerciseId);
    Flux<ExerciseDto> batchInsertExercises(List<ExerciseDto> exerciseDtos);
}
