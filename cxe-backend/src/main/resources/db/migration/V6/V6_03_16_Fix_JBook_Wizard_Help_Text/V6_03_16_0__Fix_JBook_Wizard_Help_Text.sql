-- Change insure to ensure. (P40)
update `config`
set cf_value = '<ul><li>Ensure "Generate Volume Exhibit P-1" is deselected and select any additional Line Item Table of Contents Options. Click "Continue" to open the "P-40 Volume Breakdown" page.<ul><li>Note: When building a Justification Book via the wizard, do NOT select "Generate Volume Exhibit P-1". Instead, for the PB budget cycle, the "Comptroller P-1 Exhibit" must be a PDF from PRCP and included as an attachment to the Justification Book. Follow the wizard steps to upload it in "Step 6 Attachments" below.</li></ul></li></ul>'
where cf_name = 'jbwiz.p40TableOfContentsHelp' and cf_cycle = 'PB';

-- Remove letter and roman numeral prefixes (R2)
update `config`
set cf_value = '<ul><li>Deselect "Generate Volume Exhibit R-1", and click "Continue" to open the "PE Volume Breakdown" page.<ul><li>Note: When building a Justification Book via the wizard, do NOT select "Generate Volume Exhibit R-1". Instead, for the PB budget cycle, the "Comptroller R-1 Exhibit" must be a PDF from PRCP and included as an attachment to the Justification Book. Follow the wizard steps to upload it in "Step 6 Attachments" below.</li></ul></li></ul>'
where cf_name = 'jbwiz.r2TableOfContentsHelp' and cf_cycle = 'PB';

-- Update default text (P40)
update `config`
set cf_value = '<ul><li><h4>Creating a Single Volume J-Book</h4><ul><li>Ensure "Generate Volume Exhibit P-1" is selected and select any additional Line Item Table of Contents Options. Click "Continue" to open the "P-40 Volume Breakdown" page.</li></ul></li><li><h4>Creating a Multi-Volume J-Book</h4><ul><li>Ensure "Generate Cross-Volume Exhibit P-1" is selected and select any additional Line Item Table of Contents Options. Click "Continue" to open the "P-40 Volume Breakdown" page.</li></ul></li></ul>'
where cf_name = 'jbwiz.p40TableOfContentsHelp' and cf_cycle != 'PB';

-- Update default text (R2)
update `config`
set cf_value = '<ul><li><h4>Creating a Single Volume J-Book</h4><ul><li>Ensure "Generate Volume Exhibit R-1" is selected and select any additional Program Element Table of Contents Options. Click "Continue" to open the "PE Volume Breakdown" page.</li></ul></li><li><h4>Creating a Multi-Volume J-Book</h4><ul><li>Ensure "Generate Cross-Volume Exhibit R-1" is selected and select any additional Program Element Table of Contents Options. Click "Continue" to open the "PE Volume Breakdown" page.</li></ul></li></ul>'
where cf_name = 'jbwiz.r2TableOfContentsHelp' and cf_cycle != 'PB';

-- Remove letter and roman numeral prefixes (P40)
update `config`
set cf_value = '<ul><li>Select the "Select from P-40s That Exist on This System" radio button and then click "Select P-40s" button; which will open the "Select Exhibit P-40s" page.<ul><li>Select the appropriate P-40s from the "Available Exhibit P-40''s" list on the left and add these to the "Selected" list on the right by clicking on the "Add All" or "Add All Highlighted" button.<ul><li>Hint: You can narrow your selections by entering appropriate parameters in the "Filter" section at the top of the Available Exhibit P-40''s listing or adding additional Columns to the table and sorting by selected Column values (e.g., Agency, BA#, etc.).</li></ul></li><li>Once P-40 selections are complete click the "Return to JB Wizard With Selections" button, scroll to the bottom of the page, and click "Continue" to open the "Volumes" page.</li></ul></li></ul>'
where cf_name = 'jbwiz.p40ExhibitSelectHelp';

-- Remove letter and roman numeral prefixes (R2)
update `config`
set cf_value = '<ul><li>Select the "Select from Exhibit R-2s That Exist on This System" radio button and then click "Select R-2s" button; which will open the "Exhibit R-2 Selections for Document Build" page.<ul><li>Select the appropriate R-2s from the "Available Exhibit R-2''s" list on the left and add these to the "Exhibit R-2''s To Include In Justification Book/Other Documents" list on the right by clicking on the "Add Selected" button.</li><ul><li>Hint: You can narrow your selections by entering appropriate parameters in the "Narrow Displayed Exhibit R-2''s" section at the top of the page and clicking on the "Refresh" button.</li></ul><li>Once PE selections are complete click the "Return to JB Wizard" button, scroll to the bottom of the page and click "Continue" to open the "Volumes" page.</li></ul></li></ul>'
where cf_name = 'jbwiz.r2ExhibitSelectHelp';

-- Remove letter and roman numeral prefixes (P40)
update `config`
set cf_value = '<ul><li>Choose which P-40s go in each volume:<ul><li>All the P-40s originally selected for inclusion in the JBook will default to the first Volume.</li><li>Select the P-40s to be moved to a different Volume by holding down the "Ctrl" key and clicking on them.</li><li>After all the desired P-40s are highlighted, click on the "Bring highlighted P-40s from other Volumes to this Volume" button under the Volume you wish to move them to.</li><li>Once the P-40s have been distributed to the desired Volumes, scroll to the bottom of the page and click "Continue" to open the "Attachments" page.</li></ul></li></ul>'
where cf_name = 'jbwiz.p40MultiVolumeBreakdownHelp';

-- Remove letter and roman numeral prefixes (R2)
update config
set cf_value = '<ul><li>Choose which PEs go in each volume:<ul><li>All the PEs originally selected for inclusion in the JBook will default to the first Volume.</li><li>Select the PEs to be moved to a different Volume by holding down the "Ctrl" key and clicking on them.</li><li>After all the desired PEs are highlighted, click on the "Bring highlighted Program Elements from other Volumes to this Volume" button under the Volume you wish to move them to.</li><li>Once the PEs have been distributed to the desired Volumes, scroll to the bottom of the page and click "Continue" to open the "Attachments" page.</li></ul></li></ul>'
where cf_name = 'jbwiz.r2MultiVolumeBreakdownHelp';

-- Remove letter and roman numeral prefixes (P40)
update `config`
set cf_value = '<ul><li>As a minimum, include a PDF version (must be a PDF) of the Comptroller''s P-1 from PRCP:<ul><li>To upload the "Comptroller Exhibit P-1 Document", select the checkbox in the "Volume" column to the left of the Comptroller Exhibit P-1 Document section, and browse to the PDF location. Add any other attachments as necessary and click on the "Upload Logo and Attachments" button at the bottom of the page.</li><li>Once Attachments have been uploaded, click "Continue" to open the "Summary and Build" page.</li></ul></li></ul>'
where cf_name = 'jbwiz.p40AttachmentsHelp' and cf_cycle = 'PB';

-- Remove letter and roman numeral prefixes (R2)
update `config`
set cf_value = '<ul><li>As a minimum, include a PDF version (must be a PDF) of the Comptroller''s R-1 from PRCP.<ul><li>To upload the "Comptroller Exhibit R-1 Document", select the checkbox in the "Volume 5" column to the left of the Comptroller Exhibit R-1 Document section, and browse to the PDF location. Add any other attachments as necessary and click on the "Upload Logo and Attachments" button at the bottom of the page.</li><li>Once Attachments have been uploaded, click Continue" to open the "Summary and Build" page.</li></ul></li></ul>'
where cf_name = 'jbwiz.r2AttachmentsHelp' and cf_cycle = 'PB';

-- Update default text (P40)
update `config`
set cf_value = '<ul><li>For the BES cycle, you must select either the "Generate Volume Exhibit P-1" or "Generate Cross-Volume Exhibit P-1" options on "Step 4: Table of Contents" depending on whether your J-Book contains one or multiple volumes.  For the PB cycle, the Comptroller P-1 Exhibit PDF must be attached.</li></ul>'
where cf_name = 'jbwiz.p40AttachmentsHelp' and cf_cycle != 'PB';

-- Update default text (R2)
update `config`
set cf_value = '<ul><li>For the BES cycle, you must select either the "Generate Volume Exhibit R-1" or "Generate Cross-Volume Exhibit R-1" options on "Step 4: Table of Contents" depending on whether your J-Book contains one or multiple volumes.  For the PB cycle, the Comptroller R-1 Exhibit PDF must be attached.</li></ul>'
where cf_name = 'jbwiz.r2AttachmentsHelp' and cf_cycle != 'PB';

-- Remove letter and roman numeral prefixes
update `config`
set cf_value = '<ul><li>Check "Include TOV" and enter other Volume information, including the optional Volume Title, Volume Description and Volume Number if desired:<ul><li>There must be at least one volume.</li><li>The Volume Number must be unique.</li><li>If provided, the Volume Title will appear on the Volume''s cover page.</li><li>Click "Continue" to open the "Table of Contents" page.</li></ul></li></ul>'
where cf_name = 'jbwiz.singleVolumesHelp';

-- Remove letter and roman numeral prefixes
update `config`
set cf_value = '<ul><li>Create as many Volumes as desired by clicking on the "Add Volume" link at the bottom of the "Volume Description and Number" table.<ul><li>Check "Include TOV" and enter other Volume information for each of the Volumes.</li><li>The Volume Number must be unique.</li><li>If provided, the Volume Title will appear on the Volume''s cover page.</li><li>Click "Continue" to open the "Table of Contents" page.</li></ul></li></ul>'
where cf_name = 'jbwiz.multiVolumesHelp';
