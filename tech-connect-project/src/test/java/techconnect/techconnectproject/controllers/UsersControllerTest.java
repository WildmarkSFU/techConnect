package techconnect.techconnectproject.controllers;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import techconnect.techconnectproject.models.User;
import techconnect.techconnectproject.models.UserRepository;


import static org.mockito.Mockito.when;
import static org.hamcrest.Matchers.*;

@WebMvcTest(UsersController.class)
public class UsersControllerTest {
    @MockBean
    private UserRepository userRepository;

    @Autowired
    private MockMvc mockMvc;

    // @Test
    // void testGetAllUsers() throws Exception{

    //     User  u1 = new User();
    //     u1.setUid(404);
    //     u1.setName("billy");
    //     u1.setPassword("sus");
    //     u1.setEmail("example@sussy.com");
    //     u1.setUsername("billysussy");

    //     List<User> users = new ArrayList<User>();
    //     users.add(u1);

    //     when(userRepository.findAll()).thenReturn(users);

    //     mockMvc.perform(MockMvcRequestBuilders.get("/users/all"))
    //             .andExpect(MockMvcResultMatchers.status().isOk())
    //             .andExpect(MockMvcResultMatchers.view().name("users/showAll"))

    //             .andExpect(MockMvcResultMatchers.model().attribute("usrs", hasItem(
    //                     allOf(
    //                             hasProperty("uid", Matchers.is(404)),
    //                             hasProperty("name", Matchers.is("billy")),
    //                             hasProperty("password", Matchers.is("sus")),
    //                             hasProperty("email", Matchers.is("example@sussy.com")),
    //                             hasProperty("username", Matchers.is("billysussy"))
    //                     )
    //             )));
    // }

    @Test
    void testGetLogin() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/login"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("users/login"));
    }

    @Test
    public void testRootRedirectsToLogin() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/"))
            .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
            .andExpect(MockMvcResultMatchers.redirectedUrl("/login"));
    }

}
