#lang racket

(define (get subst s)
    (if (null? subst)
        (null)
        (if (eq? s (car subst))
            (car (cdr subst))
            (get (cdr subst) s)
        )
    )
)

(get '((a 4) (b 6)) 'a)
