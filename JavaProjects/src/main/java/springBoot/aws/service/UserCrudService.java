package springBoot.aws.service;

import springBoot.aws.model.User;

public interface UserCrudService {

    public User createUser(User user) ;


    public User readUser(String userId) ;


    public User updateUser(User user) ;


    public void deleteUser(String userId);
}
