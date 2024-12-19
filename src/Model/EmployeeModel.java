package Model;

import DAO.EmployeeDAOimplement;
import java.util.List;

import javax.swing.JOptionPane;

public class EmployeeModel {
    private EmployeeDAOimplement dao;


    public EmployeeModel(EmployeeDAOimplement dao) {
        this.dao = dao;
    }

    public boolean addEmployee(int id,String nom, String prenom, String email, String telephone, double salaire, Role role, Poste poste) {

        if (telephone.length() != 10) {
        	JOptionPane.showMessageDialog(null,"The phone number must contain exactly 10 digits.","message",JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        Employees emp = new Employees(id,nom, prenom, email, telephone, salaire, role, poste);
        dao.addGeneric(emp);
        return true;
    }

    public boolean modifyEmployee(int id,String nom, String prenom, String email, String telephone, double salaire, Role role, Poste poste) {

        if (telephone.length() != 10) {
        	JOptionPane.showMessageDialog(null,"The phone number must contain exactly 10 digits.","message",JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        Employees emp = new Employees(id,nom, prenom, email, telephone, salaire, role, poste);
        dao.modifyGeneric(emp);
        return true;
    }

    public boolean deleteEmployee(int id){
    	dao.deleteGeneric(id);
    	return true;
    }
    public Object[][] getAllEmployees() {
        List<Employees> employees = dao.getAllGeneric();
        Object[][] employeeData = new Object[employees.size()][8]; 

        for (int i = 0; i < employees.size(); i++) {
            Employees emp = employees.get(i);
            employeeData[i][0] = emp.getId();
            employeeData[i][1] = emp.getNom();
            employeeData[i][2] = emp.getPrenom();
            employeeData[i][3] = emp.getTelephone();
            employeeData[i][4] = emp.getEmail();
            employeeData[i][5] = emp.getSalaire();
            employeeData[i][6] = emp.getRole();
            employeeData[i][7] = emp.getPoste();
        }
        return employeeData;
    }
    public int[] getid() {
        List<Employees> employees = dao.getAllGeneric(); 
        int[] ids = new int[employees.size()];

        for (int j = 0; j < employees.size(); j++) {
            ids[j] = employees.get(j).getId(); 
        }
        return ids;
    }
  

    
}
