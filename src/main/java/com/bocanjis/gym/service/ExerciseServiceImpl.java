package com.bocanjis.gym.service;

import com.bocanjis.gym.conversion.ExerciseMapper;
import com.bocanjis.gym.model.ExerciseDto;
import com.bocanjis.gym.repository.ExerciseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExerciseServiceImpl implements ExerciseService {

    private final ExerciseRepository exerciseRepository;
    private final ExerciseMapper exerciseMapper;

    @Override
    public Flux<ExerciseDto> getAllExercises() {
        return exerciseRepository
                .findAll()
                .map(exerciseMapper.toExerciseDto());
    }

    @Override
    public Mono<ExerciseDto> insertExercise(ExerciseDto exerciseDto) {
        return updateExercise(exerciseDto);
    }

    @Override
    public Mono<ExerciseDto> updateExercise(ExerciseDto exerciseDto) {
        return exerciseRepository.save(Optional.of(exerciseDto)
                        .map(exerciseMapper.toExercise())
                        .get())
                .map(exerciseMapper.toExerciseDto());
    }

    @Override
    public Mono<Void> deleteExercise(String exerciseId) {
        return exerciseRepository.deleteById(exerciseId);
    }

    @Override
    public Mono<ExerciseDto> getExercise(String exerciseId) {
        return exerciseRepository.findById(exerciseId).map(exerciseMapper.toExerciseDto());
    }
}
