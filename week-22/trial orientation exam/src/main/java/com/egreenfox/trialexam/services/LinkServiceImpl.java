package com.egreenfox.trialexam.services;

import com.egreenfox.trialexam.models.Link;
import com.egreenfox.trialexam.repositories.LinkRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class LinkServiceImpl implements LinkService{

    private LinkRepository linkRepository;

    public LinkServiceImpl(LinkRepository linkRepository) {
        this.linkRepository = linkRepository;
    }


    @Override
    public void saveLink(Link link) {
        linkRepository.save(link);
    }

    @Override
    public Link getLinkByAlias(String alias) {
        if(linkRepository.findByAlias(alias) != null) {
            return linkRepository.findByAlias(alias);
        } else return null;
    }

    @Override
    public List<Link> getAll() {
        return linkRepository.findAll();
    }

    @Override
    public Link getLinkByid(Integer id) {
        return linkRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteById(Integer id) {
        linkRepository.deleteById(id);
    }


    public String urlShorting (String url){
        int indexOfFirstsSlash;
        int indexOfClosingSlash;
        String urlWithoutProtocolInfo = "";
        String newShortenedUrl = "";

        if(url.contains("www.")){
            indexOfFirstsSlash = url.indexOf("/") + 6;
        } else {
            indexOfFirstsSlash = url.indexOf("/") + 2;
        }

        urlWithoutProtocolInfo = url.substring(indexOfFirstsSlash);

        indexOfClosingSlash = urlWithoutProtocolInfo.indexOf("/");

        newShortenedUrl = urlWithoutProtocolInfo.substring(0, indexOfClosingSlash);


        return newShortenedUrl;

    }

    @Override
    public void getLinkByAliasAndIncrementHitCountByOne(String alias) {
        getLinkByAlias(alias).setHitCount(getLinkByAlias(alias).getHitCount() + 1);
    }
}