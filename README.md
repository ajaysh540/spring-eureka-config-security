# spring-eureka-config-security

Eureka server and config server configured on ports 8080 and 8081

configurations are on git repository

## Manager-Service 

Username and Password is manager/manager. Authorization is done using Basic auth. With every request Authorization header is needed to be passed. 

End Points: 
  - manager/
  - manager/employees
  - manager/addemployee(Post)
  - manager/updateemployee(PUT)
  - manager/deleteemployee(DELETE)
  
This service consumes Employee-service. 
Two profiles are added as Default and build.


## Employee-Service
Username and Password is employee/employee. Authorization is done using Basic auth. With every request Authorization header is needed to be passed. 

End Points: 
  - employee/
  - employee/employees
  - employee/addemployee(Post)
  - employee/updateemployee(PUT)
  - employee/deleteemployee(DELETE)
  
Provides data to manager service. Communication is through eureka client.



