package com.egreenfox.trialexam.repositories;


import com.egreenfox.trialexam.models.Link;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface LinkRepository extends JpaRepository <Link, Integer> {

    Link findByAlias (String alias);

/*
    @Query (value = "SELECT links.url, links.hitCount, links.alias, links.id from links ")
    List<Link>getAllWithNoSecretCode ();



    /*
    @Query (value = "SELECT l.url, l.alias, l.hitCount, l.id from Link l")
    List<Link>getAllWithNoSecretCode ();

 */

    /*
    @Query (value = "SELECT l from Link l")
    List<Link>getAllWithNoSecretCode ();
     */

}
