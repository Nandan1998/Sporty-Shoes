package com.shoes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoes.entity.Product;
import com.shoes.repository.ProductRepository;

@Service
	public class ProductService {
		@Autowired
		public ProductRepository prodrepo;
		
		public Iterable<Product> listAll()
		{
			return prodrepo.findAll();
		}
		
		public void save(Product product)
		{
			prodrepo.save(product);
		}
		public void deleteProduct(int id) {
			prodrepo.deleteById(id);
		}
		
		//get products by keyword
		public List<Product> findByKeyword(String keyword){
			return prodrepo.findByKeyword(keyword);
		}
		public void delete(Integer id)
		{
			prodrepo.deleteById(id);
		}


}
