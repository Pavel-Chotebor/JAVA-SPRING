package com.DI.greenFoxClass.controllers;

import com.DI.greenFoxClass.services.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WebController {

    private StudentService studentService;

    public WebController(StudentService studentService) {
        this.studentService = studentService;
    }


    // HOMEPAGE
    @RequestMapping(value="/gfa")
    public String showHomepage(Model model) {
        model.addAttribute("totalStudents", studentService.count());
        return "homepage";
    }


    // ADDING NEW STUDENT FORM
    @RequestMapping(value="/gfa/add")
    public String showRequestFormForNewStudent() {
        return "newStudent";
    }


    // FORM PROCESSING
    @PostMapping("gfa/studentAdding")
    public String getDataFromNewStudents (@RequestParam String nameFromForm) {
        studentService.save(nameFromForm);
        return "redirect:/gfa";
    }


    // STUDENT CHECKING FORM
    @RequestMapping("gfa/checkForm")
    public String showCheckForm () {
        return "formForStudent";
    }


    // FORM PROCESSING
    @PostMapping(value="gfa/check")
    public String checkTheStudent(@RequestParam String nameFromForm, Model model) {
        model.addAttribute("nameCheck", studentService.checkTheStudent(nameFromForm));
        return "checkStatusInfo";
    }


    // PRINTING ALL STUDENTS
    @RequestMapping("gfa/allStudents")
    public String showCheckForm (Model model) {
        model.addAttribute("allStudents", studentService.findAll());
        return "checkStatusInfo";
    }

}