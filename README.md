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
* Login system
  * Authentication: POST /api/v1/token/access/
    * Refresh: POST /api/v1/token/refresh/
* Users functionality
  * Retrieve users: GET /api/v1/user/
  * Retrieve user: GET /api/v1/user/:id/
  * User registration: POST /api/v1/user/
  * Update user: PUT /api/v1/user/:id/
  * Delete user: DELETE /api/v1/user/:id/
* Product functionality
* Cart functionality
* Order functionality
