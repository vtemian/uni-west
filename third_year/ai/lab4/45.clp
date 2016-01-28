(+ 2 3 4) 		; the sum of 2, 3 and 4, the result is 9. it can be written at the CLIPS prompt to obtain 9
(* 2 (+ 3 4))	; 2 * (3 + 4)
(< 2 3)			; returns true or false, if 2<3
(neq 2 3) 		; not equal

(deffunction add ($?y) ; a function of 2 arguments, it adds them and returns the result
	(bind ?sum 0)
	(progn$ (?field $?y)
            (bind ?sum (+ ?sum ?field))
    )
)

