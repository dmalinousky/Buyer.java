package am.itstep.projectWarehouse.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.stereotype.Component;

import java.io.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Component
public class Buyer implements Serializable {
    private String entityTitle;
    private String registrationNumber;
    private String legalAddress;
    private String realAddress;
    private String phoneNumber;
    private String emailAddress;
    private Double balance;
    private String status;
    private String login;
    private String password;
    private Long buyerId;

    public Long buyerIdGenerator() {
        Long id = null;
        File file = new File(System.getProperty("user.dir") + File.separator + "projectWarehouse" + File.separator + "src" + File.separator + "main" + File.separator + "resources"  + File.separator + "buyer_id_generator.txt");
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
