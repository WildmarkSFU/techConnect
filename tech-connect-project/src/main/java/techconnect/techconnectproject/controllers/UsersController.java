package techconnect.techconnectproject.controllers;

import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import techconnect.techconnectproject.models.User;
import techconnect.techconnectproject.models.UserRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class UsersController {
    @Autowired
    private UserRepository userRepo;

    @Autowired
    private MailSender mailSender;

    @GetMapping("/")
    public RedirectView process(){
        return new RedirectView("/login");
    }

    @GetMapping("/newInq")
    public String redirect(){
        return "users/newInquiry";
    }


    @GetMapping("/login")
    public String getLogin(Model model, HttpServletRequest request, HttpSession session) {
        User user = (User) session.getAttribute("session_user");
        if(user == null) 
        {
            return "users/login";
        } 
        else 
        {
            model.addAttribute("user", user);

            // Redirect to the appropriate dashboard
            if (user.getUsername().equals("admin") && user.getPassword().equals("admin")) 
            {
                // Redirect to admin dashboard
                return "users/adminDashboard";
            }
            else 
            {
                // Redirect to user dashboard
                return "users/userDashboard";
            }
        }
    }

    @PostMapping("/login")
    public String postLogin(@RequestParam Map<String, String> formData, Model model, 
                            HttpServletRequest request, HttpSession session) {
        //processing login
        String username = formData.get("username");
        String pwd = formData.get("password");
        List<User> userlist = userRepo.findByUsernameAndPassword(username, pwd);
        if (userlist.isEmpty())
        {
            return "users/login";
        }
        else
        {
            //Successful login
            User user = userlist.get(0);
            session.setAttribute("session_user", user);
            model.addAttribute("user", user);
            if (user.getUsername().equals("admin") && user.getPassword().equals("admin")) 
            {
                // Redirect to admin dashboard
                return "users/adminDashboard";
            }
            else 
            {
                // Redirect to user dashboard
                return "users/userDashboard";
            }
        }  
    }

    @GetMapping("/logout")
    public String destroySession(HttpServletResponse response, HttpServletRequest request) {
        HttpSession httpSession = request.getSession();
        httpSession.removeAttribute("session_user");
        httpSession.invalidate();
        // request.getSession().invalidate();
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "0");
        // HttpSession session = request.getSession();
        // if (session != null){
        //     session.invalidate();
        //     System.out.println("Session invalidated");
        // } else {
        //     System.out.println("Session is null");
        // }
        // if (session == null || session.getAttribute("userId") == null) {
        //     return "users/login";
        // }
        // redirectAttributes.addAttribute("logout", "1234");
        // model.addAttribute("login", null);
        // session.removeAttribute("");
        return "users/login";
    }  

    @GetMapping("/register")
    public String getRegister() {
        return "users/register";
    }

    @PostMapping("/register")
    public String postRegister(@RequestParam Map<String, String> formData, Model model, RedirectAttributes redirectAttributes) {
        String name = formData.get("name");
        String username = formData.get("username");
        String pwd = formData.get("password");
        String email = formData.get("email"); // TODO: email verification

        List<User> getUserbyUserName = userRepo.findByUsername(username);
        if (!getUserbyUserName.isEmpty()) {
            model.addAttribute("error", "Username already exists. Please choose a different username.");
            return "users/register";
        }
        else{
            // userRepo.save(new User(name, username, email, pwd));
            // return "redirect:/login";
            // Generate verification code
            int verificationCode = generateVerificationCode();
            User newUser = new User(name, username, email, pwd, verificationCode, false);
            newUser.setVerificationCode(verificationCode);
            userRepo.save(newUser);
            
            System.out.println("New user registered: " + newUser.toString());
            System.out.println("Verification code: " + newUser.getVerificationCode());
            
            System.out.println("Sending verification email...");
            // Send verification email
            sendVerificationEmail(newUser);

            // Redirect to registration success page
            redirectAttributes.addFlashAttribute("message", "Registration successful. Please check your email for verification.");
            return "users/verify";  
        }
    }

    @GetMapping("/verify")
    public String verifyCode(@RequestParam String code, Model model) {
        List<User> users = userRepo.findByVerificationCode(Integer.parseInt(code));
        if (!users.isEmpty()) {
            User user = users.get(0);
            user.setEnabled(true);
            user.setVerificationCode(-1); // Set to a value that indicates verification is complete
            userRepo.save(user);
            model.addAttribute("verificationSuccess", true);
            return "redirect:/login";
        } else {
            model.addAttribute("verificationSuccess", false);
            return "users/verify";
        }
    }

    private void sendVerificationEmail(User user) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(user.getEmail());
        mailMessage.setSubject("TechConnect Registration Verification");
        mailMessage.setText("Thank you for registering with TechConnect. Your verification code is: " + user.getVerificationCode());
    
        // Log email content for testing purposes
        System.out.println("Sending verification email to: " + user.getEmail());
        System.out.println("Email subject: " + mailMessage.getSubject());
        System.out.println("Email body: " + mailMessage.getText());
    
        try {
            mailSender.send(mailMessage);
            System.out.println("Verification email sent successfully.");
        } catch (MailException e) {
            e.printStackTrace();
            System.out.println("Failed to send verification email.");
        }
    }

    private int generateVerificationCode() {
        Random random = new Random();
        // Generate a random integer between 100000 and 999999 (inclusive)
        return random.nextInt(900000) + 100000;
    }

    @GetMapping("/faq")
    public String getFAQ() {
        return "users/faq";
    }
}