package org.example.learningmanagementsystem.Service;

import org.example.learningmanagementsystem.Model.Assessment;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AssessmentService {

    ArrayList<Assessment> assessments = new ArrayList<>();

    public ArrayList<Assessment> getAssessments() {
        return assessments;
    }

    public void addAssessment(Assessment assessment) {
        assessments.add(assessment);
    }

    public boolean updateAssessment(String id, Assessment assessment) {
        for (Assessment a : assessments) {
            if (id.equals(a.getId())) {
                assessments.set(assessments.indexOf(a), assessment);
                return true;
            }
        }
        return false;
    }

    public boolean deleteAssessment(String id) {
        for (Assessment a : assessments) {
            if (id.equals(a.getId())) {
                assessments.remove(a);
                return true;
            }
        }
        return false;
    }

    public ArrayList<Assessment> getAssessmentsByCoordinator(String coordinator) {
        ArrayList<Assessment> result = new ArrayList<>(assessments);
        result.removeIf(a -> !coordinator.equals(a.getCoordinator()));
        return result;
    }

    public boolean gradeAssessments() {
        boolean graded = false;
        for (Assessment a : assessments) {
            if (!a.getIsGraded()) {
                a.setIsGraded(true);
                graded = true;
            }
        }
        return graded;
    }

    public boolean removeGradedAssessments() {
        ArrayList<Assessment> graded = new ArrayList<>(assessments);
        graded.removeIf(a -> !a.getIsGraded());
        if (graded.isEmpty())
            return false;
        assessments.removeIf(Assessment::getIsGraded);
        return true;
    }

    public int getAveragePassingScore() {
        if (assessments.isEmpty())
            return 0;

        int sum = 0;
        for (Assessment a : assessments)
            sum += a.getPassingScore();
        return sum / assessments.size();
    }
}
