package com.familytoto.familytotoProject.board.service;

import java.io.File;

import org.springframework.stereotype.Service;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;

@Service
public class AWSService {
	private String BUCKET_NAME = "*";
	private String ACCESS_KEY = "*";
	private String SECRET_KEY = "*";
	private AmazonS3 amazonS3;

	public AWSService() {
		AWSCredentials awsCredentials = new BasicAWSCredentials(ACCESS_KEY, SECRET_KEY);
		amazonS3 = new AmazonS3Client(awsCredentials);
	}

	public int uploadFile(String sPath, File file) {
		if (amazonS3 != null) {
			try {
				PutObjectRequest putObjectRequest = new PutObjectRequest(BUCKET_NAME + "/" + sPath, file.getName(),
						file);
				putObjectRequest.setCannedAcl(CannedAccessControlList.PublicRead); // file permission
				amazonS3.putObject(putObjectRequest); // upload file
				return 0;
			} catch (AmazonServiceException ase) {
				ase.printStackTrace();
			}

			return -99;
		} else {
			return -99;
		}
	}
	
	public S3ObjectInputStream downloadFIle(String fullFilePath, String fileName) {
    	AWSCredentials credentials = new BasicAWSCredentials(ACCESS_KEY, SECRET_KEY);
		AmazonS3 s3Client = new AmazonS3Client(credentials);
		
		S3Object s3object = s3Client.getObject(BUCKET_NAME, fullFilePath);
        S3ObjectInputStream inputStream = s3object.getObjectContent();
        
        return inputStream;
	}
}