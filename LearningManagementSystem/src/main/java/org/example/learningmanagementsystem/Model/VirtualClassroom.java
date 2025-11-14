package org.example.learningmanagementsystem.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class VirtualClassroom {

    @NotBlank(message = "id cannot be empty")
    @Size(min = 10, max = 10, message = "id length must be exactly 10 in length")
    @Pattern(regexp = "^[0-9]+$", message = "id must contain digits only")
    private String id;

    @NotBlank(message = "Classroom Name cannot be empty")
    @Size(min = 3, max = 20, message = "Classroom Name length must be minimum '3' and maximum '20' in length")
    private String classroomName;

    @NotBlank(message = "Assigned Teacher cannot be empty")
    @Size(min = 3, max = 20, message = "Assigned Teacher length must be minimum '3' and maximum '20' in length")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Assigned Teacher must contain Characters only")
    private String assignedTeacher;

    @NotNull(message = "maxParticipants cannot be empty")
    @PositiveOrZero(message = "maxParticipants cannot be negative")
    @Min(value = 0, message = "maxParticipants must be minimum of '0' ")
    @Max(value = 100, message = "maxParticipants must be maximum of '100' ")
    private int maxParticipants;

    @NotBlank(message = "Scheduled Date cannot be empty")
    @FutureOrPresent(message = "Cannot schedule classroom in the past")
    private LocalDate scheduledDate;

    @NotNull(message = "isPrivate cannot be empty")
    private boolean isPrivate;

    @NotNull(message = "classroomURL cannot be empty")
    @URL
    private String classroomURL;

    @NotNull(message = "isRecorded cannot be empty")
    @AssertFalse(message = "isRecorded must be 'false' ")
    private boolean isRecorded;
}
