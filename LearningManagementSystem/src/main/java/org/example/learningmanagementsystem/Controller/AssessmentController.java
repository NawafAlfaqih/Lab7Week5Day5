package org.example.learningmanagementsystem.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.learningmanagementsystem.ApiResponse.ApiResponse;
import org.example.learningmanagementsystem.Model.Assessment;
import org.example.learningmanagementsystem.Service.AssessmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/assessment")
@RequiredArgsConstructor
public class AssessmentController {

    private final AssessmentService assessmentService;

    @GetMapping("/get")
    public ResponseEntity<?> getAssessments() {
        return ResponseEntity.status(200).body(assessmentService.getAssessments());
    }

    @PostMapping("/add")
    public ResponseEntity<?> addAssessment(@RequestBody @Valid Assessment assessment, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }
        assessmentService.addAssessment(assessment);
        String message = "Assessment added successfully (ID: " + assessment.getId() + ").";
        return ResponseEntity.status(201).body(new ApiResponse(message));
    }

    @PutMapping("/update/id/{id}")
    public ResponseEntity<?> updateAssessment(@PathVariable String id, @RequestBody @Valid Assessment assessment, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }
        if (assessmentService.updateAssessment(id, assessment)) {
            String message = "Assessment updated successfully (ID: " + id + ").";
            return ResponseEntity.status(200).body(new ApiResponse(message));
        }
        String message = "Assessment not found (ID: " + id + ").";
        return ResponseEntity.status(404).body(new ApiResponse(message));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteAssessment(@PathVariable String id) {
        if (assessmentService.deleteAssessment(id)) {
            String message = "Assessment deleted successfully (ID: " + id + ").";
            return ResponseEntity.status(200).body(new ApiResponse(message));
        }
        String message = "Assessment not found (ID: " + id + ").";
        return ResponseEntity.status(404).body(new ApiResponse(message));
    }

    @GetMapping("/get/coordinator/{coordinator}")
    public ResponseEntity<?> getAssessmentsByCoordinator(@PathVariable String coordinator) {
        ArrayList<Assessment> assessments = assessmentService.getAssessmentsByCoordinator(coordinator);
        if (assessments.isEmpty()) {
            String message = "Assessments not found (Coordinator: " + coordinator + ").";
            return ResponseEntity.status(404).body(new ApiResponse(message));
        }
        return ResponseEntity.status(200).body(assessments);
    }

    @PutMapping("/update/grade")
    public ResponseEntity<?> gradeAssessments() {
        if (!assessmentService.gradeAssessments()) {
            String message = "All assessments are already graded (No ungraded assessments found).";
            return ResponseEntity.status(404).body(new ApiResponse(message));
        }
        String message = "Assessments graded successfully (All are now graded).";
        return ResponseEntity.status(200).body(new ApiResponse(message));
    }

    @DeleteMapping("/delete/graded")
    public ResponseEntity<?> removeGradedAssessments() {
        if (!assessmentService.removeGradedAssessments()) {
            String message = "Graded assessments not found.";
            return ResponseEntity.status(404).body(new ApiResponse(message));
        }
        String message = "Graded assessments deleted successfully.";
        return ResponseEntity.status(200).body(new ApiResponse(message));
    }

    @GetMapping("/get/average-passing-score")
    public ResponseEntity<?> getAveragePassingScore() {
        int avg = assessmentService.getAveragePassingScore();
        return ResponseEntity.status(200).body(avg);
    }
}
