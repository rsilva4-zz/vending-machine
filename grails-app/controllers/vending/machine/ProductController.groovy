package vending.machine

import grails.rest.RestfulController

class ProductController extends RestfulController{

    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", patch: "PATCH", delete: "DELETE", buy:"PUT"]
    def vendingMachineService

    ProductController(){
        super(Product, false)
    }

    def buy(){
        def result
        try{
            result = vendingMachineService.buy(request.JSON.productId,request.JSON.change)
        }catch(NoChangeException e){
            result = ["message": "No change available, please insert the exact amount"]
        }
        respond result

    }
}
