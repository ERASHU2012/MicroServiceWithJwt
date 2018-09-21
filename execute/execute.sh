#!/bin/sh
CONFIG_LOG_FILE='config.log'
TRACE_LOG_FILE='trace.log'
ORDER_LOG_FILE='order.log'
PRODUCT_LOG_FILE='product.log'
GETWAY_LOG_FILE='getway.log'

# build tha application 
echo "Going to build the project"
mvn clean install
echo "Project build successfully"
mkdir -p logs

echo ''
#start config MS
echo 'Going to start config ms at port 9000'
java -jar ../config/target/config-1.0.0.jar > logs/${CONFIG_LOG_FILE} &
echo 'check' ${CONFIG_LOG_FILE} 'file for more details'
echo ''

# start trace MS
echo 'Going to start trace ms at port 9001'
java -jar ../trace/target/trace-1.0.0.jar > logs/${TRACE_LOG_FILE} &
echo 'check' ${TRACE_LOG_FILE} 'file for more details'
echo ''

# start orderorder MS
echo 'Going to start order ms at port 9002'
java -jar ../order/target/order-1.0.0.jar > logs/${ORDER_LOG_FILE} &
echo 'check' ${ORDER_LOG_FILE} 'file for more details'
echo ''


# start product MS
echo 'Going to start product ms at port 9003'
java -jar ../product/target/product-1.0.0.jar > logs/${PRODUCT_LOG_FILE} &
echo 'check' ${PRODUCT_LOG_FILE} 'file for more details'
echo ''

# start getway MS
echo 'Going to start getway ms at port 9004'
java -jar ../getway/target/getway-1.0.0.jar > logs/${GETWAY_LOG_FILE} &
echo 'check' ${GETWAY_LOG_FILE} 'file for more details'
echo ''

