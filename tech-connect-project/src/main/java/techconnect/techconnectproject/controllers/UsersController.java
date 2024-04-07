package techconnect.techconnectproject.controllers;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.springframework.http.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.client.RestTemplate;


import techconnect.techconnectproject.models.User;
import techconnect.techconnectproject.models.UserRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class UsersController {
    @Autowired
    private UserRepository userRepo;
    private final RestTemplate restTemplate;

    public UsersController(UserRepository userRepo, RestTemplate restTemplate ) {
        this.restTemplate = restTemplate;
        this.userRepo = userRepo;
    }

    private static final String API_KEY = "wys_SzqodD4Y6OmimyXGiL5o7ZXHr3lZYx0NBVRb";
    private static final String WEAVY_SERVER = "https://fd97facfe4e14f09abbbcd0641057eb7.weavy.io";

    private String getWeavyTokenForUser(User user, HttpSession session) {
        // Prepare the request to obtain the token from Weavy's API
        String existingToken = (String) session.getAttribute("weavy_token");
        if (existingToken != null) {
            return existingToken;
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + API_KEY);
        String requestBody = "{\"uid\": \"" + user.getUsername() + "\", \"name\": \"" + user.getName() + "\"}";
        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);
    
        // Send the request to Weavy's API to obtain the token
        String URL = WEAVY_SERVER + "/api/users/" + user.getUsername() + "/tokens";

        ResponseEntity<String> responseEntity = restTemplate.postForEntity(
                URL,
                requestEntity,
                String.class);
    
        // Check if the request was successful and extract the token from the response
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            JSONObject jsonResponse = new JSONObject(responseEntity.getBody());
            String token = jsonResponse.getString("access_token");
            System.out.println("Token: " + token);
            return token;
        } else {
            return null;
        }
    }

    @GetMapping("/getWeavyToken")
    public ResponseEntity<String> getWeavyToken(HttpSession session) {
        String weavyToken = (String) session.getAttribute("weavy_token");
        if (weavyToken != null) {
            return ResponseEntity.ok(weavyToken);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

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
            String weavyToken = getWeavyTokenForUser(user, session);

            session.setAttribute("weavy_token", weavyToken);
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
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + API_KEY);
            headers.setContentType(MediaType.APPLICATION_JSON);

            String userDataJson = "{\"uid\": \"" + username + "\", \"name\": \"" + name + "\", \"directory\": \"acme\"}";

            HttpEntity<String> requestEntity = new HttpEntity<>(userDataJson, headers);

            ResponseEntity<String> responseEntity = restTemplate.postForEntity(
                    WEAVY_SERVER + "/api/users",
                    requestEntity,
                    String.class);

            if (responseEntity.getStatusCode().is2xxSuccessful()) {
                // User successfully created in Weavy
                return "redirect:/login";
            } else {
                // Handle error
                model.addAttribute("error", "Failed to register user in Weavy.");
                return "users/register";
            }
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
}