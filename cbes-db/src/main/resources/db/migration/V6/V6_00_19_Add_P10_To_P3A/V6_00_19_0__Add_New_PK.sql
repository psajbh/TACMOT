ALTER TABLE `proc_advance_rqmt` ADD id int(10) UNSIGNED NOT NULL AUTO_INCREMENT AFTER `ar_ID`,
  ADD UNIQUE KEY `id` (`id`);