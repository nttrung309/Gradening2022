CREATE TABLE category (
	id INT NOT NULL PRIMARY KEY,
	name VARCHAR(100),
	image TEXT
);

CREATE TABLE product (
	id INT NOT NULL PRIMARY KEY,
	idCategory INT NOT NULL,
	name VARCHAR(100),
	price BIGINT,
	discount BIGINT,
	image TEXT,
	quantity INT,
	rating VARCHAR(100),
	note TEXT
);

CREATE TABLE product_detail (
	idProduct INT,
	water VARCHAR(100),
	sunlight VARCHAR(100)
);
	
CREATE TABLE cart (
	id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	idUser INT NOT NULL,
	total BIGINT,
	state INT
);

CREATE TABLE cart_detail (
	id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	idCart INT NOT NULL,
	idProduct INT NOT NULL,
	quantity INT
);

CREATE TABLE order_ (
	id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	idUser INT NOT NULL,
	idAddress INT NOT NULL,
	date DATE,
	state VARCHAR(100),
	provisional_money BIGINT,
	shipping BIGINT,
	total BIGINT
);

CREATE TABLE order_detail (
	id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
idOrder INT NOT NULL,
	idProduct INT NOT NULL,
	quantity INT
);

CREATE TABLE user_address (
	id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	idUser INT NOT NULL,
	address_name VARCHAR(100),
	address_number VARCHAR(100),
	address_line VARCHAR(1000),
	province VARCHAR(100),
	district VARCHAR(100),
	ward VARCHAR(100)
);

CREATE TABLE users (
	id INT NOT NULL PRIMARY KEY AUTO_INCREMENT, 
	username VARCHAR(100),
	password VARCHAR(1000),
	name VARCHAR(100),
	date DATE,
	gender VARCHAR(10),
	telephone VARCHAR(15),
	email VARCHAR(100)
);

INSERT INTO `category`(`id`, `name`, `image`) VALUES ('1','Plants','https://roadtouni.000webhostapp.com/image/plant.png');
INSERT INTO `category`(`id`, `name`, `image`) VALUES ('2','Tools','https://roadtouni.000webhostapp.com/image/tool.png');
INSERT INTO `category`(`id`, `name`, `image`) VALUES ('3','Seeds','https://roadtouni.000webhostapp.com/image/seed.png');
INSERT INTO `category`(`id`, `name`, `image`) VALUES ('4','Pots','https://roadtouni.000webhostapp.com/image/pot.png');
INSERT INTO `category`(`id`, `name`, `image`) VALUES ('5','Accssories','https://roadtouni.000webhostapp.com/image/accessory.png');
INSERT INTO `category`(`id`, `name`, `image`) VALUES ('6','Service','https://roadtouni.000webhostapp.com/image/service.png');

INSERT INTO `product`(`id`, `idCategory`, `name`, `price`, `discount`, `image`, `quantity`, `rating`, `note`) VALUES ('1','1','Kim Ngân','100000','25000','https://roadtouni.000webhostapp.com/image/product/kimngan.png','50','4.5','');
INSERT INTO `product`(`id`, `idCategory`, `name`, `price`, `discount`, `image`, `quantity`, `rating`, `note`) VALUES ('2','1','Phát tài','200000','35000','https://roadtouni.000webhostapp.com/image/product/phattai.png','60','5','');
INSERT INTO `product`(`id`, `idCategory`, `name`, `price`, `discount`, `image`, `quantity`, `rating`, `note`) VALUES ('3','2','Xẻng','100000','5000','https://roadtouni.000webhostapp.com/image/product/bay.png','10','5','');
INSERT INTO `product`(`id`, `idCategory`, `name`, `price`, `discount`, `image`, `quantity`, `rating`, `note`) VALUES ('4','2','Bay','50000','5000','https://roadtouni.000webhostapp.com/image/product/xeng.png','12','5','');
INSERT INTO `product`(`id`, `idCategory`, `name`, `price`, `discount`, `image`, `quantity`, `rating`, `note`) VALUES ('5','3','Hoa Hồng','100000','0','https://roadtouni.000webhostapp.com/image/product/hoahong.png','21','5','');
INSERT INTO `product`(`id`, `idCategory`, `name`, `price`, `discount`, `image`, `quantity`, `rating`, `note`) VALUES ('6','3','Hoa Hướng Dương','5000','0','https://roadtouni.000webhostapp.com/image/product/hoahuongduong.png','12','5','');
INSERT INTO `product`(`id`, `idCategory`, `name`, `price`, `discount`, `image`, `quantity`, `rating`, `note`) VALUES ('7','4','Chậu hạt dẻ','25000','2000','https://roadtouni.000webhostapp.com/image/product/chauhatde.png','9','5','');
INSERT INTO `product`(`id`, `idCategory`, `name`, `price`, `discount`, `image`, `quantity`, `rating`, `note`) VALUES ('8','4','Chậu men vuông','20000','5000','https://roadtouni.000webhostapp.com/image/product/chaumenvuong.png','6','4','');
INSERT INTO `product`(`id`, `idCategory`, `name`, `price`, `discount`, `image`, `quantity`, `rating`, `note`) VALUES ('9','4','Chậu tự vẽ','50000','0','https://roadtouni.000webhostapp.com/image/product/chaumenve.png','10','5','');
INSERT INTO `product`(`id`, `idCategory`, `name`, `price`, `discount`, `image`, `quantity`, `rating`, `note`) VALUES ('10','5','Kệ','250000','1000','https://roadtouni.000webhostapp.com/image/product/ke.png','25','3.5','');
INSERT INTO `product`(`id`, `idCategory`, `name`, `price`, `discount`, `image`, `quantity`, `rating`, `note`) VALUES ('11','5','Hàng rào','20000','1000','https://roadtouni.000webhostapp.com/image/product/hangrao.png','52','3','');
INSERT INTO `product`(`id`, `idCategory`, `name`, `price`, `discount`, `image`, `quantity`, `rating`, `note`) VALUES ('12','6','Home Decor','100000','0','https://roadtouni.000webhostapp.com/image/decordHome.png','10','4','');
INSERT INTO `product`(`id`, `idCategory`, `name`, `price`, `discount`, `image`, `quantity`, `rating`, `note`) VALUES ('13','6','Organize garden','150000','0','https://roadtouni.000webhostapp.com/image/decordOffice.png','10','4','');
INSERT INTO `product`(`id`, `idCategory`, `name`, `price`, `discount`, `image`, `quantity`, `rating`, `note`) VALUES ('14','6','Office Decor','300000','0','https://roadtouni.000webhostapp.com/image/decordOffice.png','10','4','');
INSERT INTO `product`(`id`, `idCategory`, `name`, `price`, `discount`, `image`, `quantity`, `rating`, `note`) VALUES ('15','6','Monthly Care','500000','0','https://roadtouni.000webhostapp.com/image/careMonthly.png','10','5','');
INSERT INTO `product`(`id`, `idCategory`, `name`, `price`, `discount`, `image`, `quantity`, `rating`, `note`) VALUES ('16','1','Lan Ý','100000','20000','https://roadtouni.000webhostapp.com/image/product/lany.png','30','4','');
INSERT INTO `product`(`id`, `idCategory`, `name`, `price`, `discount`, `image`, `quantity`, `rating`, `note`) VALUES ('17','1','Bàng Sing','150000','10000','https://roadtouni.000webhostapp.com/image/product/bangsing.png','40','3','');
INSERT INTO `product`(`id`, `idCategory`, `name`, `price`, `discount`, `image`, `quantity`, `rating`, `note`) VALUES ('18','1','Hương Thảo','120000','10000','https://roadtouni.000webhostapp.com/image/product/huongthao.png','50','2','');
INSERT INTO `product`(`id`, `idCategory`, `name`, `price`, `discount`, `image`, `quantity`, `rating`, `note`) VALUES ('19','1','Lưỡi Hổ','200000','10000','https://roadtouni.000webhostapp.com/image/product/luoiho.png','60','3','');
INSERT INTO `product`(`id`, `idCategory`, `name`, `price`, `discount`, `image`, `quantity`, `rating`, `note`) VALUES ('20','1','Lưỡi Hỗ Ombre','250000','10000','https://roadtouni.000webhostapp.com/image/product/luoiho1.png','70','5','');

INSERT INTO `product_detail`(`idProduct`, `water`, `sunlight`) VALUES ('1','Weekly', 'Medium');
INSERT INTO `product_detail`(`idProduct`, `water`, `sunlight`) VALUES ('2','Weekly', 'Medium');
INSERT INTO `product_detail`(`idProduct`, `water`, `sunlight`) VALUES ('16','Weekly', 'Medium');
INSERT INTO `product_detail`(`idProduct`, `water`, `sunlight`) VALUES ('17','Weekly', 'Medium');
INSERT INTO `product_detail`(`idProduct`, `water`, `sunlight`) VALUES ('18','Daily', 'Medium');
INSERT INTO `product_detail`(`idProduct`, `water`, `sunlight`) VALUES ('19','Weekly', 'Medium');
INSERT INTO `product_detail`(`idProduct`, `water`, `sunlight`) VALUES ('20','Weekly', 'High');



