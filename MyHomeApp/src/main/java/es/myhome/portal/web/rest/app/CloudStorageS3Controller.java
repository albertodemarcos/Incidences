package es.myhome.portal.web.rest.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import es.myhome.portal.domain.cloud.Asset;
import es.myhome.portal.service.cloud.CloudStorageS3Service;

@RestController
@RequestMapping(value = "/cloudStorage")
public class CloudStorageS3Controller {
	
	private final CloudStorageS3Service cloudStorageS3Service;

    public CloudStorageS3Controller(CloudStorageS3Service cloudStorageS3Service) {
        this.cloudStorageS3Service = cloudStorageS3Service;
    }

    @GetMapping("/getS3FileContent")
    public ResponseEntity<String> getS3FileContent(@RequestParam(value = "bucketName") String bucketName, @RequestParam(value = "fileName") String fileName) throws IOException {
        return new ResponseEntity<>(cloudStorageS3Service.getS3FileContent(bucketName, fileName), HttpStatus.OK);
    }

    @GetMapping("/listS3Files")
    public ResponseEntity<List<Asset>> getS3Files(@RequestParam(value = "bucketName") String bucketName) throws IOException {
        List<Asset> list= new ArrayList<>();
        HttpStatus status=  HttpStatus.OK;
        try {
            list =  cloudStorageS3Service.getS3Files(bucketName);
        } catch (Exception e) {
            status =  HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(list, status);
    }

    @GetMapping("/downloadS3File")
    public ResponseEntity<ByteArrayResource> downloadS3File(@RequestParam(value = "bucketName") String bucketName, @RequestParam(value = "filePath") String filePath, @RequestParam(value = "fileName") String fileName)
            throws IOException {
        byte[] data = cloudStorageS3Service.downloadFile(bucketName, fileName);
        ByteArrayResource resource = new ByteArrayResource(data);
        return ResponseEntity
                .ok()
                .contentLength(data.length)
                .header("Content-type", "application/octet-stream")
                .header("Content-disposition", "attachment; filename=\"" + fileName + "\"")
                .body(resource);
    }

    @DeleteMapping("/deleteObject")
    public ResponseEntity<String> deleteFile(@RequestParam(value = "bucketName") String bucketName, @RequestParam(value = "fileName") String fileName) {
    	cloudStorageS3Service.deleteObject(bucketName, fileName);
        return new ResponseEntity<>("File deleted", HttpStatus.OK);
    }

    @GetMapping("/moveFile")
    public ResponseEntity<String> moveFile(@RequestParam(value = "bucketName") String bucketName,
                                           @RequestParam(value = "fileName") String fileKey,
                                           @RequestParam(value = "fileNameDest") String fileKeyDest) {
    	cloudStorageS3Service.moveObject(bucketName, fileKey, fileKeyDest);
        return new ResponseEntity<>("File moved", HttpStatus.OK);
    }


    @PostMapping("/uploadFile")
    public ResponseEntity<String> uploadFile(@RequestParam(value = "bucketName") String bucketName, @RequestParam(value = "filePath") String filePath, @RequestParam(value = "file") MultipartFile file) {
        return new ResponseEntity<>(cloudStorageS3Service.uploadFile(bucketName, filePath, file), HttpStatus.OK);
    }

}
