package mx.tec.test.controller;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.ArrayList;

import mx.tec.test.enumeration.MetricType;
import mx.tec.test.service.MetricsService;
import mx.tec.test.vo.MetricVO;

@WebMvcTest(MetricsController.class)
class MetricsControllerTest {
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	ObjectMapper mapper;

	@MockBean
	MetricsService metricsService;

	@Test
	void testGivenValidListWhenSortThenSortedList() throws JsonProcessingException, Exception {
		List<MetricVO> unsortedMetrics = new ArrayList<>();
		unsortedMetrics.add(new MetricVO(0, 5, MetricType.RESPONSE_TYPE));
		unsortedMetrics.add(new MetricVO(0, 8, MetricType.RESPONSE_TYPE));
		unsortedMetrics.add(new MetricVO(0, 3, MetricType.RESPONSE_TYPE));

		List<MetricVO> expectedSortedMetrics = new ArrayList<>();
		expectedSortedMetrics.add(new MetricVO(0, 3, MetricType.RESPONSE_TYPE));
		expectedSortedMetrics.add(new MetricVO(0, 5, MetricType.RESPONSE_TYPE));
		expectedSortedMetrics.add(new MetricVO(0, 8, MetricType.RESPONSE_TYPE));

		when(metricsService.sort(anyList())).thenReturn(expectedSortedMetrics);

		mockMvc.perform(MockMvcRequestBuilders.post("/metrics/sort")
				.content(new ObjectMapper().writeValueAsString(unsortedMetrics)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.content()
						.json(mapper.writeValueAsString(expectedSortedMetrics)));
	}

	@Test
	void testGivenEmptyListWhenSortThenEmptyList() throws JsonProcessingException, Exception {
		List<MetricVO> unsortedMetrics = new ArrayList<>();

		List<MetricVO> expectedSortedMetrics = new ArrayList<>();
		
		when(metricsService.sort(anyList())).thenReturn(expectedSortedMetrics);

		mockMvc.perform(MockMvcRequestBuilders.post("/metrics/sort")
				.content(new ObjectMapper().writeValueAsString(unsortedMetrics)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.content()
						.json(mapper.writeValueAsString(expectedSortedMetrics)));
	}

	@Test
	void testGivenEmptyBodyWhenSortThenBadRequest() throws JsonProcessingException, Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/metrics/sort").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
	}

	@Test
	void testGivenListWithInvalidTypeWhenSortThenBadRequest() throws JsonProcessingException, Exception {
		String invalidList = "[{'value': 5, 'type': 'RESPONSE_TYPE'}, {'value': 3, 'type': 'OTHER'}, {'value': 8, 'type': 'PERFORMANCE'}]";

		mockMvc.perform(MockMvcRequestBuilders.post("/metrics/sort").content(invalidList)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());
	}
	
	@Test
	void testGivenListWithNullValueWhenSortThenBadRequest() throws JsonProcessingException, Exception {
		List<MetricVO> unsortedMetrics = new ArrayList<>();
		unsortedMetrics.add(new MetricVO(0, 5, MetricType.RESPONSE_TYPE));
		unsortedMetrics.add(new MetricVO(0, 8, null));
		unsortedMetrics.add(new MetricVO(0, 3, MetricType.RESPONSE_TYPE));

		mockMvc.perform(MockMvcRequestBuilders.post("/metrics/sort")
				.content(new ObjectMapper().writeValueAsString(unsortedMetrics)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
	}
}
