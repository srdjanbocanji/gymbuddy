package com.bocanjis.gym.repository;

import com.bocanjis.gym.document.Exercise;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ExerciseRepository extends ReactiveMongoRepository<Exercise, String> {
}
