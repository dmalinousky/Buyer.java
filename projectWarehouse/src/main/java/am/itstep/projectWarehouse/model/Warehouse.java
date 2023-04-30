package am.itstep.projectWarehouse.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Component;

import java.io.*;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Component
public class Warehouse implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "Id")
    private Long id;
//    @Column(name = "EntityTitle")
    private String entityTitle;
//    @Column(name = "RegistrationNumber")
    private String registrationNumber;
//    @Column(name = "LegalAddress")
    private String legalAddress;
//    @Column(name = "RealAddress")
    private String realAddress;
//    @Column(name = "GpsCoordinates")
    private String GpsCoordinates;
//    @Column(name = "PhoneNumber")
    private String phoneNumber;
//    @Column(name = "EmailAddress")
    private String emailAddress;
//    @Column(name = "IsTemperatureWH")
    private Boolean isTemperatureWH;
//    @Column(name = "IsCustomWH")
    private Boolean isCustomWH;
//    @Column(name = "SpaceFree")
    private Double spaceFree;
//    @Column(name = "Balance")
    private Double balance;
//    @Column(name = "Status")
    private String status;
//    @Column(name = "Login")
    private String login;
//    @Column(name = "Password")
    private String password;
//    @Column(name = "WarehouseId")
    private Long warehouseId;


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
