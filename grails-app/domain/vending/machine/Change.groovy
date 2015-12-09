package vending.machine

import grails.rest.Resource

@Resource(uri='/change', formats=['json', 'xml'])
class Change {
    String denomination
    Integer qnt

    static def conversionTable =  ['1p':1, '2p':2, '5p':5, '10p':10, '20p':20, '50p':50, '£1':100, '£2':200]

    static constraints = {
        denomination inList: ['1p', '2p', '5p', '10p', '20p', '50p', '£1', '£2'], unique: true
        qnt min: 0
    }

    Integer getValue() {
        conversionTable[denomination]
    }

}
