package com.bocanjis.gym.model;

import com.bocanjis.gym.document.SplitType;
import lombok.Data;

import java.util.List;

@Data
public class TrainingSplitDto {

    private String id;
    private SplitType splitType;
    private List<Training> trainings;

    @Data
    public static class Training {
        private String id;
        private String name;
        private List<Exercise> exercises;
    }

    @Data
    public static class Exercise {

        private String name;
        private int sets;
    }
}
