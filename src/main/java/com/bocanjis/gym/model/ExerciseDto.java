package com.bocanjis.gym.model;

import lombok.Data;

import java.util.List;

@Data
public class ExerciseDto {
    private String id;
    private String name;
    private String description;
    private List<String> tags;
}
