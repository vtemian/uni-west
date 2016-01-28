; example taken from [GR, 380(391)]
; it shows how to implement a stack

(deffacts some-facts
    (stack 1 2 3 4)
;    (push-value 5)
    (pop-value)
    (pop-value)	
(pop-value)
    (pop-value)
(pop-value)
    (pop-value)						; we cannot place two identical facts in the knowledge base, we have to assert the second one after running once the program.then run the program again
    )

(defrule push-value
    ?push-value <- (push-value ?value)  ; we have a fact saying which value to place on the stack, we retain it's address ?push-value
    ?stack <- (stack $?rest)   			; we store the address of the existing stack, after placing the value on it, we will delete it and add to memory the new stack
    =>
    (retract ?push-value ?stack) 		; we retract both the facts
    (assert (stack ?value $?rest)) 		; we pace the new value on the stack and add the new stack to memory
    (printout t "Pushing value " ?value crlf)
    (printout t "The stack contains " (create$ ?value ?rest) crlf)
    )

;(defrule pop-value-valid 				; it works for a non-empty stack
;    ?pop-value <- (pop-value)
;	(stack $elements)
;     (if (> (length$ $elements) 0))
;    ?stack <- (stack ?value $?rest)
;    =>
;    (retract ?pop-value ?stack)
;    (assert (stack $?rest))
;    (printout t "Popping value " ?value crlf)
;    )

;(defrule pop-value-invalid 				; it works for an empty stack
;    ?pop-value <- (pop-value)
;    ?stack <- (stack)
;    =>
;    (retract ?pop-value)
;    (printout t "Popping from an empty stack" crlf)
;    )

(defrule popall
    ?pop-value <- (pop-value)
    ?stack <- (stack $?rest)
    =>
(if (> (length$ $?rest) 0) then
	(bind ?primul (nth$ 1 $?rest))
	(bind $?restul (rest$ $?rest))
    (retract ?pop-value ?stack)
    (assert (stack $?restul))
    (printout t "Popping value " ?primul crlf)
else 
	(retract ?pop-value)
    (printout t "Popping from an empty stack" crlf)
    )
)
(reset)
(run)
(assert (pop-value))
(run)
(facts)