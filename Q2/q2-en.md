## Overview
In this system the following requirements are required.
* Handle a large amount of writing: write 1 billion events per day
* Provide metrics to customers with delay of up to 1 hour
* Execute with minimal downtime
* Ability to reprocess history data if bug exists in processing logic

## Backend
![backend](https://github.com/takanorihozumi/ForPayPayTest/blob/master/images/backend_thozumi.png "backend")

There is a limit to processing data size of this size with RDB.<br>

Using RDB,<br>
Instead of taking a corresponding strategy with scale-out <br>
by replication partitioning,<br>
I will use KVS.<br><br>
In order to perform indexing with application specific type,<br>
We will adopt KVS and full-text search engine for Backend.<br><br>

Specifically, we use KVS as fact data.<br>
The full-text search engine is used for the index data for reference.<br><br>


If there is no problem even if there is some time lag<br>
in creating the index for reference,<br>
Fact data of KVS is extracted by batch processing,<br>
Create index data.<br><br>


When index creation needs to be performed immediately,<br>
Kafka instantly creates indexes for display<br>
and search on full-text search engines.<br><br>

When registering and updating fact data,<br>
we have prepared a general-purpose framework<br>
that creates indexes immediately,<br>
We will reduce the development burden of application developers.<br><br>

## Architecture
Adopt micro service architecture,<br>
We will speed up the development and operation of each service.<br><br>

Moreover, in order to recover with a minimum downtime <br>
when there is a problem,<br>
We use Circuit Breaker between services.<br>
In this way,<br>
if a cooperating service dies persistently,<br>
We will bypass other logic.<br><br>

We also use Gracefull shutdown, making it a system that is resistant to failures.
