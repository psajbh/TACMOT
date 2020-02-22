INSERT INTO jb_volume
(
    agencyVolume,
    bookDescription,
    bookGroup,
    bookLabel,
    bookNumber,
    generateLineItemTocByBA,
    generateLineItemTocByTitle,
    generateP1,
    generateProgramElementTocByBA,
    generateProgramElementTocByTitle,
    generateR1,
    generateR1Summary,
    generateR1c,
    generateR1d,
    overrideDocumentAssemblyOptionsInXml,
    p40Volume,
    serviceAgencyId
)
VALUES
(
    1,                                     -- agencyVolume
    'Joint Improvised−Threat Defeat Fund', -- bookDescription
    1,                                     -- bookGroup
    'Volume',                              -- bookLabel
    1,                                     -- bookNumber
    0,                                     -- generateLineItemTocByBA
    0,                                     -- generateLineItemTocByTitle
    0,                                     -- generateP1
    1,                                     -- generateProgramElementTocByBA
    1,                                     -- generateProgramElementTocByTitle
    0,                                     -- generateR1
    0,                                     -- generateR1Summary
    0,                                     -- generateR1c
    0,                                     -- generateR1d
    1,                                     -- overrideDocumentAssemblyOptionsInXml
    1,                                     -- p40Volume
    (                                      -- serviceAgencyId
        SELECT BUDGES_SERV_AGY_ID
        FROM SERVICE_AGENCY SA
        WHERE SA.BUDGES_SERV_AGY_CODE = 'DTRA'
    )
),
(
    1,                                   -- agencyVolume
    'Defense POW/MIA Accounting Agency', -- bookDescription
    1,                                   -- bookGroup
    'Volume',                            -- bookLabel
    1,                                   -- bookNumber
    0,                                   -- generateLineItemTocByBA
    0,                                   -- generateLineItemTocByTitle
    0,                                   -- generateP1
    1,                                   -- generateProgramElementTocByBA
    1,                                   -- generateProgramElementTocByTitle
    0,                                   -- generateR1
    0,                                   -- generateR1Summary
    0,                                   -- generateR1c
    0,                                   -- generateR1d
    1,                                   -- overrideDocumentAssemblyOptionsInXml
    1,                                   -- p40Volume
    (                                    -- serviceAgencyId
        SELECT BUDGES_SERV_AGY_ID
        FROM SERVICE_AGENCY SA
        WHERE SA.BUDGES_SERV_AGY_CODE = 'DPAA'
    )
);
