package Controller;

import Model.EmployeeModel;
import Model.Poste;
import Model.Role;
import View.EmployeesView;

import javax.swing.*;
import java.awt.event.*;

public class EmployeeController {
    private EmployeesView view;
    private EmployeeModel model;
    public EmployeeController(EmployeesView view, EmployeeModel model) {
        this.view = view;
        this.model = model;
        this.view.btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	int id = (int) view.idSpinner.getValue();
            	String nom = view.text1.getText();
                String prenom = view.text2.getText();
                String telephone = view.text3.getText();
                String email = view.text4.getText();
                double salaire = Double.parseDouble(view.text5.getText());
                Role role = (Role) view.roles.getSelectedItem();
                Poste poste = (Poste) view.postes.getSelectedItem();
                model.addEmployee(id,nom, prenom, email, telephone, salaire, role, poste);
            }
        });

        this.view.btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = view.table.getSelectedRow();
                if (selectedRow != -1) { 
                	int id = (int) view.idSpinner.getValue();
                    String nom = view.text1.getText();
                    String prenom = view.text2.getText();
                    String telephone = view.text3.getText();
                    String email = view.text4.getText();
                    double salaire = 0;
                    
                    
                    try {
                        salaire = Double.parseDouble(view.text5.getText());
                    } catch (NumberFormatException ex) {
                        JOptionPane.showInputDialog(view, "Invalid salary input. Setting default value.");

                    }
                    
                    Role role = (Role) view.roles.getSelectedItem();
                    Poste poste = (Poste) view.postes.getSelectedItem();
                    model.modifyEmployee(id,nom, prenom,telephone,email, salaire, role, poste);
                    view.model.setValueAt(id,selectedRow,0);
                    view.model.setValueAt(nom, selectedRow, 1);
                    view.model.setValueAt(prenom, selectedRow, 2);
                    view.model.setValueAt(telephone, selectedRow, 3);
                    view.model.setValueAt(email, selectedRow, 4);
                    view.model.setValueAt(salaire, selectedRow, 5);
                    view.model.setValueAt(role, selectedRow, 6);
                    view.model.setValueAt(poste, selectedRow, 7);
                } else {
                    JOptionPane.showInputDialog(view, "Please select a row to modify.");
                }
            }
        });

        this.view.btn3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = view.table.getSelectedRow();
                if (selectedRow != -1) {
                    int id = (int) view.model.getValueAt(selectedRow, 0);
                    model.deleteEmployee(id);
                    view.model.removeRow(selectedRow);
                } else {
                    JOptionPane.showInputDialog(view, "Please select a row to delete.");
                }
            }
        });


        this.view.btn4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	 Object[][] allEmployees = model.getAllEmployees();
                 view.model.setRowCount(0);
                 for (Object[] employee : allEmployees) {
                     view.model.addRow(employee);
                 }
            }
        });
    }
    
    

}
