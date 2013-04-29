
package com.wm

import spock.lang.Specification;

import grails.test.mixin.*;


@TestFor(ProductService)
@Mock(Product)
class ProductServiceSpec extends Specification {
	
	void "searchByProductType () should return '#count' results for query '#query'" (){
		given :
		Product product1  = new Product(productRef: 'product_ref' , productType : 'otc' , amount :0 , isTradedOverExch : false  ).save()
		Product product2  = new Product(productRef: 'product_ref' , productType : 'otc' , amount :0 , isTradedOverExch : false  ).save()
		Product product3  = new Product(productRef: 'product_ref' , productType : 'future' , amount :0 , isTradedOverExch : false  ).save()
		
		expect :
		count  == service.searchByProductType(query).size()
		
		where :
		count | query
		2	  | 'otc'
		0	  | 'null'
		1     | 'future'
		
	}
		
}
		
		
		
		
		
		
	
	

