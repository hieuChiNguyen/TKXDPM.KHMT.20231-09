BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS "Media" (
	"id"	INTEGER NOT NULL,
	"type"	VARCHAR(45) NOT NULL,
	"category"	VARCHAR(45) NOT NULL,
	"price"	INTEGER NOT NULL,
	"quantity"	INTEGER NOT NULL,
	"title"	VARCHAR(45) NOT NULL,
	"value"	INTEGER NOT NULL,
	"imageUrl"	VARCHAR(45) NOT NULL,
	PRIMARY KEY("id" AUTOINCREMENT)
);
CREATE TABLE IF NOT EXISTS "CD" (
	"id"	INTEGER NOT NULL,
	"artist"	VARCHAR(45) NOT NULL,
	"recordLabel"	VARCHAR(45) NOT NULL,
	"musicType"	VARCHAR(45) NOT NULL,
	"releasedDate"	DATE,
	CONSTRAINT "fk_cd_media" FOREIGN KEY("id") REFERENCES "Media"("id"),
	PRIMARY KEY("id")
);
CREATE TABLE IF NOT EXISTS "Book" (
	"id"	INTEGER NOT NULL,
	"author"	VARCHAR(45) NOT NULL,
	"coverType"	VARCHAR(45) NOT NULL,
	"publisher"	VARCHAR(45) NOT NULL,
	"publishDate"	DATETIME NOT NULL,
	"numOfPages"	INTEGER NOT NULL,
	"language"	VARCHAR(45) NOT NULL,
	"bookCategory"	VARCHAR(45) NOT NULL,
	CONSTRAINT "fk_book_media" FOREIGN KEY("id") REFERENCES "Media"("id"),
	PRIMARY KEY("id" AUTOINCREMENT)
);
CREATE TABLE IF NOT EXISTS "User" (
	"id"	INTEGER NOT NULL,
	"name"	VARCHAR(45) NOT NULL,
	"email"	VARCHAR(45) NOT NULL,
	"address"	VARCHAR(45) NOT NULL,
	"phone"	VARCHAR(45) NOT NULL,
	PRIMARY KEY("id" AUTOINCREMENT)
);
CREATE TABLE IF NOT EXISTS "DVD" (
	"id"	INTEGER NOT NULL,
	"discType"	VARCHAR(45) NOT NULL,
	"director"	VARCHAR(45) NOT NULL,
	"runtime"	INTEGER NOT NULL,
	"studio"	VARCHAR(45) NOT NULL,
	"subtitle"	VARCHAR(45) NOT NULL,
	"releasedDate"	DATETIME,
	"filmType"	VARCHAR(45) NOT NULL,
	CONSTRAINT "fk_dvd_media" FOREIGN KEY("id") REFERENCES "Media"("id"),
	PRIMARY KEY("id")
);
CREATE TABLE IF NOT EXISTS "OrderMedia" (
	"mediaID"	INTEGER NOT NULL,
	"orderID"	INTEGER NOT NULL,
	"price"	INTEGER NOT NULL,
	"quantity"	INTEGER NOT NULL,
	CONSTRAINT "fk_ordermedia_media" FOREIGN KEY("mediaID") REFERENCES "Media"("id"),
	CONSTRAINT "fk_ordermedia_order" FOREIGN KEY("orderID") REFERENCES "Order"("id"),
	PRIMARY KEY("mediaID","orderID")
);
CREATE TABLE IF NOT EXISTS "Transaction" (
	"id"	INTEGER NOT NULL,
	"orderID"	INTEGER NOT NULL,
	"createAt"	DATETIME NOT NULL,
	"content"	VARCHAR(45) NOT NULL,
	CONSTRAINT "fk_transaction_order" FOREIGN KEY("orderID") REFERENCES "Order"("id"),
	PRIMARY KEY("id" AUTOINCREMENT)
);
CREATE TABLE IF NOT EXISTS "Card" (
	"id"	INTEGER NOT NULL,
	"cardNumber"	VARCHAR(45) NOT NULL,
	"holderName"	VARCHAR(45) NOT NULL,
	"expirationDate"	DATE NOT NULL,
	"securityCode"	VARCHAR(45) NOT NULL,
	"userID"	INTEGER NOT NULL,
	CONSTRAINT "fk_card_user" FOREIGN KEY("userID") REFERENCES "User"("id"),
	PRIMARY KEY("id")
);
CREATE TABLE IF NOT EXISTS "Order" (
	"id"	INTEGER NOT NULL,
	"email"	VARCHAR(45) NOT NULL,
	"address"	VARCHAR(45) NOT NULL,
	"phone"	VARCHAR(45) NOT NULL,
	"userID"	INTEGER NOT NULL,
	"shipping_fee"	INTEGER NOT NULL,
	"status"	VARCHAR(45),
	"rush_shipping_time"	VARCHAR(45),
	"province"	VARCHAR(45) NOT NULL,
	"shipping_instruction"	VARCHAR(45),
	"rush_shipping_instruction"	VARCHAR(45),
	"is_rush_shipping"	INTEGER,
	CONSTRAINT "fk_order_user" FOREIGN KEY("userID") REFERENCES "User"("id"),
	PRIMARY KEY("id" AUTOINCREMENT)
);
INSERT INTO "Media" VALUES (38,'book','story',32,12,'book2',29,'assets/images/book/book2.jpg');
INSERT INTO "Media" VALUES (39,'book','adventure',21,2,'book9',20,'assets/images/book/book9.jpg');
INSERT INTO "Media" VALUES (40,'book','adventure',73,11,'book10',69,'assets/images/book/book10.jpg');
INSERT INTO "Media" VALUES (41,'book','story',66,2,'book6',62,'assets/images/book/book6.jpg');
INSERT INTO "Media" VALUES (42,'cd','pop',24,6,'cd7',20,'assets/images/cd/cd7.jpg');
INSERT INTO "Media" VALUES (43,'book','story',50,7,'book12',44,'assets/images/book/book12.jpg');
INSERT INTO "Media" VALUES (44,'book','story',57,10,'book4',53,'assets/images/book/book4.jpg');
INSERT INTO "Media" VALUES (45,'cd','pop',66,8,'cd3',60,'assets/images/cd/cd3.jpg');
INSERT INTO "Media" VALUES (46,'book','bussiness',79,17,'book1',72,'assets/images/book/book1.jpg');
INSERT INTO "Media" VALUES (47,'dvd','cartoon',82,1,'dvd12',78,'assets/images/dvd/dvd12.jpg');
INSERT INTO "Media" VALUES (48,'book','science',25,10,'book3',22,'assets/images/book/book3.jpg');
INSERT INTO "Media" VALUES (49,'dvd','science fiction',75,3,'dvd10',74,'assets/images/dvd/dvd10.jpg');
INSERT INTO "Media" VALUES (50,'book','bussiness',26,4,'book11',19,'assets/images/book/book11.jpg');
INSERT INTO "Media" VALUES (51,'dvd','action',61,18,'dvd11',52,'assets/images/dvd/dvd11.jpg');
INSERT INTO "Media" VALUES (52,'cd','rock',40,4,'cd4',35,'assets/images/cd/cd4.jpg');
INSERT INTO "Media" VALUES (53,'dvd','action',70,16,'dvd9',60,'assets/images/dvd/dvd9.jpg');
INSERT INTO "Media" VALUES (54,'dvd','romance',47,19,'dvd2',39,'assets/images/dvd/dvd2.jpg');
INSERT INTO "Media" VALUES (55,'cd','pop',74,6,'cd2',71,'assets/images/cd/cd2.jpg');
INSERT INTO "Media" VALUES (56,'cd','rock',70,20,'cd1',60,'assets/images/cd/cd1.jpg');
INSERT INTO "Media" VALUES (57,'book','adventure',38,2,'book8',36,'assets/images/book/book8.jpg');
INSERT INTO "Media" VALUES (58,'dvd','cartoon',55,13,'dvd3',51,'assets/images/dvd/dvd3.jpg');
INSERT INTO "Media" VALUES (59,'dvd','action',28,1,'dvd6',26,'assets/images/dvd/dvd6.jpg');
INSERT INTO "Media" VALUES (60,'dvd','romance',38,17,'dvd4',33,'assets/images/dvd/dvd4.jpg');
INSERT INTO "Media" VALUES (61,'cd','pop',42,15,'cd12',38,'assets/images/cd/cd12.jpg');
INSERT INTO "Media" VALUES (62,'book','bussiness',34,15,'book5',29,'assets/images/book/book5.jpg');
INSERT INTO "Media" VALUES (63,'cd','ballad',99,4,'cd5',92,'assets/images/cd/cd5.jpg');
INSERT INTO "Media" VALUES (64,'cd','pop',38,10,'cd8',32,'assets/images/cd/cd8.jpg');
INSERT INTO "Media" VALUES (65,'cd','classic',37,10,'cd6',31,'assets/images/cd/cd6.jpg');
INSERT INTO "Media" VALUES (66,'book','bussiness',93,15,'book7',88,'assets/images/book/book7.jpg');
INSERT INTO "Media" VALUES (67,'cd','classic',25,5,'cd9',23,'assets/images/cd/cd9.jpg');
INSERT INTO "Media" VALUES (68,'dvd','romance',71,4,'dvd5',64,'assets/images/dvd/dvd5.jpg');
INSERT INTO "Media" VALUES (69,'cd','pop',97,17,'cd10',89,'assets/images/cd/cd10.jpg');
INSERT INTO "Media" VALUES (70,'dvd','romance',47,19,'dvd8',37,'assets/images/dvd/dvd8.jpg');
INSERT INTO "Media" VALUES (71,'dvd','science fiction',95,11,'dvd1',92,'assets/images/dvd/dvd1.jpg');
INSERT INTO "Media" VALUES (72,'dvd','action',23,9,'dvd7',16,'assets/images/dvd/dvd7.jpg');
INSERT INTO "Media" VALUES (73,'cd','classic',28,3,'cd11',20,'assets/images/cd/cd11.jpg');
INSERT INTO "Order" VALUES (1,'','','',1,47500,'ĐÃ DUYỆT','','Bình Định','','',0);
INSERT INTO "Order" VALUES (2,'','dfg','',1,32000,'ĐÃ DUYỆT','','Hà Nội','df','',0);
INSERT INTO "Order" VALUES (3,'','aaaaa','',1,22000,'ĐÃ TỪ CHỐI','aaaaa','Hà Nội','aaaaaa','aaaa',0);
INSERT INTO "Order" VALUES (4,'','aaa','aaaa',1,22000,'ĐÃ TỪ CHỐI','','Hà Nội','aaaa','',0);
INSERT INTO "Order" VALUES (5,'a@gmail.com','','0969871766',1,32000,'ĐÃ TỪ CHỐI','','Hà Nội','','',0);
INSERT INTO "Order" VALUES (6,'long@gmail.com','bạch mai','0978777898',1,27000,'CHỜ DUYỆT','17-01','Hà Nội','Bạch Mai','bạch mai 506',0);
INSERT INTO "Order" VALUES (7,'long@gmail.com','Haha','0969871789',1,22000,'CHỜ DUYỆT','aaa','Hà Nội','Haha','aaa',0);
INSERT INTO "Order" VALUES (8,'abc@gmail.com','Bạchmai','0969078908',1,32000,'ĐÃ TỪ CHỐI','','Hà Nội','Bạch mai','',0);
INSERT INTO "Order" VALUES (9,'hihi@gmail.com','lfhds','0969871766',1,37500,'ĐÃ TỪ CHỐI','','Hà Giang','sadfdsfsd','',0);
INSERT INTO "Order" VALUES (10,'aaa@gmail.com','','0969871766',1,47500,'ĐÃ TỪ CHỐI','','Thái Nguyên','','',0);
INSERT INTO "Order" VALUES (11,'aa@gmail.com','aaa','0969871766',1,32000,'ĐÃ DUYỆT','','Hà Nội','aaaa','',0);
INSERT INTO "Order" VALUES (12,'hieu@gmail.com','Lĩnh Nam','0987676888',1,32000,'ĐÃ TỪ CHỐI','','Hà Nội','LĨnh Nam','',0);
INSERT INTO "Order" VALUES (13,'chihieu@gmail.com','Bách Khoa','0968888888',1,32000,'ĐÃ DUYỆT','','Hà Nội','Bách Khoa','',0);
INSERT INTO "Order" VALUES (14,'baohieu888@gmail.com','hanoi','0916384476',1,32500,'ĐÃ TỪ CHỐI','','Bắc Giang','no','',0);
INSERT INTO "Order" VALUES (15,'baohieu888@gmail.com','hanoi','0916384476',1,47500,'CHỜ DUYỆT','','Bắc Kạn','990909','',0);
INSERT INTO "Order" VALUES (16,'baohieu888@gmail.com','hanoi','0916384476',1,42500,'CHỜ DUYỆT','','Bắc Kạn','no','',0);
INSERT INTO "Order" VALUES (17,'baohieu888@gmail.com','hanoi','0916384476',1,52500,'CHỜ DUYỆT','','Bắc Kạn','no','',0);
INSERT INTO "Order" VALUES (20,'nguyenchihieu1707@gmail.com','Bách khoa','0969871766',1,22000,'ĐÃ DUYỆT','','Hà Nội','Bách khoa','',0);
INSERT INTO "Order" VALUES (21,'nguyenchihieu1707@gmail.com','Cầu vượt','0969871766',1,22000,'CHỜ DUYỆT','','Hà Nội','Cầu vượt','',0);
INSERT INTO "Order" VALUES (22,'nguyenchihieu1707@gmail.com','Bạch Mai','0969871766',1,22000,'CHỜ DUYỆT','','Hà Nội','Bạch Mai','',0);
CREATE INDEX IF NOT EXISTS "OrderMedia.fk_ordermedia_order_idx" ON "OrderMedia" (
	"orderID"
);
CREATE INDEX IF NOT EXISTS "Transaction.fk_transaction_order_idx" ON "Transaction" (
	"orderID"
);
CREATE INDEX IF NOT EXISTS "Card.fk_card_user_idx" ON "Card" (
	"userID"
);
CREATE INDEX IF NOT EXISTS "Order.fk_order_user_idx" ON "Order" (
	"userID"
);
COMMIT;
