package techconnect.techconnectproject.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import techconnect.techconnectproject.models.Inquiry;
import techconnect.techconnectproject.models.InquiryRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class InquiryController {
    @Autowired
    private InquiryRepository inquiryRepo;

    @GetMapping("/display/emergency")
    public String getUrgentInq(Model model) {

        List<Inquiry> unresolvedInquiries = inquiryRepo.findByTypeAndResolved("emergency", false);
        List<Inquiry> resolvedInquiries = inquiryRepo.findByTypeAndResolved("emergency", true);

        model.addAttribute("unresolvedInquiries", unresolvedInquiries);
        model.addAttribute("resolvedInquiries", resolvedInquiries);

        return "inquiry/displayEmergencyInq";
    }

    @GetMapping("/display/general")
    public String getGeneralInq(Model model) {

        List<Inquiry> unresolvedInquiries = inquiryRepo.findByTypeAndResolved("general", false);
        List<Inquiry> resolvedInquiries = inquiryRepo.findByTypeAndResolved("general", true);

        model.addAttribute("unresolvedInquiries", unresolvedInquiries);
        model.addAttribute("resolvedInquiries", resolvedInquiries);

        return "inquiry/displayGeneralInq";
    }
    
    @GetMapping("/display/quick")
    public String getQuickInq(Model model) {

        List<Inquiry> unresolvedInquiries = inquiryRepo.findByTypeAndResolved("quick", false);
        List<Inquiry> resolvedInquiries = inquiryRepo.findByTypeAndResolved("quick", true);

        model.addAttribute("unresolvedInquiries", unresolvedInquiries);
        model.addAttribute("resolvedInquiries", resolvedInquiries);

        return "inquiry/displayQuickInq";
    }


    
    
}
