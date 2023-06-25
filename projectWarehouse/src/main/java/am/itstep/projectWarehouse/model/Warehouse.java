package am.itstep.projectWarehouse.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Component;

import java.io.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Component
public class Warehouse implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private Long warehouseId;
    private String entityTitle;
    @Column(unique = true)
    private String registrationNumber;
    private String legalAddress;
    private String realAddress;
    private String GpsCoordinates;
    private String phoneNumber;
    @Column(unique = true)
    private String emailAddress;
    private Boolean isTemperatureWH;
    private Boolean isCustomWH;
    private Double spaceFree;
    private Double balance;
    private String status;
    @Column(unique = true)
    private String login;
    private String password;
    private Role role;


    public Long warehouseIdGenerator() {
        Long id = null;
        File file = new File(System.getProperty("user.dir") + File.separator + "projectWarehouse" + File.separator + "src" + File.separator + "main" + File.separator + "resources"  + File.separator + "warehouse_id_generator.txt");
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
