package com.example.greenfoxacademy.basicwebshop.controllers;


import com.example.greenfoxacademy.basicwebshop.models.ShopItem;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class MainController {


    //ORIGINAL LIST TO PRINTING EACH FIELD IN TEMPLATES:  (LATER USED WITH STREAMS)
    List<ShopItem> shopItems = new ArrayList<>();

    public MainController() {      //database:

        shopItems.add(new ShopItem("Running shoes", "Nike running shoes for every day sport", 1000, 5,"Clothes and shoes"));
        shopItems.add(new ShopItem("Printer", "Hp printer es2000", 3000, 2, "Electronics"));
        shopItems.add(new ShopItem("Coca cola", "0,5l standard coke", 25, 0, "Beverages and Snacks"));
        shopItems.add(new ShopItem("Wokin", "Chicken with fried rice and WOKIN sauce", 119, 100, "Beverages and Snacks"));
        shopItems.add(new ShopItem("T-shirt", "Blue with corgi on a bike", 300, 1, "Clothes and shoes"));
    }





    //ENDPOINTS FOR homepage.html
    ////////////////////////////////////////////////////////////////////////////////

    //HOMEPAGE:
    @RequestMapping(value = "/home")
    public String showHomepage(Model model) {
        model.addAttribute("list", shopItems);
        return "homepage";
    }



    //ONLY ITEMS WITH MORE THAN 0 ON STOCK:
    @RequestMapping(value = "/only-available")
    public String showAvailable(Model model) {

        List<ShopItem> onlyAvailable = new ArrayList<>();

        onlyAvailable = (List<ShopItem>) shopItems.stream()
                .filter(item -> item.getQuantityOfStock() > 0)
                .collect(Collectors.toList());

        model.addAttribute("list", onlyAvailable);
        return "homepage";
    }



    //CHEAPEST FIRST:
    @RequestMapping(value = "/cheapest-first")
    public String showCheapestFirst(Model model) {

        List<ShopItem> sortedFromCheapest = new ArrayList<>();

        sortedFromCheapest = shopItems.stream()
                .sorted(Comparator.comparing(ShopItem::getPrice))
                .collect(Collectors.toList());

        model.addAttribute("list", sortedFromCheapest);
        return "homepage";
    }




    //ITEMS CONTAINS NIKE IN NAME OR DESCRIPTION (USED METHOD SEARCH IN LIST, IN THE END OF CONTROLLER):
    @RequestMapping(value = "/contains-nike")
    public String showNikesProducts(Model model) {

        model.addAttribute("list", searchInList("nike"));
        return "homepage";
    }




    //SEARCHING BAR
    @PostMapping("/search")
    public String search (@RequestParam String nameFromSearchBar, Model model) {

        model.addAttribute("list",searchInList(nameFromSearchBar));
        return "homepage";
    }




    //ENDPOINTS FOR stockInfo.html
    ////////////////////////////////////////////////////////////////////////////////

    //COUNTING AVERAGE VALUE OF STOCK ITEMS
    @RequestMapping(value = "/average-stock")
    public String showAverageStock(Model model) {

        double averageStock = shopItems.stream()
                .mapToDouble(item -> item.getQuantityOfStock())
                .average()
                .getAsDouble();

        model.addAttribute("average", averageStock);
        return "stockInfo";
    }



    //MOST EXPENSIVE PRODUCTS AND ITS PRICE
    @RequestMapping(value = "/most-expensive")
    public String showMostExpensive(Model model) {

        String maxPriceItem = "";

        double biggestPrice = shopItems.stream()
                .mapToDouble(a -> a.getPrice())
                .max()
                .getAsDouble();

        for (ShopItem item: shopItems) {
            if(item.getPrice() == biggestPrice) {
                maxPriceItem = item.getName();
            }
        }

        model.addAttribute("biggestPrice", biggestPrice);
        model.addAttribute("maxPriceItem", maxPriceItem);
        return "stockInfo";
    }




    //ENDPOINTS FOR homepage2.html
    ////////////////////////////////////////////////////////////////////////////////


    //HOMEPAGE2
    @RequestMapping(value = "/home2")
    public String showHomepage2(Model model) {
        model.addAttribute("list", shopItems);
        return "homepage2";
    }


    //ONLY CLOTHES ITEMS (USED METHOD searchByType, IN THE END OF CONTROLLER):
    @RequestMapping(value = "/clothes")
    public String showClothes(Model model) {
        model.addAttribute("list", searchByType("clothes"));
        return "homepage2";
    }



    //ONLY ELECTRONIC ITEMS (USED METHOD searchByType, IN THE END OF CONTROLLER):
    @RequestMapping(value = "/electronics")
    public String showElectronics(Model model) {
        model.addAttribute("list", searchByType("electronics"));
        return "homepage2";
    }



    //ONLY BEVERAGES ITEMS (USED METHOD searchByType, IN THE END OF CONTROLLER):
    @RequestMapping(value = "/beverages")
    public String showBeverages(Model model) {
        model.addAttribute("list", searchByType("beverages"));
        return "homepage2";
    }




    //PRICE IN EUR:
    @RequestMapping(value = "/price-in-eur")
    public String showEurPrices(Model model) {

        model.addAttribute("list", shopItems);
        model.addAttribute("displayEuro", true);

        //adding original shopItems list
        //but with variable name "displayEuro" is set condition in homepage2.html ->
        //when is displayEuro null(its just in this endpoint) ->  print *{price} field(from models) with Kč   or   print *{getPriceInEuro()} with eur

        return "homepage2";
    }



    //PRICE IN ORIGINAL:
    @RequestMapping(value = "/price-in-original")
    public String showOriginalPrices(Model model) {

        model.addAttribute("list", shopItems);
        return "homepage2";
    }


    //PRICE FILTERING:  (WHEN IS PRICE ADDED A BUTTON IS PRESSED IT RETURNS STREAM WITH PARTICULAR FILTER
    @RequestMapping(value = "/filter-by-price")
    public String filterByPrice(@RequestParam int priceFilter, Model model, String aboveButton, String belowButton, String exactlyButton ) {

        List<ShopItem> filteredListByPrice;

        if (!(aboveButton == null)) {
            filteredListByPrice = (List<ShopItem>) shopItems.stream()
                    .filter(item -> item.getPrice() >  priceFilter)
                    .collect(Collectors.toList());

            model.addAttribute("list", filteredListByPrice);
        }


        else if (!(belowButton == null)) {
            filteredListByPrice = (List<ShopItem>) shopItems.stream()
                    .filter(item -> item.getPrice() <  priceFilter)
                    .collect(Collectors.toList());

            model.addAttribute("list", filteredListByPrice);
        }

        else if (!(exactlyButton == null)) {
            filteredListByPrice = (List<ShopItem>) shopItems.stream()
                    .filter(item -> item.getPrice() ==  priceFilter)
                    .collect(Collectors.toList());

            model.addAttribute("list", filteredListByPrice);
        }

        return "homepage2";
    }




    //METHODS FOR ENDPOINTS:
    ////////////////////////////////////////////////////////////////////////////////

    //METHOD FOR SEARCHING BY NAME AND DESCRIPTION (WORKING WITH LOWER CASE OR UPPER CASE):
    public ArrayList<ShopItem> searchInList (String filterBy) {

        List <ShopItem> searchedItems = new ArrayList<>();

        searchedItems = (List<ShopItem>) shopItems.stream()
                .filter(item -> item.getName().toLowerCase().contains(filterBy.toLowerCase())
                        || item.getDescription().toLowerCase().contains(filterBy.toLowerCase()))
                .collect(Collectors.toList());

        return (ArrayList<ShopItem>) searchedItems;
    }




    //METHOD FOR FILTERING BY TYPE:
    public ArrayList<ShopItem> searchByType (String filterBy) {

        List <ShopItem> searchedItemsByType = new ArrayList<>();

        searchedItemsByType = (List<ShopItem>) shopItems.stream()
                .filter(item -> item.getType().toLowerCase().contains(filterBy.toLowerCase()))
                .collect(Collectors.toList());

        return (ArrayList<ShopItem>) searchedItemsByType;
    }
}