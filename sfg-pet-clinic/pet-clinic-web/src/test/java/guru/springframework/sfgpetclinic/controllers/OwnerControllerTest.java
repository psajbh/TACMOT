package guru.springframework.sfgpetclinic.controllers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import static org.hamcrest.Matchers.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.services.OwnerService;

@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {
	@Mock
	private OwnerService ownerService;
	
	@InjectMocks
	OwnerController ownerController;
	
	Set<Owner> owners = new HashSet<>();
	MockMvc mockMvc;

	@BeforeEach
	void setUp() throws Exception {
		owners.add(Owner.builder().id(1L).build());
		owners.add(Owner.builder().id(2L).build());
		mockMvc = MockMvcBuilders.standaloneSetup(ownerController).build();
	}


	@Disabled  // Junit 4 @Ignore
	void testListOwners() throws Exception{
		when(ownerService.findAll()).thenReturn(owners);
		mockMvc.perform(get("/owners")).andExpect(status().isOk())
		.andExpect(view().name("/owners/index"))
		.andExpect(model().attribute("owners", hasSize(2)));
	}
	

	@Disabled  // Junit 4 @Ignore
	void testFindOwners() throws Exception{
		mockMvc.perform(get("/owners/find")).andExpect(status().isOk())
		.andExpect(view().name("notimplemented"));
		verifyZeroInteractions(ownerService);
	}
	
	@Test
	void displayOwner() throws Exception{
		
		Owner owner = Owner.builder().id(1L).build();
		
		when(ownerService.findById(owner.getId())).thenReturn(owner);
	
		mockMvc.perform(get("/owners/1"))
			.andExpect(status().isOk())
			.andExpect(view().name("owners/ownerDetails"))
			.andExpect(model().attribute("owner",hasProperty("id", is(1L))));
	
	}
	
	

}