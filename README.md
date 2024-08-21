For running the project execute following commands from a command line:

```
mvn package
java -jar target/find-duplicates.jar
```

For running tests execute following command from a command line:

```
mvn test
```

[Jaro-Winkler distance](https://en.wikipedia.org/wiki/Jaro-Winkler_distance) is used for the implementation of the matching score.
Each contact's attribute (excluding the id) are compared against the other contact and the average is used as score.

Similarity is defined with following criteria:
- \>= 0.85: High
- \>= 0.77 and < 0.85: Medium
- \>= 0.70 and < 0.77: Low
- < 0.70: No match