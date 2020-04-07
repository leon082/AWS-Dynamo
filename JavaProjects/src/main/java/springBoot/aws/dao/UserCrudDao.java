package springBoot.aws.dao;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import springBoot.aws.model.User;

public interface UserCrudDao {




    public User createUser(User user) ;


    public User readUser(String userId);


    public User updateUser(User user) ;


    public void deleteUser(String userId) ;
}
