package br.com.uburu.spring;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import br.com.uburu.spring.controller.HistoryController;
import br.com.uburu.spring.controller.SearchController;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONObject;

@SpringBootTest
@AutoConfigureMockMvc
class ApplicationTests {

	@Autowired
	private HistoryController historyController;

	@Autowired
	private SearchController searchController;

	@Autowired
	private MockMvc mock;

	@Test
	void contextLoads() {}

	@Test
	void historyControllerTest() throws Exception {
		assertThat(historyController).isNotNull();

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String date = formatter.format(new Date(System.currentTimeMillis()));

		JSONObject history = new JSONObject();
		history.put("keyWords", "Uburu");
		history.put("repos", "src\\test\\java\\br\\com\\uburu\\spring\\searchTools\\test.txt");
		history.put("date", date);

		MvcResult mvcResult = mock.perform(
			post("/api/v1/history")
			.content(history.toString())
			.contentType(MediaType.APPLICATION_JSON)
		).andExpect(status().isCreated()).andReturn();

		String result = mvcResult.getResponse().getContentAsString();
		JSONObject jsonObject = new JSONObject(result);

		mock.perform(
			get("/api/v1/history")
		).andExpect(status().isOk());
		
		mock.perform(
			delete("/api/v1/history/" + jsonObject.getLong("id"))
		).andExpect(status().isAccepted());
	}

	@Test
	void searchControllerTest() throws Exception {
		assertThat(searchController).isNotNull();

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String date = formatter.format(new Date(System.currentTimeMillis()));

		JSONObject history = new JSONObject();
		history.put("keyWords", "Uburu");
		history.put("repos", "src\\test\\java\\br\\com\\uburu\\spring\\searchTools");
		history.put("date", date);

		MvcResult mvcResult = mock.perform(
			post("/api/v1/search")
			.content(history.toString())
			.contentType(MediaType.APPLICATION_JSON)
		).andExpect(status().isOk()).andReturn();

		String result = mvcResult.getResponse().getContentAsString();
		JSONArray jsonArray = new JSONArray(result);

		assertThat(result).isNotNull();
		assertTrue(jsonArray.length() > 0);
	}

}
