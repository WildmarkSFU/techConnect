package techconnect.techconnectproject.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import techconnect.techconnectproject.models.GoogleMapsService;
import techconnect.techconnectproject.models.User;
import techconnect.techconnectproject.models.UserRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class UsersController {
    @Autowired
    private UserRepository userRepo;

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
    public String postRegister(@RequestParam Map<String, String> formData, Model model) {
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
            userRepo.save(new User(name, username, email, pwd));
            return "redirect:/login";
        }
    }

    @GetMapping("/faq")
    public String getFAQ() {
        return "users/faq";
    }

    @GetMapping("/account")
    public String getAccount(Model model, HttpSession session) {
        User user = (User) session.getAttribute("session_user");
        if(user == null) 
        {
            return "users/login";
        } 
        else 
        {
            model.addAttribute("user", user);
            return "users/account";
        }
    }

    @PostMapping("/users/update")
    public String updateAccount(@RequestParam Map<String, String> formData, Model model, HttpSession session) {
        User user = (User) session.getAttribute("session_user");
        if(user == null) 
        {
            return "users/login";
        } 
        else 
        {
            String name = formData.get("name");
            String email = formData.get("email");
            String pwd = formData.get("password");
            user.setName(name);
            user.setEmail(email);
            user.setPassword(pwd);
            userRepo.save(user);
            model.addAttribute("message", name + ", you account has been updated!");
            return "users/updateSuccess";
        }
    }

    @Autowired
    private GoogleMapsService googleMapsService;

    @GetMapping("/getDistance/{longitude}/{latitude}")
    public String getDistance(@PathVariable double longitude, @PathVariable double latitude, HttpSession session, Model model) {
        User newUser = (User) session.getAttribute("session_user");
        newUser.setLongitude(longitude);
        newUser.setLatitude(latitude);
        userRepo.save(newUser);
        model.addAttribute("user", newUser);
        double distance = googleMapsService.calculateDistance(latitude, longitude, 49.220333, -123.065917);
        double duration = googleMapsService.calculateDuration(latitude, longitude, 49.220333, -123.065917); // Declare the variable 'duration'
        model.addAttribute("distance", distance);
        model.addAttribute("duration", duration);
        return "users/distance";
    }

    
}