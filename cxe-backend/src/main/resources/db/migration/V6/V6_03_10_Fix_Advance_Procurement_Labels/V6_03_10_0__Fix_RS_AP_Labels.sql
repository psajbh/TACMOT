-- Rename Advanced to Advance in the Resource Summary (and elsewhere that use the lookup values).

update proc_resource_summ_entry_lkup set rsel_desc = 'Less PY Advance Procurement' where rsel_desc = 'Less PY Advanced Procurement';
update proc_resource_summ_entry_lkup set rsel_desc = 'Plus CY Advance Procurement' where rsel_desc = 'Plus CY Advanced Procurement';
