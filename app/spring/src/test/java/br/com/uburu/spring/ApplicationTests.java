package br.com.uburu.spring;

import static org.assertj.core.api.Assertions.assertThat;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import br.com.uburu.spring.controller.FilterController;
import br.com.uburu.spring.controller.KeywordController;
import br.com.uburu.spring.controller.PathController;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest
@AutoConfigureMockMvc
class ApplicationTests {

	@Autowired
	private FilterController filterController;

	@Autowired
	private KeywordController keywordController;

	@Autowired
	private PathController pathController;

	@Autowired
	private MockMvc mock;
	private String date;

	@Test
	void contextLoads() {
		assertThat(filterController).isNotNull();
		assertThat(keywordController).isNotNull();
		assertThat(pathController).isNotNull();

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		date = formatter.format(new Date(System.currentTimeMillis()));
	}

	@Test
	void filterHistoryTest() throws Exception {
		JSONObject history = new JSONObject();
		history.put("extensionFilter", "txt, php, java");
		history.put("date", date);

		MvcResult mvcResult = mock.perform(
			post("/api/v1/filter")
			.content(history.toString())
			.contentType(MediaType.APPLICATION_JSON)
		).andExpect(status().isCreated()).andReturn();

		String result = mvcResult.getResponse().getContentAsString();
		JSONObject jsonObject = new JSONObject(result);

		mock.perform(
			get("/api/v1/filter")
		).andExpect(status().isOk());
		
		mock.perform(
			delete("/api/v1/filter/" + jsonObject.getLong("id"))
		).andExpect(status().isAccepted());
	}

	@Test
	void keywordHistoryTest() throws Exception {
		JSONObject history = new JSONObject();
		history.put("keyWords", "Uburu & \" utilizada \"");
		history.put("date", date);

		MvcResult mvcResult = mock.perform(
			post("/api/v1/keyword")
			.content(history.toString())
			.contentType(MediaType.APPLICATION_JSON)
		).andExpect(status().isCreated()).andReturn();

		String result = mvcResult.getResponse().getContentAsString();
		JSONObject jsonObject = new JSONObject(result);

		mock.perform(
			get("/api/v1/keyword")
		).andExpect(status().isOk());
		
		mock.perform(
			delete("/api/v1/keyword/" + jsonObject.getLong("id"))
		).andExpect(status().isAccepted());
	}

	@Test
	void pathHistoryTest() throws Exception {
		JSONObject history = new JSONObject();
		history.put("path", "src\\test\\java\\br\\com\\uburu\\spring");
		history.put("date", date);

		MvcResult mvcResult = mock.perform(
			post("/api/v1/path")
			.content(history.toString())
			.contentType(MediaType.APPLICATION_JSON)
		).andExpect(status().isCreated()).andReturn();

		String result = mvcResult.getResponse().getContentAsString();
		JSONObject jsonObject = new JSONObject(result);

		mock.perform(
			get("/api/v1/path")
		).andExpect(status().isOk());
		
		mock.perform(
			delete("/api/v1/path/" + jsonObject.getLong("id"))
		).andExpect(status().isAccepted());
	}

}
