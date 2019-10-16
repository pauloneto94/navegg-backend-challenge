package com.navegg.challenge.CRUDApi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.navegg.challenge.CRUDApi.model.Category;
import com.navegg.challenge.CRUDApi.model.Item;
import com.navegg.challenge.CRUDApi.model.Url;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashSet;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class CrudApiApplicationTests {

	private static final ObjectMapper om = new ObjectMapper();

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void findItemById() throws Exception {

		mockMvc.perform(get("/item/1"))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id", is("1")))
				.andExpect(jsonPath("$.name", is("Site 1")))
				.andExpect(jsonPath("$.urlList", hasSize(2)))
				.andExpect(jsonPath("$.categoryList", hasSize(2)))
				.andExpect(jsonPath("$.status", is("active")));

	}

	@Test
	public void getAll() throws Exception {

		mockMvc.perform(get("/item"))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(11)))
				.andExpect(jsonPath("$[0].id", is("1")))
				.andExpect(jsonPath("$[0].name", is("Site 1")))
				.andExpect(jsonPath("$[0].urlList", hasSize(2)))
				.andExpect(jsonPath("$[0].categoryList", hasSize(2)))
				.andExpect(jsonPath("$[0].status", is("active")))
				.andExpect(jsonPath("$[1].id", is("2")))
				.andExpect(jsonPath("$[1].name", is("Site 2")))
				.andExpect(jsonPath("$[1].urlList", hasSize(1)))
				.andExpect(jsonPath("$[1].categoryList", hasSize(3)))
				.andExpect(jsonPath("$[1].status", is("active")));

	}

	@Test
	public void itemNotFound() throws Exception {

		mockMvc.perform(get("item/99"))
				.andExpect(status().isNotFound());

	}

	@Test
	public void saveItem() throws Exception {

		Item item = new Item("Site 11", new HashSet<>(), new HashSet<>(), "active");

		Category category = new Category("news");
		Url url = new Url("game.com/nerd");

		category.setItem(item);
		url.setItem(item);

		item.getCategoryList().add(category);
		item.getUrlList().add(url);

		mockMvc.perform(post("/item")
				.content(om.writeValueAsString(item))
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id", is("11")))
				.andExpect(jsonPath("$.name", is("Site 11")))
				.andExpect(jsonPath("$.urlList", hasSize(1)))
				.andExpect(jsonPath("$.categoryList", hasSize(1)))
				.andExpect(jsonPath("$.status", is("active")));

	}

	@Test
	public void updateItem() throws Exception {

		Item item = new Item("Site 11", new HashSet<>(), new HashSet<>(), "active");

		Category category = new Category("news");
		Url url = new Url("game.com/nerd");

		category.setItem(item);
		url.setItem(item);

		item.getCategoryList().add(category);
		item.getUrlList().add(url);

		mockMvc.perform(patch("/item/2")
				.content(om.writeValueAsString(item))
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id", is("12")))
				.andExpect(jsonPath("$.name", is("Site 11")))
				.andExpect(jsonPath("$.urlList", hasSize(1)))
				.andExpect(jsonPath("$.categoryList", hasSize(1)))
				.andExpect(jsonPath("$.status", is("active")));

	}

	@Test
	public void deleteItem() throws Exception {

		mockMvc.perform(delete("/item/1"))
				.andExpect(status().isOk());

	}

}