
Different concepts were applied in this repository:

1. **N+1 problem**: The N+1 query problem is said to occur when it's executed 1 query to retrieve the parent entity and N queries to retrieve the child entities. 
In this case we have a parent entity Contract and a child entity Receipt.
Both of them with a OneToMany/ManyToOne relationship. By default, the fetch type is lazy, which means that the queries are only performed if some get of the receipt is done.
This is not the case in the relation OneToOne, which is eager by default.

   Using @EntityGraph notation we make sure that entities can be fetched eagerly from the database in a single select statement

2. **JQL**

   Different from native queries, JQL returns a entity from the database.
3. **Fetch EAGER vs LAZY**

   EAGER: To load the child data together with the rest of the fields
   
   LAZY: To load it on-demand when you do a get method.
4. **DTO mapping**

   Mapping the entity with a DTO or implementing a jpa projection. Spring creates a proxy instance of the projection interface for each entity object, and all calls to the proxy are forwarded to that object
5. **H2 - DDL automatic**
6. **Pagination**
7. **Date control fields (updated at, created at)**
8. **Cascade**
9. **Transactional**
Problem: We use @Transactional with readOnly to avoid that the entity 
vinculated with database could suffer a modification (the method is just for finding the entity)
. It's necessary to use @Transactional(propagation = Propagation.REQUIRES_NEW)  in LogRegistryService for generate a new transaction meanwhile the first one is suspended.
If an error RuntimeException occurs in the first transaction, rollback is just performed in the first transaction. the second one is not affected. This just works
if the second transaction is in a separared class due to how spring wraps the class and overrides the method using a proxy.
Also is necessary to take into account that the type of exception is important due to hierachy. With RuntimeException the rollback is performed because is not a checked exception, 
but with Exception is not done because Spring undersands that is a checked exception and some action should be implemented. 








References

https://www.baeldung.com/spring-data-jpa-projections

https://www.baeldung.com/spring-data-jpa-pagination-sorting

https://medium.com/geekculture/resolve-hibernate-n-1-problem-f0e049e689ab#:~:text=The%20N%2B1%20query%20problem,the%20performance%20of%20the%20application.