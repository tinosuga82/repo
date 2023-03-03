package com;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.controller.GetProductSimilar;
import com.service.impl.BackendDevTestService;
import com.utils.exceptions.PersonalizadaException;
import com.vo.ProductDetailVO;

//@SpringBootTest
@WebMvcTest(GetProductSimilar.class)
class BackendDevTestApplicationTests {
	
	//private GetProductSimilar backendTestController = new GetProductSimilar();
	//private BackendDevTestService backendTestService = new BackendDevTestService();
	
	@MockBean
	BackendDevTestService backendTestService;
	
	//@Autowired
	//private WebApplicationContext wac;
	
	//@Autowired
	//private MockMvc mockMvc;

	ProductDetailVO prod1= new ProductDetailVO("3","Blazer",29.99,false); 
	@Before
	public void setUp() 
	{
		//backendTestService = Mockito.mock(BackendDevTestService.class);
		//backendTestController = new GetProductSimilar();
		//this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}
	
	/*@Test
	void contextLoads() {
	}*/
	
	@Test
	public void backendService() throws Exception
	{
		BackendDevTestService backendTestService=new BackendDevTestService();
		
		List<ProductDetailVO> listVO = new ArrayList<>();
		listVO.add(new ProductDetailVO("1","Blazer",29.99,false));
		listVO.add(new ProductDetailVO("2","Trousers",49.99,false));
		listVO.add(new ProductDetailVO("3","Coat",89.99,true));
		
		Assert.assertEquals(listVO.get(0).getName(),backendTestService.getProductSimilar(2).get(0).getName());
		Assert.assertEquals(listVO.get(1).getName(),backendTestService.getProductSimilar(2).get(1).getName());
		Assert.assertEquals(listVO.get(2).getName(),backendTestService.getProductSimilar(2).get(2).getName());
		
		//Mockito.when(backendTestService.getProductSimilar(2).get(0).getName())
		//.thenReturn("Blazer");
			//.thenReturn(prod1);

		//String response = mockMvc.perform(get("/product" + "/{id}", 2))
		//      .andExpect(status().is(HttpStatus.OK.value()))
		//        .andExpect(jsonPath("$.id", is(2)))
		//        .andReturn().getResponse()
		//        .getContentAsString();
        //System.out.println(response);
        //AppLogger.info(response, GetProductSimilar.class);
	}
	
	@Test
	public void backendServiceDos() throws PersonalizadaException
	{
		
	}
}
