package com.wm

class ProductController {

    def index() { }
	
	
    ProductService productService
	
	
	def searchProductByProductType(){
		def listOfProducts = productService.searchByProductType(params.productType)
		render view : "/searchView" , model : [searchProductResult : listOfProducts] 
		
    }
	
	def createProduct(){
		def productRef = productService.createProduct(params)
		redirect(action: "show" , id: productRef)
	}
	
	def show(){
		
	}
		
}
