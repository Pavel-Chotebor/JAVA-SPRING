package com.greenfox.reddit.repositories;
import com.greenfox.reddit.models.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoteRepository extends JpaRepository <Vote, Long> {

    @Query(value="SELECT v FROM Vote v WHERE v.votedByUserId = ?1")
    List<Vote> findAllByVotedByUserId (Long userId);
}
