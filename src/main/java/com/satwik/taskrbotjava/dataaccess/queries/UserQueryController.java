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
import java.util.Arrays;
import java.util.List;

@Component
public class UserQueryController {

    private UsersRepository usersRepository;
    private ListsRepository listsRepository;
    private CommunitiesRepository communitiesRepository;

    private ListQueryController listQueryController;

    @Autowired
    public UserQueryController(UsersRepository usersRepository, ListsRepository listsRepository, CommunitiesRepository communitiesRepository, ListQueryController listQueryController) {
        this.usersRepository = usersRepository;
        this.listsRepository = listsRepository;
        this.communitiesRepository = communitiesRepository;
        this.listQueryController = listQueryController;
    }

    public void checkAndAddGuildUser(Long commID, Long userID, String userName){
        List<Users> users = usersRepository.findById(userID);
        if (users.isEmpty()) {
            Integer baseListID = listQueryController.createNewList(commID, userName, userID, false);
            Users currUser = new Users(userID, userName, new ArrayList<Integer>(Arrays.asList(baseListID)) , baseListID);
            usersRepository.save(currUser);
            Communities community = communitiesRepository.findByCommunityID(commID).get(0);
            community.getUsers().add(userID);
            communitiesRepository.save(community);
        }
    }

    public Integer getUserCurrentActiveListId(Long userID){
        List<Users> users = usersRepository.findById(userID);
        return users.get(0).getCurrentList();
    }

    public Integer swapUserCurrentActiveList(Long userID, String name){
        List<Lists> lists = listsRepository.findByName(name);
        Users user = usersRepository.findById(userID).get(0);

        if(lists.size() < 1)
            return user.getCurrentList();

        user.setCurrentList(lists.get(0).getListID());
        usersRepository.save(user);
        return user.getCurrentList();
    }

    public String swapUserCurrentActiveList(Long userID, Integer listID){
        List<Lists> lists = listsRepository.findByListID(listID);
        Users user = usersRepository.findById(userID).get(0);

        if(lists.size() < 1)
            return listsRepository.findByListID(user.getCurrentList()).get(0).getName();

        user.setCurrentList(lists.get(0).getListID());
        usersRepository.save(user);
        return lists.get(0).getName();
    }
}
