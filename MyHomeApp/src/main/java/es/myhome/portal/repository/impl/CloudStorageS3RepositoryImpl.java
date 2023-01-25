package es.myhome.portal.repository.impl;


import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CopyObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.amazonaws.util.IOUtils;

import es.myhome.portal.domain.cloud.Asset;
import es.myhome.portal.repository.CloudStorageS3Repository;

@Repository
public class CloudStorageS3RepositoryImpl implements CloudStorageS3Repository {
	
	private final Logger logger = LoggerFactory.getLogger(CloudStorageS3RepositoryImpl.class);
	
	private final AmazonS3 amazonS3Client;
	
	public CloudStorageS3RepositoryImpl(AmazonS3 amazonS3Client) {
		super();
		this.amazonS3Client = amazonS3Client;
	}

	@Override
	public List<Asset> listObjectsInBucket(String bucket) {
		// TODO Auto-generated method stub
		List<Asset>  items = amazonS3Client.listObjectsV2(bucket).getObjectSummaries().stream()
		                    .parallel()
		                    .map(S3ObjectSummary::getKey)
		                    .map(key -> mapS3ToObject(bucket, key))
		                    .collect(Collectors.toList());
		
		logger.info("Found " + items.size() + " objects in the bucket " + bucket);
		return items;
	}

	@Override
	public S3ObjectInputStream getObject(String bucketName, String fileName) throws IOException {
		// TODO Auto-generated method stub
        if (!amazonS3Client.doesBucketExistV2(bucketName)) {
            logger.error("No Bucket Found");
            return null;
        }
        S3Object s3object = amazonS3Client.getObject(bucketName, fileName);
        return s3object.getObjectContent();
	}

	@Override
	public byte[] downloadFile(String bucketName, String fileName) throws IOException {
		// TODO Auto-generated method stub
        S3Object s3Object = amazonS3Client.getObject(bucketName, fileName);
        S3ObjectInputStream inputStream = s3Object.getObjectContent();
        try {
            byte[] content = IOUtils.toByteArray(inputStream);
            return content;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
	}

	@Override
	public void moveObject(String bucketName, String fileKey, String destinationFileKey) {
		// TODO Auto-generated method stub
        CopyObjectRequest copyObjRequest = new CopyObjectRequest(bucketName, fileKey, bucketName, destinationFileKey);
        amazonS3Client.copyObject(copyObjRequest);
        deleteObject(bucketName, fileKey);
	}

	@Override
	public void deleteObject(String bucketName, String fileKey) {
		// TODO Auto-generated method stub
		amazonS3Client.deleteObject(bucketName, fileKey);
	}

	@Override
	public String uploadFile(String bucketName, String fileName, File fileObj) {
		// TODO Auto-generated method stub
		amazonS3Client.putObject(new PutObjectRequest(bucketName, fileName, fileObj));
        fileObj.delete();
        return "File uploaded : " + fileName;
	}
	
	 private Asset mapS3ToObject(String bucket, String key) {

        /*return Asset.builder()
                .name(s3Client.getObjectMetadata(bucket, key).getUserMetaDataOf("name"))
                .key(key)
                .url(s3Client.getUrl(bucket, key))
                .build();*/
		 Asset asset = new Asset();
		 
		 asset.setName(this.amazonS3Client.getObjectMetadata(bucket, key).getUserMetaDataOf("name"));
		 asset.setKey(key);
		 asset.setUrl(this.amazonS3Client.getUrl(bucket, key));
		 
		 return asset;
    }

}
