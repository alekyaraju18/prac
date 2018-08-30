package com.cg.spring.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.cg.spring.dto.Employee;
import com.cg.spring.service.EmployeeService;

@Controller
public class EmployeeContoller {

	@Autowired
	EmployeeService employeeService;

	@RequestMapping(value = "/showall", method = RequestMethod.GET)
	public ModelAndView getAllEmployees() {
		List<Employee> list = employeeService.showEmployees();
		return new ModelAndView("show", "emps", list);
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String myEmployee(@ModelAttribute("emp") Employee emp, Map<String, Object> model) {
		model.put("emp", emp);
		return "add_employee";
	}

	@RequestMapping(value = "/addemployee", method = RequestMethod.POST)
	public String addEmployee(@Valid @ModelAttribute("emp") Employee emp, BindingResult result,
			Map<String, Object> model) {
		if (result.hasErrors()) {
			return "add_employee";
		}
		employeeService.addEmployee(emp);
		return "redirect:/showall";
	}
	@RequestMapping(value = "/del", method=RequestMethod.GET)
	public String myEmployee1(@ModelAttribute("emp") Employee emp) {
		
		return "delete";
	}
	@RequestMapping(value = "/deleteemployee", method = RequestMethod.POST)
	public String deleteEmployee(@ModelAttribute("emp") Employee emp, BindingResult result)
	{
	employeeService.deleteEmployee(emp.getId());
	return "redirect:/showall";
	}
	@RequestMapping(value="/update",method=RequestMethod.GET)
	public String updateEmployee(@ModelAttribute("emp") Employee emp)
	{
		return "update";
	}
	@RequestMapping(value="/update1",method=RequestMethod.GET)
	public ModelAndView updateDetails(@ModelAttribute("emp") Employee emp)
	{
		Employee employe=employeeService.searchEmployee(emp.getId());
		
		return new ModelAndView("update2","empu",employe);
		
	}
	
	@RequestMapping(value="/updateemployee",method=RequestMethod.POST)
	public String update(@ModelAttribute("emp") Employee emp)
	{
		employeeService.updateEmployee(emp);
		return "redirect:/showall";
		
}

}