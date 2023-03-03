package com.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.service.IBackendDevTestService;
import com.vo.ProductDetailVO;

@RestController()
@RequestMapping("")
public class GetProductSimilar {
	
	static Logger logger = LoggerFactory.getLogger(GetProductSimilar.class);	
	
	@Autowired
	private IBackendDevTestService backendTestDevService;
	
	//@CrossOrigin(origins = "http://localhost:${server.port}")
	@Cacheable("products")
	@GetMapping("/product/{productId}/similar")
	public List<ProductDetailVO> getProductSimilar(@PathVariable("productId") Integer productId) throws Exception {
		logger.info("Get similar products: "+productId);
		return backendTestDevService.getProductSimilar(productId);
	}
}
