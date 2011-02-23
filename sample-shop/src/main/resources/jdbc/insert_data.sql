INSERT INTO CUSTOMER (ID, CITY, COUNTRY, FIRST_NAME, LAST_NAME, STATE) 
	values (1, 'Boston', 'USA', 'Bill', 'Burke', 'MA');
INSERT INTO CUSTOMER (ID, CITY, COUNTRY, FIRST_NAME, LAST_NAME, STATE) 
	values (2, 'Boston', 'USA', 'Ryan', 'McDonough', 'MA');
INSERT INTO CUSTOMER (ID, CITY, COUNTRY, FIRST_NAME, LAST_NAME, STATE) 
	values (3, 'New York', 'USA', 'Solomon', 'Duskis', 'NJ');
INSERT INTO CUSTOMER (ID, CITY, COUNTRY, FIRST_NAME, LAST_NAME, STATE) 
	values (4, 'New York', 'USA', 'Leonard', 'Richardson', 'NJ');

	
INSERT INTO PRODUCT (ID, DTYPE, NAME, PART_NUMBER, PRICE) 
	values (10, 'game', 'Quake', 'ID-2005', 19.99);
INSERT INTO PRODUCT (ID, DTYPE, NAME, PART_NUMBER, PRICE) 
	values (11, 'game', 'Doom', 'ID-1995', 9.99);
INSERT INTO PRODUCT (ID, DTYPE, AUTHOR, TITLE, PART_NUMBER, PRICE) 
	values (12, 'book', 'Harlan Coben', 'Just one look', 'PEN-2004', 9.99);
	
INSERT INTO PURCHASE_ORDER(ID, CANCELLED, TOTAL_PRICE, CREATION_DATE, CUSTOMER)
	values (100, false, 29.98, '2010-08-10', 1);

INSERT INTO PURCHASE_ORDER_PRODUCTS(PURCHASE_ORDER, PRODUCTS)
	values (100, 10);
INSERT INTO PURCHASE_ORDER_PRODUCTS(PURCHASE_ORDER, PRODUCTS)
	values (100, 12);
	
INSERT INTO PURCHASE_ORDER(ID, CANCELLED, TOTAL_PRICE, CREATION_DATE, CUSTOMER)
	values (101, false, 9.99, '2010-08-12', 1);
INSERT INTO PURCHASE_ORDER_PRODUCTS(PURCHASE_ORDER, PRODUCTS)
	values (101, 11);
		