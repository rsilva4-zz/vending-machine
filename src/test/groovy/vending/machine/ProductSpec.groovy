package vending.machine

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Product)
class ProductSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "Name must be set and be unique"() {
        when: 'The name is null'
        Product p = new Product(name: null, price:1, qnt: 0)

        then: 'validation should fail'
        !p.validate()

        when: 'The name is blank'
        p = new Product(name: '', price:1, qnt: 0)

        then: 'validation should fail'
        !p.validate()

        when: 'The name is a string'
        p = new Product(name: 'Fat Cola', price:1, qnt: 0)

        then: 'validation should pass'
        p.validate()

        /* TODO: still don't know how to do this check in Grails 3.X
        when: 'The name is duplicated'
        Product p1 = new Product(name: "Duality Ale", price:1, qnt: 0)
        Product p2 = new Product(name: "Duality Ale", price:1, qnt: 0)
        p1.save()


        then: 'validation should fail'
        !p2.validate()
        */

    }

    void "Price must be a positive value"() {
        when: 'The price is negative'
        Product p = new Product(name: '8 Down', price: -4, qnt: 0)

        then: 'validation should fail'
        !p.validate()

        when: 'The price is zero'
        p = new Product(name: 'Popsi', price: 0, qnt: 0)

        then: 'validation should fail'
        !p.validate()

        when: 'The price is null'
        p = new Product(name: 'Popsi', price: null, qnt: 0)

        then: 'validation should fail'
        !p.validate()

        when: 'The price is a positive value greater than 0'
        p = new Product(name: 'Popsi', price: 1, qnt: 0)

        then: 'validation should pass'
        p.validate()

    }

    void "Quantity should be a positive integer value or zero"() {
        when: 'The qnt is negative'
        Product p = new Product(name: 'FantaStic', price: 2, qnt: -1)

        then: 'validation should fail'
        !p.validate()

        when: 'The qnt is null'
        p = new Product(name: 'FantaStic', price: 1)

        then: 'validation should fail'
        !p.validate()

        when: 'The qnt is zero'
        p = new Product(name: 'FantaStic', price: 1, qnt: 0)

        then: 'validation should pass'
        p.validate()

        when: 'The qnt is a positive integer'
        p = new Product(name: 'FantaStic', price: 1, qnt: 2)

        then: 'validation should pass'
        p.validate()

    }
}
