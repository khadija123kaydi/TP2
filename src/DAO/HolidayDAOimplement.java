package DAO;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import Model.*;

public class HolidayDAOimplement implements GenericDAOI<Holiday> {

    private Connect c;

    public HolidayDAOimplement() {
        this.c = new Connect();
    }

    @Override
    public void addGeneric(Holiday holiday) {
        String sql = "INSERT INTO holiday(id,date_debut,date_fin,Type,employe_id) VALUES (?,?, ?, ?, ?)";
        try (PreparedStatement st = c.getConnect().prepareStatement(sql)) {
            st.setInt(1, holiday.getId());
            st.setDate(2, Date.valueOf(holiday.getDateDebut()));
            st.setDate(3, Date.valueOf(holiday.getDateFin()));
            st.setString(4, holiday.getType().name());
            st.setInt(5, holiday.getId_employe());
            st.executeUpdate();
           JOptionPane.showMessageDialog(null,"holiday ajouter avec succe!!","message",JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"erreur dans l'ajout d'un holiday","erreur",JOptionPane.ERROR_MESSAGE);
        }
    }
    

    @Override
    public void modifyGeneric(Holiday holiday) {
        String sql = "UPDATE Holiday SET date_debut = ?, date_fin = ?, Type = ?, employe_id = ? WHERE id = ?";
        try (PreparedStatement st = c.getConnect().prepareStatement(sql)) {
            st.setDate(1, Date.valueOf(holiday.getDateDebut()));
            st.setDate(2, Date.valueOf(holiday.getDateFin()));
            st.setString(3, holiday.getType().name());
            st.setInt(4, holiday.getId_employe());
            st.setInt(5, holiday.getId());
            int rowsAffected = st.executeUpdate();
            JOptionPane.showMessageDialog(null,"Holiday est modifier aavec succe!!","message",JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            System.err.println("Error updating holiday: " + e.getMessage());
        }
    }

    @Override
    public void deleteGeneric(int id) {
        String sql = "DELETE FROM Holiday WHERE id = ?";
        try (PreparedStatement st = c.getConnect().prepareStatement(sql)) {
            st.setInt(1, id);
            int rowsAffected = st.executeUpdate();
            JOptionPane.showMessageDialog(null,"Holiday est supprimer avec succe !!","message",JOptionPane.INFORMATION_MESSAGE);;
        } catch (SQLException e) {
            System.err.println("Error deleting holiday: " + e.getMessage());
        }
    }

    @Override
    public List<Holiday> getAllGeneric() {
        List<Holiday> holidays = new ArrayList<>();
        String sql = "SELECT h.id, h.date_debut, h.date_fin, h.Type,h.employe_id, e.* " +
                     "FROM Holiday h JOIN Employees e ON h.employe_id = e.id";
        try (PreparedStatement st = c.getConnect().prepareStatement(sql);
             ResultSet rs = st.executeQuery()) {
            while (rs.next()) {
                Employees employee = new Employees(
                    rs.getInt("id"),
                    rs.getString("nom"),
                    rs.getString("prenom"),
                    rs.getString("email"),
                    rs.getString("telephone"),
                    rs.getDouble("salaire"),
                    Role.valueOf(rs.getString("role")),
                    Poste.valueOf(rs.getString("poste"))
                );
                holidays.add(new Holiday(
                    rs.getInt("id"),
                    rs.getDate("date_debut").toLocalDate(),
                    rs.getDate("date_fin").toLocalDate(),
                    TypeH.valueOf(rs.getString("Type")),
                    rs.getInt("employe_id"),
                    employee.getNom()
                ));
            }
            }catch (SQLException e) {
            System.err.println("Error retrieving holidays: " + e.getMessage());
        }
        return holidays;
    }
    
    
    public List<Integer> FindById() {
        List<Integer> ids = new ArrayList<>();
        String query = "SELECT id FROM employees"; 

        try ( PreparedStatement ps = c.getConnect().prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                ids.add(rs.getInt("id")); 
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return ids; 
    }
    
    
    

}
