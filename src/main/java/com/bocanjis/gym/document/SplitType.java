package com.bocanjis.gym.document;

public enum SplitType {

    BRO("Bro training Split"),
    UPPER_LOWER("Upper - lower  training split"),
    PUSH_PULL_LEGS("Push pull legs training split"),
    CLASSIC("Classic training split")
    ;

    private final String name;

    private SplitType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
