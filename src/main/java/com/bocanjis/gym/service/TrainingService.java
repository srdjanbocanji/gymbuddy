package com.bocanjis.gym.service;

import com.bocanjis.gym.document.SplitType;
import com.bocanjis.gym.model.TrainingSplitDto;
import reactor.core.publisher.Mono;

import java.util.List;

public interface TrainingService {

    Mono<TrainingSplitDto> generateTrainingSplit(List<String> exercises, int daysInWeek, SplitType splitType);
}
