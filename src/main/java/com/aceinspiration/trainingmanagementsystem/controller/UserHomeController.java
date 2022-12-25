package com.aceinspiration.trainingmanagementsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.aceinspiration.trainingmanagementsystem.model.ComingCourse;
import com.aceinspiration.trainingmanagementsystem.model.Course;
import com.aceinspiration.trainingmanagementsystem.model.Register;
import com.aceinspiration.trainingmanagementsystem.service.ComingCourseJpa;
import com.aceinspiration.trainingmanagementsystem.service.CourseJpa;
import com.aceinspiration.trainingmanagementsystem.service.GalleryJpa;
import com.aceinspiration.trainingmanagementsystem.service.RegisterJpa;
import com.aceinspiration.trainingmanagementsystem.service.ReviewJpa;
import com.aceinspiration.trainingmanagementsystem.service.TimetableJpa;

@Controller
public class UserHomeController {
	
	@Autowired
	ComingCourseJpa comingCourseJpa;
	
	
    @GetMapping("/")
    public String index(Model model) {
    	List<ComingCourse>list=comingCourseJpa.selectAll();
    	ComingCourse  c=new ComingCourse("img.jpg");
    	ComingCourse c1=c;
    	ComingCourse c2=c;
    	ComingCourse c3=c;
    	ComingCourse c4=c;
    	ComingCourse c5=c;
    	try {    		
    	 c1=list.get(0);
    	 c2=list.get(1);
    	 c3=list.get(2);
    	 c4=list.get(3);
    	 c5=list.get(4);
    	}catch (Exception e) {
			System.out.println("Don't have all image.");
		}
    	model.addAttribute("c1", c1);
    	model.addAttribute("c2", c2);
    	model.addAttribute("c3", c3);
    	model.addAttribute("c4", c4);
    	model.addAttribute("c5", c5);
        return "home/index";
    }

    @Autowired
    CourseJpa courseJpa;
    
    @GetMapping("/course")
    public String course(Model model) {
    	model.addAttribute("list", courseJpa.selectAll());
        return "home/course";
    }

    @Autowired
    TimetableJpa timetable;
    @GetMapping("/timetable")
    public String timetable(Model model) {
    	model.addAttribute("list",timetable.selectAll());
        return "home/timetable";
    }

   
    @GetMapping("/roadmap")
    public String roadmap() {
        return "home/roadmap";
    }
    
    @Autowired
	GalleryJpa galleryJpa;
    @GetMapping("/activity")
    public String gallery(Model model) {
    	model.addAttribute("list", galleryJpa.selectAll());
    	return "home/activity";
    }

    @GetMapping("/courseDetail/{id}")
    public String courseDetail(Model model,@PathVariable("id")Long id) {
    	Course course=courseJpa.selectOne(id);
    	String detail=course.getDetail();
    	String []details=detail.split(",");
    	model.addAttribute("desName", course.getDesName());
    	model.addAttribute("details", details);
    	model.addAttribute("img", course.getImg());
    	model.addAttribute("duration", course.getDuration());
    	
        return "home/detail";
    }

    @GetMapping("/aboutus")
    public String aboutus() {
        return "home/aboutus";
    }


    @GetMapping("/register")
    public String register(@ModelAttribute("register")Register register) {
        return "home/register";
    }
    @Autowired
    ReviewJpa reviewJpa;
    @GetMapping("/review")
    public String review(Model model) {
    	model.addAttribute("list", reviewJpa.selectAll());
        return "home/review";
    }
    
    
    @Autowired
    RegisterJpa reposity;
    
    @PostMapping("/userRegister")
    public String userRegister(@ModelAttribute("register")Register register) {
    	register.setStatus("Pending");
    	register.setKnownfrom("Websites");
    	reposity.save(register);
    	return "redirect:/register";
    }
}
