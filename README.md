# JPA Playground

This project is for devs to play around with JPA concepts without having to use a Spring Boot Controller.

All main()-type functionality should be in the `tests` package. No Controllers are necessary to run the JPA code. The JPA code runs as unit tests. Look at AuthorBookTests.java for a model.

---

## Setup

1. Execute the SQL script `db.sql` located in the root of this repo

2. Create an application.properties in the src/main/resources directory. It should include the following:
    ```
    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
    spring.datasource.username=<your db username>
    spring.datasource.password=<your db password>
    spring.datasource.url=jdbc:mysql://<your db host>:3306/jpa_tutorial
    server.port=32000
    logging.level.root=WARN
    ```
3. Execute the test case `fetchAuthors()` to ensure your db connection is working. If it passes, then you are ready to play! Try executing the other test cases and make your own. Feel free to add more classes and `autowire` new repositories into the testing class. You may also create your own testing classes.

---

## Relationship demonstrations

Relationships are expressed both as Java entities AND as database tables. Following are some example relationships that are already configured in this project. 

### One-to-one (1:1) 

- Entities: `Author` and `Address`
- Tables: `authors` and `locations`

### One-to-many (1:M)

- Entities: `Customer`, `Purchase`
- Tables: `customers`, `purchases`

### Many-to-many (M:M)

- Entities: `Author`, `Book`
- Tables: `authors`, `books`, `authors_books`

### 1:M Self relationship

- Entity: `Customer`
- Table: `customers`

---

## Observations

### Relationship equality

Many JPA relationships are not equal, i.e., one side cannot exist without the other side. In these cases, you should only specify the details of the relationship on the "owner" side, i.e., the side that CAN exist without the other. The dependent side should have a simplified relationship annotation, with a `mappedBy` attribute that points to the instance variable name in the owning class that represents the dependent.

If the relationship is equal, i.e., both sides can exist without the other OR neither side can exist without the other, then may want to forego the use of `mappedBy` and instead use complete annotations in both sides of the relationship.

### Self relationships

It is possible for an entity class to have one or more relationships defined with itself. An example of this is a human having another human as a spouse. You could have a separate table for storing spouse relationships. Another approach is to simply have a foreign key in the human table to store the id of that record's spouse. Some examples of various self-relationships are:
1. Spouses have a 1:1 self-relationship.
2. Branches in Git have a 1:M self-relationship. One branch can have many branches created from it.
3. Friends have a M:M relationship. Note that a separate table must be used to record the friendships between people.

In the JPA playground: a customer can refer another customer. Thus, one customer (the child) can have another customer as its referrer (the parent).

This creates a 1:M parent/child relationship within the same table. It can be accomplished by having 2 instance vars in the same class use the same table column (referrer_id in this case):

```java
@ManyToOne
@JoinColumn(name = "referrer_id")
private Customer referrer;

@OneToMany
@JoinColumn(name = "referrer_id")
private Set<Customer> referrals;
```

As an example, Customer 1 is the referrer for Customer 2. And Customer 2 should show up in Customer 1's set of referrals. Like this:

```js
Customer{id=1, customerName='Customer 1', purchases= [Purchase(id=1, itemDescription=Item 1, purchaseAmount=5.5)], referred by= null, referrals= [Customer 2, ] }

Customer{id=2, customerName='Customer 2', purchases= [], referred by= Customer 1, referrals= [] } (edited)
```

Notice that Customer 1 does not have a referred by  and Customer 2 has no referrals

### Common Problems in Bi-directional Many-to-many

Bi-directional M:M relationships have the potential to recursively evaluate unto infinity. You will see a `StackOverflow Exception` when this occurs. One place where you may see it is by simply calling the `toString()` method on one side of the M:M relationship.

There are a few ways to prevent this undesirable behavior:
1. If you use Lombok's `@ToString` annotation, you can use `@ToString.Exclude` to skip any property from that class' `toString()` Lombok-generated method. The downside to this is that field will be completely excluded from the `toString()` method.
2. You can use `@JsonIgnoreProperty` for the collection of objects on the other side of a class but this will only affect JSON expressions of the objects. Useful for controllers and REST APIs, but not useful for a simple `toString()`
3. Make your own `toString()` or output function.

I implemented #3 for the Author/Book M:M relationship.

### Cascade Types

We use `DETACH` and `REFRESH` for cascade types. `DETACH` removes a child from memory when the parent is removed. `REFRESH` reloads the child whenever the parent is reloaded. Avoid using the wimp-out of `CascadeType.ALL`. 

---

## References

- https://www.baeldung.com/jpa-many-to-many
- https://www.baeldung.com/hibernate-lazy-eager-loading
- https://www.baeldung.com/jpa-cascade-types
- https://www.baeldung.com/jackson-bidirectional-relationships-and-infinite-recursion
- https://www.baeldung.com/jpa-one-to-one