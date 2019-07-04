package guru.springframework.services;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import guru.springframework.model.Recipe;
import guru.springframework.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ImageServiceImpl implements ImageService {
	
	private final RecipeRepository recipeRepository;
	
	@Autowired
	public ImageServiceImpl(RecipeRepository recipeRepository) {
		this.recipeRepository = recipeRepository; 
	}

	@Override
	@Transactional
	public void saveImageFile(Long recipeId, MultipartFile file) {
		log.debug("saveImageFile:  saving file: " + file.getName() + " to recipeId: " + recipeId);
		
		try {
            Recipe recipe = recipeRepository.findById(recipeId).get();
            
            Byte[] byteObjects = new Byte[file.getBytes().length];

            int i = 0;

            for (byte b : file.getBytes()){
                byteObjects[i++] = b;
            }

            recipe.setImage(byteObjects);
            recipeRepository.save(recipe);
            log.debug("saveImageFile:  sucessful save: " + file.getName() + " to recipeId: " + recipeId);
            
        } catch (IOException e) {
            //todo handle better
        	log.error("saveImageFile:  FAILURE: " + file.getName() + " to recipeId: " + recipeId, e);
            //log.error("Error occurred", e);

            e.printStackTrace();
        }		

	}

}
