package com.xworkz.myProject.Service;

import com.xworkz.myProject.dto.FileUploadDTO;
import com.xworkz.myProject.repository.FileUploadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FileUploadServiceImpl implements FileUploadService {

    @Autowired
    private FileUploadRepository fileUploadRepository;

    @Override
    public void saveImageDetails(FileUploadDTO fileUploadDTO) {
        fileUploadRepository.saveImageDetails(fileUploadDTO);
    }

    @Override
    public Optional<FileUploadDTO> getImageDetailsByUserId(int userId) {
        return fileUploadRepository.findByUserId(userId);
    }

    @Override
    public void updateImageDetails(FileUploadDTO fileUploadDTO) {
        fileUploadRepository.updateImageDetails(fileUploadDTO);
    }

    @Override
    public void setAllImagesInactiveForUser(int userId) {
        fileUploadRepository.setAllImagesInactiveForUser(userId);
    }
}
