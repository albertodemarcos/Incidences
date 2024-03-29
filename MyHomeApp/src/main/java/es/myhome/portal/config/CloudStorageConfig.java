package es.myhome.portal.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

@Configuration
@Profile(value = {"prod"})
public class CloudStorageConfig {

	@Value("${cloud.aws.credentials.access-key}")
    private String accessKey;
    @Value("${cloud.aws.credentials.secret-key}")
    private String accessSecret;
    @Value("${cloud.aws.region.static}")
    private String region;
    
    @Bean
    @Primary
    public AmazonS3 s3Client() {
        AWSCredentials credentials = new BasicAWSCredentials(accessKey, accessSecret);
        AmazonS3 amazonS3Client = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(credentials)).withRegion(region).build();
        return amazonS3Client;
    }
    
}
