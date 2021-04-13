package com.greenfoxacademy.excercises.controllers;

import com.greenfoxacademy.excercises.models.BankAccount;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Controller
public class MainController {

    List<BankAccount> animals = new ArrayList<>();
    //DecimalFormat df = new DecimalFormat("0.00");

    public MainController() {

        // this provides data before we start work with databases ->  so just temporary solution:
        animals.add(new BankAccount("Scar", 3000, "lion", "classic", false));
        animals.add(new BankAccount("Mufasa", 1000, "lion", "Kings", true));
        animals.add(new BankAccount("Nala", 4000, "lioness", "classic", true));
        animals.add(new BankAccount("Zazu", 500, "bird", "classic", true));
    }


    //showing all data in table:
    @RequestMapping(value = "/showAll")
    public String showAll(@RequestParam(required = false, defaultValue = "false") boolean error, Model model) {
        model.addAttribute("animalsList", animals);
        return "template3";
    }


    // Single data for exercise bellow:
    BankAccount bankAccount1 = new BankAccount("Simba", 2000, "lion", "Kings", true);


    // showing single fields of object:
    @RequestMapping(value = "/showOne")
    public String show(Model model) {
        model.addAttribute("name", bankAccount1.getName());
        model.addAttribute("balance", bankAccount1.getBalance());
        model.addAttribute("animalType", bankAccount1.getAnimalType());
        return "template1";
    }


    // showing difference between th:text  an  th:utext    ->    utext can tranform string to html working tags, text print everything
    String sampleText = "This is an <em>HTML</em> text. <b>Enjoy yourself!</b>";

    @RequestMapping(value = "/text")
    public String uText(Model model) {
        model.addAttribute("sample", sampleText);
        return "template2";
    }


    // Endpoint just for data input, so returning new form.html
    @RequestMapping("/sendMoney")
    public String showForm() {
        return "send_money_form";
    }


    @RequestMapping("/add")
    public String showAddAccountForm() {
        return "newBankAccount_form";
    }


    // entering data from send_money_form
    @PostMapping("/transaction")
    public String raiseTheBalance(@RequestParam("clientName") String clientName, Model model) {

//        List <BankAccount> accounts;
        List <BankAccount> accounts =   animals.stream()
                .filter(animal -> animal.getName().equalsIgnoreCase(clientName))
                .collect(Collectors.toList());

        //if client is has king account:
        accounts = animals.stream()
               // .filter(animal -> animal.getName().equalsIgnoreCase(clientName))
                .filter(animal -> animal.getTypeOfAccount().equalsIgnoreCase("Kings"))
                .peek(account -> account.setBalance(account.getBalance() + 100))
                .collect(Collectors.toList());
       accounts = animals;


        accounts = animals.stream()
                //.filter(animal -> animal.getName().equalsIgnoreCase(clientName))
                .filter(animal -> animal.getTypeOfAccount().equalsIgnoreCase("classic"))
                .peek(account -> account.setBalance(account.getBalance() + 10))
                .collect(Collectors.toList());
        accounts = animals;


            return "redirect:/showAll";
            //return "redirect:/showAll?error=true";
        }

    @PostMapping("/accountCreating")
    public String accountCreating (@RequestParam String clientName, String typeOfAnimal, double startingBalance, String typeOfAccount, String behaviour) {

        boolean finalBehaviour = false;

        if (behaviour == "Good guy") {
            finalBehaviour = true;
        } else finalBehaviour = false;

        animals.add(new BankAccount(clientName, startingBalance, typeOfAnimal, typeOfAccount, finalBehaviour));
        return "redirect:/showAll";
    }
}