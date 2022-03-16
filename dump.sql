;             
CREATE USER IF NOT EXISTS "SA" SALT 'abdf442ebdca60e8' HASH 'feef8f622dabb41e211df9d0399301661dce90095dca247482fc803629e866d3' ADMIN;         
CREATE SEQUENCE "PUBLIC"."SYSTEM_SEQUENCE_E72B6D8A_5FA9_4845_8FC0_7F221EB79FB2" START WITH 5 BELONGS_TO_TABLE;
CREATE SEQUENCE "PUBLIC"."SYSTEM_SEQUENCE_C42245CB_583B_4EF4_803E_4AC4FC0D8960" START WITH 5 BELONGS_TO_TABLE;
CREATE MEMORY TABLE "PUBLIC"."POSTS"(
    "ID" BIGINT DEFAULT NEXT VALUE FOR "PUBLIC"."SYSTEM_SEQUENCE_C42245CB_583B_4EF4_803E_4AC4FC0D8960" NOT NULL NULL_TO_DEFAULT SEQUENCE "PUBLIC"."SYSTEM_SEQUENCE_C42245CB_583B_4EF4_803E_4AC4FC0D8960",
    "DESCRIPTION" CLOB,
    "LIKES" BIGINT,
    "POST_DATE" DATE,
    "POST_TIME" TIME,
    "USER_ID" BIGINT
);        
ALTER TABLE "PUBLIC"."POSTS" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_4" PRIMARY KEY("ID");        
-- 4 +/- SELECT COUNT(*) FROM PUBLIC.POSTS;   
INSERT INTO "PUBLIC"."POSTS" VALUES
(1, 'Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry''s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.', 11, DATE '2022-02-28', TIME '10:43:12', 1),
(2, 'Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry''s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.', 18, DATE '2022-03-02', TIME '21:43:13', 2),
(3, 'test', 0, DATE '2022-03-08', TIME '21:50:10', 4),
(4, 'chyba dziala', 0, DATE '2022-03-08', TIME '21:50:20', 4);           
CREATE MEMORY TABLE "PUBLIC"."USERS"(
    "ID" BIGINT DEFAULT NEXT VALUE FOR "PUBLIC"."SYSTEM_SEQUENCE_E72B6D8A_5FA9_4845_8FC0_7F221EB79FB2" NOT NULL NULL_TO_DEFAULT SEQUENCE "PUBLIC"."SYSTEM_SEQUENCE_E72B6D8A_5FA9_4845_8FC0_7F221EB79FB2",
    "EMAIL" VARCHAR(255),
    "FIRST_NAME" VARCHAR(255),
    "LAST_NAME" VARCHAR(255)
); 
ALTER TABLE "PUBLIC"."USERS" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_4D" PRIMARY KEY("ID");       
-- 4 +/- SELECT COUNT(*) FROM PUBLIC.USERS;   
INSERT INTO "PUBLIC"."USERS" VALUES
(1, 'abc1@gmail.com', 'first', 'last 1'),
(2, 'abc2@gmail.com', 'first', 'last 2'),
(3, 'abc3@gmail.com', 'first', 'last 3'),
(4, 'email@email.com', 'Jan', 'Nowak'); 
ALTER TABLE "PUBLIC"."POSTS" ADD CONSTRAINT "PUBLIC"."FK5LIDM6CQBC7U4XHQPXM898QME" FOREIGN KEY("USER_ID") REFERENCES "PUBLIC"."USERS"("ID") NOCHECK;          
