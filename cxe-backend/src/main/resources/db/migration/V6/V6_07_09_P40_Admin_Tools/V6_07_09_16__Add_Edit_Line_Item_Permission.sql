-- Add ROLE_P40_EDIT_LINE_ITEM permission
INSERT INTO `permission` (`perm_name`, `perm_desc`, `perm_fixed`) VALUES ('ROLE_P40_EDIT_LINE_ITEM', 'Defaults user access to edit on line items if access has not been specifically defined for line item.', '1');