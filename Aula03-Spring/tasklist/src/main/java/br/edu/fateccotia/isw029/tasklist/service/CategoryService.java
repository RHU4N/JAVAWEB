package br.edu.fateccotia.isw029.tasklist.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.fateccotia.isw029.tasklist.model.Category;
import br.edu.fateccotia.isw029.tasklist.repository.CategoryRepository;

@Service
public class CategoryService {
	@Autowired
	private CategoryRepository categoryRepository;
	
	public Category save(String category, String color) {
		Category cat = new Category();
		cat.setCategory(category);
		cat.setColor(color);
		
		Category saved = categoryRepository.save(cat);
		return saved;
	}
	
	public List<Category> findAll() {
		List<Category> list = new ArrayList<Category>();
		categoryRepository.findAll().iterator().forEachRemaining(list::add);
		return list;		
	}
}
