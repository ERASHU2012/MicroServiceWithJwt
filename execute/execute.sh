#!/bin/sh
CONFIG_LOG_FILE='config.log'
TRACE_LOG_FILE='trace.log'
ORDER_LOG_FILE='order.log'
PRODUCT_LOG_FILE='product.log'
GETWAY_LOG_FILE='getway.log'

# build tha application 
echo "Going to build the project"
#mvn clean install
echo "Project build successfully"
mkdir -p logs

echo ''
#start config MS
echo 'Going to start config ms'
java -jar ../config/target/config-1.0.0.jar > logs/${CONFIG_LOG_FILE} &
echo 'check' ${CONFIG_LOG_FILE} 'file for more details'
echo ''

# start trace MS
echo 'Going to start trace ms'
java -jar ../trace/target/trace-1.0.0.jar > logs/${TRACE_LOG_FILE} &
echo 'check' ${TRACE_LOG_FILE} 'file for more details'
echo ''

# start order MS
echo 'Going to start order ms'
java -jar ../order/target/order-1.0.0.jar > logs/${ORDER_LOG_FILE} &
echo 'check' ${ORDER_LOG_FILE} 'file for more details'
echo ''


# start product MS
echo 'Going to start config ms'
java -jar ../product/target/product-1.0.0.jar > logs/${PRODUCT_LOG_FILE} &
echo 'check' ${PRODUCT_LOG_FILE} 'file for more details'
echo ''

# start getway MS
echo 'Going to start config ms'
java -jar ../getway/target/getway-1.0.0.jar > logs/${GETWAY_LOG_FILE} &
echo 'check' ${GETWAY_LOG_FILE} 'file for more details'
echo ''

