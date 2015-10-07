#lang racket

(require racket/format)

(define (zero-replace n v l)
     (if (equal? (car l) 0)
         (if (equal? (- n 1) 0)
             (append (list v) (cdr l))
             (append (list (car l)) (zero-replace (- n 1) v (cdr l)))
         )
         (append (list (car l)) (zero-replace n v (cdr l)))
     )
)

(zero-replace 1 4 '(4 0 8 0 0 0 5 8))
