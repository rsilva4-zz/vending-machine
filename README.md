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

### `GET /product` - List products

Curl sample:
```bash
curl -X GET -H "Content-Type: application/json" 'http://localhost:8080/product'
```

### `POST /product` - Add new product

Json body:
```json
{
  "name": "A name",
  "price":120,
  "qnt": 10
}
```

Curl sample:
```bash
curl -X POST -H "Content-Type: application/json" -d '{   
    "denomination": "£2",
    "qnt": 10
  }' 'http://localhost:8080/change'
```

### `PUT /product/{id}` - Update existing product

Json body:
```json
{
  "name": "A name",
  "price":120,
  "qnt": 10
}
```

Curl sample:
```bash
curl -X PUT -H "Content-Type: application/json" -d '{   
    "qnt": 1000
  }' 'http://localhost:8080/change/1'
```

### `DELETE /product/{id}` - Delete existing product

Curl sample:
```bash
curl -X DELETE -H "Content-Type: application/json" -d '{   
    "qnt": 1000
  }' 'http://localhost:8080/product/1'
```

### `GET /change` - List available change

Curl sample:
```bash
curl -X GET -H "Content-Type: application/json" 'http://localhost:8080/change'
```

### `POST /change` - Add new denomination

Json body:
```json
{
  "denomination": "£2",
  "qnt": 10
}
```

Curl sample:
```bash
curl -X POST -H "Content-Type: application/json" -d '{   
    "denomination": "£2",
    "qnt": 10
  }' 'http://localhost:8080/change'
```

### `PUT /change/{id}` - Update existing denomination

Json body:
```json
{
  "qnt": 1000
}
```

Curl sample:
```bash
curl -X PUT -H "Content-Type: application/json" -d '{   
    "qnt": 1000
  }' 'http://localhost:8080/change/1'
```

### `DELETE /change/{id}` - Delete existing denomination

Curl sample:
```bash
curl -X DELETE -H "Content-Type: application/json" -d '{   
    "qnt": 1000
  }' 'http://localhost:8080/change/1'
```

### `PUT /product/buy` - Buy a product

Json body:
```json
{
    "productId":1,
    "change":["£1","2p","2p","20p","10p"]
}
```

Curl sample:
```bash
curl -X PUT -H "Content-Type: application/json" -d '{
    "productId":1,
    "change":["£1","2p","2p","20p","10p"]
}' 'http://localhost:8080/product/buy'
```

The machine is initial load can be checked at Bootstrap.groovy.
