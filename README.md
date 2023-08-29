# shopElectronics
API that stores information about electronics shops showcases and its related products

Подключить на свободный порт PostgreSQL.<br />
По умолчанию стоит стандартный порт localhost:5432,<br />
название БД - postgres,<br />
username - postgres,<br />
password - 1337.<br />
<br />
Liquibase changeset-ы находятся в файле db/changelog/db.changelog-master.yaml<br />
<br />
Описание реализованных методов (тестирование проводилось в Postman):<br />
- Получить все витрины (/showcases/get/all)
- - Фильтрация по типу (/showcases/get/type)<br />
    Параметры:<br /> type - String
- - Фильтрация по адресу (/showcases/get/address)<br />
    Параметры:<br /> address - String
- - Фильтрация по периоду создания (/showcases/get/creation_date_period)<br />
    Параметры:<br /> date1 - Date (в SQL формате, н-р: 2000-01-01)<br />
							 date2 - Date<br />
							 (Прим.: date1 < date2)
- - Фильтрация по последней актуализации (/showcases/get/last_update_period)<br />
    Параметры:<br /> date1 - Date (в SQL формате, н-р: 2000-01-01)<br />
                date2 - Date<br />
                (Прим.: date1 < date2)
- Получить все товары витрины (/products/get/all)
- - Фильтрация по типу товара (/products/get/type)<br />
     Параметры:<br /> showcaseId - UUID<br />
                type - String
- - Фильтрация по диапазону цен (/products/get/price)<br />
     Параметры:<br /> showcaseId - UUID<br />
                price1 - double<br />
                price2 - double
- Добавить витрину (/showcases/add)<br />
  Тело запроса (пример в формате JSON):<br />
  {<br />
    "name": "qwe",<br />
    "address": "qwer",<br />
    "type": "qwerty"<br />
  }
- Добавить товар на витрину (/products/add)<br />
  Тело запроса (пример в формате JSON):<br />
  {<br />
      "name": "q",<br />
      "type": "qw",<br />
      "price": 14,<br />
      "showcaseId": "69a9948b-c626-4614-b1e2-8d5646937585",<br />
      "positionOnShowcase": 5<br />
  }
- Изменение данных витрины (/showcases/update)<br />
  Параметры:<br /> id - UUID<br />
             name - String - наличие параметра опционально<br />
             address - String - наличие параметра опционально<br />
             type - String - наличие параметра опционально
- Изменение данных товара (/products/update)<br />
  Параметры:<br /> id - UUID<br />
             name - String - наличие параметра опционально<br />
             type - String - наличие параметра опционально<br />
             price - double - наличие параметра опционально<br />
             showcaseId - UUID - наличие параметра опционально<br />
             positionOnShowcase - int - наличие параметра опционально
- Удаление витрины (/showcases/delete)<br />
  Параметры: id - UUID
- Удаление товара (/products/delete)<br />
  Параметры: id - UUID
