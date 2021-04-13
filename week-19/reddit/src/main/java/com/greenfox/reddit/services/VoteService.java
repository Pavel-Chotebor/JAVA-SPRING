package com.greenfox.reddit.services;

import com.greenfox.reddit.models.Vote;
import com.greenfox.reddit.repositories.VoteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface VoteService {

     void saveVote (Vote vote);

     List<Vote> findVoteByUserId (Long userId);
}
