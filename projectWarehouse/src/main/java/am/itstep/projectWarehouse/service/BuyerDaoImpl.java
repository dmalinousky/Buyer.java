package am.itstep.projectWarehouse.service;

import am.itstep.projectWarehouse.model.Buyer;
import am.itstep.projectWarehouse.model.DBConnection;
import am.itstep.projectWarehouse.model.Warehouse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Service
public class BuyerDaoImpl implements BuyerDaoService {
    @Autowired
    private Buyer buyer;

    @Override
    public boolean createBuyer(Buyer buyer) {
        Connection connection = DBConnection.getConnection();
        PreparedStatement ps = null;
        try {
            String query = "INSERT INTO buyers (" +
                    "entityTitle," +
                    "registrationNumber, " +
                    "legalAddress, " +
                    "realAddress, " +
                    "phoneNumber, " +
                    "emailAddress, " +
                    "balance, " +
                    "status, " +
                    "login, " +
                    "password," +
                    "buyerid" +
                    ") values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
            ps = connection.prepareStatement(query);
            ps.setString(1, buyer.getEntityTitle());
            ps.setString(2, buyer.getRegistrationNumber());
            ps.setString(3, buyer.getLegalAddress());
            ps.setString(4, buyer.getRealAddress());
            ps.setString(5, buyer.getPhoneNumber());
            ps.setString(6, buyer.getEmailAddress());
            ps.setDouble(7, buyer.getBalance());
            ps.setString(8, buyer.getStatus());
            ps.setString(9, buyer.getLogin());
            ps.setString(10, buyer.getPassword());
            ps.setLong(11, buyer.buyerIdGenerator());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                ps.close();
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public Buyer editBuyer(String login, String password) {
        Connection connection = DBConnection.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String query = "SELECT * FROM buyers WHERE login = ? AND password = ?";
            ps = connection.prepareStatement(query);
            ps.setString(1, login);
            ps.setString(2, password);
            rs = ps.executeQuery();
            Buyer buyer = new Buyer();
            while (rs.next()) {
                buyer.setEntityTitle(rs.getString("EntityTitle"));
                buyer.setRegistrationNumber(rs.getString("RegistrationNumber"));
                buyer.setLegalAddress(rs.getString("LegalAddress"));
                buyer.setRealAddress(rs.getString("RealAddress"));
                buyer.setPhoneNumber(rs.getString("PhoneNumber"));
                buyer.setEmailAddress(rs.getString("EmailAddress"));
                buyer.setBalance(rs.getDouble("Balance"));
                buyer.setStatus(rs.getString("Status"));
                buyer.setLogin(rs.getString("Login"));
                buyer.setPassword(rs.getString("Password"));
                return buyer;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                ps.close();
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public boolean deleteBuyer(String registrationNumber) {
        Connection connection = DBConnection.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String query = "DELETE FROM buyers WHERE registrationNumber = ?";
            ps = connection.prepareStatement(query);
            ps.setString(1, registrationNumber);
            return ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                ps.close();
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public ArrayList<Warehouse> showAllWarehouses() {
        return null;
    }
}
