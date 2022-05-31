package com.satwik.taskrbotjava.dataaccess.repository;

import com.satwik.taskrbotjava.dataaccess.document.Users;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersRepository extends MongoRepository<Users, Integer> {
    @Query("{'id' : ?0}")
    List<Users> findById(Long userID);

    List<Users> findByIdIn(List<Long> userIDs);
}
