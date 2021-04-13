package com.egreenfox.trialexam.Controllers;

import com.egreenfox.trialexam.models.Link;
import com.egreenfox.trialexam.models.SecretNumber;
import com.egreenfox.trialexam.services.LinkService;
import com.egreenfox.trialexam.services.SecretNumberService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@Controller
public class MainController {

    private LinkService linkService;
    private SecretNumberService secretNumberService;

    public MainController(LinkService linkService, SecretNumberService secretNumberService) {
        this.secretNumberService = secretNumberService;
        this.linkService = linkService;
    }

    @GetMapping("/")
    public String showMainPage() {
        return "index";
    }

    @PostMapping("/saveLinkProcessing")
    public String saveLinkProcessing(@RequestParam String url, @RequestParam String alias, Model model) {

        if (linkService.getLinkByAlias(alias) == null) {
            Link link = (new Link(linkService.urlShorting(url),alias,0));
            linkService.saveLink(link);

            SecretNumber number = new SecretNumber(secretNumberService.generateRandomSecretNumber());
            secretNumberService.saveSecretNumber(number);
            link.setSecretNumber(number);

            linkService.saveLink(link);

            model.addAttribute("message", "Your URL is aliased to " + alias + " and your secret code is " + number.getNumber() + ".");

        } else {
            model.addAttribute("message", "Your alias is already in use!");
            model.addAttribute("urlHolder", alias);
            model.addAttribute("aliasHolder", alias);
        }
        return "index";
    }

    @GetMapping("/a/{alias}")   //nefunguje
    @ResponseBody
    public ResponseEntity<?> getLink (@PathVariable String alias) throws URISyntaxException {
        if (linkService.getLinkByAlias(alias) != null) {
            linkService.getLinkByAliasAndIncrementHitCountByOne(alias);
            linkService.saveLink(linkService.getLinkByAlias(alias));

            // return (ResponseEntity<?>) ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY).header(HttpHeaders.LOCATION,
            //       linkService.getLinkByAlias(alias).getUrl()).build();

          /*
            URI uri = new URI(linkService.getLinkByAlias(alias).getUrl());
            HttpHeaders myHeader = new HttpHeaders();
            myHeader.setLocation(uri);

            return new ResponseEntity<>(myHeader, HttpStatus.SEE_OTHER);

           */

            /*
            HttpHeaders headers = new HttpHeaders();
            headers.add("Custom-Header", linkService.getLinkByAlias(alias).getUrl());

            return new ResponseEntity<>(
                    "Custom header set", headers, HttpStatus.OK);

             */

            return (ResponseEntity<?>) ResponseEntity.status(HttpStatus.OK).header(HttpHeaders.LOCATION,
                           linkService.getLinkByAlias(alias).getUrl()).build();

           }  else return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @GetMapping("/api/links")
    @ResponseBody
    public ResponseEntity<?> getLinks() {
        return new ResponseEntity<>(linkService.getAll(), HttpStatus.OK);
    }


    @DeleteMapping("/api/links/{linkId}")
    @ResponseBody
    public ResponseEntity<?> deleteLinkById (@RequestBody SecretNumber secretNumber, @PathVariable Integer linkId) {

        if(linkService.getLinkByid(linkId).getSecretNumber().getNumber().equals(secretNumber.getNumber())) {
            linkService.deleteById(linkId);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        else if(secretNumberService.findSecretNumberById(linkId) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else  {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    /*   SQL SYNTAX

    SELECT name FROM applicants WHERE country LIKE "Czech Republic" ORDER BY age ASC LIMIT 5

     */

}
