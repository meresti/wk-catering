
CREATE TABLE USERS(
    ID          BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY,
    NAME        VARCHAR(50) NOT NULL UNIQUE,
    PASSWORD    VARCHAR(100),
    CONSTRAINT PK_USERS PRIMARY KEY (ID)
);

CREATE UNIQUE INDEX IDX_USER_NAME ON USERS(NAME);

CREATE TABLE GROUPS(
    ID          BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY,
    NAME        VARCHAR(50) NOT NULL UNIQUE,
    DESCRIPTION VARCHAR(100),
    CONSTRAINT PK_GROUPS PRIMARY KEY (ID)
);

CREATE UNIQUE INDEX IDX_GROUP_NAME ON GROUPS(NAME);

CREATE TABLE USER_GROUPS(
    USER_ID     BIGINT NOT NULL,
    GROUP_ID    BIGINT NOT NULL,
    CONSTRAINT USER_GROUPS PRIMARY KEY (USER_ID, GROUP_ID)
);

ALTER TABLE USER_GROUPS ADD CONSTRAINT FK_USER_GROUPS_USER FOREIGN KEY (USER_ID) REFERENCES USERS(ID);
ALTER TABLE USER_GROUPS ADD CONSTRAINT FK_USER_GROUPS_GROUP FOREIGN KEY (GROUP_ID) REFERENCES GROUPS(ID);

CREATE TABLE MONEY(
  ID          BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY,
  USER_ID     BIGINT NOT NULL,
  GROUP_ID    BIGINT NOT NULL,
  TOTAL       DOUBLE PRECISION NOT NULL,
  USED        DOUBLE PRECISION NOT NULL,
  MISSING     DOUBLE PRECISION NOT NULL,
  CONSTRAINT PK_MONEY PRIMARY KEY (ID)
);

ALTER TABLE MONEY ADD CONSTRAINT FK_USER_MONEY FOREIGN KEY (USER_ID) REFERENCES USERS(ID);
ALTER TABLE MONEY ADD CONSTRAINT FK_GROUP_MONEY FOREIGN KEY (GROUP_ID) REFERENCES GROUPS(ID);


CREATE VIEW W_USER_GROUPS (USER_NAME, GROUP_NAME) AS SELECT u.NAME, g.NAME FROM USER_GROUPS ug INNER JOIN USERS u ON ug.USER_ID=u.ID INNER JOIN GROUPS g ON ug.GROUP_ID=g.ID;

CREATE TABLE FOODS(
    ID            BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY,
    NAME          VARCHAR(100) NOT NULL,
    PRICE         DOUBLE PRECISION NOT NULL,
    DELIVERY_DATE DATE NOT NULL,
    CONSTRAINT PK_FOODSS PRIMARY KEY (ID)
);

CREATE UNIQUE INDEX IDX_FOOD_NAME ON FOODS(NAME, DELIVERY_DATE);

CREATE TABLE ORDERS(
    ID          BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY,
    USER_ID     BIGINT NOT NULL,
    FOOD_ID     BIGINT NOT NULL,
    QUANTITY    INTEGER NOT NULL,
    CONSTRAINT PK_ORDERS PRIMARY KEY (ID)
);

ALTER TABLE ORDERS ADD CONSTRAINT FK_ORDERS_USER FOREIGN KEY (USER_ID) REFERENCES USERS(ID);
ALTER TABLE ORDERS ADD CONSTRAINT FK_ORDERS_FOOD FOREIGN KEY (FOOD_ID) REFERENCES FOODS(ID);

CREATE UNIQUE INDEX IDX_ORDERS ON ORDERS(USER_ID, FOOD_ID);
