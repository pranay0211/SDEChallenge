
# PaytmLabs SDE Challenge

## Coding Question

Write an interface for a data structure that can provide the moving average of the last N elements added, add elements to the structure and get access to the elements. Provide an efficient implementation of the interface for the data structure.

### Minimum Requirements

1. Provide a separate interface (IE `interface`/`trait`) with documentation for the data structure
2. Provide an implementation for the interface
3. Provide any additional explanation about the interface and implementation in a README file.

## Design Question

Design A Google Analytic like Backend System.
We need to provide Google Analytic like services to our customers. Please provide a high level solution design for the backend system. Feel free to choose any open source tools as you want.

### Requirements

1. Handle large write volume: Billions of write events per day.
2. Handle large read/query volume: Millions of merchants wish to gain insight into their business. Read/Query patterns are time-series related metrics.
3. Provide metrics to customers with at most one hour delay.
4. Run with minimum downtime.
5. Have the ability to reprocess historical data in case of bugs in the processing logic.

# ---------------------------------
# Solution
# ---------------------------------

## Coding Question

Write an interface for a data structure that can provide the moving average of the last N elements added, add elements to the structure and get access to the elements. Provide an efficient implementation of the interface for the data structure.

### Minimum Requirements

1. Provide a separate interface (IE `interface`/`trait`) with documentation for the data structure
2. Provide an implementation for the interface
3. Provide any additional explanation about the interface and implementation in a README file.

## Code Explanation

It's a maven project since using Junit as testing framework.

## Solution
The <code>MovingAverage</code> named owned data extended structure interface, which is for tracking the moving average of the last N elements added. 
Return value of moving average is Big Decimal, which support any kind of number.
This interface supports,
1. Adding elements to the dataset
2. Provides read-access to the elements from dataset.
3. Clear the dataset of elements
4. Get moving average of provided N of last elements
5. If N not provided than it return the moving average of whole dataset.

### Functions provided : Interface <MovingAverage>

void clear();
* Clears the list of dataset.

boolean isEmpty();
* Returns true if the dataset is empty.

void add(BigDecimal value);
* Appends the specified element to the end of the list

BigDecimal get(int index);
* Get the element at given index.

int size();
* Return the size of dataset

BigDecimal getMovingAverage(Long lastN);
* Return the moving average of last *lastN* element in dataset.

BigDecimal getMovingAverage();
* Return the moving average of last *whole* element in dataset.

### Implementation

- Implementation provided for ArrayList type of list. We can extend the functionality for any other kind of list as per convenience.
- Also caching implemented. If list is not modified and require to get same last elements movingAverage, in that case it will not calculate again and again movingAverage. It will return value from cached value.
- Test cases provided for the same. In test case also 90M dataset been tested where tried to get moving average of last 70M data.

### Future Scope
- This implementation ideated and designed in such a way to support any kind of number, currency, crypto, stock price as well.
- This functionality can be extended to support different size of digits. As in crypto we have 18 digit decimals so we can calculate with more extension.
- To support any kind of decimal we have to extend functionality where we need to pass decimal number so calculation for moving average happens in such a way. 

# -------------------------------
# System Design 
# -------------------------------

GA like analytics system design is available in "GASystemDesign".
