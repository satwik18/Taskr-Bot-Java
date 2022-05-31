package com.satwik.taskrbotjava.dataaccess.queries;

import com.satwik.taskrbotjava.dataaccess.document.Items;
import com.satwik.taskrbotjava.dataaccess.document.Lists;
import com.satwik.taskrbotjava.dataaccess.document.util.TaskStatus;
import com.satwik.taskrbotjava.dataaccess.repository.ItemsRepository;
import com.satwik.taskrbotjava.dataaccess.repository.ListsRepository;
import com.satwik.taskrbotjava.dataaccess.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class ItemQueryController {

    private ItemsRepository itemsRepository;
    private ListsRepository listsRepository;
    private UsersRepository usersRepository;
    private UserQueryController userQueryController;
    private ListQueryController listQueryController;

    @Autowired
    public ItemQueryController(ItemsRepository itemsRepository, ListsRepository listsRepository, UsersRepository usersRepository, UserQueryController userQueryController, ListQueryController listQueryController) {
        this.itemsRepository = itemsRepository;
        this.listsRepository = listsRepository;
        this.usersRepository = usersRepository;
        this.userQueryController = userQueryController;
        this.listQueryController = listQueryController;
    }

    public List<Items> getCurrentUserListItems(Long userID){
        Lists list = listQueryController.getCurrentUsersActiveList(userID);
        List<Items> items = itemsRepository.findByItemIDIn(list.getItemIds());
        return items;
    }

    public void createNewItem(Long userID, Date dueDate, String itemDescription){
        Integer listID = userQueryController.getUserCurrentActiveListId(userID);
        Integer itemID = itemsRepository.getLatestId() + 1;
        Items newItem = new Items(itemID, userID, dueDate, itemDescription, TaskStatus.TODO);
        itemsRepository.save(newItem);
        Lists list = listsRepository.findByListID(listID).get(0);
        list.getItemIds().add(itemID);
        listsRepository.save(list);
    }
}
