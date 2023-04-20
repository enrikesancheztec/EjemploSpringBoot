package mx.tec.test.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import mx.tec.test.enumeration.MetricType;

@Entity
@Table(name = "metrics")
public class Metric {
	@Id
	@GeneratedValue
	private long id;
	@NotNull
	@Column(name = "`value`")
	private double value;
	@NotNull
	private MetricType type;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "record_id", referencedColumnName = "id")
	private MetricSortRecord record;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public double getValue() {
		return value;
	}
	
	public void setValue(double value) {
		this.value = value;
	}
	
	public MetricType getType() {
		return type;
	}
	
	public void setType(MetricType type) {
		this.type = type;
	}

	public MetricSortRecord getRecord() {
		return record;
	}

	public void setRecord(MetricSortRecord record) {
		this.record = record;
	}
}
