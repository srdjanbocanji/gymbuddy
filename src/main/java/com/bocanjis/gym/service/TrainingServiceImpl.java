package com.bocanjis.gym.service;

import com.bocanjis.gym.document.SplitType;
import com.bocanjis.gym.model.TrainingSplitDto;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.converter.BeanOutputConverter;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TrainingServiceImpl implements TrainingService {

    private final ChatClient chatClient;
    private final ExerciseService exerciseService;

    @Override
    public Mono<TrainingSplitDto> generateTrainingSplit(List<String> exercises, int daysInWeek, SplitType splitType) {
        var converter = new BeanOutputConverter<>(new ParameterizedTypeReference<TrainingSplitDto>() {
        });
        return chatClient.prompt()
                .user(getTrainingGenerationPrompt(exercises, daysInWeek, splitType, converter.getFormat()))
                .stream()
                .content()
                .collect(Collectors.joining())
                .map(converter::convert);
    }

    private Consumer<ChatClient.PromptUserSpec> getTrainingGenerationPrompt(List<String> exercises, int daysInWeek, SplitType splitType, String format) {
        return u -> {
            u.text("""
                     Generate {daysInWeek} days {splitType} using following exercises: {exercises}.
                    {format}
                    """)
                    .param("daysInWeek", daysInWeek)
                    .param("splitType", splitType.getName())
                    .param("exercises", String.join(", ", exercises))
                    .param("format", format);
        };
    }
}
