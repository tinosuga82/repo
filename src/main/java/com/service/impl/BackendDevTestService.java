package com.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.google.gson.Gson;
import com.service.IBackendDevTestService;
import com.utils.exceptions.PersonalizadaException;
import com.vo.MensajeVO;
import com.vo.ProductDetailVO;

import reactor.core.publisher.Flux;

@Service
public class BackendDevTestService implements IBackendDevTestService{
	static Logger logger = LoggerFactory.getLogger(BackendDevTestService.class);
	
	//@Autowired
	//WebClientService webClientService; 
	
	@Override
	public List<ProductDetailVO> getProductSimilar(Integer productId) throws Exception {
		//SERVICIO PRINCIPAL QUE SE ENCARGA DE TODO
		
		RestTemplate restTemplate = new RestTemplate();
		List<Integer> productsId=getIds(productId);
		List<ProductDetailVO> lista_prod = new ArrayList<ProductDetailVO>();
		logger.info("Linea 31");
		//List<Integer> listProductsId = productsId.collectList().block();
		//productsId.collectList().block();
		logger.info("Linea 33");
		//List<Integer> listProductsId = productsId.collectList().block();
		for (Integer product : productsId) {
			logger.info("Get products [id]: "+product);
			Gson g = new Gson();  
			try {
				WebClient productVal = WebClient.create("http://localhost:3001/product/"+product);
				Flux<ProductDetailVO> response = productVal.get().retrieve().bodyToFlux(ProductDetailVO.class);
				List<ProductDetailVO> producto = response.collectList().block();
				//ProductDetailVO prod = g.fromJson(restTemplate.getForObject("http://localhost:3001/product/"+product, String.class), ProductDetailVO.class);
				//ProductDetailVO prod = webClientService.getProducto(product);
				logger.info("http://localhost:3001/product/"+product);
				//if (prod!=null) lista_prod.add(prod);
				if (producto.get(0)!=null) lista_prod.add(producto.get(0));
			} catch (Exception e) {
				MensajeVO mensaje = getMensaje(product);
				logger.info("Linea 42");
				if (mensaje.equals("Product not found")) throw new PersonalizadaException("Product not found");
			}
		}
		if (lista_prod.isEmpty()) throw new PersonalizadaException("Not found");
		return lista_prod;
	}
	
	public List<Integer> getIds(Integer productId) throws Exception {
		//FUNCION QUE SE ENCARGA DE OBTENER EL ARRAY DE ID'S DE PRODUCTOS SIMILARES
		
		//RestTemplate restTemplate = new RestTemplate();
		try {
			logger.info("Linea 54:  http://localhost:3001/product/"+productId+"/similarids");
			//ResponseEntity<Integer[]> response =restTemplate.getForEntity("http://localhost:3001/product/"+productId+"/similarids", Integer[].class);
			WebClient productsId = WebClient.create("http://localhost:3001/product/"+productId+"/similarids");
			logger.info("Linea 56");
			Flux<Integer> response = productsId.get().retrieve().bodyToFlux(Integer.class);
			
			/*List<Integer> response = Arrays.asList(WebClient.builder()
						.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build().get()
						.uri("http://localhost:3001/product/"+productId+"/similarids").retrieve()
						.bodyToMono(Integer[].class).block());
						//.defaultIfEmpty(ResponseEntity.notFound().build());*/
					//.switchIfEmpty(Mono.error(new PersonalizadaException("Not found")))
			//List<Integer> response = webClientService.getListProductsId(productId);		
			logger.info("Linea 58");
			//if () response is vacio  throw new PersonalizadaException("Not found");
				List<Integer> listProductsId = response.collectList().block();
			//List<Integer> listProductsId=Arrays.asList(response.getBody());
			logger.info("Linea 61");
			//return response;
			return listProductsId;
		} catch (Exception e) {
			throw new PersonalizadaException("Not found");
		}
	}
	
	//FUNCION PARA OBTENER EL MENSAJE EN CASO DE QUE FALTEn DETALLES DE UN PRODCUTCO 
	public MensajeVO getMensaje(Integer product) throws Exception {
		RestTemplate restTemplate = new RestTemplate();
		Gson g = new Gson();
		try {
			logger.info("Linea 70");
			MensajeVO mensa = g.fromJson(restTemplate.getForObject("http://localhost:3001/product/"+product, String.class), MensajeVO.class);
			logger.info("Linea 72");
			return mensa;
		} catch (Exception e) {
			logger.info("Linea 75");
			if (e.getMessage().substring(0,3).equals("404")) throw new PersonalizadaException("Product not found");
			else throw new Exception();
		}
	}
}
