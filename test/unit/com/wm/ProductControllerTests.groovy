package com.wm



import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(ProductController)
class ProductControllerTests {

	void  testSearchProductbyProductType(){
		Product product  = new Product(productRef: 'product_ref' , productType : 'otc' , amount :0 , isTradedOverExch : false  )
		def listOfProducts = [product]
		params.productType  = product.productType

		def mockProductService = mockFor(ProductService)
		mockProductService.demand.searchByProductType(){String a  ->
			assert a == product.productType
			listOfProducts
		}

		controller.productService = mockProductService.createMock();
		

		controller.searchProductByProductType()
		mockProductService.verify()

		assert view : "/product/searchView"
		assert  model.searchProductResult  == listOfProducts
	}
	
	
	void testCreateProductService(){
		
		params.productRef = 'product_ref'
		params.productType = 'future'
		params.amount  = 0
		params.isTradedOverExch = false
		
		def mockProductService = mockFor(ProductService)
		mockProductService.demand.createProduct(){Map a -> 
			assert a.productRef ==  params.productRef
			assert a.amount  == 0
			'product_ref'
		}
		
		controller.productService = mockProductService.createMock()
		
		controller.createProduct()
		mockProductService.verify()
		
		assert response.redirectUrl == "/product/show/${params.productRef}" 
		
		//Product product  = new Product(productRef: 'product_ref' , productType : 'otc' , amount :0 , isTradedOverExch : false  )
		
	}
		
}
