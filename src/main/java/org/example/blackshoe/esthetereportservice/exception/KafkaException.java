package org.example.blackshoe.esthetereportservice.exception;

public class KafkaException extends RuntimeException {
    private final KafkaErrorResult kafkaErrorResult;

    public KafkaException(KafkaErrorResult kafkaErrorResult) {
        super(kafkaErrorResult.getMessage());
        this.kafkaErrorResult = kafkaErrorResult;
    }
}