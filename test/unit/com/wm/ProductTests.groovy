package com.wm



import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Product)
class ProductTests {

	void testProductAttributes(){
		Product product  = new Product(productRef: 'product_ref' , productType : 'otc' , amount :0 , isTradedOverExch : false  )
		Assert.assertArrayEquals([
			product.productRef ,
			product.productType ,
			product.amount ,
			product.isTradedOverExch
		].toArray(),
		'product_ref' , 'otc' ,0,false)
	}

	void testProductTradedOverExchCanNothaveAmountZero(){
		mockForConstraintsTests(Product)

		Product product  = new Product(productRef: 'product_ref' , productType : 'otc' , amount :0 , isTradedOverExch : true)
		assert !product.validate()
		assert product.errors.getFieldError("amount").code == "Product when traded over exchange can not have 0 amount"
	}

	void testProductRefcanNotbeBlank(){
		mockForConstraintsTests(Product)
		Product product  = new Product(productRef: '' , productType : 'otc' , amount :122 , isTradedOverExch : true)
		assert !product.validate()
		assert "blank" == product.errors.productRef
	}



	void testProductCanHaveManyTradeObject(){
		mockDomain(Product)
		mockDomain(Trade)
		Trade trade  = new Trade()
		Trade trade2  = new Trade()
		
		def tradeList  = [trade , trade2]
				Product product  = new Product(productRef: '' , productType : 'otc' , amount :122 , isTradedOverExch : true , trades : tradeList)
		product.save()
		assert product.trades.size() ==2
	}

}


