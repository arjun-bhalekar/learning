# Some of these URLs may be complex to write by hand:

# Original Service URLs :
URL for currency-exchange service 
- http://localhost:8000/currency-exchange/from/USD/to/INR
URL for currency-conversion service
- http://localhost:8100/currency-conversion/from/USD/to/INR/quantity/10
- http://localhost:8100/currency-conversion-feign/from/USD/to/INR/quantity/10


# API Gateway URLs :
URL for currency-exchange service 
- http://localhost:8765/CURRENCY-EXCHANGE/currency-exchange/from/USD/to/INR
URL for currency-conversion service
- http://localhost:8765/CURRENCY-CONVERSION/currency-conversion/from/USD/to/INR/quantity/10
- http://localhost:8765/CURRENCY-CONVERSION/currency-conversion-feign/from/USD/to/INR/quantity/10

# lower case URLs after : spring.cloud.gateway.discovery.locator.lower-case-service-id=true
- http://localhost:8765/currency-exchange/currency-exchange/from/USD/to/INR
- http://localhost:8765/currency-conversion/currency-conversion/from/USD/to/INR/quantity/10
- http://localhost:8765/currency-conversion/currency-conversion-feign/from/USD/to/INR/quantity/10
