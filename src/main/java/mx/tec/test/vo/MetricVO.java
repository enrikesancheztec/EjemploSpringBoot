package mx.tec.test.vo;

import java.util.Objects;

import javax.validation.constraints.NotNull;

import mx.tec.test.enumeration.MetricType;

public class MetricVO {
	private long id;
	
	@NotNull
	private double value;
	
	@NotNull
	private MetricType type;

	public MetricVO() {

	}
	
	public MetricVO(long id, double value, MetricType type) {
		this.id = id;
		this.value = value;
		this.type = type;
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

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, type, value);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MetricVO other = (MetricVO) obj;
		return id == other.id && type == other.type
				&& Double.doubleToLongBits(value) == Double.doubleToLongBits(other.value);
	}

	@Override
	public String toString() {
		return "MetricVO [id=" + id + ", value=" + value + ", type=" + type + "]";
	}
}
