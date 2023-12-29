# store-mgmt
Simple REST API that acts as a store management tool
- implemented using SpringBoot
- provides the following endpoints:
  - /api/store-mgmt/hello [GET]
  - /api/store-mgmt/add-product [POST]
  - /api/store-mgmt/{id} [PUT]
  - /api/store-mgmt/{id} [DELETE]
  - /api/store-mgmt/get-all-products [GET]
  - /api/store-mgmt/find-by-id/{id} [GET]
  - /api/store-mgmt/find-by-name/{name} [GET]
  - /api/store-mgmt/find-out-of-stock [GET]
  - /api/store-mgmt/change-price/{id} [PUT]


- The endpoints are accessible via basic authentication using username & password
- persistence layer implemented with CRUD Repository over H2 in-memory database
- tested using RestAssured