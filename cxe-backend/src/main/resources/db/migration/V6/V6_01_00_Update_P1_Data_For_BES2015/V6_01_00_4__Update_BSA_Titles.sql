update proc_budget_sub_activity set bsa_title='Artillery and Other Weapons'
where (select ba_id from proc_budget_activity ba, APPROP_ACCOUNT a where ba.ba_num=4 and ba.ba_title='Other Weapons' and a.BUDGES_APPROP_ACCT_CODE='1507N' and a.BUDGES_APPROP_ACCT_NAME='Weapons Procurement, Navy' and a.BUDGES_APPROP_ACCT_ID = ba.aa_id)
and bsa_title='Artillery And Other Weapons' and bsa_num=2;

update proc_budget_sub_activity set bsa_title='ASW Electronic Equipment'
where (select ba_id from proc_budget_activity ba, APPROP_ACCOUNT a where ba.ba_num=2 and ba.ba_title='Communications & Electronics Equip' and a.BUDGES_APPROP_ACCT_CODE='1810N' and a.BUDGES_APPROP_ACCT_NAME='Other Procurement, Navy' and a.BUDGES_APPROP_ACCT_ID = ba.aa_id)
and bsa_title='Asw Electronic Equipment' and bsa_num=3;

update proc_budget_sub_activity set bsa_title='ASW Support Equipment'
where (select ba_id from proc_budget_activity ba, APPROP_ACCOUNT a where ba.ba_num=4 and ba.ba_title='Ordnance Support Equipment' and a.BUDGES_APPROP_ACCT_CODE='1810N' and a.BUDGES_APPROP_ACCT_NAME='Other Procurement, Navy' and a.BUDGES_APPROP_ACCT_ID = ba.aa_id)
and bsa_title='Asw Support Equipment' and bsa_num=5;

update proc_budget_sub_activity set bsa_title='Auxiliaries, Craft and Prior Yr Program Cost'
where (select ba_id from proc_budget_activity ba, APPROP_ACCOUNT a where ba.ba_num=5 and ba.ba_title='Auxiliaries, Craft, and Prior-Year Program Costs' and a.BUDGES_APPROP_ACCT_CODE='1611N' and a.BUDGES_APPROP_ACCT_NAME='Shipbuilding & Conversion, Navy' and a.BUDGES_APPROP_ACCT_ID = ba.aa_id)
and bsa_title='Auxiliaries, Craft And Prior Yr Program Cost' and bsa_num=1;

update proc_budget_sub_activity set bsa_title='Command and Control System (Non-Tel)'
where (select ba_id from proc_budget_activity ba, APPROP_ACCOUNT a where ba.ba_num=4 and ba.ba_title='Communications & electronics equipment' and a.BUDGES_APPROP_ACCT_CODE='1109N' and a.BUDGES_APPROP_ACCT_NAME='Procurement, Marine Corps' and a.BUDGES_APPROP_ACCT_ID = ba.aa_id)
and bsa_title='Command And Control System (Non-Tel)' and bsa_num=14;

update proc_budget_sub_activity set bsa_title='Command and Control Systems'
where (select ba_id from proc_budget_activity ba, APPROP_ACCOUNT a where ba.ba_num=4 and ba.ba_title='Communications & electronics equipment' and a.BUDGES_APPROP_ACCT_CODE='1109N' and a.BUDGES_APPROP_ACCT_NAME='Procurement, Marine Corps' and a.BUDGES_APPROP_ACCT_ID = ba.aa_id)
and bsa_title='Command And Control Systems' and bsa_num=4;

update proc_budget_sub_activity set bsa_title='Engineer and Other Equipment'
where (select ba_id from proc_budget_activity ba, APPROP_ACCOUNT a where ba.ba_num=6 and ba.ba_title='Engineer and Other Equipment' and a.BUDGES_APPROP_ACCT_CODE='1109N' and a.BUDGES_APPROP_ACCT_NAME='Procurement, Marine Corps' and a.BUDGES_APPROP_ACCT_ID = ba.aa_id)
and bsa_title='Engineer And Other Equipment' and bsa_num=1;

update proc_budget_sub_activity set bsa_title='FBM Support Equipment'
where (select ba_id from proc_budget_activity ba, APPROP_ACCOUNT a where ba.ba_num=4 and ba.ba_title='Ordnance Support Equipment' and a.BUDGES_APPROP_ACCT_CODE='1810N' and a.BUDGES_APPROP_ACCT_NAME='Other Procurement, Navy' and a.BUDGES_APPROP_ACCT_ID = ba.aa_id)
and bsa_title='Fbm Support Equipment' and bsa_num=4;

update proc_budget_sub_activity set bsa_title='Guns and Gun Mounts'
where (select ba_id from proc_budget_activity ba, APPROP_ACCOUNT a where ba.ba_num=4 and ba.ba_title='Other Weapons' and a.BUDGES_APPROP_ACCT_CODE='1507N' and a.BUDGES_APPROP_ACCT_NAME='Weapons Procurement, Navy' and a.BUDGES_APPROP_ACCT_ID = ba.aa_id)
and bsa_title='Guns And Gun Mounts' and bsa_num=1;

update proc_budget_sub_activity set bsa_title='Mod of Weapons and Other Combat Veh'
where (select ba_id from proc_budget_activity ba, APPROP_ACCOUNT a where ba.ba_num=2 and ba.ba_title='Weapons and Other Combat Vehicles' and a.BUDGES_APPROP_ACCT_CODE='2033A' and a.BUDGES_APPROP_ACCT_NAME='Procurement of W&TCV, Army' and a.BUDGES_APPROP_ACCT_ID = ba.aa_id)
and bsa_title='Mod Of Weapons And Other Combat Veh' and bsa_num=20;

update proc_budget_sub_activity set bsa_title='Mod of Torpedoes and Related Equip'
where (select ba_id from proc_budget_activity ba, APPROP_ACCOUNT a where ba.ba_num=3 and ba.ba_title='Torpedoes and Related Equipment' and a.BUDGES_APPROP_ACCT_CODE='1507N' and a.BUDGES_APPROP_ACCT_NAME='Weapons Procurement, Navy' and a.BUDGES_APPROP_ACCT_ID = ba.aa_id)
and bsa_title='Mod Of Torpedoes And Related Equip' and bsa_num=2;

update proc_budget_sub_activity set bsa_title='Modification of Aircraft'
where (select ba_id from proc_budget_activity ba, APPROP_ACCOUNT a where ba.ba_num=5 and ba.ba_title='Modification of Aircraft' and a.BUDGES_APPROP_ACCT_CODE='1506N' and a.BUDGES_APPROP_ACCT_NAME='Aircraft Procurement, Navy' and a.BUDGES_APPROP_ACCT_ID = ba.aa_id)
and bsa_title='Modification Of Aircraft' and bsa_num=1;

update proc_budget_sub_activity set bsa_title='Modification of Aircraft'
where (select ba_id from proc_budget_activity ba, APPROP_ACCOUNT a where ba.ba_num=2 and ba.ba_title='Modification of Aircraft' and a.BUDGES_APPROP_ACCT_CODE='2031A' and a.BUDGES_APPROP_ACCT_NAME='Aircraft Procurement, Army' and a.BUDGES_APPROP_ACCT_ID = ba.aa_id)
and bsa_title='Modification Of Aircraft' and bsa_num=1;

update proc_budget_sub_activity set bsa_title='Modification of Guns And Gun Mounts'
where (select ba_id from proc_budget_activity ba, APPROP_ACCOUNT a where ba.ba_num=4 and ba.ba_title='Other Weapons' and a.BUDGES_APPROP_ACCT_CODE='1507N' and a.BUDGES_APPROP_ACCT_NAME='Weapons Procurement, Navy' and a.BUDGES_APPROP_ACCT_ID = ba.aa_id)
and bsa_title='Modification Of Guns And Gun Mounts' and bsa_num=2;

update proc_budget_sub_activity set bsa_title='Modification of Missiles'
where (select ba_id from proc_budget_activity ba, APPROP_ACCOUNT a where ba.ba_num=1 and ba.ba_title='Ballistic Missiles' and a.BUDGES_APPROP_ACCT_CODE='1507N' and a.BUDGES_APPROP_ACCT_NAME='Weapons Procurement, Navy' and a.BUDGES_APPROP_ACCT_ID = ba.aa_id)
and bsa_title='Modification Of Missiles' and bsa_num=2;

update proc_budget_sub_activity set bsa_title='Modification of Missiles'
where (select ba_id from proc_budget_activity ba, APPROP_ACCOUNT a where ba.ba_num=2 and ba.ba_title='Other Missiles' and a.BUDGES_APPROP_ACCT_CODE='1507N' and a.BUDGES_APPROP_ACCT_NAME='Weapons Procurement, Navy' and a.BUDGES_APPROP_ACCT_ID = ba.aa_id)
and bsa_title='Modification Of Missiles' and bsa_num=2;

update proc_budget_sub_activity set bsa_title='Modification of Tracked Combat Vehicles'
where (select ba_id from proc_budget_activity ba, APPROP_ACCOUNT a where ba.ba_num=1 and ba.ba_title='Tracked Combat Vehicles' and a.BUDGES_APPROP_ACCT_CODE='2033A' and a.BUDGES_APPROP_ACCT_NAME='Procurement of W&TCV, Army' and a.BUDGES_APPROP_ACCT_ID = ba.aa_id)
and bsa_title='Modification Of Tracked Combat Vehicles' and bsa_num=20;

update proc_budget_sub_activity set bsa_title='Organization and Base'
where (select ba_id from proc_budget_activity ba, APPROP_ACCOUNT a where ba.ba_num=3 and ba.ba_title='Electronics and Telecommunications Equip' and a.BUDGES_APPROP_ACCT_CODE='3080F' and a.BUDGES_APPROP_ACCT_NAME='Other Procurement, Air Force' and a.BUDGES_APPROP_ACCT_ID = ba.aa_id)
and bsa_title='Organization And Base' and bsa_num=7;

update proc_budget_sub_activity set bsa_title='Repair and Test Equipment'
where (select ba_id from proc_budget_activity ba, APPROP_ACCOUNT a where ba.ba_num=4 and ba.ba_title='Communications & electronics equipment' and a.BUDGES_APPROP_ACCT_CODE='1109N' and a.BUDGES_APPROP_ACCT_NAME='Procurement, Marine Corps' and a.BUDGES_APPROP_ACCT_ID = ba.aa_id)
and bsa_title='Repair And Test Equipment' and bsa_num=7;

update proc_budget_sub_activity set bsa_title='Staff and Infrastructure'
where (select ba_id from proc_budget_activity ba, APPROP_ACCOUNT a where ba.ba_num=4 and ba.ba_title='Staff and Infrastructure' and a.BUDGES_APPROP_ACCT_CODE='2093A' and a.BUDGES_APPROP_ACCT_NAME='Joint Impr Explosive Dev Defeat Fund' and a.BUDGES_APPROP_ACCT_ID = ba.aa_id)
and bsa_title='Staff And Infrastructure' and bsa_num=1;

update proc_budget_sub_activity set bsa_title='Surface-to-Air Missile System'
where (select ba_id from proc_budget_activity ba, APPROP_ACCOUNT a where ba.ba_num=2 and ba.ba_title='Other Missiles' and a.BUDGES_APPROP_ACCT_CODE='2032A' and a.BUDGES_APPROP_ACCT_NAME='Missile Procurement, Army' and a.BUDGES_APPROP_ACCT_ID = ba.aa_id)
and bsa_title='Surface-To-Air Missile System' and bsa_num=10;

update proc_budget_sub_activity set bsa_title='Test Measure and Dig Equipment (TMD)'
where (select ba_id from proc_budget_activity ba, APPROP_ACCOUNT a where ba.ba_num=3 and ba.ba_title='Other Support Equipment' and a.BUDGES_APPROP_ACCT_CODE='2035A' and a.BUDGES_APPROP_ACCT_NAME='Other Procurement, Army' and a.BUDGES_APPROP_ACCT_ID = ba.aa_id)
and bsa_title='Test Measure And Dig Equipment (TMD)' and bsa_num=80;

update proc_budget_sub_activity set bsa_title='Torpedoes and Related Equip'
where (select ba_id from proc_budget_activity ba, APPROP_ACCOUNT a where ba.ba_num=3 and ba.ba_title='Torpedoes and Related Equipment' and a.BUDGES_APPROP_ACCT_CODE='1507N' and a.BUDGES_APPROP_ACCT_NAME='Weapons Procurement, Navy' and a.BUDGES_APPROP_ACCT_ID = ba.aa_id)
and bsa_title='Torpedoes And Related Equip' and bsa_num=1;

update proc_budget_sub_activity set bsa_title='Research and Development'
where (select ba_id from proc_budget_activity ba, APPROP_ACCOUNT a where ba.ba_num=4 and ba.ba_title='Research and Development' and a.BUDGES_APPROP_ACCT_CODE='4557N' and a.BUDGES_APPROP_ACCT_NAME='National Defense Sealift Fund' and a.BUDGES_APPROP_ACCT_ID = ba.aa_id)
and bsa_title='Research And Development' and bsa_num=900;

update proc_budget_sub_activity set bsa_status_flag='I' where bsa_ID>'0' and  bsa_title='Cancelled Account Adjustm';

update proc_budget_sub_activity set bsa_status_flag='I' where bsa_ID>'0' and bsa_title='CTR_SCVS_FTE';

update proc_budget_sub_activity set bsa_status_flag='I' where bsa_ID>'0' and bsa_title='Defense-Wide';

update proc_budget_sub_activity set bsa_status_flag='I' where bsa_ID>'0' and bsa_title='DoD Mobilization Alterations';

update proc_budget_sub_activity set bsa_status_flag='I' where bsa_ID>'0' and bsa_title='LG Med Spd Ro/Ro Maintenance';

update proc_budget_sub_activity set bsa_status_flag='I' where bsa_ID>'0' and bsa_title='MARAD Ship Financing Guarantee Program';

update proc_budget_sub_activity set bsa_status_flag='I' where bsa_ID>'0' and bsa_title='National Def Sealift Vessel';

update proc_budget_sub_activity set bsa_status_flag='I' where bsa_ID>'0' and bsa_title='NFIP';

update proc_budget_sub_activity set bsa_status_flag='I' where bsa_ID>'0' and bsa_title='Post Delivery and Outfitting';

update proc_budget_sub_activity set bsa_status_flag='I' where bsa_ID>'0' and bsa_title='Ready Reserve Force';

update proc_budget_sub_activity set bsa_status_flag='I' where bsa_ID>'0' and bsa_title='TAH Maintenance';
 
update proc_budget_sub_activity set bsa_status_flag='I' where bsa_ID>'0' and bsa_title='T-AKE';

update proc_budget_sub_activity set bsa_status_flag='I' where bsa_ID>'0' and bsa_title='Undistributed NFIP Adjustments';