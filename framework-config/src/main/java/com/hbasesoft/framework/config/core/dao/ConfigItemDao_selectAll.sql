SELECT 
    A.MODULE_CODE,A.CONFIG_ITEM_CODE,B.PARAM_CODE,B.PARAM_VALUE, B.DEFAULT_PARAM_VALUE
FROM 
    T_SYS_CONFIG_ITEM A,T_SYS_CONFIG_ITEM_PARAM B
WHERE 
    A.CONFIG_ITEM_ID = B.CONFIG_ITEM_ID
AND
    A.MODULE_CODE = :moduleCode