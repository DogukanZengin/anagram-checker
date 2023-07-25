package com.io.beyonnex.model;

import jakarta.validation.constraints.NotEmpty;
import java.io.Serializable;

public record InputDto(
        @NotEmpty(message = "First text can not be null or empty")
        String first,
        @NotEmpty(message = "Second text can not be null or empty")
        String second) implements Serializable {}
