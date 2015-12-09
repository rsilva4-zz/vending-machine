package vending.machine

import grails.rest.Resource

@Resource(uri='/product', formats=['json', 'xml'])
class Product {

    String name
    Integer price
    Integer qnt

    static constraints = {
        name blank: false, unique: true
        price min: 1
        qnt min: 0
    }

}
