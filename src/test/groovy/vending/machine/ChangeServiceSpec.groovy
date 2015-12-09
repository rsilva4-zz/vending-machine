package vending.machine

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(ChangeService)
@Mock([Change])
class ChangeServiceSpec extends Specification {

    void "test saving a list of denominations"() {
        when: "supplied a list of denominations, store the correspondent change"
        service.saveChange(['£2','20p','20p','5p','5p','33p'])

        then: "change must be stored"
        Change.findByDenomination('£2').qnt == 1
        Change.findByDenomination('20p').qnt == 2
        Change.findByDenomination('5p').qnt == 2
        !Change.findByDenomination('33p')
    }
}
