package techconnect.techconnectproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import techconnect.techconnectproject.models.Inquiry;
import techconnect.techconnectproject.models.InquiryRepository;
import techconnect.techconnectproject.models.User;

import java.util.List;
import java.util.Map;

@Controller
public class InquiryController {
    @Autowired

    private InquiryRepository inqRepo;

    @GetMapping("/display/emergency")
    public String getUrgentInq(Model model) {

        List<Inquiry> unresolvedInquiries = inqRepo.findByTypeAndResolved("emergency", false);
        List<Inquiry> resolvedInquiries = inqRepo.findByTypeAndResolved("emergency", true);

        model.addAttribute("unresolvedInquiries", unresolvedInquiries);
        model.addAttribute("resolvedInquiries", resolvedInquiries);

        return "inquiry/displayEmergencyInq";
    }

    @GetMapping("/display/general")
    public String getGeneralInq(Model model) {

        List<Inquiry> unresolvedInquiries = inqRepo.findByTypeAndResolved("general", false);
        List<Inquiry> resolvedInquiries = inqRepo.findByTypeAndResolved("general", true);

        model.addAttribute("unresolvedInquiries", unresolvedInquiries);
        model.addAttribute("resolvedInquiries", resolvedInquiries);

        return "inquiry/displayGeneralInq";
    }
    
    @GetMapping("/display/quick")
    public String getQuickInq(Model model) {

        List<Inquiry> unresolvedInquiries = inqRepo.findByTypeAndResolved("quick", false);
        List<Inquiry> resolvedInquiries = inqRepo.findByTypeAndResolved("quick", true);

        model.addAttribute("unresolvedInquiries", unresolvedInquiries);
        model.addAttribute("resolvedInquiries", resolvedInquiries);

        return "inquiry/displayQuickInq";
    }

   
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
        model.addAttribute("newInq", newInq);
        return "users/formSuccess";
    }

    @GetMapping("/message-history")
    public String messageHistory(Model model, HttpSession session){
        User newUser = (User) session.getAttribute("session_user");
        Integer uid = newUser.getUid();
        List<Inquiry> messages = inqRepo.findByUserIdAndResolved(uid, false);
        model.addAttribute("messages", messages);
        return "users/userMessageHistory";

    }
    
    @GetMapping("/inquiry-details/{inqNumber}")
    public String inquiryDetails(@PathVariable int inqNumber, HttpSession session, Model model) {
        Inquiry inquiry = inqRepo.findById(inqNumber).orElse(null);
        if (inquiry != null) {
            model.addAttribute("inquiry", inquiry);
            return "users/inquiryDetails";
        } else {
            return "redirect:/message-history";
        }
    }
}
