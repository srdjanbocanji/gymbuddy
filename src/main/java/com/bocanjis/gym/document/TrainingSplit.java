package com.bocanjis.gym.document;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("training_splits")
@Data
public class TrainingSplit {

    private String id;
    private SplitType splitType;
    private List<Training> trainings;


    @Data
    class Training {
        private String id;
        private String name;
        private List<Exercise> exercises;
    }

    @Data
    class Exercise {

        private String name;
        private int sets;
    }
}
