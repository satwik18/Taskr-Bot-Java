package com.satwik.taskrbotjava.dataaccess.repository;

import com.satwik.taskrbotjava.dataaccess.document.Communities;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommunitiesRepository extends MongoRepository<Communities, Long> {
    @Query("{'communityID' : ?0}")
    List<Communities> findByCommunityID(Long communityID);
}
