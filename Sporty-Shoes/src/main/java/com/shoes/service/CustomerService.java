package com.shoes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoes.entity.Customer;
import com.shoes.entity.Product;
import com.shoes.repository.CustomerRepository;
import com.shoes.repository.ProductRepository;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	public ProductRepository prodrepo;

	public void saveCustomer(Customer customer) {
		this.customerRepository.save(customer);
		
	}
	
	public Iterable<Product> listAll()
	{
		return prodrepo.findAll();
	}

	public boolean loginVerify(String email, String password) {
		Customer customer = customerRepository.findByEmail(email);
        if (customer!= null && customer.getEmail().equals(email) && customer.getPassword().equals(password)) {
            return true;
        }
        return false;
	}

	public Customer getCustomer(String email) {
		return customerRepository.findByEmail(email);
	}

	public List<Customer> getAllCustomers() {
		return customerRepository.findAll();
	}

	public void deleteCustomer(String email) {
		customerRepository.deleteById(email);
	}

	public List<Customer> searchCustomer(String keyword) {
		return customerRepository.userSearch(keyword);
	}
	
	public List<String> customerEmails(){
		return customerRepository.customerEmails();
	}

}
