DROP DATABASE IF EXISTS pizzeria;

CREATE USER 'pizzeria'@'localhost' IDENTIFIED BY 'pizzeria';

CREATE DATABASE IF NOT EXISTS pizzeria;

GRANT ALL PRIVILEGES ON pizzeria.* TO 'pizzeria'@'localhost';

USE pizzeria;

CREATE TABLE `employees` (
  `id_employee` int(11) NOT NULL,
  `name` varchar(30) NOT NULL,
  `surname` varchar(30) NOT NULL,
  `login` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL,
  `id_role` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE `menu` (
  `id_product` int(11) NOT NULL,
  `name` varchar(30) NOT NULL,
  `price` int(11) NOT NULL,
  `id_size` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE `orders` (
  `id_order` int(11) NOT NULL,
  `id_status` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE `orders_menu` (
  `id_order` int(11) NOT NULL,
  `id_product` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE `role` (
  `id_role` int(11) NOT NULL,
  `role` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE `size` (
  `id_size` int(11) NOT NULL,
  `size` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE `status` (
  `id_status` int(11) NOT NULL,
  `status` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

ALTER TABLE `employees`
  ADD PRIMARY KEY (`id_employee`),
  ADD  KEY `id_role` (`id_role`);

ALTER TABLE `menu`
  ADD PRIMARY KEY (`id_product`),
  ADD  KEY `id_size` (`id_size`);

ALTER TABLE `orders`
  ADD PRIMARY KEY (`id_order`),
  ADD KEY `id_status` (`id_status`);

ALTER TABLE `orders_menu`
  ADD  KEY `id_order` (`id_order`),
  ADD  KEY `id_product` (`id_product`);

ALTER TABLE `role`
  ADD PRIMARY KEY (`id_role`);

ALTER TABLE `size`
  ADD PRIMARY KEY (`id_size`);

ALTER TABLE `status`
  ADD PRIMARY KEY (`id_status`);

ALTER TABLE `employees`
  MODIFY `id_employee` int(11) NOT NULL AUTO_INCREMENT;
  
ALTER TABLE `orders` 
  MODIFY `id_order` int(11) NOT NULL AUTO_INCREMENT;

ALTER TABLE `menu`
  MODIFY `id_product` int(11) NOT NULL AUTO_INCREMENT;

ALTER TABLE `role`
  MODIFY `id_role` int(11) NOT NULL AUTO_INCREMENT;

ALTER TABLE `size`
  MODIFY `id_size` int(11) NOT NULL AUTO_INCREMENT;

ALTER TABLE `status`
  MODIFY `id_status` int(11) NOT NULL AUTO_INCREMENT;

ALTER TABLE `orders_menu`
  ADD CONSTRAINT `menu_ibfk_1` FOREIGN KEY (`id_product`) REFERENCES `menu` (`id_product`);

ALTER TABLE `orders_menu`
  ADD CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`id_order`) REFERENCES `orders` (`id_order`);

ALTER TABLE `employees`
  ADD CONSTRAINT `role_ibfk_1` FOREIGN KEY (`id_role`) REFERENCES `role` (`id_role`);

ALTER TABLE `menu`
  ADD CONSTRAINT `size_ibfk_1` FOREIGN KEY (`id_size`) REFERENCES `size` (`id_size`);

ALTER TABLE `orders`
  ADD CONSTRAINT `status_ibfk_1` FOREIGN KEY (`id_status`) REFERENCES `status` (`id_status`);
