package com.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.service.IBackendDevTestService;
import com.utils.exceptions.PersonalizadaException;
import com.utils.exceptions.PersonalizadaExceptionDos;
import com.vo.MensajeVO;
import com.vo.ProductDetailVO;

import reactor.core.publisher.Flux;

@Service
public class BackendDevTestService implements IBackendDevTestService{
	static Logger logger = LoggerFactory.getLogger(BackendDevTestService.class);
	
	@Override
	public List<ProductDetailVO> getProductSimilar(Integer productId) throws Exception {
		List<ProductDetailVO> lista_prod = new ArrayList<ProductDetailVO>();
		try {
			logger.info("Llamada: http://localhost:3001/product/"+productId+"/similarids");
			WebClient productsId = WebClient.create("http://localhost:3001/product/"+productId+"/similarids");
			List<Integer> listProductsId = productsId.get().retrieve().bodyToFlux(Integer.class).collectList().block();
			listProductsId.parallelStream().forEach(p->{
				try {
					logger.info("Llamada: http://localhost:3001/product/"+p);
					WebClient product = WebClient.create("http://localhost:3001/product/"+p);
					Flux<ProductDetailVO> response = product.get().retrieve().bodyToFlux(ProductDetailVO.class);
					lista_prod.add(response.collectList().block().get(0));
				} catch (Exception e) {
					try {
						WebClient productB = WebClient.create("http://localhost:3001/product/"+p);
						Flux<MensajeVO> responseB = productB.get().retrieve().bodyToFlux(MensajeVO.class);
						List<MensajeVO> mensa = responseB.collectList().block();
						if (mensa.get(0).getMessage().equals("Product not found")) throw new PersonalizadaException("Product not found");
					} catch (Exception e2) {
						if (e2.getMessage().contains("404")) throw new PersonalizadaException("Product not found");
						else throw new PersonalizadaExceptionDos("HTTP ERROR 500");
					}
				}
			});
		} catch (Exception e0) {
			if (e0.getCause()!=null&&e0.getCause().getMessage().contains("Product not found")) throw new PersonalizadaException("Product not found");
			else if (e0.getMessage()!=null&& e0.getMessage().contains("404"))throw new PersonalizadaException("Not found");
			else throw new PersonalizadaExceptionDos("HTTP ERROR 500");
		}
		if (lista_prod.isEmpty()) throw new PersonalizadaException("Not Found");
		Collections.sort(lista_prod, (ProductDetailVO p1, ProductDetailVO p2) -> p1.getId().compareTo(p2.getId() ) );
		return lista_prod;
	}
	
}
