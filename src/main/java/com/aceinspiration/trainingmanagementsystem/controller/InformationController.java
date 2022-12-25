package com.aceinspiration.trainingmanagementsystem.controller;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.aceinspiration.trainingmanagementsystem.model.ComingCourse;
import com.aceinspiration.trainingmanagementsystem.model.Gallery;
import com.aceinspiration.trainingmanagementsystem.model.Review;
import com.aceinspiration.trainingmanagementsystem.model.Timetable;
import com.aceinspiration.trainingmanagementsystem.service.ComingCourseJpa;
import com.aceinspiration.trainingmanagementsystem.service.GalleryJpa;
import com.aceinspiration.trainingmanagementsystem.service.ReviewJpa;
import com.aceinspiration.trainingmanagementsystem.service.TimetableJpa;

@Controller
public class InformationController {
	
	@Autowired
	GalleryJpa galleryJpa;
	
	@PostMapping("/admin/addGallery")
	public String addGallery(@RequestParam("f[]")MultipartFile[] multipartFile) {
		
//Get file names
		List<String> fileName=new ArrayList<String>();
		for(MultipartFile file:multipartFile) {
			fileName.add(file.getOriginalFilename());
		}
		
//Get img name for database		
		List<Gallery> gallery=new ArrayList<Gallery>();
		for(String file:fileName) {
			gallery.add(new Gallery(file));
		}

//Create path or dir		
		String dir="./images/Information/Gallery/";
    	Path path=Paths.get(dir);
    	if(!Files.exists(path)) {
    		try {
				Files.createDirectories(path);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	
		
//Save images
    	for(int i=0;i<multipartFile.length;i++) {
    		try {
				InputStream inputStream=multipartFile[i].getInputStream();
				Path filePath=path.resolve(fileName.get(i));
				Files.copy(inputStream,filePath, StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
 
    	
 //save img name to datebase
		for(int i=0;i<gallery.size();i++) {
			galleryJpa.insert(gallery.get(i));
		}
		
		return "redirect:/admin/gallery";
	}
   
	@GetMapping("/admin/deleteGallery/{id}")
	public String deleteGallery(@PathVariable("id")Long id) {
		Gallery gallery=galleryJpa.selectOne(id);
		
		String dir="."+gallery.getImg();
    	Path path=Paths.get(dir);
    	
    	try {
			Files.delete(path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	galleryJpa.delete(id);
		return "redirect:/admin/gallery";
	}
	
    @GetMapping("/admin/galleryEditView/{id}")
    public String galleryEditView(@PathVariable("id")Long id,Model model) {
    	model.addAttribute("gallery",galleryJpa.selectOne(id));
        return "/admin/Information/galleryEditView";
    }
    
    @PostMapping("/admin/updateGallery/{id}")
    public String updateGallery(@PathVariable("id")Long id,@RequestParam("f")MultipartFile multipartFile) {
    	Gallery gallery=galleryJpa.selectOne(id);
 
 //Get new File Name
    	String fileName=multipartFile.getOriginalFilename();
    	
    	
//Create path or dir for update
    			String dir="./images/Information/Gallery/";
    	    	Path path=Paths.get(dir);
    	    	if(!Files.exists(path)) {
    	    		try {
    					Files.createDirectories(path);
    				} catch (IOException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				}
    	    	}
    	    	Path filePath=path.resolve(fileName);
    	    	
//Create pate or dir for delete
    	    	String delDir="."+gallery.getImg();
    	    	Path delPath=Paths.get(delDir);
    	
 
//Save and deleve img
    	    	try {
    	    		Files.delete(delPath);
					InputStream inputStream=multipartFile.getInputStream();
					Files.copy(inputStream,filePath, StandardCopyOption.REPLACE_EXISTING);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    	    	
    	    	
    	    	
    	gallery.setImg(fileName);
    	galleryJpa.update(gallery);
    	return "redirect:/admin/gallery";
    }
    
    

    @Autowired
    ComingCourseJpa comingCourseJpa;
    
    
    @PostMapping("/admin/addComingCourse")
    public String addCource(@ModelAttribute("comingCourse")@Valid ComingCourse course,BindingResult bindingResult, @RequestParam("f")MultipartFile multiPartfile,Model model) {
    	
    	if(bindingResult.hasErrors()) {
    		model.addAttribute("comingCourse", course);
    		model.addAttribute("list", comingCourseJpa.selectAll());
    		return "admin/Information/comingCourse";
    	}
    	
//Get file name
    	String fileName=multiPartfile.getOriginalFilename();
    	
    	
//Create path or dir
    	String dir="./images/Information/CommingCourse/";
    	Path path=Paths.get(dir);
    	if(!Files.exists(path)) {
    		try {
				Files.createDirectories(path);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	Path filePath=path.resolve(fileName);
    	
    	
 //Save Image 
    	try {
    		InputStream inputStream=multiPartfile.getInputStream();
	    	Files.copy(inputStream,filePath, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	course.setImg(fileName);
    	comingCourseJpa.insert(course);
    	return "redirect:/admin/comingCourse";
    }
    
    @GetMapping("/admin/deleteComingCourse/{id}")
    public String deleteComingCourse(@PathVariable("id")Long id) {
    	ComingCourse course=comingCourseJpa.selectOne(id);
    	
    
    	String dir="."+course.getImg();
    	Path path=Paths.get(dir);
    	try {
			Files.deleteIfExists(path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	comingCourseJpa.delete(id);
    	return "redirect:/admin/comingCourse";
    }
    
    @GetMapping("/admin/comingCourseEditView/{id}")
    public String comingCourseEditView(@PathVariable("id")Long id,Model model) {
    	model.addAttribute("comingCourse", comingCourseJpa.selectOne(id));
        return "admin/Information/comingCourseEditView";
    }
    
    @PostMapping("/admin/updateComingCourse/{id}")
    public String updateComingCourse(@ModelAttribute("comingCourse")@Valid ComingCourse course,BindingResult bindingResult, @PathVariable("id")Long id,
    		@RequestParam("f")MultipartFile multipartFile,Model model) {
    	if(bindingResult.hasErrors()) {
    		model.addAttribute("course", course);
    		return "admin/Information/comingCourseEditView";
    	}
    	
    	ComingCourse updateCourse=comingCourseJpa.selectOne(id);

    	

//Create path or dir to delete
    	    	String deldir="."+updateCourse.getImg();
    	    	Path delpath=Paths.get(deldir);
    	    	
//Delete Image
    	    	try {
    	    		Files.deleteIfExists(delpath);
    			} catch (IOException e) {
    				e.printStackTrace();
    			}    	
    	    	
    	
//Get updated file name
    	String fileName=multipartFile.getOriginalFilename();	
 //Create path or dir to update
    	String dir="./images/Information/CommingCourse/";
    	Path path=Paths.get(dir);
    	if(!Files.exists(path)) {
    		try {
				Files.createDirectories(path);
			} catch (IOException e) {
				e.printStackTrace();
			}
    	}
    	Path filePath=path.resolve(fileName);   	
//Save Image
    	try {
    		InputStream inputStream=multipartFile.getInputStream();
	    	Files.copy(inputStream,filePath, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			e.printStackTrace();
		}    	
    	updateCourse.setImg(fileName);
    	updateCourse.setName(course.getName());
    	comingCourseJpa.update(updateCourse);
    	return "redirect:/admin/comingCourse";
    }
    
    
    
    @Autowired
    TimetableJpa timetable;
    
    @PostMapping("/admin/addTimetable")
    public String addTimetable(@ModelAttribute("timeTable") @Valid Timetable timeTable,BindingResult bindingResult,Model model) {
    	
    	if(bindingResult.hasErrors()) {
    		
    		model.addAttribute("timeTable", timeTable);
    		model.addAttribute("list", timetable.selectAll());
    		return "admin/Information/timetable";
    	}
    	timetable.insert(timeTable);
    	return "redirect:/admin/timeTable";
    }
    
    
    
    @PostMapping("/admin/updateTimetable/{id}")
    public String updateTimeTable(@ModelAttribute("timetable")@Valid Timetable timeTable,BindingResult bindingResult,@PathVariable("id")Long id,Model model) {
    	
    	if(bindingResult.hasErrors()) {
    		model.addAttribute("timetable", timeTable);
    		return "/admin/Information/timetableUpdate";
    	}
    	
    	Timetable updateTimetable=timetable.selectOne(id);
    	updateTimetable.setName(timeTable.getName());
    	updateTimetable.setDuration(timeTable.getDuration());
    	updateTimetable.setStartDate(timeTable.getStartDate());
    	updateTimetable.setEndDate(timeTable.getEndDate());
    	
    	
    	timetable.update(updateTimetable);
    	return "redirect:/admin/timeTable";
    }
    @GetMapping("/admin/updateTimetable/{id}")
    public String updateTimetable(@PathVariable("id")Long id,Model model) {
    	model.addAttribute("timetable", timetable.selectOne(id));
    	return "/admin/Information/timetableUpdate";
    }
    
    @GetMapping("/admin/deleteTimetable/{id}")
    public String deleteTimetable(@PathVariable("id")Long id) {
    	timetable.delete(id);
    	return "redirect:/admin/timeTable";
    }
    

    
    @Autowired
    ReviewJpa reviewJpa;
    
    @GetMapping("/admin/review")
    
    public String review(@ModelAttribute("review")Review review,Model model) {
    	 model.addAttribute("list", reviewJpa.selectAll());
        return "admin/Information/review";
    }
    
    
    @PostMapping("/admin/addReview")
    public String addReview(@ModelAttribute("review")@Valid Review review,BindingResult bindingResult, @RequestParam("f")MultipartFile multiPartfile,Model model) {
    	
    	if(bindingResult.hasErrors()) {
    		model.addAttribute("Review", review);
    		model.addAttribute("list", reviewJpa.selectAll());
    		return "admin/Information/review";
    	}
    	
//Get file name
    	String fileName=multiPartfile.getOriginalFilename();
    	
    	
//Create path or dir
    	String dir="./images/Information/Review/";
    	Path path=Paths.get(dir);
    	if(!Files.exists(path)) {
    		try {
				Files.createDirectories(path);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	Path filePath=path.resolve(fileName);
    	
    	
 //Save Image 
    	try {
    		InputStream inputStream=multiPartfile.getInputStream();
	    	Files.copy(inputStream,filePath, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	review.setImg(fileName);
    	reviewJpa.insert(review);
    	return "redirect:/admin/review";
    }
    

    
    
    @GetMapping("/admin/reviewEditView/{id}")
    public String reviewEditView(@PathVariable("id")Long id,Model model) {
    	model.addAttribute("review", reviewJpa.selectOne(id));
    	return "/admin/Information/reviewEditView";
    }
    
    @PostMapping("/admin/updateReview/{id}")
    public String updateReview(@ModelAttribute("review")@Valid Review review,BindingResult bindingResult, @PathVariable("id")Long id,
    		Model model) {
    	if(bindingResult.hasErrors()) {
    		model.addAttribute("review", review);
    		return "admin/Information/reviewEditView";
    	}
    	
    	Review updateReview=reviewJpa.selectOne(id);
    	
    	
    	updateReview.setName(review.getName());
    	updateReview.setDetail(review.getDetail());
    	
    	
    	reviewJpa.update(updateReview);
    	return "redirect:/admin/review";
    }
    
    @GetMapping("/admin/deleteReview/{id}")
    public String deleteReview(@PathVariable("id")Long id) {
    	Review review=reviewJpa.selectOne(id);
    	
    
    	String dir="."+review.getImg();
    	Path path=Paths.get(dir);
    	try {
			Files.deleteIfExists(path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	reviewJpa.delete(id);
    	return "redirect:/admin/review";
    }

}
