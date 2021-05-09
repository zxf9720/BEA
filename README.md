# BEA API
- This API is based on Spring Boot and MongoDB Cloud.

### Run the API
- Run 'com.view.market.bea.BeaApplication' as Spring Boot Application.

### Endpoints
- /api/v1/upload - upload a bulk data set
- /api/v1/stock/{stock} - query for data by stock ticker
- /api/v1/create - add a new record

### Design
- com.view.market.bea.BeaApplication is used for initializing SpringBoot application.
- com.view.market.bea.controller.RecordController is used for mapping API's endpoints and invoking services.
- com.view.market.bea.service.RecordService/com.view.market.bea.service.RecordServiceImpl are used for handling business logic, org.apache.commons.csv.CSVParser is used for processing csv file.
- com.view.market.bea.repository.RecordRepository extends MongoRepository to handle the CRUD operations in MongoDB.
- com.view.market.bea.model.StockRecord is the data model.
- properties for MongoDB Cloud are stored in application.properties.

### Resource
- .\BEA\resource\dow_jones_index.data - test data.
- .\BEA\resource\BEA_LOCAL.postman_collection.json - Postman Collection for testing.

