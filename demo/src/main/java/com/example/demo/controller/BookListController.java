package com.example.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.model.Book;
import com.example.demo.service.BookService;
@Controller
@RequestMapping("/read")
public class BookListController {
	
	@Autowired
	private BookService bookService;
	/*
	@RequestMapping(value="/{reader}",method=RequestMethod.GET)
	public String readersBooks(@PathVariable("reader") String reader,Model model){
		System.out.println(reader);
		List<Book> books=bookService.findByReader(reader);
		model.addAttribute("books", books);
		return "bookList";
		//return reader;
		
	}*/
	
	@RequestMapping(value="/bookList",method=RequestMethod.GET)
	public String bookList(Model model){
		List<Book> books=bookService.findAll();
		model.addAttribute("books", books);
		return "bookList";
		//return reader;
		
	}

	
	@RequestMapping(value="/{reader}",method=RequestMethod.POST)
	public String addToReadingList(@PathVariable("reader") String reader,Book book){
		book.setReader(reader);
		bookService.addorEdit(book);
		return "rediret:/{reader}";
		
	}
	
	@RequestMapping(value="/beforeSave",method=RequestMethod.GET)
	public String beforeSaveBook(){
		return "bookadd";
	}
	
	@RequestMapping(value="/saveOrEdit",method=RequestMethod.POST)
	public String saveBook(String id,Book book,HttpServletRequest request,Model model){
		String  author=request.getParameter("author");
		String  title=request.getParameter("title");
		System.out.println(author+"---"+title+"----"+book);
		bookService.addorEdit(book);
		return "redirect:/read/bookList";
	}
	@RequestMapping(value="/beforeEdit",method=RequestMethod.GET)
	public String editBook(String id,Model model){
		Book b=null;
		System.out.println(id);
		if(StringUtils.isNotBlank(id)){
			b=bookService.getById(Long.valueOf(id));
		}
		model.addAttribute("book", b);
		return "bookadd";
	}
	

	@RequestMapping(value="/delete",method=RequestMethod.GET)
	public String deleteBook(String id){
		
		System.out.println(id);
		bookService.deleteBook(Long.valueOf(id));
		return "redirect:/read/bookList";
	}
}
