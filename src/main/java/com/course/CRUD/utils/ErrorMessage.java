package com.course.CRUD.utils;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.BindingResult;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
public class ErrorMessage {
    @JsonProperty("message")
    private String message;
    @JsonProperty("status_code")
    private int code;
    @JsonProperty("url_requested")
    private String urlRequested;

    public ErrorMessage(String message, int code, String urlRequested) {
        this.message = message;
        this.code = code;
        this.urlRequested = urlRequested;
    }

}

