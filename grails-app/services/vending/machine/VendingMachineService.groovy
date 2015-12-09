package vending.machine

import grails.transaction.Transactional

@Transactional(rollbackFor = Throwable)
class VendingMachineService {

    def changeService

    def buy(Number id, List<String> money) {
        Product selectedProduct = Product.get(id)
        if(!selectedProduct || selectedProduct.qnt < 1){
            return [message: "Product not available" ]
        }
        Integer insertedAmount = money.sum { String denomination -> Change.conversionTable[denomination] }
        if(insertedAmount >= selectedProduct.price){
            selectedProduct.qnt--
            changeService.saveChange(money)
            return [product:selectedProduct, change: getChangeFor(insertedAmount-selectedProduct.price)]
        }else{
            return [missingAmount: (selectedProduct.price-insertedAmount) ]
        }
    }

    /*
    Found inspiration at http://putridparrot.com/blog/the-vending-machine-change-problem/
     */
    private Map<String,Integer> getChangeFor(Integer amount){
        List<Change> availableChange = Change.all.sort { Change a, Change b -> a.value.compareTo(b.value)}
        Map<String,Integer> returningChange = [:]
        if( amount>0 ){
            calculateReturningChange(availableChange, amount, returningChange)
        }
        return returningChange
    }

    private void calculateReturningChange(List<Change> availableChange, Integer amount, LinkedHashMap<String, Integer> returningChange) {
        if (availableChange && availableChange.sum { Change coin -> coin.value * coin.qnt } >= amount) {
            Change changeCandidate = availableChange.last()
            if (changeCandidate.qnt > 0 && changeCandidate.value <= amount) {
                int remainder = amount % changeCandidate.value
                if (remainder < amount) {
                    Integer candidateCount = Math.min(changeCandidate.qnt, ((amount - remainder) / changeCandidate.value).intValue())
                    returningChange[changeCandidate.denomination] = candidateCount
                    changeCandidate.qnt-=candidateCount
                    int amountLeft = amount - (candidateCount * changeCandidate.value)
                    if (amountLeft != 0) {
                        calculateReturningChange(availableChange,amountLeft, returningChange)
                    }
                }
            } else {
                availableChange.pop()
                calculateReturningChange(availableChange,amount, returningChange)
            }
        }else{
            throw new Exception("No change available")
        }
    }
}
