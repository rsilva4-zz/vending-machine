package vending.machine

import grails.transaction.Transactional

@Transactional
class ChangeService {

    def saveChange( List<String> suppliedChange) {
        suppliedChange.each {
            Change change = Change.findOrCreateByDenomination(it)
            change.qnt?change.qnt++:(change.qnt=1)
            change.save()
        }
    }
}
