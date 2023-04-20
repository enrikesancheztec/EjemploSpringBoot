package mx.tec.test.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import mx.tec.test.enumeration.MetricType;
import mx.tec.test.vo.MetricVO;

@SpringBootTest
class MetricsServiceTest {
	@Autowired
	MetricsService metricsService;

	@Test
	void testGivenValidListWhenSortThenSortedList() throws Exception {
		List<MetricVO> unsortedMetrics = new ArrayList<>();
		unsortedMetrics.add(new MetricVO(0, 5, MetricType.RESPONSE_TYPE));
		unsortedMetrics.add(new MetricVO(0, 8, MetricType.RESPONSE_TYPE));
		unsortedMetrics.add(new MetricVO(0, 3, MetricType.RESPONSE_TYPE));

		List<MetricVO> expectedSortedMetrics = new ArrayList<>();
		expectedSortedMetrics.add(new MetricVO(0, 3, MetricType.RESPONSE_TYPE));
		expectedSortedMetrics.add(new MetricVO(0, 5, MetricType.RESPONSE_TYPE));
		expectedSortedMetrics.add(new MetricVO(0, 8, MetricType.RESPONSE_TYPE));
		
		List<MetricVO> actualSortedMetrics = metricsService.sort(unsortedMetrics);
		
		assertEquals(expectedSortedMetrics, actualSortedMetrics);
	}

	@Test
	void testGivenEmptyListWhenSortThenEmptyList() throws Exception {
		List<MetricVO> unsortedMetrics = new ArrayList<>();

		List<MetricVO> expectedSortedMetrics = new ArrayList<>();

		List<MetricVO> actualSortedMetrics = metricsService.sort(unsortedMetrics);
		
		assertEquals(expectedSortedMetrics, actualSortedMetrics);
	}
}
