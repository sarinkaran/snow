package com.wm

class Product {
	String productRef
	String productType
	int amount
	boolean isTradedOverExch
	static hasMany = [trades : Trade]


	static constraints = {
		amount(validator:{ val , obj ->
			if(obj.isTradedOverExch && val ==0){
				return "Product when traded over exchange can not have 0 amount"
			}
		})
		
		productRef blank:false 
	}
}


