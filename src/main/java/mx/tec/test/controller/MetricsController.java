package mx.tec.test.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import mx.tec.test.service.MetricsService;
import mx.tec.test.vo.MetricVO;

@RestController
@RequestMapping("metrics")
@Validated
public class MetricsController {
	@Autowired
	MetricsService metricsService;

	@PostMapping("/sort")
	ResponseEntity<List<MetricVO>> sort(@RequestBody List<@Valid MetricVO> metrics) {
		return new ResponseEntity<>(metricsService.sort(metrics), HttpStatus.OK);
	}

	@ExceptionHandler(value = ConstraintViolationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<String> handleCustomerAlreadyExistsException(ConstraintViolationException cve) {
		return new ResponseEntity<String>(cve.getMessage(), HttpStatus.BAD_REQUEST);
	}
}
