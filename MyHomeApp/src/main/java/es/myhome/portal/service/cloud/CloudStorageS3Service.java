package es.myhome.portal.service.cloud;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.util.StringUtils;

import es.myhome.portal.domain.cloud.Asset;
import es.myhome.portal.repository.impl.CloudStorageS3RepositoryImpl;

@Service
public class CloudStorageS3Service implements ICloudStorageS3 {
	
	private static final Logger log = LoggerFactory.getLogger(CloudStorageS3Service.class);

    private final CloudStorageS3RepositoryImpl cloudStorageS3RepositoryImpl;

    public CloudStorageS3Service(CloudStorageS3RepositoryImpl cloudStorageS3RepositoryImpl) {

        this.cloudStorageS3RepositoryImpl = cloudStorageS3RepositoryImpl;
    }
    
	@Override
	public InputStream getS3File(String bucketName, String fileName) throws IOException {
		// TODO Auto-generated method stub
		
		//InputStream input = cloudStorageS3RepositoryImpl.getObject(bucketName, fileName);
		
		 return null;
	}

	@Override
	public String getS3FileContent(String bucketName, String fileName) throws IOException {
		// TODO Auto-generated method stub
		 return getAsString(cloudStorageS3RepositoryImpl.getObject(bucketName, fileName));
	}

	@Override
	public List<Asset> getS3Files(String bucketName) throws IOException {
		// TODO Auto-generated method stub
		 return cloudStorageS3RepositoryImpl.listObjectsInBucket(bucketName);
	}

	@Override
	public byte[] downloadFile(String bucketName, String fileName) throws IOException {
		// TODO Auto-generated method stub
		return cloudStorageS3RepositoryImpl.downloadFile(bucketName, fileName);
	}

	@Override
	public void moveObject(String bucketName, String fileKey, String destinationFileKey) {
		// TODO Auto-generated method stub
		cloudStorageS3RepositoryImpl.moveObject(bucketName, fileKey, destinationFileKey);
	}

	@Override
	public void deleteObject(String bucketName, String fileKey) {
		// TODO Auto-generated method stub
		cloudStorageS3RepositoryImpl.deleteObject(bucketName, fileKey);
	}

	@Override
	public String uploadFile(String bucketName, String filePath, MultipartFile file) {
		// TODO Auto-generated method stub
        File fileObj = convertMultiPartFileToFile(file);
        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        return cloudStorageS3RepositoryImpl.uploadFile(bucketName, filePath + fileName, fileObj);
	}
	
	@Override
	public String uploadFileFromIncidence(String bucketName, String filePath, MultipartFile file) {
		// TODO Auto-generated method stub
		File fileObj = convertMultiPartFileToFile(file);
        return cloudStorageS3RepositoryImpl.uploadFile(bucketName, filePath, fileObj);
	}
	
	private static String getAsString(InputStream is) throws IOException {
		if (is == null) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(is, StringUtils.UTF8));
			String line;
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
		} finally {
			is.close();
		}
		return sb.toString();
	}
 
	private File convertMultiPartFileToFile(MultipartFile file) {
		File convertedFile = new File(file.getOriginalFilename());
		try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
		    fos.write(file.getBytes());
		} catch (IOException e) {
		    log.error("Error converting multipartFile to file", e);
		}
		return convertedFile;
	}



}
