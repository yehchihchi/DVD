package com.example.controller;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.dao.CustomerDAO;
import com.example.entity.Customer;

@Controller
public class CustomerController {
	@Autowired
	CustomerDAO dao;
	
	@RequestMapping(value = "/customerCreate", method = RequestMethod.GET)
    public ModelAndView openFormCreate() {
       ModelAndView model = new ModelAndView("customerCreate");
       return model;
    }

    @RequestMapping(value = "/customerCreate", method = RequestMethod.POST)
    public ModelAndView processFormCreate(@ModelAttribute Customer cus) throws SQLException {
//       ModelAndView model = new ModelAndView("customerDone");
       ModelAndView model = new ModelAndView("redirect:/customerRetrieveAll");

       dao.save(cus);
              
       model.addObject(cus);
       return model;
    }
    
	@RequestMapping(value = {"/customerRetrieveAll","/"}, method = RequestMethod.GET)
    public ModelAndView retrieveCustomers() throws SQLException{
	   
	   Iterable<Customer> customers = dao.findAll();
	   //Iterable<Customer> customers = dao.findByOrderByWeightAsc();
	   //Iterable<Customer> customers = dao.findByWeightGreaterThan(40);
	   //Iterable<Customer> customers = dao.findOverWeight();
	   //System.out.println(customers.size());
       ModelAndView model = new ModelAndView("customerList");
       model.addObject("allCustomers",customers);
       //model.addObject(allCustomers);
       return model;
    }

	@RequestMapping(value = "/customerUpdate", method = RequestMethod.GET)
    public ModelAndView openFormUpdate(@RequestParam(value="id", required=false, defaultValue="1") Long id) {
       ModelAndView model = new ModelAndView("customerUpdate");
       Customer cus = dao.findOne(id);
       model.addObject(cus);
       
       return model;
    }

    @RequestMapping(value = "/customerUpdate", method = RequestMethod.POST)
    public ModelAndView processFormUpdate(@ModelAttribute Customer cus) throws SQLException {
//       ModelAndView model = new ModelAndView("customerDone");
       ModelAndView model = new ModelAndView("redirect:/customerRetrieveAll");
       dao.save(cus);             

       return model;
    }
    
	@RequestMapping(value = "/customerDelete", method = RequestMethod.GET)
    public ModelAndView deleteCustomer(@RequestParam(value="id", required=false, defaultValue="1") Long id) {
       ModelAndView model = new ModelAndView("redirect:/customerRetrieveAll");
       
       dao.delete(id);

       return model;
    }


	
}


