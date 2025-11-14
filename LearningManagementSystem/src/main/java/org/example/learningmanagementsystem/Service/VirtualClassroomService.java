package org.example.learningmanagementsystem.Service;

import org.example.learningmanagementsystem.Model.VirtualClassroom;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Random;

@Service
public class VirtualClassroomService {

    ArrayList<VirtualClassroom> virtualClassrooms = new ArrayList<>();

    public ArrayList<VirtualClassroom> getVirtualClassrooms() {
        return virtualClassrooms;
    }

    public void addVirtualClassroom(VirtualClassroom virtualClassroom) {
        virtualClassrooms.add(virtualClassroom);
    }

    public boolean updateVirtualClassroom(String id, VirtualClassroom virtualClassroom) {
        for(VirtualClassroom c: virtualClassrooms) {
            if (id.equals(c.getId())) {
                virtualClassrooms.set(virtualClassrooms.indexOf(c),virtualClassroom);
                return true;
            }
        }
        return false;
    }

    public boolean deleteVirtualClassroom(String id) {
        for(VirtualClassroom c: virtualClassrooms) {
            if (id.equals(c.getId())) {
                virtualClassrooms.remove(c);
                return true;
            }
        }
        return false;
    }

    public VirtualClassroom getClassroomByTeacher(String teacher) {
        for(VirtualClassroom c: virtualClassrooms)
            if (teacher.equals(c.getAssignedTeacher()))
                return c;
        return null;
    }

    public boolean changeRecordingStatus(String id) {
        for(VirtualClassroom c: virtualClassrooms) {
            if (id.equals(c.getId())) {
                c.setRecorded(!c.isRecorded());
                return true;
            }
        }
        return false;
    }

    public int generatePasscode() {
        Random rand = new Random();
        return rand.nextInt(100, 1000);
    }

    public ArrayList<VirtualClassroom> getPublicSessions() {
        ArrayList<VirtualClassroom> publicSessions = new ArrayList<>(virtualClassrooms);
        publicSessions.removeIf(c -> c.isPrivate());
        return publicSessions;
    }

}
