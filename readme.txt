
config 9000
trace 9001
order 9002
product 9003
getway 9004

URLs: 
http://localhost:9000/config/getConfig
http://localhost:9002/order/getProduct\
http://localhost:9003/product/getProduct


Use Case UML Code 

@startuml
participant U as "USER"
participant G as "GETWAY-MS \nJWT SECURE"
participant P as "PRODUCT-MS"
participant O as "ORDER-MS"
participant C as "CONFIG-MS"
participant T as "TRACE-MS"

U -> G : getProduct \n   or \ngetProductWithDetails
G -> C : get Product service api url
C -> G : productapiUrl

alt If getProductWithDetails
    G -> P : getProduct \nADDITIONAL_DATA = true
else getProduct
    G-> P : getProduct \nADDITIONAL_DATA = false
end

P -> C : get Order service api url
C -> P : orderApiUrl

P -> O : getProduct withAdditionalDataFlag
O -> O : Get Product from DB

alt If AdditionalData Flag present
    O -> O : getOrders from DB
end

O -> C : getTraceApiUrl
C -> O : TraceApiUrl
O -> T : put log to trace api
O -> P : Result


P -> C : getTraceApiUrl
C -> P : TraceApiUrl
P -> T : put log to trace api
P -> G : Result

G -> C : getTraceApiUrl
C -> G : TraceApiUrl
G -> T : put log to trace api

G -> U : Result
@enduml