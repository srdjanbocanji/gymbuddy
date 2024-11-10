package com.bocanjis.gym.conversion;

import com.bocanjis.gym.document.Exercise;
import com.bocanjis.gym.model.ExerciseDto;
import com.bocanjis.gym.util.SneakyFun;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class ExerciseMapper {

    private final ObjectMapper objectMapper;

    public SneakyFun<Exercise, ExerciseDto> toExerciseDto() {
        return (ex) -> objectMapper.readValue(objectMapper.writeValueAsString(ex), ExerciseDto.class);
    }

    public SneakyFun<ExerciseDto, Exercise> toExercise() {
        return (ex) -> objectMapper.readValue(objectMapper.writeValueAsString(ex), Exercise.class);
    }
}
