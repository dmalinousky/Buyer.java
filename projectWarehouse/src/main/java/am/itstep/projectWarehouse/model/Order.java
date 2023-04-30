package am.itstep.projectWarehouse.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@Component
public class Order implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long orderId;
    private Long warehouseId;
	private Long buyerId;
	private Double totalSum;
	private Integer keepingDays;
	private Integer collisUnloaded;
	private Boolean isUnloadingByWH;
	private Boolean isLoadingByWH;
	private String platesNumberUnloading;
	private String platesNumberLoading;
	private String status;
	private String comment;

	@JsonCreator
	public Order(Long orderId,
				 @JsonProperty("warehouseId") Long warehouseId,
				 @JsonProperty("buyerId") Long buyerId,
				 @JsonProperty("totalSum") Double totalSum,
				 @JsonProperty("keepingDays") Integer keepingDays,
				 @JsonProperty("collisUnloaded") Integer collisUnloaded,
				 @JsonProperty("isUnloadingByWH") Boolean isUnloadingByWH,
				 @JsonProperty("isLoadingByWH") Boolean isLoadingByWH,
				 @JsonProperty("platesNumberUnloading") String platesNumberUnloading,
				 @JsonProperty("platesNumberLoading") String platesNumberLoading,
				 @JsonProperty("status") String status,
				 @JsonProperty("comment") String comment) {
		this.orderId = orderId;
		this.warehouseId = warehouseId;
		this.buyerId = buyerId;
		this.totalSum = totalSum;
		this.keepingDays = keepingDays;
		this.collisUnloaded = collisUnloaded;
		this.isUnloadingByWH = isUnloadingByWH;
		this.isLoadingByWH = isLoadingByWH;
		this.platesNumberUnloading = platesNumberUnloading;
		this.platesNumberLoading = platesNumberLoading;
		this.status = status;
		this.comment = comment;
	}
}
