package com.satwik.taskrbotjava.dataaccess.queries;

import com.satwik.taskrbotjava.dataaccess.document.Communities;
import com.satwik.taskrbotjava.dataaccess.document.Lists;
import com.satwik.taskrbotjava.dataaccess.document.Users;
import com.satwik.taskrbotjava.dataaccess.repository.CommunitiesRepository;
import com.satwik.taskrbotjava.dataaccess.repository.ListsRepository;
import com.satwik.taskrbotjava.dataaccess.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ListQueryController {

    private ListsRepository listsRepository;
    private UsersRepository usersRepository;
    private CommunitiesRepository communitiesRepository;

    @Autowired
    public ListQueryController(ListsRepository listsRepository, UsersRepository usersRepository, CommunitiesRepository communitiesRepository) {
        this.listsRepository = listsRepository;
        this.usersRepository = usersRepository;
        this.communitiesRepository = communitiesRepository;
    }

//    public void checkAndAddGuildUser(MessageReceivedEvent event){
//        Long user = event.getAuthor().getIdLong();
//        Long community = event.getGuild().getIdLong();
//
//        List<Lists> lists = listsRepository.findById(user);
//        if (lists.isEmpty()) {
//            Users currUser = new Users(user, new ArrayList<Integer>(), 0);
//            listsRepository.save(currUser);
//        }
//    }

    public Lists getCurrentUsersActiveList(Long userID){
        List<Users> users = usersRepository.findById(userID);
        List<Lists> lists = listsRepository.findByListID(users.get(0).getCurrentList());
        return lists.get(0);
    }

    public Integer createNewList(Long communityID, String listName, Long ownerID, Boolean isOpen){
        isOpen = isOpen == null ? false : isOpen;
        Integer listID = listsRepository.getLatestId() + 1;
        Lists list = new Lists(listID, listName, ownerID, isOpen, new ArrayList<Integer>());
        listsRepository.save(list);
        Communities community = communitiesRepository.findByCommunityID(communityID).get(0);
        community.getLists().add(listID);
        communitiesRepository.save(community);
        return listID;
    }

    public List<Lists> getAllListsByCommunity(Long communityID){
        Communities community = communitiesRepository.findByCommunityID(communityID).get(0);
        return listsRepository.findByListIDIn(community.getLists());
    }

    public List<Lists> getAllListsByUser(Long userID){
        Users user = usersRepository.findById(userID).get(0);
        return listsRepository.findByListIDIn(user.getListIds());
    }
}
