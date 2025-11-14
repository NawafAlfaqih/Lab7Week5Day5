package org.example.learningmanagementsystem.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Assessment {

    @NotEmpty(message = "id cannot be empty")
    @Size(min = 10, max = 10, message = "id length must be exactly 10 in length")
    @Pattern(regexp = "^[0-9]+$", message = "ID must contain only digits")
    private String id;

    @NotEmpty(message = "Coordinator cannot be empty")
    @Size(min = 5, max = 20, message = "Coordinator must be between 5 and 20 characters")
    private String coordinator;

    @NotEmpty(message = "Type cannot be empty")
    @Pattern(regexp = "^(project|exam|lab)$", message = "Type must be only (project, exam, lab)")
    private String type;

    @NotNull(message = "Total score cannot be empty")
    @Min(value = 1, message = "Total score must be more than 0")
    private Integer totalScore;

    @NotNull(message = "Passing score cannot be empty")
    @Min(value = 1, message = "Passing score must be more than 0")
    private Integer passingScore;

    @NotNull(message = "Duration cannot be empty")
    @Min(value = 10, message = "Duration must be more than 10")
    private Integer duration;

    @NotNull(message = "Date cannot be empty")
    @PastOrPresent(message = "Date cannot be in the future")
    private LocalDate date;

    @NotNull(message = "isGraded cannot be empty")
    private Boolean isGraded;
}
