package com.nice.ex.documents;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nice.ex.documents.entity.Document;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class DocumentsIntegrationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void testSuccessfulAddNewDocument() throws Exception {
		Document doc = new Document();
		doc.setName("Itamar");
		doc.setProfession("Developer");
		buildAddRequest(doc).andExpect(status().isOk());
	}


	@Test
	void testNameMoreThen20Characters() throws Exception {
		Document doc = new Document();
		doc.setName("ItamarItamarItamarIta");
		doc.setProfession("Developer");
		buildAddRequest(doc).andExpect(status().isBadRequest());
	}

	@Test
	void testBlankName() throws Exception {
		Document doc = new Document();
		doc.setProfession("Developer");
		buildAddRequest(doc).andExpect(status().isBadRequest());
	}

	@Test
	void testBlankProfession() throws Exception {
		Document doc = new Document();
		doc.setName("Itamar");
		buildAddRequest(doc).andExpect(status().isBadRequest());
	}

	@Test
	void tesProfessionMoreThen20Characters() throws Exception {
		Document doc = new Document();
		doc.setName("Itamar");
		doc.setProfession("DeveloperDeveloperDev");
		buildAddRequest(doc).andExpect(status().isBadRequest());
	}

	@Test
	void testAddTwoDocsWithTheSameName() throws Exception {

		Document doc = new Document();
		doc.setName("TestName");
		doc.setProfession("Developer");
		buildAddRequest(doc).andExpect(status().isOk());

		Document secondDoc = new Document();
		doc.setName("TestName");
		doc.setProfession("Developer");
		buildAddRequest(secondDoc).andExpect(status().isBadRequest());
	}



	public static String asJsonString(final Object obj) {
		try {
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonContent = mapper.writeValueAsString(obj);
			return jsonContent;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private ResultActions buildAddRequest(Document document) throws Exception {
		return this.mockMvc.perform(post("/api/add")
				.content(asJsonString(document))
				.contentType(MediaType.APPLICATION_JSON));
	}


}
