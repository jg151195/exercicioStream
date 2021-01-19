package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

import entities.Employee;
import model.services.EmployeeService;

public class Program {

	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Path: ");
		String path = sc.nextLine();
		
		try (BufferedReader br = new BufferedReader(new FileReader(path))){
			
			List<Employee> list = new ArrayList<>();
			
			String line = br.readLine();
			while (line != null) {
				String [] fields = line.split(",");
				list.add(new Employee(fields[0], fields[1], Double.parseDouble(fields[2])));
				line = br.readLine();
			}
			
			System.out.println("Enter salary: ");
			double value = sc.nextDouble();
			
			System.out.println("Email of people whose salary is more than " + value + ":");
			
			Comparator<String> comp = (s1, s2) -> s1.toUpperCase().compareTo(s2.toUpperCase());
			
			List<String> orderNames = list.stream()
					.filter(e->e.getSalary() > value)
					.map(e->e.getEmail())
					.sorted(comp)
					.collect(Collectors.toList());
			orderNames.forEach(System.out::println);
			
			EmployeeService emp = new EmployeeService();
			
			double sum = emp.filteredSum(list, e -> e.getName().charAt(0)=='M');
			System.out.println("Sum of salary of people whose name starts with 'M': " + String.format("%.2f", sum));

		}

		catch(IOException e) {
			System.out.println("Error: " + e.getMessage());
		}		
		
		sc.close();

	}

}
