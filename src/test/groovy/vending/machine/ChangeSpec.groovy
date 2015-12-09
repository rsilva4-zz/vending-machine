package vending.machine

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Change)
class ChangeSpec extends Specification {


    void "denomination must be one of 1p, 2p, 5p, 10p, 20p, 50p, £1 or £2"() {
        when: 'The denomination is not in the specified list'
        Change c = new Change(denomination: '3p', qnt: 0)

        then: 'validation should fail'
        !c.validate()

        when: 'The denomination is null'
        c = new Change(denomination: null, qnt: 0)

        then: 'validation should fail'
        !c.validate()

        when: 'The denomination is blank'
        c = new Change(denomination: '', qnt: 0)

        then: 'validation should fail'
        !c.validate()

        when: 'The denomination is in the specified list'
        Change c1 = new Change(denomination: '1p', qnt: 0)
        Change c2 = new Change(denomination: '2p', qnt: 0)
        Change c5 = new Change(denomination: '5p', qnt: 0)
        Change c10 = new Change(denomination: '10p', qnt: 0)
        Change c20 = new Change(denomination: '20p', qnt: 0)
        Change c50 = new Change(denomination: '50p', qnt: 0)
        Change c1L = new Change(denomination: '£1', qnt: 0)
        Change c2L = new Change(denomination: '£2', qnt: 0)

        then: 'validation should pass'
        c1.validate()
        c2.validate()
        c5.validate()
        c10.validate()
        c20.validate()
        c50.validate()
        c1L.validate()
        c2L.validate()

    }


    void "Quantity should be a positive integer value or zero"() {
        when: 'The qnt is negative'
        Change c = new Change(denomination: '2p', qnt: -1)

        then: 'validation should fail'
        !c.validate()

        when: 'The qnt is null'
        c = new Change(denomination: '2p')

        then: 'validation should fail'
        !c.validate()

        when: 'The qnt is zero'
        c = new Change(denomination: '2p', qnt: 0)

        then: 'validation should pass'
        c.validate()

        when: 'The qnt is a positive integer'
        c = new Change(denomination: '2p', qnt: 2)

        then: 'validation should pass'
        c.validate()

    }

    void "getValue for a a change must return the decimal value of the denomination"() {
        when: 'asked for, the value of the denomination must correspond'
        Change c1 = new Change(denomination: '1p', qnt: 0)
        Change c2 = new Change(denomination: '2p', qnt: 0)
        Change c5 = new Change(denomination: '5p', qnt: 0)
        Change c10 = new Change(denomination: '10p', qnt: 0)
        Change c20 = new Change(denomination: '20p', qnt: 0)
        Change c50 = new Change(denomination: '50p', qnt: 0)
        Change c1L = new Change(denomination: '£1', qnt: 0)
        Change c2L = new Change(denomination: '£2', qnt: 0)

        then: 'validation should pass'
        c1.value == 1
        c2.value == 2
        c5.value == 5
        c10.value == 10
        c20.value == 20
        c50.value == 50
        c1L.value == 100
        c2L.value == 200
    }
}
