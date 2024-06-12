package com.udemy.gatewayserver.filter;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.util.List;

/**
 * @author : Tommy
 * @version : 1.0
 * @createTime : 11/06/2024 23:45
 * @Description :
 */
@Component
public class FilterUtility {

    //defines the header name for the correlation ID
    //This is used to track requests across different services for logging and tracing purposes.
    public final static String CORRELATION_ID = "X-Correlation-Id";

    /**
     * Purpose: This method retrieves the correlation ID from the HTTP request headers.
     * @param requestHeaders - the HTTP headers from the request.
     * @return
     */
    public String getCorrelationId(HttpHeaders requestHeaders) {
        //Check for Header: It checks if the CORRELATION_ID header is present in the requestHeaders.
        if (requestHeaders.get(CORRELATION_ID) != null) { //Checks if the correlation ID header exists.
            //Retrieve Header List: If the header is present, it gets the list of values associated with this header.
            List<String> requestHeaderList = requestHeaders.get(CORRELATION_ID); //Retrieves the list of values for the correlation ID header.
            //Return First Value: It streams the list, finds the first value, and returns it. This assumes that there's only one value for this header.
            return requestHeaderList
                    .stream()
                    .findFirst()
                    .get();
        } else {
            //Return null: If the header is not present, it returns null.
            return null;
        }
    }

    /**
     * Purpose: This method sets a custom header in the HTTP request.
     * @param exchange - Represents the web exchange, including the request and response.
     * @param name - The name of the header to set.
     * @param value - The value of the header to set.
     * @return
     */
    public ServerWebExchange setRequestHeader(ServerWebExchange exchange,
                                              String name,
                                              String value) {
        /*
        // Step 1: Create a mutable copy of the current ServerWebExchange.
        Code: exchange.mutate():
        Principle: The ServerWebExchange is immutable, meaning it cannot be changed directly.
                   To modify it, we create a mutable copy using the mutate() method.
                   This method provides a builder pattern to facilitate the changes.

        //Step 2: Modify the Request within the Exchange
        Code: .request(
        Principle: The request() method on the exchange's builder allows us to specify
                   modifications to the HTTP request contained within the exchange.
                   We pass a modified request here.

        //Step 3: Create a Mutable Copy of the Current Request
        Code: exchange.getRequest().mutate()
        Principle: Similar to the exchange, the ServerHttpRequest is also immutable.
                   To modify it, we use the mutate() method to create a mutable copy.

        //Step 4: Add the Header with the Given Name and Value
        Code: .header(name, value)
        Principle: This method on the ServerHttpRequest.Builder allows us to
                   add or modify headers. Here, we add the
                   specified header (name) with its value (value).

        //Step 5: Build the Modified Request
        Code: .build()
        Principle: Once the modifications are specified,
                   the build() method constructs a new
                   ServerHttpRequest instance with these changes.

        //Step 6: Build the Modified ServerWebExchange
        Code: ).build();
        Principle: Finally, after modifying the request, the build() method
                   on the ServerWebExchange.Builder constructs
                   a new ServerWebExchange instance that includes the modified request.
         */
        return exchange
                .mutate() // Step 1: Create a mutable copy of the current ServerWebExchange.
                .request( // Step 2: Modify the request within the exchange: The request within the exchange is also mutated to add the new header.
                        exchange.getRequest()
                                .mutate() //Step 3: Create a mutable copy of the current request.
                                .header(name, value) //Step 4: Add the header with the given name and value.
                                .build() //Step 5: Build the modified request.
                ).build(); //Step 6: Build the modified ServerWebExchange.
    }

    /**
     * This method sets the correlation ID header in the HTTP request.
     * @param exchange - Represents the web exchange, including the request and response.
     * @param correlationId - The correlation ID to set in the request header.
     * @return
     */
    public ServerWebExchange setCorrelationId(ServerWebExchange exchange,
                                              String correlationId) {
        // Calls the setRequestHeader method,
        // passing the CORRELATION_ID constant and
        // the correlationId value to set the correlation ID header in the request.
        return this.setRequestHeader(exchange, CORRELATION_ID, correlationId);
    }
}
