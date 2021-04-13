package com.egreenfox.trialexam.services;

import com.egreenfox.trialexam.models.Link;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LinkService {

    void saveLink (Link link);

    Link getLinkByAlias (String alias);

    List<Link> getAll ();

    Link getLinkByid (Integer id);

    void deleteById (Integer id);

    String urlShorting (String url);

    void getLinkByAliasAndIncrementHitCountByOne(String alias);

}

