package Model;

import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import DAO.EmployeeDAOimplement;
import DAO.HolidayDAOimplement;

public class HolidayModel {
    private HolidayDAOimplement dao;

    public HolidayModel(HolidayDAOimplement dao) {
        this.dao= dao;
       
    }

 
    
    public void addHoliday(int id,LocalDate dateDebut,LocalDate dateFin, TypeH type, int id_employe) {
    	
    	
    	if(differentInDays(dateFin,dateDebut) <0) {
    		
    		JOptionPane.showMessageDialog(null, "erreur dans les dates ");
    		return ;
    	}
    	
    	List<Integer>lis=new ArrayList<>();
		lis=dao.FindById();
		
		if(!lis.contains(id_employe)) {
			JOptionPane.showConfirmDialog(null, "l'id d'employe n'est pas trouve ");
		}
    	
        Holiday holiday = new Holiday(id,dateDebut, dateFin,type,id_employe,null);
        dao.addGeneric(holiday); 
    }

    public void modifyHoliday(int id, LocalDate dateDebut, LocalDate dateFin, TypeH  type,int id_employe) {
        Holiday holiday = new Holiday(id, dateDebut, dateFin,type,id_employe,null);
        dao.modifyGeneric(holiday);
    }

    public void deleteHoliday(int id) {
        dao.deleteGeneric(id);
    }

    public Object[][] getAllHolidays() {
        List<Holiday> holidays = dao.getAllGeneric();
        Object[][] holidayData = new Object[holidays.size()][5]; 

        for (int i = 0; i < holidays.size(); i++) {
            Holiday holiday = holidays.get(i);
            holidayData[i][0] = holiday.getId();
            holidayData[i][1] = holiday.getDateDebut();
            holidayData[i][2] = holiday.getDateFin();
            holidayData[i][3] = holiday.getType();
            holidayData[i][4]=holiday.getNom_employe();
        }
        return holidayData;
    }
    
   
       public List<Integer> getIds(){
      return  dao.FindById();
   }
    
    
    
    public  int differentInDays(LocalDate dateFin, LocalDate dateDebut) {
        return (int) ChronoUnit.DAYS.between(dateDebut, dateFin);
    }
    
}



