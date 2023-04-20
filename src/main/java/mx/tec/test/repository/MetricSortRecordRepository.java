package mx.tec.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mx.tec.test.entity.MetricSortRecord;

@Repository
public interface MetricSortRecordRepository extends JpaRepository<MetricSortRecord, Long> {

}
