package com.bocanjis.gym.document;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("trainings")
@Data
public class Training {

    private String id;
    private List<Exercise> exercises;

    @Data
    class Exercise {

        private String name;
        private int sets;
    }

}


