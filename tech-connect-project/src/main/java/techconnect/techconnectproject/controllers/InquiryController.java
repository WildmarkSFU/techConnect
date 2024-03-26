package techconnect.techconnectproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import techconnect.techconnectproject.models.Inquiry;
import techconnect.techconnectproject.models.InquiryRepository;
import techconnect.techconnectproject.models.User;


import java.util.Map;


@Controller
public class InquiryController {
    @Autowired
    private InquiryRepository inqRepo;


    @PostMapping("/form-submit")
    public String getId(@RequestParam Map<String, String> newInquiry, HttpSession session, Model model){
        System.out.println("GET request to /form endpoint is reached");
        User newUser = (User) session.getAttribute("session_user");
        
        String newUsername = newUser.getUsername();
        Integer id = newUser.getUid();
        String title = newInquiry.get("title");
        String newType = newInquiry.get("type");
        String newDescription = newInquiry.get("description");
        Boolean newResolved = false;
        Inquiry newInq = new Inquiry(id, title, newUsername, newType, newDescription, newResolved);
        inqRepo.save(newInq);
        System.out.println("ID = " + id);
        model.addAttribute(newInq);
        return "users/formSuccess";
    }
    
}
