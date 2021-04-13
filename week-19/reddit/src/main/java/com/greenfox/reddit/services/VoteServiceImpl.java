package com.greenfox.reddit.services;

import com.greenfox.reddit.models.Vote;
import com.greenfox.reddit.repositories.VoteRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class VoteServiceImpl implements VoteService {

    private VoteRepository voteRepository;

    public VoteServiceImpl(VoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }

    @Override
    public void saveVote(Vote vote) {
        voteRepository.save(vote);
    }

    @Override
    public List<Vote> findVoteByUserId(Long userId) {
        return voteRepository.findAllByVotedByUserId(userId);
    }
}
