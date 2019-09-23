package com.denizturkmen.Client;


import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import org.hibernate.query.Query;
import org.hibernate.Session;

import com.denizturkmen.Entity.Employee;
import com.denizturkmen.Util.HibernateUtil;

public class CriteriaMultipleAttributeSelectedTest {

	public static void main(String[] args) {
		
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Object[]> criteriaQuery = builder.createQuery(Object[].class);
		Root<Employee> root = criteriaQuery.from(Employee.class);
		
		Path<Employee> employeeName = root.get("employeeName");
		Path<Employee> employeeEmail = root.get("email");
		Path<Employee> employeeSalary = root.get("salary");
		
		//birden çok alan sececeğimiz için multiselect
		criteriaQuery.multiselect(employeeName,employeeEmail,employeeSalary);
		
		Query<Object[]> query = session.createQuery(criteriaQuery);
		List<Object[]> list = query.list();
		System.out.println("-----------------------------------------------------");
		
		for (Object[] objects : list) {
			System.out.println("Employee Name :"+(String)objects[0]);
			System.out.println("Email:" + (String) objects[1]);
			System.out.println("Salary:" + (Double) objects[2]);
			System.out.println("--------------------------");
		}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
