package springBoot.aws.controller;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import springBoot.aws.model.User;
import springBoot.aws.service.UserCrudService;

import java.util.logging.Logger;

@RestController
@RequestMapping("/api-users")
public class UserCrudController {

    private final static Logger log = Logger.getLogger(UserCrudController.class.getName());
    private UserCrudService userCrudService;

    @Autowired
    public UserCrudController(UserCrudService userCrudService) {
        this.userCrudService = userCrudService;
    }

    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity createUser(@RequestBody User user) {
        try {
            log.info("createUser");
            User response = userCrudService.createUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (AmazonServiceException e) {
            log.info("Exception: "+ e.getMessage());
            throw new ResponseStatusException(HttpStatus.valueOf(e.getStatusCode()), e.getMessage(), e);
        } catch (AmazonClientException e) {
            log.info("Exception: "+ e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);

        }
    }

    @GetMapping(path = "/{userId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity readUser(@PathVariable String userId) {
        try {
            log.info("readUser");
            User response = userCrudService.readUser(userId);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (AmazonServiceException e) {
            log.info("Exception: "+ e.getMessage());
            throw new ResponseStatusException(HttpStatus.valueOf(e.getStatusCode()), e.getMessage(), e);

        } catch (AmazonClientException e) {
            log.info("Exception: "+ e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);

        }
    }

    @PutMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity updateUser(@RequestBody User user) {
        try {
            log.info("updateUser");
            User response = userCrudService.updateUser(user);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (AmazonServiceException e) {
            log.info("Exception: "+ e.getMessage());
            throw new ResponseStatusException(HttpStatus.valueOf(e.getStatusCode()), e.getMessage(), e);
        } catch (AmazonClientException e) {
            log.info("Exception: "+ e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);

        }
    }

    @DeleteMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity deleteUser(@PathVariable String userId) {
        try {
            log.info("deleteUser");
            userCrudService.deleteUser(userId);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } catch (AmazonServiceException e) {
            log.info("Exception: "+ e.getMessage());
            throw new ResponseStatusException(HttpStatus.valueOf(e.getStatusCode()), e.getMessage(), e);
        } catch (AmazonClientException e) {
            log.info("Exception: "+ e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);

        }
    }
}
