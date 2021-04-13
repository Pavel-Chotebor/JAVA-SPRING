package com.DI.greenFoxClass.services;


import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {
    private List<String> names;

    public List<String> getNames() {
        return names;
    }

    public void setNames(List<String> names) {
        this.names = names;
    }

    public StudentService() {
        names = new ArrayList<>();
        names.add("Sanyi");
        names.add("Lilla");
        names.add("John");
    }

    public List<String> findAll() {
        return names;
    }

    public void save(String student) {
        names.add(student);
    }

    public int count() {
        int totalNumberOfStudents = names.size();
        return totalNumberOfStudents;
    }

    public Boolean checkTheStudent(String nameOfTheStudent) {
        for (String name : names) {
            if (name.equalsIgnoreCase(nameOfTheStudent)) {
                return true;
            }
        }
        return false;
    }
}

