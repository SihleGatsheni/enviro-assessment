package com.eviro.assessment.grad001.sihlendlovu.enviroassessment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import com.eviro.assessment.grad001.sihlendlovu.enviroassessment.contracts.FileParser;
import com.eviro.assessment.grad001.sihlendlovu.enviroassessment.entities.AccountProfile;
import com.eviro.assessment.grad001.sihlendlovu.enviroassessment.services.AccountProfileService;

@Component
public class CsvParserImpl implements FileParser{

    private final AccountProfileService accountService;
    public CsvParserImpl(AccountProfileService accountService) {
        this.accountService = accountService;  
    }

    @Override
    public void parseCSV(File csvFile) {
        try {
            List<String> lines = Files.lines(csvFile.toPath())
                                        .skip(1) //skipping the file header
                                        .collect(Collectors.toList());

            for (String line : lines) {// reading the string data line by line and spliting it where there is a comma and assigning the data to a string array. 
                String[] fields = line.split(",");
                String name = fields[0];
                String surname = fields[1];
                String imageType = fields[2];
                String base64ImageData = fields[3];

                // Convert base64ImageData to file
                File imageFile = convertCSVDataToImage(base64ImageData, imageType);
                // Create HTTP image link
                URI httpImageLink = createImageLink(imageFile);
                AccountProfile profile = new AccountProfile();
                    profile.setAccountHolderName(name);
                    profile.setAccountHolderSurname(surname);
                    profile.setHttpImageLink(httpImageLink.toString());
                accountService.createProfile(profile); // saving to database.
            }
        } catch (IOException e) {
            
            e.printStackTrace();
        }
    }

    @Override
    public File convertCSVDataToImage(String base64ImageData) {
        try {
            byte[] imageData = Base64.getDecoder().decode(base64ImageData);
            File imageFile = File.createTempFile("image", ".jpg"); // Assuming the image type is always JPEG because this method does not take in imageType.
            FileOutputStream fileOutputStream = new FileOutputStream(imageFile);
            fileOutputStream.write(imageData);
            fileOutputStream.close();
            return imageFile;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public URI createImageLink(File fileImage) {
     return fileImage.toURI();
    }

    protected File convertCSVDataToImage(String base64ImageData, String imageType) {
      try {
            byte[] decodedBytes = Base64.getDecoder().decode(base64ImageData); // decoding the base64 string to byte array for further processing.
            String fileName = UUID.randomUUID().toString() + "." + imageType.substring(imageType.lastIndexOf("/") + 1); //constructing file name.
            String directoryPath = "wwwroot/images"; // Directory path
            var outputPath = Paths.get(directoryPath, fileName); // Create a Path with the directory and file name
            File file = outputPath.toFile();
            FileOutputStream outputStream = new FileOutputStream(file);
            outputStream.write(decodedBytes);
            outputStream.close();
            return file;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
