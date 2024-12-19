package Controller;

import Model.HolidayModel;

import View.HolidayView;
import Model.Employees; 
import Model.TypeH;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.event.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public class HolidayController {
    private HolidayView view;
    private HolidayModel model;

    public HolidayController(HolidayView view, HolidayModel model) {
        this.view = view;
        this.model = model;
        fillEmployeeIdsComboBox();

      
        this.view.btnAjouter.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
            	int id=Integer.parseInt(view.idField.getText());
                int idEmploye = Integer.parseInt(view.cmbIdEmploye.getSelectedItem().toString()); 
                TypeH typeConges =(TypeH) view.cmbType.getSelectedItem();
                String dateDebutStr = view.txtDateDebut.getText();
                String dateFinStr = view.txtDateFin.getText();
                if (!isValidDate(dateDebutStr) || !isValidDate(dateFinStr)) {
                    JOptionPane.showInputDialog(view, "Please enter valid dates in the format yyyy-MM-dd.");
                    return;
                }
                LocalDate dateDebut = LocalDate.parse(dateDebutStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                LocalDate dateFin = LocalDate.parse(dateFinStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                
                model.addHoliday(id,dateDebut, dateFin, typeConges, idEmploye);
        		afficher();

            }
        });

     
        this.view.btnModifier.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = view.table.getSelectedRow();
                if (selectedRow != -1) {
                	
                	int id=Integer.parseInt(view.idField.getText());
                    int idEmploye = Integer.parseInt(view.cmbIdEmploye.getSelectedItem().toString());
                    TypeH typeConges = (TypeH) view.cmbType.getSelectedItem();
                    String dateDebutStr = view.txtDateDebut.getText();
                    String dateFinStr = view.txtDateFin.getText();

                  
                    if (!isValidDate(dateDebutStr) || !isValidDate(dateFinStr)) {
                        JOptionPane.showInputDialog(view, "Please enter valid dates in the format yyyy-MM-dd.");
                        return;
                    }
                    LocalDate dateDebut = LocalDate.parse(dateDebutStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    LocalDate dateFin = LocalDate.parse(dateFinStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

                    model.modifyHoliday(id,dateDebut,dateFin,typeConges,idEmploye);
                    view.model.setValueAt(idEmploye,selectedRow,0);
                    view.model.setValueAt(dateDebut, selectedRow, 1);
                    view.model.setValueAt(dateFin, selectedRow, 2);
                    view.model.setValueAt(typeConges, selectedRow, 3);
                } else {
                    JOptionPane.showInputDialog(view, "Please select a row to modify.");
                }
            }
        });

      
        this.view.btnSupprimer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = view.table.getSelectedRow();
                if (selectedRow != -1) {
                    int id = Integer.parseInt(view.model.getValueAt(selectedRow,0).toString());
                    model.deleteHoliday(id);
                    view.model.removeRow(selectedRow);
                } else {
                    JOptionPane.showInputDialog(view, "Please select a row to modify.");
                }
            }
        });
        
        this.view.btnAfficher.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
        
            		afficher();
    }
});
    }

    
    private boolean isValidDate(String dateStr) {
        try {
            LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
    
    private void fillEmployeeIdsComboBox() {
        
        List<Integer> employeeIds = model.getIds();
        view.cmbIdEmploye.removeAllItems();
        for (Integer id : employeeIds) {
            view.cmbIdEmploye.addItem(id.toString());
        }
    }
    
    public void afficher() {
    	Object[][] holidayData = model.getAllHolidays();

        
        view.model = new DefaultTableModel(holidayData,view.getColumnNames());
        view.getTable().setModel(view.model);
    }
}
