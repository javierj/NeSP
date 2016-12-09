-- Devuelve el diagrama de casos d eusod efinido dentro del casod e uso 13

SELECT t_diagram.[Diagram_ID]
FROM `t_diagram`
WHERE t_diagram.[ParentID] = 13
AND t_diagram.[Diagram_Type] = "Use Case";