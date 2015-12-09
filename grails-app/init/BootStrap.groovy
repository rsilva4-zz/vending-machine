import vending.machine.Change
import vending.machine.Product

class BootStrap {

    def init = { servletContext ->
        new Product(name: "Coca Kola", price: 125, qnt: 12 ).save()
        new Product(name: "Popsi", price: 120, qnt: 1 ).save()
        new Product(name: "8 Down", price: 155, qnt: 2 ).save()
        new Product(name: "FantaStic", price: 119, qnt: 3 ).save()
        new Product(name: "Leapton Tea", price: 99, qnt: 5 ).save()
        new Product(name: "POWater", price: 73, qnt: 0 ).save()

        new Change(denomination:'£2' ,qnt: 10).save()
        new Change(denomination:'£1' ,qnt: 10).save()
        new Change(denomination:'50p' ,qnt: 10).save()
        new Change(denomination:'20p' ,qnt: 10).save()
        new Change(denomination:'10p' ,qnt: 10).save()
        new Change(denomination:'5p' ,qnt: 10).save()
        new Change(denomination:'2p' ,qnt: 10).save()
        new Change(denomination:'1p' ,qnt: 10).save()
    }
    def destroy = {
    }
}
