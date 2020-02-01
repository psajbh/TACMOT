--- update user_role
insert into user_role (user_id, role_id)
select budges_user_id, 1 from user where role = 'R2AppMgr';

insert into user_role (user_id, role_id)
select budges_user_id, 2 from user where role = 'R2User';
