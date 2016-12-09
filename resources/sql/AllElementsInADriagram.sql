
SELECT *
FROM t_object, `t_diagramobjects`
WHERE t_object.[Object_ID] = t_diagramobjects.[Object_ID]
AND t_diagramobjects.[Diagram_ID] = 3
AND t_object.[Object_Type] = "UseCase"
;