# Vending Machine exercise

> Design a vending machine using a programming language of your choice.
  The vending machine should perform as follows:
  * Once an item is selected and the appropriate amount of money is inserted, the vending machine should return the correct product.
  * It should also return change if too much money is provided, or ask for more money if insufficient funds have been inserted.
  * The machine should take an initial load of products and change. The change will be of denominations 1p, 2p, 5p, 10p, 20p, 50p, £1, £2.
  * There should be a way of reloading either products or change at a later point.
  * The machine should keep track of the products and change that it contains.

## How to

The machine is implemented as a Grails REST application. To run it execute the following commands in a Linux terminal:

```bash
curl -s get.sdkman.io | bash
source "$HOME/.sdkman/bin/sdkman-init.sh"
sdk install grails 3.0.10
git clone https://github.com/rsilva4/vending-machine.git
cd vending-machine
grails run-app
```

The machine should be used via a REST interface. All prices are in pence. The following list contains the available endpoints to execute the required actions

* `GET /product` - List products
* `POST /product` - Add new product
* `PUT /product` - Update existing product
* `DELETE /product` - Delete existing product

* `GET /change` - List available change
* `POST /change` - Add new denomination
* `PUT /change` - Update existing denomination
* `DELETE /change` - Delete existing denomination

* `PUT /product/buy` - Buy a product

The machine is initial load can be checked at Bootstrap.groovy.
