package techconnect.techconnectproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;
import org.springframework.web.client.HttpClientErrorException;

import jakarta.servlet.http.HttpSession;
import techconnect.techconnectproject.models.Inquiry;
import techconnect.techconnectproject.models.InquiryRepository;
import techconnect.techconnectproject.models.User;
import techconnect.techconnectproject.models.UserRepository;

import java.util.List;
import java.util.Map;
import java.util.Random;

@Controller
public class InquiryController {
    @Autowired

    private final InquiryRepository inqRepo;
    private final UserRepository userRepo;
    private final RestTemplate restTemplate;

    public InquiryController(InquiryRepository inqRepo, RestTemplate restTemplate, UserRepository userRepo) {
        this.restTemplate = restTemplate;
        this.inqRepo = inqRepo;
        this.userRepo = userRepo;
    }

    private static final String API_KEY = "wys_Jf13Lg6Tib55iNAcvI3m0U49VOnPdL1O7EBc";
    private static final String WEAVY_SERVER = "https://778daca51ec145f8808581cc3828afb5.weavy.io";

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

    private String generateRandomWord(int length){
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder randomString = new StringBuilder();
        Random rnd = new Random();
        while (randomString.length() < length) {
            int index = (int) (rnd.nextFloat() * characters.length());
            randomString.append(characters.charAt(index));
        }
        return randomString.toString();
    }

    @PostMapping("/form-submit")
    public String getId(@RequestParam Map<String, String> newInquiry, HttpSession session, Model model){
        System.out.println("GET request to /form endpoint is reached");
        User newUser = (User) session.getAttribute("session_user");
        String newDirectory = generateRandomWord(4);
        String newChannel = generateRandomWord(4);
        
        String newUsername = newUser.getUsername();
        Integer id = newUser.getUid();
        String title = newInquiry.get("title");
        String newType = newInquiry.get("type");
        String newDescription = newInquiry.get("description");
        Boolean newResolved = false;
        Inquiry newInq = new Inquiry(id, title, newUsername, newType, newDescription, newResolved, newDirectory, newChannel);
        inqRepo.save(newInq);
        System.out.println("ID = " + id);
        model.addAttribute("newInq", newInq);
        model.addAttribute("user", newUser);
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

    private void updateWeavyAdminDirectory(String username, HttpSession session, Model model, String inqDirectory){

        String userDataJson = "{\"directory\": \"" + inqDirectory + "\"}";

        // Set up the HTTP headers
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + API_KEY);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> requestEntity = new HttpEntity<>(userDataJson, headers);

        try {
            restTemplate.put(
                    WEAVY_SERVER + "/api/users/admin",
                    requestEntity
            );
            System.out.println("User's directory updated successfully.");
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode().is2xxSuccessful()) {
                System.out.println("User's directory updated successfully.");
            } else {
                System.out.println("Failed to update user's directory in Weavy. Status code: " + e.getStatusCode());
            }
        } catch (Exception e) {
            System.out.println("Failed to update user's directory in Weavy. Error: " + e.getMessage());
        }
    }
    private void updateUserDirectory(String username, HttpSession session, Model model, String inqDirectory){

        String userDataJson = "{\"directory\": \"" + inqDirectory + "\"}";

        // Set up the HTTP headers
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + API_KEY);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> requestEntity = new HttpEntity<>(userDataJson, headers);

        try {
            restTemplate.put(
                    WEAVY_SERVER + "/api/users/" + username,
                    requestEntity
            );
            System.out.println(username + "directory updated successfully to" + inqDirectory);
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode().is2xxSuccessful()) {
                System.out.println(username + ": directory updated to " + inqDirectory);
            } else {
                System.out.println("Failed to update user's directory in Weavy. Status code: " + e.getStatusCode());
            }
        } catch (Exception e) {
            System.out.println("Failed to update user's directory in Weavy. Error: " + e.getMessage());
        }
    }
    
    @GetMapping("/inquiry-details/{inqNumber}")
    public String inquiryDetails(@PathVariable int inqNumber, HttpSession session, Model model) {
        Inquiry inquiry = inqRepo.findById(inqNumber).orElse(null);
 
        User newUser = (User) session.getAttribute("session_user");
        if (inquiry != null) {
            String channel = inquiry.getChannel();
            model.addAttribute("inquiry", inquiry);
            model.addAttribute("channel", channel);

            if (newUser.getUsername().equals("admin")){
                updateWeavyAdminDirectory(inquiry.getUserName(), session, model, inquiry.getDirectory());
            }
            else if(newUser.getUsername().equals(inquiry.getUserName())){
                updateUserDirectory(inquiry.getUserName(), session, model, inquiry.getDirectory());
            }
            return "users/inquiryDetails";
        } else {
            return "redirect:/message-history";
        }
    }

    @GetMapping("/resolved/{inqNumber}")
    public String resolveInquiry(@PathVariable int inqNumber, HttpSession session) {
        Inquiry inquiry = inqRepo.findById(inqNumber).orElse(null);
        if (inquiry != null) {
            if(inquiry.isResolved())
            {
                inquiry.setResolved(false);

            }
            else
            {
                inquiry.setResolved(true);
            }

            inqRepo.save(inquiry);
        }
        if (inquiry.getType().equals("emergency")) {
            return "redirect:/display/emergency";
        } else if (inquiry.getType().equals("general")) {
            return "redirect:/display/general";
        } else {
            return "redirect:/display/quick";
        }
    }

    @GetMapping("/delete/{inqNumber}")
    public String deleteInquiry(@PathVariable int inqNumber, HttpSession session) {
        Inquiry inquiry = inqRepo.findById(inqNumber).orElse(null);
        if (inquiry != null) {
            inqRepo.delete(inquiry);
        }
        if (inquiry.getType().equals("emergency")) {
            return "redirect:/display/emergency";
        } else if (inquiry.getType().equals("general")) {
            return "redirect:/display/general";
        } else {
            return "redirect:/display/quick";
        }
    }
}
