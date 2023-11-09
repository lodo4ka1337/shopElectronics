# ShopElectronics
**API that stores information about electronics shops showcases and its related products in database.**

## Default configuration
Application configuration is located in application.properties

Datasource driver - org.postgresql.Driver<br />
Database name - postgres<br />
username - postgres<br />
password - 1337<br />

Liquibase changesets are located in db/changelog/db.changelog-master.yaml<br />

## API Methods Description
(Testing was performed with Postman)

- Get all showcases (/showcases/get)
	- Showcase search request filters
	<br />Request parameters:
	<br />type - String
	<br />address - String
	<br />createdAfter - Date
	<br />createdBefore - Date
	<br />updatedAfter - Date
	<br />updatedBefore - Date
	<br />(Note: enter dates in a standard ISO pattern, e.g 2000-01-01)
- Get all products on a showcase (/products/get)
	- Product search request filters
	<br />Request parameters:
	<br />showcaseId - UUID - mandatory parameter
	<br />type - String
	<br />priceMoreThan - double
	<br />priceLessThan - double
- Add showcase (/showcases/add)<br />
	- Request body (example is in JSON):
<pre>
	{
	   "name": "qwe",
	   "address": "qwer"
	   "type": "qwerty"
	}
</pre>
    (Note:<br />
    name - String<br />
    address - String<br />
    type - String)
- Add product to a showcase (/products/add)<br />
	- Request body (example is in JSON):<br />
<pre>
	{
	   "name": "q",
	   "type": "qw",
	   "price": 14.5,
	   "showcaseId": "69a9948b-c626-4614-b1e2-8d5646937585",
	   "positionOnShowcase": 5
	}
</pre>
    (Note:<br />
    name - String<br />
    type - String<br />
    price - double<br />
    showcaseId - UUID<br />
    positionOnShowcase - int)
- Update showcase (/showcases/update)<br />
	- Request parameters:<br />
	id - UUID - mandatory parameter<br />
  	name - String<br />
  	address - String<br />
  	type - String
- Update product on a showcase (/products/update)<br />
	- Request parameters:<br />
	id - UUID - mandatory parameter<br />
	name - String<br />
  	type - String<br />
  	price - double<br />
   	showcaseId - UUID<br />
 	positionOnShowcase - int
- Delete showcase (/showcases/delete)
	- Request parameters:<br />
 	id - UUID
- Delete product (/products/delete)
	- Request parameters:<br />
	id - UUID<br />
