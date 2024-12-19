package View;  

import Controller.EmployeeController;
import Controller.HolidayController;
import Model.EmployeeModel;
import Model.HolidayModel;
import DAO.EmployeeDAOimplement;
import DAO.HolidayDAOimplement;
import javax.swing.*;

public class GestionEmployesConges extends JFrame {

    private JTabbedPane tabbedPane;

    public GestionEmployesConges() {
        this.setSize(800, 600);  
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Gestion Employés et Congés");

        tabbedPane = new JTabbedPane();

        initializeEmployeeManagement();
        initializeHolidayManagement();

        add(tabbedPane);
    }

    private void initializeEmployeeManagement() {
        EmployeeDAOimplement employeeDAO = new EmployeeDAOimplement();
        EmployeeModel employeeModel = new EmployeeModel(employeeDAO);
        EmployeesView employeeView = new EmployeesView();

        EmployeeController employeeController = new EmployeeController(employeeView, employeeModel);

        tabbedPane.addTab("Employés", employeeView.getPan());
    }

    private void initializeHolidayManagement() {
        HolidayDAOimplement holidayDAO = new HolidayDAOimplement();
        HolidayModel holidayModel = new HolidayModel(holidayDAO);
        HolidayView holidayView = new HolidayView();

        HolidayController holidayController = new HolidayController(holidayView, holidayModel);

        tabbedPane.addTab("Congés", holidayView.getpan());
    }
}
