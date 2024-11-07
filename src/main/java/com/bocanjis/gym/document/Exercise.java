package com.bocanjis.gym.document;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "exercises")
@Data
public class Exercise {

    private String id;
    private String name;
    private String description;
    private List<String> tags;
}
