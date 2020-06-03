# Ikkon

Ikkon is convention based object mapping tool.

> [Images of Ikkon](https://media.comicbook.com/2018/08/images-of-ikonn-1128198.jpeg)

## Examples 

### Immutable object to mutable object

```java
public class Source {

    private final UUID id;
    public UUID getId() { return id; }
    
    private final String name;
    public String getName() { return name; }

    public Source(UUID id, String name) {
        this.id = id;
        this.name = name;
    }
}

public class Destination {

    private UUID id;
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    private String name;
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}

var source = new Source(UUID.randomUUID(), "foo");
var destination = new Mapper().map(source, Destination.class);
```

### Mutable object to immutable object

> `-parameters` compiler option is required in order to map to immutable object in convention based manner.

```java
public class Source {

    private UUID id;
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    private String name;
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}

public class Destination {

    private final UUID id;
    public UUID getId() { return id; }
    
    private final String name;
    public String getName() { return name; }

    public Source(UUID id, String name) {
        this.id = id;
        this.name = name;
    }
}

var source = new Source(UUID.randomUUID(), "foo");
var destination = new Mapper().map(source, Destination.class);
```
