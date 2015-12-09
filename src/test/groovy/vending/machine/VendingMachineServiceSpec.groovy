package vending.machine

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(VendingMachineService)
@Mock([Product,Change,ChangeService])
class VendingMachineServiceSpec extends Specification {

    def setup() {
        Product p = new Product(name: "Super Beer", price: 245, qnt:2)
        p.save()
    }

    def cleanup() {
    }

    void "Buying stuff"() {

        when: 'choosing something that exist and inserting the inferior amount of money'
        def productId = Product.findByName("Super Beer").id
        def result = service.buy(productId,['£2','20p'])

        then: 'should ask for more money'
        result.missingAmount == 25
        Product.findByName("Super Beer").qnt == 2
        !Change.findByDenomination('£2')
        !Change.findByDenomination('20p')

        when: 'choosing something that exist and inserting the exact amount of money'
        result = service.buy(productId,['£2','20p','20p','5p'])

        then: 'product must be served'
        Product.findByName("Super Beer").qnt == 1
        result.product == Product.get(productId)
        result.change == [:]
        Change.findByDenomination('£2').qnt == 1
        Change.findByDenomination('20p').qnt == 2
        Change.findByDenomination('5p').qnt == 1

        when: 'choosing something that exist and inserting the superior amount of money with available change'
        result = service.buy(productId,['£2','50p'])

        then: 'product must be served'
        Product.findByName("Super Beer").qnt == 0
        result.product == Product.get(productId)
        result.change == ['5p':1]
        Change.findByDenomination('£2').qnt == 2
        Change.findByDenomination('20p').qnt == 2
        Change.findByDenomination('50p').qnt == 1
        Change.findByDenomination('5p').qnt == 0

        when: 'choosing something that do not exist'
        result = service.buy(productId,['£2','20p','20p','5p'])

        then: 'message to select another product'
        Product.findByName("Super Beer").qnt == 0
        result.message == "Product not available"
        Change.findByDenomination('£2').qnt == 2
        Change.findByDenomination('20p').qnt == 2
        Change.findByDenomination('50p').qnt == 1
        Change.findByDenomination('5p').qnt == 0

        /*when: 'choosing something that exist and inserting the superior amount of money but no change available'
        service.buy("Super Beer",['£2','£1'])

        then: 'no change should be available'
        thrown(Exception)
        Product.findByName("Super Beer").qnt == 1
        Change.findByDenomination('£2').qnt == 1
        Change.findByDenomination('20p').qnt == 2
        Change.findByDenomination('5p').qnt == 1
        */

    }

    void "Crazy stuff"() {

        when: 'inserting no money'
        def productId = Product.findByName("Super Beer").id
        def result = service.buy(productId, [])

        then: 'should ask for more money'
        result.missingAmount == 245
        Product.findByName("Super Beer").qnt == 2
    }
}
