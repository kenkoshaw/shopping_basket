# shopping_basket

# Setting Up
- Download the repo and open in Intellij or other IDE
- Download scala plugin in IDE and load SDK
- Right click src/main/scala/ShoppingBasket -> click 'Run ShoppingBasket'
- should see "Please place your shopping basket: " in the terminal

# Command options
PriceBasket Item1 Item2 Item3 ...
- get back subtotal, offers and total of basket
- ex. 'PriceBasket Apples Soup Soup Bread'

addProduct product cost
- add a new product to the store or update an existing product (cost in pence)
- ex. 'addProduct Apples 130' (Apples new cost = £1.30)

removeProduct product
- remove a product from the store
- ex. 'removeProduct Apples'

addOffer product coProduct coProductCount discount 
- add a new Offer to the store or update an existing Offer 
- discount in decimal. e.g. 0.4 = 40% off, 1.0 = 100% off
- if coProductCount is 0 this means this discount has no requirement
- ex. 'addOffer Apples Milk 2 0.5' (buy 2 Milk get 1 apple 50% off)
- ex. 'addOffer Apples Apples 3 1.0' (buy 3 apples get 1 apple free)
- ex. 'addOffer Apples Apples 0 0.2' (apples 20% off)

removeOffer item
- remove an Offer from the store
- ex. 'removeOffer Apples'

exit
- stops run (leaves shop)

# Objects/Classes
- can easily change the default products and offers available in the src/main/scala/Offers.scala and src/main/scala/Products.scala in the 'offers' & 'products' Maps.
- src/main/test holds the unit tests

# Possible improvements
- as of now modified products and offers lists during runtime will not persist over multiple runs, 
could store the product and offer data in a file which can be updated and read to persist the data, or use databases/tables in a real scenario.
- limit to one offer per product at one time, could have a deeper map allowing products to have multiple offers and then select the best offer for the customer's basket.
- authentication for input (password) so only managers can update products and offers (access above add/remove commands)
- intellij terminal shows '£' as '?' so used '$' instead but context still in GBP, could add some currency class dependency to handle use of different currencies.