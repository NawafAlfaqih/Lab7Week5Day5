package org.example.learningmanagementsystem.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.learningmanagementsystem.ApiResponse.ApiResponse;
import org.example.learningmanagementsystem.Model.VirtualClassroom;
import org.example.learningmanagementsystem.Service.VirtualClassroomService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/virtual-classroom")
@RequiredArgsConstructor
public class VirtualClassroomController {

    private final VirtualClassroomService virtualClassroomService;

    @GetMapping("/get")
    public ResponseEntity<?> getVirtualClassrooms() {
        return ResponseEntity.status(200).body(virtualClassroomService.getVirtualClassrooms());
    }

    @PostMapping("/add")
    public ResponseEntity<?> addVirtualClassroom(@RequestBody @Valid VirtualClassroom virtualClassroom, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }
        virtualClassroomService.addVirtualClassroom(virtualClassroom);
        String message = "Virtual classroom added successfully (ID: " + virtualClassroom.getId() + ").";
        return ResponseEntity.status(201).body(new ApiResponse(message));
    }

    @PutMapping("/update/id/{id}")
    public ResponseEntity<?> updateVirtualClassroom(@PathVariable String id, @RequestBody @Valid VirtualClassroom virtualClassroom, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }
        if (virtualClassroomService.updateVirtualClassroom(id, virtualClassroom)) {
            String message = "Virtual classroom updated successfully (ID: " + id + ").";
            return ResponseEntity.status(200).body(new ApiResponse(message));
        }
        String message = "Virtual classroom not found (ID: " + id + ").";
        return ResponseEntity.status(404).body(new ApiResponse(message));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteVirtualClassroom(@PathVariable String id) {
        if (virtualClassroomService.deleteVirtualClassroom(id)) {
            String message = "Virtual classroom deleted successfully (ID: " + id + ").";
            return ResponseEntity.status(200).body(new ApiResponse(message));
        }
        String message = "Virtual classroom not found (ID: " + id + ").";
        return ResponseEntity.status(404).body(new ApiResponse(message));
    }

    @GetMapping("/get/teacher/{teacher}")
    public ResponseEntity<?> getClassroomByTeacher(@PathVariable String teacher) {
        VirtualClassroom classroom = virtualClassroomService.getClassroomByTeacher(teacher);
        if (classroom == null) {
            String message = "Virtual classroom not found for teacher (Name: " + teacher + ").";
            return ResponseEntity.status(404).body(new ApiResponse(message));
        }
        return ResponseEntity.status(200).body(classroom);
    }

    @PutMapping("/update/recording/{id}")
    public ResponseEntity<?> changeRecordingStatus(@PathVariable String id) {
        if (virtualClassroomService.changeRecordingStatus(id)) {
            String message = "Recording status changed successfully (ID: " + id + ").";
            return ResponseEntity.status(200).body(new ApiResponse(message));
        }
        String message = "Virtual classroom not found (ID: " + id + ").";
        return ResponseEntity.status(404).body(new ApiResponse(message));
    }

    @GetMapping("/get/public-sessions")
    public ResponseEntity<?> getPublicSessions() {
        if (virtualClassroomService.getPublicSessions().isEmpty()) {
            String message = "Public sessions are not found.";
            return ResponseEntity.status(404).body(new ApiResponse(message));
        }
        return ResponseEntity.status(200).body(virtualClassroomService.getPublicSessions());
    }

    @GetMapping("/generate-passcode")
    public ResponseEntity<?> generatePasscode() {
        int passcode = virtualClassroomService.generatePasscode();
        String message = "Generated passcode: " + passcode;
        return ResponseEntity.status(200).body(new ApiResponse(message));
    }
}
