package com.service;

import java.util.List;

import com.vo.ProductDetailVO;

public interface IBackendDevTestService {
	List<ProductDetailVO> getProductSimilar(Integer productId) throws Exception;
}
