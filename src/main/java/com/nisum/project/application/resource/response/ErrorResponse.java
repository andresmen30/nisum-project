package com.nisum.project.application.resource.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor(staticName = "error")
@Getter(AccessLevel.PUBLIC)
@JsonPropertyOrder({"code", "message", "details"})
public class ErrorResponse {

    private final int code;
    private final String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final List<String> details;
}
