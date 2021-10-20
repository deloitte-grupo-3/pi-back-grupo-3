package br.com.exlivraria.services;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import br.com.exlivraria.converter.mocks.MockBook;
import br.com.exlivraria.data.model.Book;
import br.com.exlivraria.data.vo.v1.BookVO;
import br.com.exlivraria.repository.BookRepository;

@RunWith(SpringJUnit4ClassRunner.class)
public class BookServiceTest {

	
	@Autowired
	BookRepository bookRepository;
	
	BookService bookService;
	
	Book book;
	
	BookVO bookVO;
	
	 @Before
	    public void setUp() { //Arrange
		    MockitoAnnotations.initMocks(this);
		 	MockBook mockBook = new MockBook();
		 	bookVO = mockBook.mockVO();
		 	book = mockBook.mockEntity();
	        Mockito.when(bookRepository.save(book)).thenReturn(null);
	        bookService  = new BookService();
	    }
	 
	 @Test
	 public void shouldNotCreateBook() {
		 /* Arrange - setar para fazer a ação
		  * Act -  bookService.create neste caso
		  * Assert - verificação dos resultados do teste
		  */	
		 var bookServiceResult = bookService.create(bookVO);
		 Assert.assertNull(bookServiceResult);
		 
	}
	 
	 
	 
	
}
