package com.wm

class ProductService {

	
    def searchByProductType(String productType) {
		return Product.createCriteria().list { eq ('productType' , productType)  }
    }
		
}

