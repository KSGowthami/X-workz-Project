package com.xworkz.myProject.Service;

import com.xworkz.myProject.dto.FileUploadDTO;

import java.util.Optional;

public interface FileUploadService {

    void saveImageDetails(FileUploadDTO fileUploadDTO);

    Optional<FileUploadDTO> getImageDetailsByUserId(int userId);

    void updateImageDetails(FileUploadDTO fileUploadDTO);

    void setAllImagesInactiveForUser(int userId);
}
