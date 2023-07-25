package com.io.beyonnex.controller;

import com.io.beyonnex.model.ErrorResponse;
import com.io.beyonnex.model.InputDto;
import com.io.beyonnex.service.AnagramCheckerService;
import jakarta.validation.Valid;
import java.util.StringJoiner;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * REST API for checking anagrams
 */
@Controller
@RequestMapping("/api/anagrams")
public class RestController {

    private final AnagramCheckerService service;

    public RestController(AnagramCheckerService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Boolean> checkAnagram(@Valid @RequestBody InputDto input) {
        return ResponseEntity.ok(service.isAnagram(input));
    }

    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<ErrorResponse> handleException(Exception exception) {
        return ResponseEntity.badRequest().body(new ErrorResponse(exception.getMessage()));
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException exception) {
        var message = exception.getBindingResult();
        var sb = new StringJoiner(", ");
        message.getAllErrors().forEach(e -> sb.add(e.getDefaultMessage()));
        return ResponseEntity.badRequest().body(new ErrorResponse(sb.toString()));
    }
}
