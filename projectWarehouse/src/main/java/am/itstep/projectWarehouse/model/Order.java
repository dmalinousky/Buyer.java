package am.itstep.projectWarehouse.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.io.*;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@Component
@ToString
public class Order implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(unique = true)
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
	public Order(Long id,
				 @JsonProperty("orderId") Long orderId,
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
		this.id = id;
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

	public Long orderIdGenerator() {
		Long id = null;
		File file = new File(System.getProperty("user.dir") + File.separator + "projectWarehouse" + File.separator + "src" + File.separator + "main" + File.separator + "resources"  + File.separator + "order_id_generator.txt");
		if (file.exists()) {
			BufferedReader br = null;
			BufferedWriter bw = null;
			try {
				br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
				id = Long.parseLong(br.readLine()) + 1;
				bw = new BufferedWriter(new PrintWriter(file));
				bw.write(String.valueOf(id));
			} catch (IOException e) {
				throw new RuntimeException(e);
			} finally {
				try {
					br.close();
					bw.close();
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}
		}
		return id;
	}
}
