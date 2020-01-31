
USE `pizzeria`;

INSERT INTO `role` (`id_role`, `role`) VALUES
(1, 'kelner/sprzedawca'),
(2, 'obsługa'),
(3, 'manager'),
(4, 'administrator');

INSERT INTO `size` (`id_size`, `size`) VALUES
(1, '26 cm'),
(2, '33 cm'),
(3, '41 cm'),
(4, '250 ml'),
(5, '500 ml');

INSERT INTO `status` (`id_status`, `status`) VALUES
(1, 'oczekujące'),
(2, 'w realizacji'),
(3, 'gotowe do wydania'),
(4, 'zrealizowane'),
(5, 'anulowane');

INSERT INTO `employees` (`id_employee`, `name`, `surname`, `login`, `password`, `id_role`) VALUES
(1, 'Jakub', 'Burzański', 'kelner', 'kelner', 1),
(2, 'Jakub', 'Koper', 'kucharz', 'kucharz', 2),
(3, 'Katarzyna', 'Nalepa', 'manager', 'manager', 3),
(4, 'Wojciech', 'Pochroń', 'admin', 'admin', 4);

INSERT INTO `menu` (`id_product`, `name`, `price`, `id_size`) VALUES
(1, 'Margherita', 16, 1),
(2, 'Margherita', 21, 2),
(3, 'Margherita', 25, 3),
(4, 'Hawajska', 18, 1),
(5, 'Hawajska', 23, 2),
(6, 'Hawajska', 27, 3),
(7, 'Pepperone', 23, 1),
(8, 'Pepperone', 28, 2),
(9, 'Pepperone', 35, 3),
(10, 'Coca-Cola', 4, 4),
(11, 'Coca-Cola', 7, 5),
(12, 'Sprite', 4, 4),
(13, 'Sprite', 7, 5);

INSERT INTO `orders` (`id_order`, `id_status`) VALUES
(3, 1),
(2, 2),
(4, 3),
(1, 4),
(5, 5);

INSERT INTO `orders_menu` (`id_order`, `id_product`) VALUES
(1, 1),
(1, 7),
(1, 10),
(1, 10),
(2, 3),
(2, 13),
(3, 9),
(3, 9),
(3, 6),
(3, 13),
(3, 12),
(3, 10),
(4, 1),
(4, 2),
(4, 12),
(4, 13),
(4, 7),
(4, 7),
(4, 10),
(5, 4),
(5, 4),
(5, 4),
(5, 12);