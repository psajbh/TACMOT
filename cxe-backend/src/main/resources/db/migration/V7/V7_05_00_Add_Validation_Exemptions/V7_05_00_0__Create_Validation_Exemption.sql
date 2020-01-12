-- Validation exemptions table by organization for both R2 and P40.
-- Either rdte_rule or validation_rule_fk must be non-null.  The former
-- is for R2 exemptions and the latter is for P40 exemptions.
-- When a validation is first exempted, a record will be created here with
-- status = 'A'.  Later, if the rule is turned back on, status = 'I'.  This
-- provides a minor audit trail (via creation/modification date/user)
-- of who activated/disabled the rule for an organization.

create table validation_exemption
(
  id integer unsigned not null auto_increment primary key comment "Primary Key.",
  date_created timestamp not null DEFAULT CURRENT_TIMESTAMP comment "Date exemption was created.",
  date_modified timestamp not null DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment "Date exemption was modified.",
  created_by_user varchar(255) not null comment "User who created initial exemption.",
  modified_by_user varchar(255) not null comment "User who modified exemption.",
  status enum('A', 'I') not null comment "Active or Inactive exemption.",
  rdte_rule varchar(25) comment "R2 rule number.",
  organization_fk integer unsigned not null comment "FK to Service/Agency table.",
  validation_rule_fk integer unsigned comment "FK to P40 Validation Rule table."
)
engine=InnoDB
default charset=utf8
comment="Many-to-Many join table Service/Agency <<->> RuleStatus/ValidationRule (shared join table).";
