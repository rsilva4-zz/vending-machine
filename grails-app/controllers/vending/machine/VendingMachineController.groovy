package vending.machine

class VendingMachineController {

    static responseFormats = ['json', 'xml']
    static allowedMethods = [buy:"PUT"]
    def vendingMachineService

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
