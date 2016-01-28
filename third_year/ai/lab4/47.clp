(deftemplate rectangle (slot height)
                       (slot width))

(deffacts initial-facts
    (sum 0)
    (rectangle (height 10) (width 6))
    (rectangle (height 7) (width 5))
    (rectangle (height 6) (width 8))
    (rectangle (height 2) (width 5))
    )

(defglobal
   ?*sum* = 0
)

(defrule sum-rectangles
    (rectangle (height ?h) (width ?w))
    =>
    (bind ?*sum* (+ (* ?h ?w) ?*sum*))
)


(defrule print-result
    =>
    (printout t "the sum is" ?*sum* crlf)
)

(reset)
(run)
