package com.revature.controllers;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.util.Optional;

import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.User;
import com.revature.repositories.UserRepository;

import junit.framework.Assert;

@SpringBootTest(classes = AuthController.class)
@WebAppConfiguration
public class AuthControllerTest {
	@Autowired
	private MockMvc mockMvc;
	protected ObjectMapper objectMapper;
	protected UserRepository userRepository;
	
	@Autowired
    WebApplicationContext webApplicationContext;

    protected void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
    protected String mapToJson(Object obj) throws JsonProcessingException {
        return objectMapper.writeValueAsString(obj);
    }
    protected <T> T mapFromJson(String json, Class<T> clazz)
        throws JsonParseException, JsonMappingException, IOException {
      
        return objectMapper.readValue(json, clazz);
    }
    
    User user = new User(1,"joewham14@gmail.com","password","Joey","Wham","What is your favorite color?","red");
    
    @Test
    void shouldCreateUser() throws Exception {
    	String url = "/auth/register";
    	User use = new User();
    	use.setId(1);
    	use.setEmail("joewham14@gmail.com");
    	use.setPassword("password");
    	use.setFirstName("Joey");
    	use.setLastName("Wham");
    	use.setQuestion("What is your favorite color?");
    	use.setAnswer("Red");
    	
    	MvcResult mvcResult = mockMvc.perform( MockMvcRequestBuilders.post(url)
    			.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andReturn();
    	
    	int status = mvcResult.getResponse().getStatus();
    	assertEquals(201, status);
    	assertEquals(mvcResult.getAsyncResult(),use);
    }
    
    
    @Test
    void shouldGetUserByEmail() throws Exception {
    	String url = "/auth/joewham14@gmail.com";
    	
    	MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(url).accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
    	
    	int status = mvcResult.getResponse().getStatus();
    	assertEquals(200, status);
    	assertEquals(mvcResult.getAsyncResult(),user);
    	
    }
    
    
    @Test
    void shouldUpdatePassword() throws Exception {
    	String url = "/auth/resetPassword";
    	user.setPassword("newPassword");
    	String inputJson = new ObjectMapper().writeValueAsString(user.getEmail());
    	
    	MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.patch(url)
    			.contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
    	
    	int status = mvcResult.getResponse().getStatus();
    	assertEquals(200, status);
    	assertEquals(user.getPassword(),"newPassword");
    }
    
    
    @Test
    void loginWorks() throws Exception {
    	String url = "/auth/login";
    	
    	String inputJson = new ObjectMapper().writeValueAsString(user.getEmail());
    	String inputJson2 = new ObjectMapper().writeValueAsString(user.getPassword());
    	
    	MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(url)
    			.contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson).content(inputJson2)).andReturn();
    	
    	int status= mvcResult.getResponse().getStatus();
    	assertEquals(200, status);
    }
  
    
    
    @Test
    void logoutWorks() throws Exception {
    	String url = "/auth/logout";
    	MvcResult mvcResult = (MvcResult) mockMvc.perform((RequestBuilder) ((ResultActions) MockMvcRequestBuilders.post(url)).andReturn());
    	int status = mvcResult.getResponse().getStatus();
    	assertEquals(200, status);
    }

    

    @Test
    void shouldRegisterUser() throws Exception {
    	
    	mockMvc.perform(post("/auth/register").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isCreated())
                .andDo(print());
        }
            
    @Test
    void shouldResetPassword() throws Exception {
    	String email = "joewham14@gmail.com";
    	
    	userRepository.save(user);
    	String password = "newPassword";
    	user.setPassword(password);
    	userRepository.save(user);
    	Mockito.when(userRepository.findByEmail("joewham14@gmail.com")).thenReturn(Optional.of(user));
    	mockMvc.perform(get("/auth/{email}", email)).andExpect(status().isOk())
    	.andExpect(jsonPath("$.password").value(password));

    	
    }
}

