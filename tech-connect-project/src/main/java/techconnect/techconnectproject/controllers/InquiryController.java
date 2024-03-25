package techconnect.techconnectproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.servlet.http.HttpServletResponse;
import techconnect.techconnectproject.models.Inquiry;
import techconnect.techconnectproject.models.InquiryRepository;
import java.util.Map;


@Controller
public class InquiryController {
    @Autowired
    private InquiryRepository inqRepo;


    @PostMapping("/submit-inquiry")
    public String submitInquiry(@RequestParam Map<String, String> newInquiry, HttpServletResponse response) {
        System.out.println("Submit Inquiry");
        Integer newUserId = Integer.parseInt(newInquiry.get("userID"));
        String newType = newInquiry.get("type");
        String newDescription = newInquiry.get("description");
        Boolean newResolved = Boolean.parseBoolean(newInquiry.get("resolved"));
        inqRepo.save(new Inquiry(newUserId, newType, newDescription, newResolved));
        response.setStatus(201);
        return "users/userDashboard";
        
    }
    
}
