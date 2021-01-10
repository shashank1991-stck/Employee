package com.kodak.employee.view;



import com.kodak.employee.entity.Employee;
import com.kodak.employee.repository.EmployeeRepository;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;

@Route
@StyleSheet("style.css")
public class MainView extends VerticalLayout {
	
	 private EmployeeRepository employeeRepository;

	 	final Label hdr;
	 	
	    final Grid<Employee> grid;

	    final TextField filter;
	    
	    final HorizontalLayout layout;
	    
	    final Anchor anchor;
	    
	    final Label error;
	   

	    public MainView(EmployeeRepository repo) {
	        this.employeeRepository = repo;
	        this.hdr = new Label("Employee Details");
	        this.grid = new Grid<>(Employee.class);
	        this.filter = new TextField();
	        this.layout = new HorizontalLayout();
	        this.anchor = new Anchor();
	        this.error = new Label();
	        
	        VerticalLayout container = new VerticalLayout();
	        
	        add(container,hdr);
	        add(container,error);
	        add(container, layout);
	        
	        error.setVisible(false);
	        
	        //set classnames for css related changes
	        container.setClassName("container");
	        hdr.setClassName("empHdr");
	        error.setClassName("error");
	        layout.setClassName("empDetails");
	        anchor.setClassName("empLbl");
	        grid.setClassName("empTable");
	        filter.setClassName("searchFilter");
	        
	        //EmpFilter
	        filter.setPlaceholder("Filter by first name or last name");
	        filter.setValueChangeMode(ValueChangeMode.EAGER);
	        filter.setVisible(false);
	        filter.addValueChangeListener(e -> listEmployees(e.getValue()));
	        
	        //EmpDetails
	        layout.add(anchor);
	        
	        VerticalLayout vertical = new VerticalLayout ();
	        vertical.add(filter);//Emp Filter
	        vertical.add(grid);//Emp Table
	        
	        layout.add(vertical);
	        
	        //EmpLabel
	        anchor.setText("Employee");
	        anchor.getElement().addEventListener("click", e -> {
	        	filter.setValue("");
	        	
	        	filter.setVisible(true); 
	        	grid.setVisible(true);
	        	listEmployees(null);
	        });
	        
	        //EmpTable
	        grid.setColumns("firstName", "lastName", "emailId");
	        grid.setVisible(false);
	    }

	    void listEmployees(String filterText) {
	    	try {
	    		if(filterText!=null) {
		    		grid.setItems(employeeRepository.findEmployeeByFirstNameIgnoreCaseContainingOrLastNameIgnoreCaseContaining(filterText,filterText));
		    	}else {
		    		grid.setItems(employeeRepository.findAll());
		    	}
			} catch (Exception e) {
				// TODO: handle exception
				error.setText("Error fetching employee details - " + e.getMessage());
				error.setVisible(true);
			}
	    }

}


