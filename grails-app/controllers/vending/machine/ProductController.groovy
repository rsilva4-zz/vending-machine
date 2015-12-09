package vending.machine

import grails.rest.RestfulController

class ProductController extends RestfulController{

    static responseFormats = ['json', 'xml']
    def vendingMachineService

    ProductController(){
        super(Product)
    }

    def buy(Integer productId, String change){
        def result
        try{
            result = vendingMachineService.buy(productId,change.split(',').toList())
        }catch(e){
            result = ["message": "No change available, please insert the exact amount"]
        }
        respond result

    }
}
