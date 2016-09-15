package com.formation.ensta.computerdb;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * ComputerDB class provide hard coded Computer & Company values as a replacement for an SQL Database.
 * @author nicolas
 *
 */
public class ComputerDB {

	private static Map<String, String> COMPANY_NAMES = new HashMap<>();

	private static ComputerDB instance;

	private static Map<Integer, ComputerDbObject> computers = new HashMap<>();
	private static Map<Integer, CompanyDbObject> companies = new HashMap<>();

	static {
		COMPANY_NAMES.put("dell", "dell");
		COMPANY_NAMES.put("hp", "hp");
		COMPANY_NAMES.put("smg", "samsung");
		COMPANY_NAMES.put("tosh", "toshiba");
		COMPANY_NAMES.put("iMac", "apple");
	}
	
	private ComputerDB() {
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");

		// fill companies		
		int i = 1;
		int currentComputerId = 1;
		for (String compPrefix : COMPANY_NAMES.keySet()) {
			companies.put(i, new CompanyDbObject(i, COMPANY_NAMES.get(compPrefix)));
			
			for (int j = 1; j < 10; ++j) {
				String startStr = null, endStr = null;
				Calendar cal = null;
				
				if (j % 7 != 0) {
					cal = makeStartDate(i, j);
					startStr = df.format(cal.getTime());							
				}
				
				if (cal != null && j % 2 == 0) {
					cal.add(Calendar.YEAR, j);
					endStr = df.format(cal.getTime());							
				}
				
				String name = String.format("%s%3d", compPrefix, j);
				Integer compId = currentComputerId % 3 == 0 ? null : i;
				computers.put(currentComputerId, new ComputerDbObject(currentComputerId, compId, name, startStr, endStr));
				currentComputerId++;
			}
			++i;
		}		
	}

	/**
	 * 
	 * @return the instance to ComputerDB.
	 */
	public static ComputerDB getInstance() {
		if (instance == null) {
			synchronized (ComputerDB.class) {
				if (instance == null) {
					instance = new ComputerDB();	
				}
			}
		}
		return instance;
	}

	/**
	 * 
	 * @return all known companies
	 */
	public CompanyDbObject[] selectCompanies() {
		return companies.values().toArray(new CompanyDbObject[0]);
	}

	/**
	 * 
	 * @param id id of company to search for
	 * @return the company with provided id, or null if not found.
	 */
	public CompanyDbObject[] selectCompanies(int id) {
		CompanyDbObject[] res = {
			companies.get(id)
		};
		return res;
	}

	/**
	 * 
	 * @return all known computers
	 */
	public ComputerDbObject[] selectComputers() {
		return computers.values().toArray(new ComputerDbObject[0]);
	}

	public void insertCompany(int id, Integer companyId, String name, String startDate, String endDate) {
		computers.put(id, new ComputerDbObject(id, companyId, name, startDate, endDate));
	}

	public void insertComputer(int id, Integer companyId, String name, String startDate, String endDate) {
		if (companyId != null && companies.get(companyId) == null) {
			throw new NoSuchElementException("no company found with id " + companyId);
		}
		
		computers.put(id, new ComputerDbObject(id, companyId, name, startDate, endDate));
	}

	
	/**
	 * 
	 * @param id id of computer to search for
	 * @return the computer with provided id, or null if not found.
	 */
	public ComputerDbObject[] selectComputers(int id) {
		ComputerDbObject[] res = {
				computers.get(id)
		};
		return res;
	}
	
	private Calendar makeStartDate(int i, int j) {
		return new GregorianCalendar(2000 + (i * 2 * j) % 16, (i + 1) , (j * 3) % 12);
	}
		
	public class ComputerDbObject {
		public final int id;
		public final Integer companyId;
		public final String name;
		public final String startDate;
		public final String endDate;
		
		public ComputerDbObject(int id, Integer companyId, String name, String startDate, String endDate) {
			this.id = id;
			this.companyId = companyId;
			this.name = name;
			this.startDate = startDate;
			this.endDate = endDate;
		}
	}
	
	public class CompanyDbObject {
		public final int id;
		public final String name;
		
		public CompanyDbObject(int id, String name) {
			this.id = id;
			this.name = name;
		}
	}
	
	
}
