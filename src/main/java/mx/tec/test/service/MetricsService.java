package mx.tec.test.service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.tec.test.entity.Metric;
import mx.tec.test.entity.MetricSortRecord;
import mx.tec.test.repository.MetricSortRecordRepository;
import mx.tec.test.vo.MetricVO;

@Service
public class MetricsService {
	@Autowired
    private ModelMapper modelMapper;
	
	@Autowired
	private MetricSortRecordRepository repository;
	
	Logger logger = LogManager.getLogger(MetricsService.class);
	
	public List<MetricVO> sort(List<MetricVO> unsortedMetrics) {
		List<MetricVO> sortedMetrics = unsortedMetrics.stream().sorted(Comparator.comparingDouble(MetricVO::getValue))
				.collect(Collectors.toList());
		
		MetricSortRecord newRecord = new MetricSortRecord();
		newRecord.setTimestamp(LocalDateTime.now());
		newRecord.setMetrics(Arrays.asList(modelMapper.map(sortedMetrics, Metric[].class)));
		MetricSortRecord savedRecord = repository.save(newRecord);
		
		logger.debug(savedRecord);
		
		return sortedMetrics;
	}
}
