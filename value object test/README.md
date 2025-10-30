# Value Object Assignment

## Assignment Goal

Implement a `ValueObject` base class that provides structural equality and comparability for domain objects.

## Solution Overview

The `ValueObject` class now guarantees:

- **Structural equality**: Two instances with the same field values are considered equal, regardless of identity
- **Consistent comparability**: Objects can be ordered predictably based on their field values
- **Readable representation**: The `toString()` method displays the class name and all field values in declaration order
- **Safe null handling**: Null values are properly handled in equality checks, comparisons, and string representation

## Running Tests

Execute the test suite using Maven:

```bash
mvn clean test
```

Expected outcome: All 3 tests pass with BUILD SUCCESS.
