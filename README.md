## Mukhitdinov Amirlan, ITSE1909-R

### Internet shop

**Description**

System based on user - role mechanism. Each role can only interact with the entities available to it:
* Administrator - has full access to the system. Administrator also can create / delete / change other users.
* Manager - has limited access to the system - can interact with all orders, carts, products and see users information.
* Customer - can interact with his orders, carts and products

**Entities**
* User
* Role
* Product
* Cart
* Order

**Functionality (Endpoints)**
* Login system (TODO)
  * <code>POST /api/v1/token/access</code> - auth
  * <code>POST /api/v1/token/refresh</code> - refresh 
* Users functionality
  * <code>GET /api/v1/user</code> - retrieve users 
  * <code>GET /api/v1/user/:id</code> - retrieve user 
  * <code>POST /api/v1/user</code> - user registration
  * <code>PUT /api/v1/user/:id</code> - update user 
  * <code>DELETE /api/v1/user/:id</code> - delete user
* Product functionality
* Cart functionality
* Order functionality
