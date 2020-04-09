package springBoot.aws.configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.InstanceProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class DynamoDBConfig {

    @Value("${amazon.aws.accesskey:accesskey}")
    private String accesskey;
    @Value("${amazon.aws.secretkey:secretkey}")
    private String secretkey;
    @Value("${amazon.aws.region:region}")
    private String region;

    @Profile("dev")
    @Bean
    public DynamoDBMapper dynamoDBMapper() {
        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(accesskey, secretkey)))
                .withRegion(region)
                .build();
        return new DynamoDBMapper(client, DynamoDBMapperConfig.DEFAULT);
    }

    @Profile("qa")
    @Bean
    public DynamoDBMapper dynamoDBMapperQa() {
        InstanceProfileCredentialsProvider provider = new InstanceProfileCredentialsProvider(false);
        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
                .withCredentials(provider)
                .build();
        return new DynamoDBMapper(client, DynamoDBMapperConfig.DEFAULT);
    }
}