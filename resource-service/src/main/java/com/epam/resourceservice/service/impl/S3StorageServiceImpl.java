package com.epam.resourceservice.service.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.epam.resourceservice.config.AwsS3Config;
import com.epam.resourceservice.domain.entity.SongContent;
import com.epam.resourceservice.domain.entity.SongResource;
import com.epam.resourceservice.service.S3StorageService;
import java.time.LocalDateTime;
import java.util.Objects;
import lombok.SneakyThrows;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class S3StorageServiceImpl implements S3StorageService {

  private static final String FILE_EXTENSION = "fileExtension";

  private final AwsS3Config awsS3Config;

  private final AmazonS3 amazonS3;

  @Autowired
  public S3StorageServiceImpl(AwsS3Config awsS3Config, AmazonS3 amazonS3) {
    this.awsS3Config = awsS3Config;
    this.amazonS3 = amazonS3;
  }

  @Override
  @SneakyThrows
  public SongResource upload(MultipartFile file) {
    String fileName = file.getOriginalFilename();
    String fileUrl =
        awsS3Config.getAwsS3EndpointUrl() + "/" + awsS3Config.getBucketName() + "/" + fileName;
    PutObjectResult putObjectResult =
        amazonS3.putObject(awsS3Config.getBucketName(), fileName, file.getInputStream(),
            this.extractObjectMetadata(file));
    return SongResource.builder()
        .name(fileName)
        .locationPath(fileUrl)
        .isUploadSuccessFul(Objects.nonNull(putObjectResult))
        .uploadDate(LocalDateTime.now())
        .build();
  }

  @Override
  @SneakyThrows
  public SongContent download(String songName) {
    final S3ObjectInputStream objectContent = amazonS3.getObject(awsS3Config.getBucketName(),
        songName).getObjectContent();
    return new SongContent(objectContent.readAllBytes());
  }

  @Override
  public void delete(String songName) {
    amazonS3.deleteObject(awsS3Config.getBucketName(), songName);
  }

  private ObjectMetadata extractObjectMetadata(MultipartFile song) {
    ObjectMetadata objectMetadata = new ObjectMetadata();
    objectMetadata.setContentLength(song.getSize());
    objectMetadata.setContentType(song.getContentType());
    objectMetadata.getUserMetadata()
        .put(FILE_EXTENSION, FilenameUtils.getExtension(song.getOriginalFilename()));
    return objectMetadata;
  }
}
