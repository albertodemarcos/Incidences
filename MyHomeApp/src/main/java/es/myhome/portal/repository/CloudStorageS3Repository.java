package es.myhome.portal.repository;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.amazonaws.services.s3.model.S3ObjectInputStream;

import es.myhome.portal.domain.cloud.Asset;

public interface CloudStorageS3Repository {

	 	List<Asset> listObjectsInBucket(String bucket);

	    S3ObjectInputStream getObject(String bucketName, String fileName) throws IOException;

	    byte[] downloadFile(String bucketName, String fileName) throws IOException;

	    void moveObject(String bucketName, String fileKey, String destinationFileKey);

	    void deleteObject(String bucketName, String fileKey);

	    String uploadFile(String bucketName, String fileName, File fileObj);
}
