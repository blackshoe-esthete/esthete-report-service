package org.example.blackshoe.esthetereportservice.exception;

public class ReportException extends RuntimeException {
    private final ReportErrorResult reportErrorResult;

    public ReportException(ReportErrorResult reportErrorResult) {
        super(reportErrorResult.getMessage());
        this.reportErrorResult = reportErrorResult;
    }
}