package pwr.cospacefinderbackend.service;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import com.azure.storage.blob.models.BlobHttpHeaders;
import com.azure.storage.blob.options.BlobParallelUploadOptions;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pwr.cospacefinderbackend.model.Image;
import pwr.cospacefinderbackend.repository.ImageRepository;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;

@Service
public class ImageService {
    @Autowired
    private ImageRepository imageRepository;

    @Value("${spring.cloud.azure.storage.blob.container-name}")
    private String containerName;

    @Value("${spring.cloud.azure.storage.connection-string}")
    private String connectionString;

    private BlobServiceClient blobServiceClient;

    @PostConstruct
    private void initBlobServiceClient() {
        blobServiceClient = new BlobServiceClientBuilder()
                .connectionString(connectionString)
                .buildClient();
    }

    public List<Image> getAllImages() {
        return imageRepository.findAll();
    }

    public Image addImage(MultipartFile image, String caption) throws IOException {
        String blobFileName = image.getOriginalFilename();
        BlobClient blobClient = blobServiceClient
                .getBlobContainerClient(containerName)
                .getBlobClient(blobFileName);

        String contentType = image.getContentType();
        BlobHttpHeaders headers = new BlobHttpHeaders()
                .setContentType(contentType);

        BlobParallelUploadOptions options = new BlobParallelUploadOptions(image.getInputStream())
                .setHeaders(headers);

        blobClient.uploadWithResponse(options, null, null);

        String imageUrl = blobClient.getBlobUrl();

        Image newImage = new Image();
        newImage.setCaption(caption);
        newImage.setUrl(imageUrl);

        return imageRepository.save(newImage);
    }
}
