package br.com.uburu.spring;

import static org.assertj.core.api.Assertions.assertThat;

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

import java.util.Date;

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
	void contextLoads() throws Exception {}

	@Test
	void historyControllerTest() throws Exception {
		assertThat(historyController).isNotNull();

		JSONObject history = new JSONObject();
		history.put("keyWords", "Uburu");
		history.put("repos", "src\\test\\java\\br\\com\\uburu\\spring\\searchTools\\test.txt");
		history.put("date", new Date());

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
			delete("/api/v1/history/" + jsonObject.getString("id"))
		).andExpect(status().isAccepted());
	}

	@Test
	void searchControllerTest() throws Exception {
		assertThat(searchController).isNotNull();
	}

}
