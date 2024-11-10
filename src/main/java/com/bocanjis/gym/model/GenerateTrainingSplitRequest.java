package com.bocanjis.gym.model;

import com.bocanjis.gym.document.SplitType;
import lombok.Data;

import java.util.List;

@Data
public class GenerateTrainingSplitRequest {

    private List<String> exercises;
    private int daysInWeek;
    private SplitType splitType;
}
