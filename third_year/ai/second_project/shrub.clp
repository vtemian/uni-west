(deffacts start
    (get-next-input)
    (char cold shade dry wet-soil acid-soil city pot easy-care grows-fast)
    (shrub French-hydrangea shade city pot grows-fast)
    (shrub Oleander city pot easy-care grows-fast)
    (shrub Northem-Bayberry cold shade dry wet-soil city easy-care grows-fast)
    (shrub Box-honeysuckle city pot easy-care grows-fast)
    (shrub Gardenia shade acid-soil pot)
    (shrub Common-Juniper cold dry acid-soil cidy easy-care)
    (shrub Sweet-Pepperbush cold shade wet-soil acid-soil easy-care)
    (shrub Tartarian-DogWood cold shade wet-soil city easy-care)
    (shrub Japanese-Aucuba shade dry pot easy-care)
    (shrub Swamp-Azalea shade wet-soil acid-soil pot)
)

(defrule check-input
    (get-next-input)
    (not (shrub-char end))
    (not (compute))
    ?fact <- (shrub-char ?char)
    (not (char $? ?char $?))
    =>
    (printout t "Invalid characteristic " ?char crlf)
    (retract ?fact)
    (facts)
)

(defrule get-input
    (char $?chars)
    ?next <- (get-next-input)
    (not (shrub-char end))
    (not (compute))
    =>
    (printout t "Please insert a wanted shrub characteristic (end to stop) " crlf "Avaible: " ?chars crlf)
    (bind ?fact (read))
    (assert (shrub-char ?fact))
    (retract ?next)
    (assert (get-next-input))
)

(defrule finish-input
    ?end <- (shrub-char end)
    =>
    (retract ?end)
    (assert (compute))
)

(defrule compute-result
    (compute)
    (shrub-char ?char)
    ?shrub <- (shrub ?name $?)
    (not (shrub ?name $? ?char $?))
    =>
    (retract ?shrub)
)

(defrule show-results
    (compute)
    (shrub ?name $?)
    =>
    (printout t ?name crlf)
)

(reset)
(run)
