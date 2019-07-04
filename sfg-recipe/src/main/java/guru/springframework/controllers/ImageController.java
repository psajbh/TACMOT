package guru.springframework.controllers;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import guru.springframework.backbeans.RecipeBean;
import guru.springframework.services.ImageService;
import guru.springframework.services.RecipeService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ImageController {
	
	private final RecipeService recipeService;
	private final ImageService imageService;
	
	
	public ImageController(RecipeService recipeService, ImageService imageService) {
		this.recipeService = recipeService;
		this.imageService = imageService;
	}
	
	@GetMapping("recipe/{id}/image")
	public String showUploadForm(@PathVariable String id, Model model) {
		log.debug("showUploadForm: - id: " + id);
		model.addAttribute("recipe", recipeService.getRecipeById(Long.valueOf(id)));
		return "recipe/imageuploadform";
	}
	
    @PostMapping("recipe/{id}/image")
    public String handleImagePost(@PathVariable String id, @RequestParam("imagefile") MultipartFile file){
    	log.debug("handleImagePost: - id: " + id + "fileName: " + file.getName());
        imageService.saveImageFile(Long.valueOf(id), file);
        return "redirect:/recipe/" + id + "/show";
    }
    
    @GetMapping("recipe/{id}/recipeimage")
    public void renderImageFromDB(@PathVariable String id, HttpServletResponse response) throws IOException {
    	log.debug("renderImageFromDB: - id: " + id);
        RecipeBean recipeBean = recipeService.getRecipeById(Long.valueOf(id));

        if (recipeBean.getImage() != null) {
        	log.debug("renderImageFromDB: image persisted");
            byte[] byteArray = new byte[recipeBean.getImage().length];
            int i = 0;

            // need to turn Bytes into primative in order to display
            for (Byte wrappedByte : recipeBean.getImage()){
                byteArray[i++] = wrappedByte; //auto unboxing
            }

            response.setContentType("image/jpeg");
            InputStream is = new ByteArrayInputStream(byteArray);
            IOUtils.copy(is, response.getOutputStream());
        }
    }
    
	

}
