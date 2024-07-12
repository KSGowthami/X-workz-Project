package com.xworkz.myProject.repository;

import com.xworkz.myProject.dto.FileUploadDTO;

import java.util.Optional;

public interface FileUploadRepository {

    void saveImageDetails(FileUploadDTO fileUploadDTO);

    Optional<FileUploadDTO> findByUserId(int userId);


    void updateImageDetails(FileUploadDTO fileUploadDTO);

    void setAllImagesInactiveForUser(int userId);
}
