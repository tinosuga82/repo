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
//import com.utils.exceptions.PersonalizadaException;
import com.vo.ProductDetailVO;

@WebMvcTest(GetProductSimilar.class)
class BackendDevTestApplicationTests {
	@MockBean
	BackendDevTestService backendTestService;
	
	@Test
	public void test1() throws Exception
	{
		BackendDevTestService backendTestService=new BackendDevTestService();
		List<ProductDetailVO> list1 = new ArrayList<>();
		
		//list1.add(new ProductDetailVO("1","Shirt",9.99,true));
		list1.add(new ProductDetailVO("2","Dress",19.99,true));
		list1.add(new ProductDetailVO("3","Blazer",29.99,false));
		list1.add(new ProductDetailVO("4","Boots",39.99,true));
		
		Assert.assertEquals(list1.get(0).getName(),backendTestService.getProductSimilar(1).get(0).getName());
		Assert.assertEquals(list1.get(1).getName(),backendTestService.getProductSimilar(1).get(1).getName());
		Assert.assertEquals(list1.get(2).getName(),backendTestService.getProductSimilar(1).get(2).getName());
		
	}
	
	@Test
	public void test2() throws Exception
	{
		BackendDevTestService backendTestService=new BackendDevTestService();
		List<ProductDetailVO> list2 = new ArrayList<>();
		list2.add(new ProductDetailVO("3","Blazer",29.99,false));
		list2.add(new ProductDetailVO("100","Trousers",49.99,false));
		list2.add(new ProductDetailVO("1000","Coat",89.99,false));
		
		Assert.assertEquals(list2.get(0).getName(),backendTestService.getProductSimilar(2).get(2).getName());
		Assert.assertEquals(list2.get(1).getName(),backendTestService.getProductSimilar(2).get(0).getName());
		Assert.assertEquals(list2.get(2).getName(),backendTestService.getProductSimilar(2).get(1).getName());
	}
	
	@Test
	public void test3() throws Exception
	{
		BackendDevTestService backendTestService=new BackendDevTestService();
		
		List<ProductDetailVO> list3 = new ArrayList<>();
		list3.add(new ProductDetailVO("100","Trousers",49.99,false));
		list3.add(new ProductDetailVO("1000","Coat",89.99,true));
		list3.add(new ProductDetailVO("10000","Leather jacket",89.99,true));
		
		Assert.assertEquals(list3.get(0).getName(),backendTestService.getProductSimilar(3).get(0).getName());
		Assert.assertEquals(list3.get(1).getName(),backendTestService.getProductSimilar(3).get(1).getName());
		Assert.assertEquals(list3.get(2).getName(),backendTestService.getProductSimilar(3).get(2).getName());
		
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
	
}
