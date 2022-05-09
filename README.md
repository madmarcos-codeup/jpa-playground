# JPA Playground

This project is for devs to play around with JPA concepts without having to use a Spring Boot Controller.

All main()-type functionality should be in the `tests` package. No Controllers are necessary to run the JPA code. The JPA code runs as unit tests. Look at AuthorBookTests.java for a model.

## Observations

Bi-directional M:M relationships have the potential to recursively evaluate unto infinity. You will see a `StackOverflow Exception` when this occurs. A place where you may see it is simply calling the `toString()` method on one side of the M:M relationship.

There are a few ways to prevent this undesirable behavior:
1. If you use Lombok's `@ToString` annotation, you can use `@ToString.Exclude` to skip any property from that class' `toString()` Lombok-generated method. The downside to this is that field will be completely excluded from the `toString()` method.
2. You can use `@JsonIgnoreProperty` for the collection of objects on the other side of a class but this will only affect JSON expressions of the objects. Useful for controllers and REST APIs, but not useful for a simple `toString()`
3. Make your own `toString()` or output function.

I implemented #3 for the Author/Book M:M relationship.

---

## References

https://www.baeldung.com/jpa-many-to-many