# MultiTypeJsonParser

This example demonstrates how to parse complex json objects into Kotlin objects using Gson library.

## Standard json response ##
```
{
    "data": {
        "type":"user"
        "id": 1,
        "name": "John",
        "lastName": "Doe",
        "email": "johndoe@gmail.com",
        "phoneNumber": "1232123213"
    }
}
```

## Complex json response ##

This response alongside standard User object contains a list of objects of different types.

```
{
    "data": {
        "type":"user"
        "id": 1,
        "name": "John",
        "lastName": "Doe",
        "email": "johndoe@gmail.com",
        "phoneNumber": "1232123213"
    },
    "productsList":[
         {
            "type":"cellphone",
            "id": 2343,
            "manufacturer": "Sony",
            "model": "Xperia",
            "displaySize" : 6.3,
            "batteryCapacity":"4000"
       },
       {
            "type":"charger",
            "id": 554545,
            "manufacturer": "Xiaomi",
            "model": "m2",
            "chargerType":"type C"
       },
       {
            "type":"headphone",
            "id": 897865,
            "manufacturer": "Samsung",
            "model": "cbm-2343",
            "maxDb":"79dB"
       },
       {
            "type":"memory_card",
            "id": 12354,
            "manufacturer": "Transcend",
            "capacity": 20000
       }
    ]
}

```

In order to extract Kotlin objects from multi-type productsList we have to use create custom json deserializer. 

The process of deserialization consists of extracting the objects one by one from the complex json list, calling Gson's method fromJson() on those json object with the right Type
parametar. After the object has been deserialized we can manipulate it as any other Kotlin object.
 


