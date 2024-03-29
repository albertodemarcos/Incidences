package es.myhome.portal.service.cloud;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import es.myhome.portal.domain.cloud.Asset;

public interface ICloudStorageS3 {

	InputStream getS3File(String bucketName, String fileName) throws IOException;
	
    String getS3FileContent(String bucketName, String fileName) throws IOException;

    List<Asset> getS3Files(String bucketName) throws IOException;

    byte[] downloadFile(String bucketName, String fileName) throws IOException;

    void moveObject(String bucketName, String fileKey, String destinationFileKey);

    void deleteObject (String bucketName, String fileKey);

    String uploadFile(String bucketName, String filePath, MultipartFile file);
    
    String uploadFileFromIncidence(String bucketName, String filePath, MultipartFile file);
}
