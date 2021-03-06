SET CLUSTER '';
SET DEFAULT_TABLE_TYPE 0;
SET DB_CLOSE_DELAY -1;
SET WRITE_DELAY 500;
SET DEFAULT_LOCK_TIMEOUT 2000;
SET CACHE_SIZE 16384;
CREATE USER IF NOT EXISTS SA SALT '421aee6e43fb53d1' HASH '4cfa2d82b00d9c2eee74578dcf922eb3f317f87a150894e74177b116bf856305' ADMIN;
CREATE SEQUENCE PUBLIC.SYSTEM_SEQUENCE_DB15CFCE_CB2F_45A3_9704_8C35A568646C START WITH 1 BELONGS_TO_TABLE;
CREATE SEQUENCE PUBLIC.SYSTEM_SEQUENCE_171B46E2_E0D1_4720_BE04_B70741E4DD96 START WITH 1 BELONGS_TO_TABLE;
CREATE SEQUENCE PUBLIC.SYSTEM_SEQUENCE_0BB5BAE5_520C_4BAE_8EEC_7802AAA43F6D START WITH 1 BELONGS_TO_TABLE;
CREATE MEMORY TABLE PUBLIC.CUSTOMER(
    ID INTEGER DEFAULT (NEXT VALUE FOR PUBLIC.SYSTEM_SEQUENCE_DB15CFCE_CB2F_45A3_9704_8C35A568646C) NOT NULL NULL_TO_DEFAULT SEQUENCE PUBLIC.SYSTEM_SEQUENCE_DB15CFCE_CB2F_45A3_9704_8C35A568646C,
    CITY VARCHAR(255),
    COUNTRY VARCHAR(255),
    STREET VARCHAR(255),
    STATE VARCHAR(255),
    ZIP VARCHAR(255),
    FIRST_NAME VARCHAR(255),
    LAST_NAME VARCHAR(255)
);
ALTER TABLE PUBLIC.CUSTOMER ADD CONSTRAINT PUBLIC.CONSTRAINT_5 PRIMARY KEY(ID);
-- 0 +/- SELECT COUNT(*) FROM PUBLIC.CUSTOMER;
CREATE MEMORY TABLE PUBLIC.PRODUCT(
    ID INTEGER DEFAULT (NEXT VALUE FOR PUBLIC.SYSTEM_SEQUENCE_171B46E2_E0D1_4720_BE04_B70741E4DD96) NOT NULL NULL_TO_DEFAULT SEQUENCE PUBLIC.SYSTEM_SEQUENCE_171B46E2_E0D1_4720_BE04_B70741E4DD96,
    DTYPE VARCHAR(5) NOT NULL,    
    PART_NUMBER VARCHAR(25) NOT NULL,
    PRICE FLOAT NOT NULL,
    NAME VARCHAR(255),
    TITLE VARCHAR(255),
    AUTHOR VARCHAR(255)
);
ALTER TABLE PUBLIC.PRODUCT ADD CONSTRAINT PUBLIC.CONSTRAINT_1 PRIMARY KEY(ID);
-- 0 +/- SELECT COUNT(*) FROM PUBLIC.PRODUCT;
CREATE MEMORY TABLE PUBLIC.PURCHASE_ORDER(
    ID INTEGER DEFAULT (NEXT VALUE FOR PUBLIC.SYSTEM_SEQUENCE_0BB5BAE5_520C_4BAE_8EEC_7802AAA43F6D) NOT NULL NULL_TO_DEFAULT SEQUENCE PUBLIC.SYSTEM_SEQUENCE_0BB5BAE5_520C_4BAE_8EEC_7802AAA43F6D,
    CANCELLED BOOLEAN,
    CREATION_DATE DATE,
    TOTAL_PRICE FLOAT,
    CUSTOMER INTEGER
);
ALTER TABLE PUBLIC.PURCHASE_ORDER ADD CONSTRAINT PUBLIC.CONSTRAINT_2 PRIMARY KEY(ID);
-- 0 +/- SELECT COUNT(*) FROM PUBLIC.PURCHASE_ORDER;
CREATE MEMORY TABLE PUBLIC.PURCHASE_ORDER_PRODUCTS(
    PURCHASE_ORDER INTEGER NOT NULL,
    PRODUCTS INTEGER NOT NULL
);
-- 0 +/- SELECT COUNT(*) FROM PUBLIC.PURCHASE_ORDER_PRODUCTS;
ALTER TABLE PUBLIC.PURCHASE_ORDER_PRODUCTS ADD CONSTRAINT PUBLIC.FK33B27A33DE37DCE9 FOREIGN KEY(PRODUCTS) REFERENCES PUBLIC.PRODUCT(ID) NOCHECK;
ALTER TABLE PUBLIC.PURCHASE_ORDER_PRODUCTS ADD CONSTRAINT PUBLIC.FK33B27A3339196B73 FOREIGN KEY(PURCHASE_ORDER) REFERENCES PUBLIC.PURCHASE_ORDER(ID) NOCHECK;
ALTER TABLE PUBLIC.PURCHASE_ORDER ADD CONSTRAINT PUBLIC.FK71A56A90AB8C93E6 FOREIGN KEY(CUSTOMER) REFERENCES PUBLIC.CUSTOMER(ID) NOCHECK;